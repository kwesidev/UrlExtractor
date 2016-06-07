package com.github.kwesidev.UrlExtractor;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
	static final String BOT_AGENT = "Kwesidev Bot";
	static final int POOL = 10;

	public static void main(String[] args) throws IOException {
		// Url Info

		ExecutorService executor = Executors.newFixedThreadPool(POOL);
		if( args.length == 0){

			System.err.println("Usage : java -jar UrlExtractor http://twitter.com http://facebook.com ...");

			System.exit(0);
		}

		for (int i = 0; i < args.length; i++) {
			Runnable craw = new Crawler(args[i]);
			executor.execute(craw);

		}

		executor.shutdown();
		// executor.awaitTermination();

		//System.out.println("Finished ");

	}
}
