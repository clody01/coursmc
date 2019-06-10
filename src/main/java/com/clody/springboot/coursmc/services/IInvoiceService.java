package com.clody.springboot.coursmc.services; 
import javax.validation.Valid;

import com.clody.springboot.coursmc.models.Invoice;

public interface IInvoiceService {
	Invoice findById(Integer id);

	Invoice insert(Invoice invoice);
}
