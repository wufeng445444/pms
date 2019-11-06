package com.wf.cust.service;


import com.wf.cust.bean.Customer;

import java.util.List;

public interface CustomerService {

    public void saveInfo(Customer customer);

    List<Customer> getCustomerList();

    Customer getCustomerDetail(Integer cid);


    void updateCustomer(Customer customer);


    Boolean BatchDelete(Integer[] ids);

    List<Customer> search(Integer cid, String keyword, Integer orderby);
}
