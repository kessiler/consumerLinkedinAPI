/**
 * 
 */
package br.com.bigdata.linkedin.factory;

import com.google.code.linkedinapi.client.LinkedInApiClientFactory;

public class Factory {
	private String consumerKey;
	private String consumerSecretKey;
	private String accessToken;
	private String accessTokenSecret;
	private LinkedInApiClientFactory factoryClient;

	public Factory(String consumerKey, String consumerSecretKey, String accessToken,
			String accessTokenSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecretKey = consumerSecretKey;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
		setFactoryClient();
	}
	
	private String getConsumerKey() {
		return consumerKey;
	}

	private String getConsumerSecretKey() {
		return consumerSecretKey;
	}

	private String getAccessToken() {
		return accessToken;
	}

	private String getAccessTokenSecret() {
		return accessTokenSecret;
	}
	
	
	public LinkedInApiClientFactory getFactoryClient() {
		return factoryClient;
	}

	private void setFactoryClient() {
		this.factoryClient = LinkedInApiClientFactory.newInstance(
				getConsumerKey(), getConsumerSecretKey());
	}

}
