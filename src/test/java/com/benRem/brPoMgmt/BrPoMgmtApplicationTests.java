package com.benRem.brPoMgmt;

import com.benRem.brPoMgmt.domain.Customer;
import com.benRem.brPoMgmt.services.mailService.SmptMailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(MockitoJUnitRunner.class)
public class BrPoMgmtApplicationTests {

	@InjectMocks
	SmptMailSender smptMailSender;

	@Test
	public void contextLoads() {
	}
	@Test
	public void sendMail() throws MessagingException {

		Customer cust = new Customer();

		try {
			smptMailSender.sendRateWithAttachement(cust);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
