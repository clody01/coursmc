package com.clody.springboot.coursmc.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.clody.springboot.coursmc.daos.ICustomerDao;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.dto.CustomerDto;
import com.clody.springboot.coursmc.restcontrollers.exceptions.FieldMessage;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDto> {
	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private ICustomerDao customerDao;

	@Override
	public void initialize(CustomerUpdate ann) {

	}

	@Override
	public boolean isValid(CustomerDto customerDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) httpServletRequest
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		List<FieldMessage> list = new ArrayList<>();

		Customer customer = customerDao.findByEmail(customerDto.getEmail());
		if (customer != null && !customer.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email already exist!"));
		}
		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
