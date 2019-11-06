package com.wf.cust.service;

import com.wf.cust.bean.Customer;
import com.wf.cust.bean.CustomerExample;
import com.wf.cust.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*在实现类上加service，因为接口没有类对象*/
@Service
public class CustomerServiceImpl implements CustomerService{
/*
*       service层在Spring容器中，容器识别不到mapper接口在容器中，
*       让容器识别到mapper接口方案一：在所有的mapper接口上加一个@Repository（不建议）
*                           方案二：在 private CustomerMapper customerMapper;上加注解@Resource
*                           方案三： 用@Autowired，虽然爆红，但是不影响程序运行
* */
    @Autowired
    private CustomerMapper customerMapper;
/*添加*/
    public void saveInfo(Customer customer) {
        customer.setAddtime(new Date());
        customerMapper.insert(customer);
    }
/*查询*/
    public List<Customer> getCustomerList() {
        CustomerExample customerExample=new CustomerExample();
        List<Customer> list = customerMapper.selectByExample(customerExample);

        return list;
    }
/*查看详情*/
    public Customer getCustomerDetail(Integer cid) {
        Customer customer = customerMapper.selectByPrimaryKey(cid);
        return customer;

    }

    public void updateCustomer(Customer customer) {

        customerMapper.updateByPrimaryKeySelective(customer);

    }

    public Boolean BatchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        CustomerExample example=new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int i = customerMapper.deleteByExample(example);
        return  ids.length==i;
    }

    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
      CustomerExample example=new CustomerExample();
      CustomerExample.Criteria criteria=example.createCriteria();
      if (cid==0){

          criteria.andCameraLike("%"+keyword+"%");
          CustomerExample.Criteria criteria1=example.createCriteria();
        criteria1.andCompanypersonLike("%"+keyword+"%");
          example.or(criteria1);
      }else if (cid==1){
          criteria.andCameraLike("%"+keyword+"%");
      }else{
          criteria.andCompanypersonLike("%"+keyword+"%");

      }if (orderby==1){
          example.setOrderByClause("id desc");
        }

        List<Customer> customers = customerMapper.selectByExample(example);
      return  customers;
    }


}
