package br.com.bigdata.linkedin.factory;

import com.google.code.linkedinapi.client.LinkedInApiClientFactory;

public class ClientFactory {
	private String consumerKey;
	private String consumerSecretKey;
	private LinkedInApiClientFactory factory;

	public ClientFactory(String consumerKey, String consumerSecretKey) {
		this.consumerKey = consumerKey;
		this.consumerSecretKey = consumerSecretKey;
		factory = LinkedInApiClientFactory.newInstance(getConsumerKey(), getConsumerSecretKey());
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerSecretKey() {
		return consumerSecretKey;
	}
	
	public LinkedInApiClientFactory getFactory() {
		return factory;
	}
}
