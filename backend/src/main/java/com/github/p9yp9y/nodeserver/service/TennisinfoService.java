package com.github.p9yp9y.nodeserver.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportradar.schemas.sportsapi.v1.tennis.PlayerCompetitor;
import com.sportradar.schemas.sportsapi.v1.tennis.Result;
import com.sportradar.schemas.sportsapi.v1.tennis.Results;
import com.sportradar.schemas.sportsapi.v1.tennis.SportEventStatus;

@Service
public class TennisinfoService {
	private final String URL = "https://api.sportradar.com/tennis-t2/en/schedules/%s/%s.xml?api_key=k5guk3nmk94b8dauat7ewwzp";

	@Autowired
	private HttpService httpService;

	public Map<Object, List<Result>> getResults(final String date) throws IOException {

		try {
			JAXBContext context = JAXBContext.newInstance(Results.class);
			Results results = (Results) context.createUnmarshaller().unmarshal(new URL(getResultUrl(date)));

			Map<Object, List<Result>> map = results.getResult().stream()
					.collect(Collectors.groupingBy(r -> r.getSportEvent().get(0).getTournament().getId()));
			return map;
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public String getResultUrl(final String date) {
		return String.format(URL, date, "results");
	}

	public String getSchedule(final String date) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		httpService.get(getScheduleUrl(date), outputStream);
		return outputStream.toString();
	}

	public String getScheduleUrl(final String date) {
		return String.format(URL, date, "schedule");
	}

	public List<Object[][]> getResultsContent(final String date, final String tournamentId) throws IOException {
		List<Object[][]> res = new ArrayList<>();
		Map<Object, List<Result>> map = getResults(date);

		List<Result> list = map.get("sr:tournament:" + tournamentId);
		if (list != null) {
			for (Result result : list) {
				SportEventStatus sportEventStatus = result.getSportEventStatus().get(0);
				String winnerId = sportEventStatus.getWinnerId();
				List<PlayerCompetitor> players = result.getSportEvent().get(0).getCompetitors().getPlayer();
				if (!players.isEmpty() && "closed".equals(result.getSportEventStatus().get(0).getStatus())) {
					PlayerCompetitor playerCompetitor1 = players.get(0);
					Object[] p1 = new Object[] { playerCompetitor1.getName(),
							playerCompetitor1.getId().equals(winnerId) ? "bold" : "", sportEventStatus.getHomeScore(),
							sportEventStatus.getPeriodScores() };
					PlayerCompetitor playerCompetitor2 = players.get(1);
					Object[] p2 = new Object[] { playerCompetitor2.getName(),
							playerCompetitor2.getId().equals(winnerId) ? "bold" : "", sportEventStatus.getAwayScore(),
							sportEventStatus.getPeriodScores() };
					res.add(new Object[][] { p1, p2 });
				}
			}
		}

		return res;
	}
}
