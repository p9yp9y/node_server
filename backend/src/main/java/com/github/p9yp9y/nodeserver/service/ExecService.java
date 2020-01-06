package com.github.p9yp9y.nodeserver.service;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class ExecService {
	public void exec(final String command, final OutputStream outputStream) throws IOException {
		Process process = Runtime.getRuntime().exec(command);
		IOUtils.copy(process.getErrorStream(), outputStream);
		IOUtils.copy(process.getInputStream(), outputStream);
	}
}
