package com.sreenu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sreenu.dao.entities.CreditCardAccount;
import com.sreenu.dao.entities.CustomerDetails;
import com.sreenu.dao.entities.LoanAccount;
import com.sreenu.dao.entities.SavingAccount;
import com.sreenu.dao.entities.channel;
import com.sreenu.dao.entities.client;
import com.sreenu.dao.model.CustomerDetailsRequest;
import com.sreenu.dao.model.CustomerDetailsResponse;
import com.sreenu.dao.utils.HibernateUtility;

public class ModifiedSpringHibernateSessionFactory implements CustomerAccountDetailsDAOI {

	@Override
	public CustomerDetailsResponse getCustomerDetails(
			CustomerDetailsRequest custDetailReq) {
		ApplicationContext context = new ClassPathXmlApplicationContext("modifiedapplicationContext.xml");
		SessionFactory factory = (SessionFactory) context.getBean("sessionFactory");
		Session session = factory.openSession();
		CustomerDetailsResponse res = new CustomerDetailsResponse();
		Query query = session.createQuery("from client where client_id=?");
		query.setString(0, custDetailReq.getClientId());
		List<client> clientList = query.list();
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
		Criteria criteria = session.createCriteria(channel.class);
		criteria.add(Restrictions.eq("channel_id",
				custDetailReq.getChannelId()));
		List<channel> channelList = criteria.list();
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
		Criteria criteria2 = session.createCriteria(CustomerDetails.class);
		criteria2.add(Restrictions.eq("cardnum", custDetailReq.getCardNumber()));
		criteria2.add(Restrictions.eq("cvvnum", custDetailReq.getCvv()));
		List<CustomerDetails> customerDetails = criteria2.list();
		if (customerDetails != null && !(customerDetails.size() > 0)) {
			res.setResponseCode("AD1002");
			res.setResponseMesg("INVALID CARD NUM or CVV Number or Exp date");
			return res;
		}
		// load details
        if(custDetailReq.getAccountType()!=null && (custDetailReq.getAccountType().equalsIgnoreCase("LOAN") || custDetailReq.getAccountType().equalsIgnoreCase("ALL")))
        {
        	Criteria criteria3 = session.createCriteria(LoanAccount.class);
    		List<LoanAccount> loanAccounts = criteria3.list();
    		res.setLoanAccounts(loanAccounts);
        }
     // saving details
        if(custDetailReq.getAccountType()!=null && (custDetailReq.getAccountType().equalsIgnoreCase("SAVING") || custDetailReq.getAccountType().equalsIgnoreCase("ALL")))
        {
        	Criteria criteria3 = session.createCriteria(SavingAccount.class);
    		List<SavingAccount> savingAccounts = criteria3.list();
    		res.setSavingAccounts(savingAccounts);
        }
        // credit card details
        if(custDetailReq.getAccountType()!=null && (custDetailReq.getAccountType().equalsIgnoreCase("CREDIT") || custDetailReq.getAccountType().equalsIgnoreCase("ALL")))
        {
        	Criteria criteria3 = session.createCriteria(CreditCardAccount.class);
    		List<CreditCardAccount> creditCardAccounts = criteria3.list();
    		res.setCreditCardAccounts(creditCardAccounts);
        }	
		
		res.setResponseCode("000000");
		res.setResponseMesg("SUCCESS");
		session.close();
		HibernateUtility.shutdown();
		return res;
	}

	public static void main(String[] args) {
		CustomerDetailsRequest req = new CustomerDetailsRequest();
		req.setClientId("WEB");
		req.setChannelId("ONLINE");
		req.setCardNumber("789456217865124");
		req.setCvv("112");
		req.setAccountType("ALL");
		ModifiedSpringHibernateSessionFactory custDAO = new ModifiedSpringHibernateSessionFactory();
		CustomerDetailsResponse res = custDAO.getCustomerDetails(req);
		System.out.println(res);
	}
}
