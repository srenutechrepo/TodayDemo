package com.sreenu.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ad_ccaccount")
public class CreditCardAccount {
	@Id
	private String card_num;
	@Column
	private String card_type;
	@Column
	private String name;
	@Column
	private Double unbilled_amt;
	@Column
	private Double credit_amt;
	@Column
	private Double balance;

	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getUnbilled_amt() {
		return unbilled_amt;
	}

	public void setUnbilled_amt(Double unbilled_amt) {
		this.unbilled_amt = unbilled_amt;
	}

	public Double getCredit_amt() {
		return credit_amt;
	}

	public void setCredit_amt(Double credit_amt) {
		this.credit_amt = credit_amt;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
