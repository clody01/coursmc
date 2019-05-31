package com.clody.springboot.coursmc.restcontrollers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.services.ICategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/categories/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Category category = null;
		try {
			category = categoryService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Base consultation error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Category>(category, HttpStatus.OK);

	}

	@PostMapping("/categories")
	public ResponseEntity<Void> insert(@RequestBody Category category) {		
		category = categoryService.insert(category);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}
	@PutMapping("/categories/{id}")
	public ResponseEntity<Void> update(@RequestBody Category category, @PathVariable Integer id) {		
		category.setId(id);
		category = categoryService.update(category);			 
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {		
		categoryService.delete(id);			 
		return ResponseEntity.noContent().build();
	}
}
