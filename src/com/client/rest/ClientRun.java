package com.client.rest;

import java.net.SocketTimeoutException;
import com.client.rest.models.Cliente;
import com.client.rest.service.ClienteServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.jersey.api.client.ClientHandlerException;

public class ClientRun {
	
	public static void main(String[] args) throws JsonProcessingException {
		
		ClienteServiceImpl clientService = new ClienteServiceImpl();
		Cliente c = new Cliente(1, "jesus", "vazquez", 28, "JVCH1112221234", "Hidalgo 62", "554431981");
	
		//GET
		System.out.println("GET: " + clientService.getClient(1));
		
		//GET
		try {
			System.out.println("GET: " + clientService.getClients());
		} catch (ClientHandlerException e) {
			if ( e.getCause() instanceof SocketTimeoutException) {
				System.out.println("Se alcanzo el limite de tiempo");
			}
		}
		
		//POST
		System.out.println("POST: " + clientService.saveClient(c));
		
		//PUT
		System.out.println("PUT: " + clientService.updateClient(1, c));
		
		//DELETE
		System.out.println("PUT: " + clientService.deleteClient(1));	

		
		
	}

}
