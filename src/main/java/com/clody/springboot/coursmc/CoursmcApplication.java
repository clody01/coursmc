package com.clody.springboot.coursmc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
import com.clody.springboot.coursmc.models.enums.StatusPayment;

@SpringBootApplication
public class CoursmcApplication implements CommandLineRunner {

  

	public static void main(String[] args) {
		SpringApplication.run(CoursmcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
 
	}

}
