package com.sreenu.dao.model;

import java.util.List;

import com.sreenu.dao.entities.CreditCardAccount;
import com.sreenu.dao.entities.LoanAccount;
import com.sreenu.dao.entities.SavingAccount;

public class CustomerDetailsResponse {
	private String responseCode;
	private String responseMesg;
	private List<LoanAccount> loanAccounts;
	private List<SavingAccount> savingAccounts;
	private List<CreditCardAccount> creditCardAccounts;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMesg() {
		return responseMesg;
	}

	public void setResponseMesg(String responseMesg) {
		this.responseMesg = responseMesg;
	}

	public List<LoanAccount> getLoanAccounts() {
		return loanAccounts;
	}

	public void setLoanAccounts(List<LoanAccount> loanAccounts) {
		this.loanAccounts = loanAccounts;
	}

	public List<SavingAccount> getSavingAccounts() {
		return savingAccounts;
	}

	public void setSavingAccounts(List<SavingAccount> savingAccounts) {
		this.savingAccounts = savingAccounts;
	}

	public List<CreditCardAccount> getCreditCardAccounts() {
		return creditCardAccounts;
	}

	public void setCreditCardAccounts(List<CreditCardAccount> cardAccounts) {
		this.creditCardAccounts = cardAccounts;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerDetailsResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseMesg=");
		builder.append(responseMesg);
		builder.append(", loanAccounts=");
		builder.append(loanAccounts);
		builder.append(", savingAccounts=");
		builder.append(savingAccounts);
		builder.append(", creditCardAccounts=");
		builder.append(creditCardAccounts);
		builder.append("]");
		return builder.toString();
	}

	

}
