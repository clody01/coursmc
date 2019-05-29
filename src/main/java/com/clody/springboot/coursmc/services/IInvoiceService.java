package com.clody.springboot.coursmc.services; 
import com.clody.springboot.coursmc.models.Invoice;

public interface IInvoiceService {
	Invoice findById(Integer id);
}
