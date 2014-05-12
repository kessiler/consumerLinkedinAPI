package br.com.bigdata.linkedin.api;

import java.util.List;

import com.google.code.linkedinapi.client.GroupsApiClient;
import com.google.code.linkedinapi.schema.Group;
import com.google.code.linkedinapi.schema.GroupMembership;

public class GroupsAPI {
	
	private GroupsApiClient client;

	public GroupsAPI(GroupsApiClient apiClient) {
		client = apiClient;
	}

	private GroupsApiClient getClient() {
		return client;
	}

	public List<GroupMembership> getGroupMemberships() {
		return getClient().getGroupMemberships().getGroupMembershipList();
	}

	public Group getGroup(String idGroup) {
		return getClient().getGroupById(idGroup);
	}

	public List<Group> getSuggestedGroups() {
		return getClient().getSuggestedGroups().getGroupList();
	}
}
