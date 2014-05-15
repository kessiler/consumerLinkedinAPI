package br.com.bigdata.linkedin.client;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.schema.Person;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import br.com.bigdata.linkedin.api.ConnectionsAPI;
import br.com.bigdata.linkedin.api.ProfileAPI;
import br.com.bigdata.linkedin.factory.ClientFactory;

public class Application {

	private static final String CONSUMER_KEY_OPTION = "CONSUMER_KEY_OPTION";
	private static final String CONSUMER_SECRET_OPTION = "CONSUMER_SECRET_OPTION";
	private static final String ACCESS_TOKEN_OPTION = "ACCESS_TOKEN_OPTION";
	private static final String ACCESS_TOKEN_SECRET_OPTION = "ACCESS_TOKEN_SECRET_OPTION";
	private static final int QUERY_MAX = 20;

	public static void main(String[] args) throws UnknownHostException {
		ClientFactory client = new ClientFactory(
				Application.CONSUMER_KEY_OPTION,
				Application.CONSUMER_SECRET_OPTION);
		final LinkedInApiClient accessApi = client.getFactory()
				.createLinkedInApiClient(Application.ACCESS_TOKEN_OPTION,
						Application.ACCESS_TOKEN_SECRET_OPTION);

		long timeStart = System.currentTimeMillis();
		System.out.println("Process start..");
		ProfileAPI profileAPI = new ProfileAPI(accessApi);
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB mongoDB = mongoClient.getDB("bigData");
		DBCollection usersProfile = mongoDB.getCollection("usersProfile");
		BasicDBObject userDocument = new BasicDBObject();
		int countQuery = 0;
		Person profile = profileAPI.getProfile();
		ConnectionsAPI connection = new ConnectionsAPI(accessApi);
		List<Person> peopleConnection = connection.getConnections().getPersonList();
		String parentId = profile.getId();
		while(Application.QUERY_MAX > countQuery++) {
			userDocument.put("firstName", profile.getFirstName());
			userDocument.put("lastName", profile.getLastName());
			userDocument.put("headline", profile.getHeadline());
			userDocument.put("summary", profile.getSummary());
			userDocument.put("profileUrl", profile.getPublicProfileUrl());
			userDocument.put("profileId", profile.getId());
			userDocument.put("parentUser", parentId.equals(profile.getId()) ? "" : parentId) ;
			usersProfile.save(userDocument);
			userDocument.clear();
			if(peopleConnection.size() > 0 && peopleConnection.size() > countQuery) {
				profile = profileAPI.getProfile(peopleConnection.get(countQuery).getId());
			} else {
				countQuery = Application.QUERY_MAX;
			}
		}
		mongoClient.close();
		long timeEnd = System.currentTimeMillis();
		System.out.println("Time schedule " + (timeEnd - timeStart) + " ms");
		
	}
}
