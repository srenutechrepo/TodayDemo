package com.sreenu.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_details")
public class CustomerDetails {
	@Id
	private int id;
	@Column(name="card_num")
	private String cardnum;
	@Column(name="cvv")
	private String cvvnum;
	@Column(name="dob")
	private String dob_date;
	@Column(name="name")
	private String name_on_card;
	@Column
	private String email;
	@Column
	private Integer mobile;
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getCvvnum() {
		return cvvnum;
	}

	public void setCvvnum(String cvvnum) {
		this.cvvnum = cvvnum;
	}
	public String getName_on_card() {
		return name_on_card;
	}

	public void setName_on_card(String name_on_card) {
		this.name_on_card = name_on_card;
	}

	public String getDob_date() {
		return dob_date;
	}

	public void setDob_date(String dob_date) {
		this.dob_date = dob_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

}
