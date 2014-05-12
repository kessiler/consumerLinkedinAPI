package br.com.bigdata.linkedin.client;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.enumeration.ProfileType;
import com.google.code.linkedinapi.schema.MemberUrl;
import com.google.code.linkedinapi.schema.Person;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

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

		ProfileAPI profileAPI = new ProfileAPI(accessApi);
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB mongoDB = mongoClient.getDB("bigData");
		DBCollection usersProfile = mongoDB.getCollection("usersProfile");
		BasicDBObject userDocument = new BasicDBObject();
		int countQuery = 0;
		Person profile = profileAPI.getProfile();
		List<MemberUrl> members = profile.getMemberUrlResources().getMemberUrlList();
		while(Application.QUERY_MAX > countQuery++) {
			userDocument.put("firstName", profile.getFirstName());
			userDocument.put("lastName", profile.getLastName());
			userDocument.put("locationName", profile.getLocation().getName());
			userDocument.put("postCode", profile.getLocation().getPostalCode());
			userDocument.put("numRecommenders", profile.getNumRecommenders());
			usersProfile.save(userDocument);
			userDocument.clear();
			if(members.size() > 0 && members.size() > countQuery) {
				profile = profileAPI.getProfile(members.get(countQuery).getUrl(), ProfileType.STANDARD);
			} else {
				countQuery = Application.QUERY_MAX;
			}
		}
		mongoClient.close();
		
	}
}
