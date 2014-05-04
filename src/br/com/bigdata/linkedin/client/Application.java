package br.com.bigdata.linkedin.client;

import com.google.code.linkedinapi.client.LinkedInApiClient;

import br.com.bigdata.linkedin.api.CompaniesAPI;
import br.com.bigdata.linkedin.api.GroupsAPI;
import br.com.bigdata.linkedin.api.ProfileAPI;
import br.com.bigdata.linkedin.factory.ClientFactory;

public class Application {

	private static final String CONSUMER_KEY_OPTION = "CONSUMER_KEY_OPTION";
	private static final String CONSUMER_SECRET_OPTION = "CONSUMER_SECRET_OPTION";
	private static final String ACCESS_TOKEN_OPTION = "ACCESS_TOKEN_OPTION";
	private static final String ACCESS_TOKEN_SECRET_OPTION = "ACCESS_TOKEN_SECRET_OPTION";

	public static void main(String[] args) {
		ClientFactory client = new ClientFactory(
				Application.CONSUMER_KEY_OPTION,
				Application.CONSUMER_SECRET_OPTION);
		final LinkedInApiClient accessApi = client.getFactory()
				.createLinkedInApiClient(Application.ACCESS_TOKEN_OPTION,
						Application.ACCESS_TOKEN_SECRET_OPTION);
		
		ProfileAPI profile = new ProfileAPI(accessApi);
		System.out.println(profile.getProfile().getFirstName()  + profile.getProfile().getLastName());
		GroupsAPI group = new GroupsAPI(accessApi);
		System.out.println(group.getGroupMemberships().size());
		CompaniesAPI companies = new CompaniesAPI(accessApi);
		System.out.println(companies.getCompanyByName("UNI-BH").getDescription());
	}
}
