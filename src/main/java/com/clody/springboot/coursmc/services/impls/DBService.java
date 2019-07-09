package com.clody.springboot.coursmc.services.impls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clody.springboot.coursmc.daos.IAddressDao;
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
import com.clody.springboot.coursmc.models.enums.Profile;
import com.clody.springboot.coursmc.models.enums.StatusPayment;

@Service
public class DBService {
	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IStateDao stateDao;
	@Autowired
	private ICityDao cityDao;

	@Autowired
	private IAddressDao addressrDao;

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private IInvoiceDao invoiceDao;

	@Autowired
	private IPaymentDao paymentDao;

	@Autowired
	private IItemInvoiceDao itemInvoiceDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void instantiateTestDatabease() throws ParseException {
		Category cat1 = new Category(null, "Informatique");
		Category cat2 = new Category(null, "Electronic");
		Category cat3 = new Category(null, "Informatique");
		Category cat4 = new Category(null, "Category 4");
		Category cat5 = new Category(null, "Category 5");
		Category cat6 = new Category(null, "Category 6");
		Category cat7 = new Category(null, "Category 7");
		/*
		 * Category cat8 = new Category(null, "Category 8"); Category cat9 = new
		 * Category(null, "Category 9"); Category cat10 = new Category(null,
		 * "Category 10");
		 */

		Product prod1 = new Product(null, "Computer", 2000.00);
		Product prod2 = new Product(null, "Print", 800.00);
		Product prod3 = new Product(null, "Mouse", 80.00);
		Product prod4 = new Product(null, "Office desk", 300.00);
		Product prod5 = new Product(null, "Towel", 50.00);
		Product prod6 = new Product(null, "Quilt", 200.00);
		Product prod7 = new Product(null, "TV true color", 1200.00);
		Product prod8 = new Product(null, "Trimmer", 800.00);
		Product prod9 = new Product(null, "Bedside lamp", 100.00);
		Product prod10 = new Product(null, "Pending", 180.00);
		Product prod11 = new Product(null, "Shampoo", 90.00);
		Product p12 = new  Product(null, "Product 12", 10.00);
		Product p13 = new  Product(null, "Product 13", 10.00);
		Product p14 = new  Product(null, "Product 14", 10.00);
		Product p15 = new  Product(null, "Product 15", 10.00);
		Product p16 = new  Product(null, "Product 16", 10.00);
		Product p17 = new  Product(null, "Product 17", 10.00);
		Product p18 = new  Product(null, "Product 18", 10.00);
		Product p19 = new  Product(null, "Product 19", 10.00);
		Product p20 = new  Product(null, "Product 20", 10.00);
		Product p21 = new  Product(null, "Product 21", 10.00);
		Product p22 = new  Product(null, "Product 22", 10.00);
		Product p23 = new  Product(null, "Product 23", 10.00);
		Product p24 = new  Product(null, "Product 24", 10.00);
		Product p25 = new  Product(null, "Product 25", 10.00);
		Product p26 = new  Product(null, "Product 26", 10.00);
		Product p27 = new  Product(null, "Product 27", 10.00);
		Product p28 = new  Product(null, "Product 28", 10.00);
		Product p29 = new  Product(null, "Product 29", 10.00);
		Product p30 = new  Product(null, "Product 30", 10.00);
		Product p31 = new  Product(null, "Product 31", 10.00);
		Product p32 = new  Product(null, "Product 32", 10.00);
		Product p33 = new  Product(null, "Product 33", 10.00);
		Product p34 = new  Product(null, "Product 34", 10.00);
		Product p35 = new  Product(null, "Product 35", 10.00);
		Product p36 = new  Product(null, "Product 36", 10.00);
		Product p37 = new  Product(null, "Product 37", 10.00);
		Product p38 = new  Product(null, "Product 38", 10.00);
		Product p39 = new  Product(null, "Product 39", 10.00);
		Product p40 = new  Product(null, "Product 40", 10.00);
		Product p41 = new  Product(null, "Product 41", 10.00);
		Product p42 = new  Product(null, "Product 42", 10.00);
		Product p43 = new  Product(null, "Product 43", 10.00);
		Product p44 = new  Product(null, "Product 44", 10.00);
		Product p45 = new  Product(null, "Product 45", 10.00);
		Product p46 = new  Product(null, "Product 46", 10.00);
		Product p47 = new  Product(null, "Product 47", 10.00);
		Product p48 = new  Product(null, "Product 48", 10.00);
		Product p49 = new  Product(null, "Product 49", 10.00);
		Product p50 = new  Product(null, "Product 50", 10.00);

		cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3,p12, p13, p14, p15, p16, p17, p18, p19, p20, 
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, 
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		cat2.getProducts().addAll(Arrays.asList(prod2, prod4));
		cat3.getProducts().addAll(Arrays.asList(prod5, prod6));
		cat4.getProducts().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		cat5.getProducts().addAll(Arrays.asList(prod8));
		cat6.getProducts().addAll(Arrays.asList(prod9, prod10));
		cat7.getProducts().addAll(Arrays.asList(prod11));

		prod1.getCategories().addAll(Arrays.asList(cat1, cat4));
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		prod3.getCategories().addAll(Arrays.asList(cat1, cat4));
		prod4.getCategories().addAll(Arrays.asList(cat2));
		prod5.getCategories().addAll(Arrays.asList(cat3));
		prod6.getCategories().addAll(Arrays.asList(cat3));
		prod7.getCategories().addAll(Arrays.asList(cat4));
		prod8.getCategories().addAll(Arrays.asList(cat5));
		prod9.getCategories().addAll(Arrays.asList(cat6));
		prod10.getCategories().addAll(Arrays.asList(cat6));
		prod11.getCategories().addAll(Arrays.asList(cat7));
		p12.getCategories().add(cat1);
		p13.getCategories().add(cat1);
		p14.getCategories().add(cat1);
		p15.getCategories().add(cat1);
		p16.getCategories().add(cat1);
		p17.getCategories().add(cat1);
		p18.getCategories().add(cat1);
		p19.getCategories().add(cat1);
		p20.getCategories().add(cat1);
		p21.getCategories().add(cat1);
		p22.getCategories().add(cat1);
		p23.getCategories().add(cat1);
		p24.getCategories().add(cat1);
		p25.getCategories().add(cat1);
		p26.getCategories().add(cat1);
		p27.getCategories().add(cat1);
		p28.getCategories().add(cat1);
		p29.getCategories().add(cat1);
		p30.getCategories().add(cat1);
		p31.getCategories().add(cat1);
		p32.getCategories().add(cat1);
		p33.getCategories().add(cat1);
		p34.getCategories().add(cat1);
		p35.getCategories().add(cat1);
		p36.getCategories().add(cat1);
		p37.getCategories().add(cat1);
		p38.getCategories().add(cat1);
		p39.getCategories().add(cat1);
		p40.getCategories().add(cat1);
		p41.getCategories().add(cat1);
		p42.getCategories().add(cat1);
		p43.getCategories().add(cat1);
		p44.getCategories().add(cat1);
		p45.getCategories().add(cat1);
		p46.getCategories().add(cat1);
		p47.getCategories().add(cat1);
		p48.getCategories().add(cat1);
		p49.getCategories().add(cat1);
		p50.getCategories().add(cat1);

		State state1 = new State(null, "Gbèkè");
		State state2 = new State(null, "Marahoué");

		City city1 = new City(null, "Bouaké", state1);
		City city2 = new City(null, "Gohitafla", state2);
		City city3 = new City(null, "Bouaflé", state2);

		state2.getCities().addAll(Arrays.asList(city2, city3));
		state1.getCities().addAll(Arrays.asList(city1));

		Customer customer1 = new Customer(null, "Marie Silva", "mamadoumady69@gmail.com", CustomerType.PHYSICALPERSON,
				"0646462425", bCryptPasswordEncoder.encode("123"));
		customer1.getTelephones().addAll(Arrays.asList("06548725", "06548729"));
		
		Customer customer2 = new Customer(null, "Ana Costa", "tseoue01@gmail.com", CustomerType.PHYSICALPERSON,
				"25852270490", bCryptPasswordEncoder.encode("123"));
		customer2.getTelephones().addAll(Arrays.asList("03549725", "03548722"));
		customer2.addProfile(Profile.ADMIN);
		
		Address address1 = new Address(null, "Rue Louis Lherault", "Argenteuil", "chez mamadou", "Val d'argenteuil",
				"95100", customer1, city1);
		Address address2 = new Address(null, "Rue Louis Lherault", "Argenteuil", "chez mamadou", "Val d'argenteuil",
				"95100", customer1, city2);
		Address address3 = new Address(null, "Rue Jean Lherault", "Argenteuil", "chez mamadou", "Val d'argenteuil",
				"92100", customer1, city2);
		customer1.getAddressList().addAll(Arrays.asList(address1, address2));
		customer2.getAddressList().addAll(Arrays.asList(address3));

		categoryDao.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productDao.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11,p12, p13, p14, p15, p16, p17, p18, p19, p20, 
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		stateDao.saveAll(Arrays.asList(state1, state2));
		cityDao.saveAll(Arrays.asList(city1, city2, city3));
		customerDao.saveAll(Arrays.asList(customer1,customer2));
		addressrDao.saveAll(Arrays.asList(address1, address2,address3));
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
