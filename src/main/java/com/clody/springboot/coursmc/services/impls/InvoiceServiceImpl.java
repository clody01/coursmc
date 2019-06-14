package com.clody.springboot.coursmc.services.impls;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clody.springboot.coursmc.daos.IInvoiceDao;
import com.clody.springboot.coursmc.daos.IItemInvoiceDao;
import com.clody.springboot.coursmc.daos.IPaymentDao;
import com.clody.springboot.coursmc.daos.IProductDao;
import com.clody.springboot.coursmc.mail.service.EmailService;
import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.models.Invoice;
import com.clody.springboot.coursmc.models.ItemInvoice;
import com.clody.springboot.coursmc.models.PaymentWithTicket;
import com.clody.springboot.coursmc.models.Product;
import com.clody.springboot.coursmc.models.enums.StatusPayment;
import com.clody.springboot.coursmc.services.ICustomerService;
import com.clody.springboot.coursmc.services.IInvoiceService;
import com.clody.springboot.coursmc.services.IProductService;
import com.clody.springboot.coursmc.services.excepetions.ObjectNotFoundException;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
	@Autowired
	private IInvoiceDao invoiceDao;
	@Autowired
	private IPaymentDao paymentDao;
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductDao productDao;

	@Autowired
	private IItemInvoiceDao itemInvoiceDao;

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private TicketPaymentService ticketPaymentService;
	@Autowired
	private CardPaymentService cardPaymentService;

	@Autowired
	private EmailService emailService;
	
	@Override
	@Transactional(readOnly = true)
	public Invoice findById(Integer id) {
		Invoice invoice = invoiceDao.findById(id).orElse(null);
		if (invoice == null) {
			throw new ObjectNotFoundException("Object with ID = " + id.toString() + " Of Type "
					+ Category.class.getName() + " does not exist in database!");
		}
		return invoice;
	}

	@Override
	public Invoice insert(Invoice invoice) {
		invoice.setId(null);
		invoice.setInstant(new Date());
		invoice.setCustomer(customerService.findById(invoice.getCustomer().getId()));
		invoice.getPayment().setStatus(StatusPayment.PENDING);
		invoice.getPayment().setInvoice(invoice);
		if (invoice.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket paymentWithTicket = (PaymentWithTicket) invoice.getPayment();
			ticketPaymentService.completePaymentWithTicket(paymentWithTicket, invoice.getInstant());
		}
		invoice = invoiceDao.save(invoice);
		paymentDao.save(invoice.getPayment());
		for (ItemInvoice itemInvoice : invoice.getItemInvoices()) {
			Product product = productService.findById(itemInvoice.getProduct().getId());
			itemInvoice.setDiscount(0.0);
			itemInvoice.setProduct(product);
			itemInvoice.setPrice(product.getPrice());
			// itemInvoice.setPrice((productDao.findById(itemInvoice.getProduct().getId()).orElse(null)).getPrice());
			itemInvoice.setInvoice(invoice);
		}
		itemInvoiceDao.saveAll(invoice.getItemInvoices());
		emailService.sendOrderConfirmationEmail(invoice);
		/*
		 * if (invoice.getPayment() instanceof PaymentWithCard) { PaymentWithCard
		 * paymentWithCard = (PaymentWithCard) invoice.getPayment(); //
		 * ticketPaymentService.completePaymentWithTicket(paymentWithTicket,invoice.
		 * getInstant()); }
		 */
		return invoice;
	}

}
