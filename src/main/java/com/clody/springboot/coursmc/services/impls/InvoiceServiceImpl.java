package com.clody.springboot.coursmc.services.impls;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clody.springboot.coursmc.daos.ICategoryDao;
import com.clody.springboot.coursmc.daos.IInvoiceDao;
import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.models.Invoice;
import com.clody.springboot.coursmc.services.ICategoryService;
import com.clody.springboot.coursmc.services.IInvoiceService;
import com.clody.springboot.coursmc.services.excepetions.ObjectNotFoundException;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
	@Autowired
	private IInvoiceDao invoiceDao;

	@Override
	@Transactional(readOnly = true)
	public Invoice findById(Integer id) {
		Invoice invoice = invoiceDao.findById(id).orElse(null);
		if (invoice == null) {
			throw new ObjectNotFoundException("Object with ID = "+ id.toString() + " Of Type "+ Category.class.getName()+ " does not exist in database!");
		}
		return invoice;
	}

}
