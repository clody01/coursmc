package com.clody.springboot.coursmc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clody.springboot.coursmc.daos.IAddressrDao;
import com.clody.springboot.coursmc.daos.ICategoryDao;
import com.clody.springboot.coursmc.daos.ICityDao;
import com.clody.springboot.coursmc.daos.ICustomerDao;
import com.clody.springboot.coursmc.daos.IInvoiceDao;
import com.clody.springboot.coursmc.daos.IItemInvoiceDao;
import com.clody.springboot.coursmc.daos.IPaymentDao;
import com.clody.springboot.coursmc.daos.IProductDao;
import com.clody.springboot.coursmc.daos.IStateDao;
import com.clody.springboot.coursmc.models.Address;
import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.models.City;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.Invoice;
import com.clody.springboot.coursmc.models.ItemInvoice;
import com.clody.springboot.coursmc.models.Payment;
import com.clody.springboot.coursmc.models.PaymentWithCard;
import com.clody.springboot.coursmc.models.PaymentWithTicket;
import com.clody.springboot.coursmc.models.Product;
import com.clody.springboot.coursmc.models.State;
import com.clody.springboot.coursmc.models.enums.CustomerType;
import com.clody.springboot.coursmc.models.enums.StatusPayment;

@SpringBootApplication
public class CoursmcApplication implements CommandLineRunner {

	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IStateDao stateDao;
	@Autowired
	private ICityDao cityDao;

	@Autowired
	private IAddressrDao addressrDao;

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private IInvoiceDao invoiceDao;

	@Autowired
	private IPaymentDao paymentDao;

	@Autowired
	private IItemInvoiceDao itemInvoiceDao;

	public static void main(String[] args) {
		SpringApplication.run(CoursmcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informatique");
		Category cat2 = new Category(null, "Electronic");
		Category cat3 = new Category(null, "Informatique");
		Category cat4 = new Category(null, "Category 4");
		Category cat5 = new Category(null, "Category 5");
		Category cat6 = new Category(null, "Category 6");
		Category cat7 = new Category(null, "Category 7");
		Category cat8 = new Category(null, "Category 8");
		Category cat9 = new Category(null, "Category 9");
		Category cat10 = new Category(null, "Category 10");

		Product prod1 = new Product(null, "Computer", 2000.00);
		Product prod2 = new Product(null, "Print", 800.00);
		Product prod3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));
		cat1.getProducts().addAll(Arrays.asList(prod2));

		prod1.getCategories().addAll(Arrays.asList(cat1));
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategories().addAll(Arrays.asList(cat1));

		State state1 = new State(null, "Gbèkè");
		State state2 = new State(null, "Marahoué");

		City city1 = new City(null, "Bouaké", state1);
		City city2 = new City(null, "Gohitafla", state2);
		City city3 = new City(null, "Bouaflé", state2);

		state2.getCities().addAll(Arrays.asList(city2, city3));
		state1.getCities().addAll(Arrays.asList(city1));

		Customer customer1 = new Customer(null, "Marie Silva", "m.silva@gmail.com", CustomerType.PHYSICALPERSON,
				"0646462425");
		customer1.getTelephones().addAll(Arrays.asList("06548725", "06548729"));
		Address address1 = new Address(null, "Rue Louis Lherault", "Argenteuil", "chez mamadou", "Val d'argenteuil",
				"95100", customer1, city1);
		Address address2 = new Address(null, "Rue Louis Lherault", "Argenteuil", "chez mamadou", "Val d'argenteuil",
				"95100", customer1, city2);
		customer1.getAddressList().addAll(Arrays.asList(address1, address2));

		categoryDao.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));
		productDao.saveAll(Arrays.asList(prod1, prod2, prod3));
		stateDao.saveAll(Arrays.asList(state1, state2));
		cityDao.saveAll(Arrays.asList(city1, city2, city3));
		customerDao.saveAll(Arrays.asList(customer1));
		addressrDao.saveAll(Arrays.asList(address1, address2));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Invoice invoice1 = new Invoice(null, simpleDateFormat.parse("30/09/2017 10:32"), customer1, address1);
		Invoice invoice2 = new Invoice(null, simpleDateFormat.parse("10/10/2017 19:35"), customer1, address2);

		Payment paymentWithCard = new PaymentWithCard(null, StatusPayment.CANCELED, invoice1, 6);
		invoice1.setPayment(paymentWithCard);

		Payment paymentWithTicket = new PaymentWithTicket(null, StatusPayment.PENDING, invoice2,
				simpleDateFormat.parse("20/10/2017 00:00"), null);
		invoice2.setPayment(paymentWithTicket);

		customer1.getInvoices().addAll(Arrays.asList(invoice1, invoice2));
		invoiceDao.saveAll(Arrays.asList(invoice1, invoice2));
		paymentDao.saveAll(Arrays.asList(paymentWithCard, paymentWithTicket));

		ItemInvoice itemInvoice1 = new ItemInvoice(invoice1, prod1, 0.00, 1, 2000.00);
		ItemInvoice itemInvoice2 = new ItemInvoice(invoice1, prod3, 0.00, 2, 80.00);
		ItemInvoice itemInvoice3 = new ItemInvoice(invoice2, prod2, 100.00, 1, 800.00);
		invoice1.getItemInvoices().addAll(Arrays.asList(itemInvoice1, itemInvoice2));
		invoice1.getItemInvoices().addAll(Arrays.asList(itemInvoice3));
		prod1.getItemInvoices().addAll(Arrays.asList(itemInvoice1));
		prod2.getItemInvoices().addAll(Arrays.asList(itemInvoice3));
		prod3.getItemInvoices().addAll(Arrays.asList(itemInvoice2));

		itemInvoiceDao.saveAll(Arrays.asList(itemInvoice1, itemInvoice2, itemInvoice3));
	}

}
