package com.clody.springboot.coursmc.restcontrollers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.services.ICustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService; 
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Customer customer = null;
		try {
			customer = customerService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Base consultation error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}
	
}
