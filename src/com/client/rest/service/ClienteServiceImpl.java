package com.client.rest.service;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.MediaType;
import com.client.rest.models.Cliente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClienteServiceImpl {
	
	private static final String PATH_API = "http://localhost:8080/serviceREST-JAX-RS/api/v1/clientes";
	
	public List<Cliente> getClients() {
		Client client = Client.create();
		client.setReadTimeout(300);
		WebResource webResource = client.resource(PATH_API);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		Cliente[] cliente = response.getEntity(Cliente[].class);
		return Arrays.asList(cliente);
	}
	
	public Cliente getClient(int id) {
		Client client = Client.create();
		client.setReadTimeout(300);
		String path = PATH_API + "/" + String.valueOf(id);
		WebResource webResource = client.resource(path);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		return response.getEntity(Cliente.class);
	}
	
	public String saveClient(Cliente cliente) throws JsonProcessingException {
		Client client = Client.create();
		client.setReadTimeout(300);
		WebResource webResource = client.resource(PATH_API);
		ObjectMapper mapper = new ObjectMapper();
		String clientJson = mapper.writeValueAsString(cliente);
		ClientResponse response = webResource
				.entity(clientJson, MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_PLAIN)
				.post(ClientResponse.class);
		return response.getEntity(String.class);
	}
	
	public String updateClient(int id, Cliente cliente) throws JsonProcessingException {
		Client client = Client.create();
		client.setReadTimeout(300);
		String path = PATH_API + "/" + String.valueOf(id);
		WebResource webResource = client.resource(path);
		ObjectMapper mapper = new ObjectMapper();
		String clientJson = mapper.writeValueAsString(cliente);
		ClientResponse response = webResource
				.entity(clientJson, MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_PLAIN)
				.put(ClientResponse.class, cliente);
		return response.getEntity(String.class);
	}
	
	public String deleteClient(int id) {
		Client client = Client.create();
		client.setReadTimeout(300);
		String path = PATH_API + "/" + String.valueOf(id);
		WebResource webResource = client.resource(path);
		ClientResponse response = webResource.accept(MediaType.TEXT_PLAIN).delete(ClientResponse.class);
		return response.getEntity(String.class);
	}
}
