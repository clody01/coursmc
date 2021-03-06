package com.clody.springboot.coursmc.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.clody.springboot.coursmc.daos.ICustomerDao;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.dto.CustomerNewDto;
import com.clody.springboot.coursmc.models.enums.CustomerType;
import com.clody.springboot.coursmc.restcontrollers.exceptions.FieldMessage;
import com.clody.springboot.coursmc.services.validations.utils.BR;

public class CustomerInsertValidator  implements ConstraintValidator<CustomerInsert, CustomerNewDto>{
	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	public void initialize(CustomerInsert ann) {
		
	}
	@Override
	public boolean isValid(CustomerNewDto customerNewDto, ConstraintValidatorContext context) {
		 List<FieldMessage> list = new ArrayList<>();
		 if (customerNewDto.getCustomerType().equals(CustomerType.PHYSICALPERSON.getCode())
				 && !BR.isValidCPF(customerNewDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj","Invalid CPF"));
		}
		 if (customerNewDto.getCustomerType().equals(CustomerType.LEGALPERSON.getCode())
				 && !BR.isValidCNPJ(customerNewDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj","Invalid CNPJ"));
		}
		 Customer customer = customerDao.findByEmail(customerNewDto.getEmail());
		if (customer != null) {
			list.add(new FieldMessage("email","Email already exist!"));				
		}
		 for (FieldMessage fieldMessage : list) {
			 context.disableDefaultConstraintViolation();
			 context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
			 .addConstraintViolation();
		}
		return list.isEmpty();
	}

	 

}
