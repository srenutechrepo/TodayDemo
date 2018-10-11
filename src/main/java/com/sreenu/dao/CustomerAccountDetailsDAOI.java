package com.sreenu.dao;

import com.sreenu.dao.model.CustomerDetailsRequest;
import com.sreenu.dao.model.CustomerDetailsResponse;

public interface CustomerAccountDetailsDAOI {
public CustomerDetailsResponse getCustomerDetails(CustomerDetailsRequest custDetailReq);
}
