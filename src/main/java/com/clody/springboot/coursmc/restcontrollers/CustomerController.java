package com.clody.springboot.coursmc.restcontrollers;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.dto.CustomerDto;
import com.clody.springboot.coursmc.models.dto.CustomerNewDto;
import com.clody.springboot.coursmc.services.ICustomerService;
import com.clody.springboot.coursmc.uploadandload.services.IUploadFileService;
import com.clody.springboot.coursmc.uploadandload.services.exceptions.FileException;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/customers/{id}")
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

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDto>> findAll() {
		List<Customer> customers = customerService.findAll();
		List<CustomerDto> customersDto = customers.stream().map(customer -> new CustomerDto(customer))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(customersDto);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/customers/pages")
	public ResponseEntity<Page<CustomerDto>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "derection", defaultValue = "ASC") String derection,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		Page<Customer> customers = customerService.findPage(page, linesPerPage, derection, orderBy);
		Page<CustomerDto> customersDto = customers.map(customer -> new CustomerDto(customer));
		return ResponseEntity.ok().body(customersDto);
	}

	/*
	 * @PostMapping("/customers") public ResponseEntity<Void>
	 * insert(@Valid @RequestBody CustomerDto customerDto) { Customer customer =
	 * customerService.insert(customerService.fromDto(customerDto)); URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (customer.getId()) .toUri(); return ResponseEntity.created(uri).build();
	 * 
	 * }
	 */
	@PostMapping("/customers")
	public ResponseEntity<Void> insert(@Valid @RequestBody CustomerNewDto customerNewDto) {
		Customer customer = customerService.insert(customerService.fromNewDto(customerNewDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CustomerDto customerDto, @PathVariable Integer id) {
		Customer customer = customerService.fromDto(customerDto);
		customer.setId(id);
		customer = customerService.update(customer);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		customerService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/customers/uploads")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();

		if (!file.isEmpty()) {			
			try {		
				customerService.uploadProfilePiture(file,id);	 
			} catch (IOException e) {				 
				response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));				 
				throw new FileException("Error" + e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			}
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
