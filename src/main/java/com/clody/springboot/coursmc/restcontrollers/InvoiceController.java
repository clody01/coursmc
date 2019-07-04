package com.clody.springboot.coursmc.restcontrollers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clody.springboot.coursmc.models.Invoice;
import com.clody.springboot.coursmc.services.IInvoiceService;

@RestController
@RequestMapping("/api")
public class InvoiceController {
	
	@Autowired
	private IInvoiceService invoiceService;
	
	@GetMapping("/invoices/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Invoice invoice = null;
		try {
			invoice = invoiceService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Base consultation error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}
	@PostMapping("/invoices")
	public ResponseEntity<Void> insert(@Valid @RequestBody Invoice invoice) {		
		Invoice invoiceNew = invoiceService.insert(invoice);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(invoiceNew.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}
	@GetMapping("/invoices")
	public ResponseEntity<Page<Invoice>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "derection", defaultValue = "DESC") String derection,
			@RequestParam(value = "orderBy", defaultValue = "instant") String orderBy) {
		Page<Invoice> invoices = invoiceService.findPage(page, linesPerPage, derection, orderBy);
		return ResponseEntity.ok().body(invoices);
	}
}
