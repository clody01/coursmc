package com.clody.springboot.coursmc.services.impls;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.clody.springboot.coursmc.models.PaymentWithTicket;

@Service
public class TicketPaymentService {

	public void completePaymentWithTicket(PaymentWithTicket paymentWithTicket, Date instant) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instant);
		calendar.add(Calendar.DAY_OF_MONTH,7);
		paymentWithTicket.setDueDate(calendar.getTime());
		
	}

}
