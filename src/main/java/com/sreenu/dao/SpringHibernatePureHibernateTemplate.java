package com.sreenu.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.sreenu.dao.entities.CreditCardAccount;
import com.sreenu.dao.entities.CustomerDetails;
import com.sreenu.dao.entities.LoanAccount;
import com.sreenu.dao.entities.SavingAccount;
import com.sreenu.dao.entities.channel;
import com.sreenu.dao.entities.client;
import com.sreenu.dao.model.CustomerDetailsRequest;
import com.sreenu.dao.model.CustomerDetailsResponse;

public class SpringHibernatePureHibernateTemplate implements CustomerAccountDetailsDAOI {

	@Override
	public CustomerDetailsResponse getCustomerDetails(CustomerDetailsRequest custDetailReq) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_hibernateTemplate.xml");
		HibernateTemplate hibernateTemplate = (HibernateTemplate) context.getBean("hibernateTempalate");
		CustomerDetailsResponse res = new CustomerDetailsResponse();
		List<client> clientList = (List<client>) hibernateTemplate.findByNamedParam(
				"from client where client_id=:client_name", "client_name", custDetailReq.getClientId());
		String client_id = null;
		String channel_id = null;
		if (clientList != null && !(clientList.size() > 0)) {
			res.setResponseCode("AD1000");
			res.setResponseMesg("INVALID CLIENT ID");
			return res;
		} else {
			for (client client : clientList) {
				client_id = client.getClient_id();
				break;
			}
		}

		// channel id
		List<channel> channelList = (List<channel>) hibernateTemplate.findByNamedParam(
				"from channel where channel_id=:channel_name", "channel_name", custDetailReq.getChannelId());
		if (channelList != null && !(channelList.size() > 0)) {
			res.setResponseCode("AD1001");
			res.setResponseMesg("INVALID CHANNEL ID");
			return res;
		} else {
			for (channel channel : channelList) {
				channel_id = channel.getChannel_id();
				break;
			}
		}

		// customer details
		List<CustomerDetails> customerDetails = (List<CustomerDetails>) hibernateTemplate.findByNamedParam(
				"from CustomerDetails where cardnum=:cardnum and cvvnum=:cvvnum",
				new String[] { "cardnum", "cvvnum" },
				new Object[] { custDetailReq.getCardNumber(), custDetailReq.getCvv() });
		if (customerDetails != null && !(customerDetails.size() > 0)) {
			res.setResponseCode("AD1002");
			res.setResponseMesg("INVALID CARD NUM or CVV Number or Exp date");
			return res;
		}

		// load details
		if (custDetailReq.getAccountType() != null && (custDetailReq.getAccountType().equalsIgnoreCase("LOAN")
				|| custDetailReq.getAccountType().equalsIgnoreCase("ALL"))) {
			List<LoanAccount> loanAccounts = hibernateTemplate.loadAll(LoanAccount.class);
			res.setLoanAccounts(loanAccounts);
		}
		// saving details
		if (custDetailReq.getAccountType() != null && (custDetailReq.getAccountType().equalsIgnoreCase("SAVING")
				|| custDetailReq.getAccountType().equalsIgnoreCase("ALL"))) {
			List<SavingAccount> savingAccounts = hibernateTemplate.loadAll(SavingAccount.class);
			res.setSavingAccounts(savingAccounts);
		}
		// credit card details
		if (custDetailReq.getAccountType() != null && (custDetailReq.getAccountType().equalsIgnoreCase("CREDIT")
				|| custDetailReq.getAccountType().equalsIgnoreCase("ALL"))) {
			List<CreditCardAccount> creditCardAccounts = hibernateTemplate.loadAll(CreditCardAccount.class);
			res.setCreditCardAccounts(creditCardAccounts);
		}

		res.setResponseCode("AD1004");
		res.setResponseMesg("INVALID SRC & DEST FOUND");
		return res;
	}

	public static void main(String[] args) {
		CustomerDetailsRequest req = new CustomerDetailsRequest();
		req.setClientId("WEB");
		req.setChannelId("ONLINE");
		req.setCardNumber("789456217865124");
		req.setCvv("112");
		req.setAccountType("LOAN");
		SpringHibernatePureHibernateTemplate custDAO = new SpringHibernatePureHibernateTemplate();
		CustomerDetailsResponse res = custDAO.getCustomerDetails(req);
		System.out.println(res);
	}
}
