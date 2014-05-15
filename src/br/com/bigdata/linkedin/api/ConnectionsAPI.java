package br.com.bigdata.linkedin.api;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.schema.Connections;


public class ConnectionsAPI {

	private LinkedInApiClient client;

	public ConnectionsAPI(LinkedInApiClient apiClient) {
		client = apiClient;
	}

	private LinkedInApiClient getClient() {
		return client;
	}

	public Connections getConnections() {
		return getClient().getConnectionsForCurrentUser();
	}

	public Connections getConnectionsByUrl(String url) {
		return getClient().getConnectionsByUrl(url);
	}

	public Connections getConnectionsById(String id) {
		return getClient().getConnectionsById(id);
	}

}
