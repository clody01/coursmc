package com.clody.springboot.coursmc.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clody.springboot.coursmc.models.Product;
import com.clody.springboot.coursmc.models.dto.ProductDto;
import com.clody.springboot.coursmc.restcontrollers.utils.URL;
import com.clody.springboot.coursmc.services.IProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/products/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Product product = null;
		try {
			product = productService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Base consultation error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);		
	}
	
	@GetMapping("/products/pages")
	public ResponseEntity<Page<ProductDto>> findPage(
		@RequestParam(value="name", defaultValue = "") String name,
		@RequestParam(value="categories", defaultValue = "") String categories,
		@RequestParam(value="page", defaultValue = "0")Integer page, 
		@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage, 
		@RequestParam(value="derection", defaultValue = "ASC")String derection, 
		@RequestParam(value="orderBy", defaultValue = "name")String orderBy) {
		List<Integer> ids = URL.decodeIntList(categories);
		String decodedName = URL.decodeParam(name);
		Page<Product> products = productService.search(decodedName,ids,page,linesPerPage,derection,orderBy);
		Page<ProductDto> productsDto = products.map(product -> new ProductDto(product));
		return  ResponseEntity.ok().body(productsDto);
	}
}
