package com.github.kwesidev.UrlExtractor;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Crawler implements Runnable {

	private String address;
	static final String BOT_AGENT = "Kwesidev Crawler 1.0";

	public Crawler(String address) {
		this.address = address;
	}

	private void action() throws IOException {

		BufferedReader reader = null;
		HttpURLConnection http = null;
		try {

			http = (HttpURLConnection) new URL(this.address).openConnection();
			http.setRequestProperty("User-Agent", Crawler.BOT_AGENT);

			reader = new BufferedReader(new InputStreamReader(http.getInputStream()));

			StringBuilder bigData = new StringBuilder();
			String oneline;

			while ((oneline = reader.readLine()) != null) {

				bigData.append(oneline);

			}
			//Identify URLs
			String url = "\\b(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
			Matcher match = Pattern.compile(url).matcher(bigData);

			if (!match.find()) {

				return;
			}
			while (match.find()) {

				System.out.println((String) match.group());
			}

		}

		catch (MalformedURLException e) {

			System.err.println(e.getMessage());

		}

		finally {

			if (reader != null) {

				reader.close();
			}

			if (http != null) {

				http.disconnect();

			}

		}

	}

	@Override
	public void run() {

		try {

			action();
		}

		catch (IOException e) {

			System.err.println(e.getMessage());
		}

	}

}
