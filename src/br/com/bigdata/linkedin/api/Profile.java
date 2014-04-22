package br.com.bigdata.linkedin.api;

import java.util.EnumSet;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.enumeration.ProfileType;
import com.google.code.linkedinapi.schema.Person;

public class Profile {

	private LinkedInApiClient client;

	public Profile(LinkedInApiClient apiClient) {
		client = apiClient;
	}

	private LinkedInApiClient getClient() {
		return client;
	}

	public Person getProfile() {
		return getClient().getProfileForCurrentUser(
				EnumSet.allOf(ProfileField.class));
	}

	public Person getProfile(String urlProfile, ProfileType typeProfile) {
		return getClient().getProfileByUrl(urlProfile, typeProfile);
	}

	public Person getProfile(String idProfile) {
		return getClient().getProfileById(idProfile);
	}

}
