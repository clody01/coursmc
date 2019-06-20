package com.clody.springboot.coursmc.services; 
import org.springframework.data.domain.Page;

import com.clody.springboot.coursmc.models.Invoice;

public interface IInvoiceService {
	Invoice findById(Integer id);
	Invoice insert(Invoice invoice);
	Page<Invoice> findPage(Integer page, Integer linesPerPage, String derection, String orderBy);

}
