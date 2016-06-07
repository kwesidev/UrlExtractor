package com.github.kwesidev.UrlExtractor;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.IOException;

public class AppTest{

		@Test
		public void TestMain() throws IOException {
			String[] args = {"https://cnn.com","https://facebook.com"};

			App.main(args);

		}

	}
