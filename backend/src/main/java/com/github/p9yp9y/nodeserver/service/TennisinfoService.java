package com.github.p9yp9y.nodeserver.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
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
	private final String URL = "https://api.sportradar.com/tennis-t2/en/schedules/%s/%s.xml?api_key=36m6b2qn2e5mshe7phj9vws8";

	@Autowired
	private HttpService httpService;

	public Map<Object, List<Result>> getResults(final String date) throws IOException {

		try {
			JAXBContext context = JAXBContext.newInstance(Results.class);
			Results results = (Results) context.createUnmarshaller().unmarshal(new URL(getResultUrl(date)));

			Map<Object, List<Result>> map =
					results.getResult().stream().collect(Collectors.groupingBy(r -> r.getSportEvent().get(0).getTournament().getId()));
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

	public String getResultsContent(final String date) throws IOException {
		String res = "";
		Map<Object, List<Result>> map = getResults(date);

		for (Object key : map.keySet()) {
			for (Result result : map.get(key)) {
				SportEventStatus sportEventStatus = result.getSportEventStatus().get(0);
				List<PlayerCompetitor> players = result.getSportEvent().get(0).getCompetitors().getPlayer();
				if (!players.isEmpty()) {
					System.out.println(players.get(0).getName() + "-" + players.get(1).getName());
					System.out.println(sportEventStatus.getHomeScore() + "-" + sportEventStatus.getAwayScore());
				}
			}
		}

		return res;
	}
}
