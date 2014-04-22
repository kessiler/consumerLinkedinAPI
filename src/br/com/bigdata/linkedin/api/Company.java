package br.com.bigdata.linkedin.api;

import java.util.EnumSet;
import com.google.code.linkedinapi.client.CompaniesApiClient;
import com.google.code.linkedinapi.client.enumeration.CompanyField;
import com.google.code.linkedinapi.client.enumeration.ProductField;
import com.google.code.linkedinapi.schema.Products;

public class Company {

	private CompaniesApiClient client;

	public Company(CompaniesApiClient apiClient) {
		client = apiClient;
	}

	private CompaniesApiClient getClient() {
		return client;
	}

	public Company getCompany(String idCompany) {
		return (Company) getClient().getCompanyById(idCompany,
				EnumSet.allOf(CompanyField.class));
	}

	public Products getProductsCompany(String idCompany) {
		return (Products) getClient().getCompanyProducts(idCompany,
				EnumSet.allOf(ProductField.class));
	}

	public Company getCompanyByName(String name) {
		return (Company) getClient().getCompanyByUniversalName(name,
				EnumSet.allOf(CompanyField.class));
	}

}
