package br.com.bigdata.linkedin.client;

import br.com.bigdata.linkedin.factory.Factory;

public class Application {

	private static final String CONSUMER_KEY_OPTION = "consumerKey";
	private static final String CONSUMER_SECRET_OPTION = "consumerSecret";
	private static final String ACCESS_TOKEN_OPTION = "token";
	private static final String ACCESS_TOKEN_SECRET_OPTION = "tokenSecret";

	public static void main(String[] args) {
		Factory connectionFactory = new Factory(Application.CONSUMER_KEY_OPTION,
				Application.CONSUMER_SECRET_OPTION,
				Application.ACCESS_TOKEN_OPTION,
				Application.ACCESS_TOKEN_SECRET_OPTION);
	}

}
