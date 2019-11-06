package com.wf.cust.controller;

import com.wf.cust.bean.Customer;
import com.wf.cust.service.CustomerService;
import com.wf.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/cust")
public class CustomerController {
    /*在这里写接口，不要写实现类，如果写实现类，会使之后创建容器之后，放到容器里的是代理类对象，而不是对象本身*/
    @Autowired
    private CustomerService customerService;

    /*查询用户*/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getCustomerList() {
        List<Customer> list = customerService.getCustomerList();
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list", list);
        return mv;

    }

    /*增加用户*/
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(Customer customer) {
        customerService.saveInfo(customer);
        return "redirect:/cust/list";


    }

    /*查询详情页*/
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer cid, Map<String, Object> map) {
        Customer customer = customerService.getCustomerDetail(cid);
        map.put("customer", customer);
        return "customer-look";
    }

    /*编辑用户*/
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer cid, Map<String, Object> map) {
        Customer customer = customerService.getCustomerDetail(cid);
        map.put("customer", customer);
        return "customer-edit";
    }
    /*修改*/

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(Customer customer) {
        customerService.updateCustomer(customer);

        return "redirect:/cust/list";
    }

    /*删除*/
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public Map<String, Object> batchDelete(@PathVariable("ids[]") Integer[] ids) {
        Boolean result = customerService.BatchDelete(ids);
        Map<String, Object> map = new HashMap<String, Object>();
        if (result) {
            map.put("statusCode", 200);
            map.put("massage", "删除成功");
        } else {
            map.put("statusCode", 500);
            map.put("massage", "删除失败");

        }
        return map;

    }

    /*搜索*/
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView getCustomerList(Integer cid, String keyword, Integer orderby) {
        List<Customer> list = customerService.search(cid, keyword, orderby);
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list", list);
        return mv;

    }


    /*搜索所有客户公司名称   异步*/
    @RequestMapping(value = "/jsonlist", method = RequestMethod.GET)
//    因为为异步请求方式所以需要加   @ResponseBody
    @ResponseBody
    public List<Customer> getCustomerJsonList() {

        return customerService.getCustomerList();
    }

    /*查询某个客户*/
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer info(@PathVariable("id") Integer cid) {
        Customer customer = customerService.getCustomerDetail(cid);
        return customer;
    }

    /*导出excel*/
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> exprotExcel() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("customers");
//设置列宽
        sheet.setColumnWidth(3,4000);

        Row row = sheet.createRow(0);
        Cell[] cell = new HSSFCell[5];
        for (int i = 0; i < 5; i++) {
            cell[i] = row.createCell(i);
        }
        cell[0].setCellValue("ID");
        cell[1].setCellValue("联系人");
        cell[2].setCellValue("公司名称");
        cell[3].setCellValue("添加时间");
        cell[4].setCellValue("联系电话");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<Customer> customerList = customerService.getCustomerList();
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(0);
            Row row1 = sheet.createRow(i + 1);
            Cell[] cell1 = new HSSFCell[5];
            for (int j = 0; j < 5; j++) {
                cell1[j] = row1.createCell(j);
            }

            cell1[0].setCellValue(customer.getId());
            cell1[1].setCellValue(customer.getCompanyperson());
            cell1[2].setCellValue(customer.getComname());
            Date addtime = customer.getAddtime();
            String format = sdf.format(addtime);
            cell1[3].setCellValue(format);
            cell1[4].setCellValue(customer.getComphone());
        }


        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("D:\\Desktop\\customers.xls"));
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        Map<String,Object> map=new HashMap<String, Object>();
            map.put("code",200);
            map.put("message","下载成功");
            return map;

    }



//    导入excel
    @ResponseBody
    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    public Map<String,Object> importExcel(MultipartFile excel) {
        Map<String,Object> map=new HashMap<String, Object>();
//      创建一个列表用于存储用户
        List<Customer> customers=new ArrayList<Customer>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        //    导入excel
            try {
                //        根据地址导入表格
                Workbook wb = WorkbookFactory.create(excel.getInputStream());

                for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                    //      根据索引获取sheet
                    Sheet sheet = wb.getSheetAt(k);

                    if (sheet == null){
                        break;
                    }
//遍历行
                    for (int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++) {
//        获取到行
                        Row row = sheet.getRow(i);
                        Customer customer=new Customer();
                        if (row !=null){
                            // 获取联系人
                            String linkMan =row.getCell(1).getStringCellValue();
//                            设置给customer
                            customer.setCompanyperson(linkMan);
//                          获取获取公司名称
                            String companyName = row.getCell(2).getStringCellValue();
                            customer.setComname(companyName);
//                         获取添加时间
                            Date dateCellValue = row.getCell(3).getDateCellValue();
//                           转换成字符串类型
                            String format = sdf.format(dateCellValue);
//                          转换日期类型
                            Date addTime = sdf.parse(format);
                            customer.setAddtime(addTime);
//                           获取手机号（科学计数法类型）
                            double numericCellValue = row.getCell(4).getNumericCellValue();
//                            转换成数字类型，---》字符串类型
                            BigDecimal decimal=new BigDecimal(String.valueOf(numericCellValue));
                            customer.setComphone(decimal.toPlainString());






                           /* //        遍历列
                            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
//            获取到列
                                Cell cell = row.getCell(j);
//              转换类型
                                String value = ExcelUtils.parseExcelValueToString(cell);
                                if (i > 0 && j == 0) {
                                    value = value.substring(0, value.indexOf("."));
                                }
                                System.out.print(value + "  ");

                            }*/

                            System.out.println();

                        }
                            customers.add(customer);

                    }
                }            System.out.println(customers);
                              System.out.println();
                    /*批量插入*/
//                    customerService.batchInsert(customers);

                map.put("statusCode",200);
                map.put("message","上传成功");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("出异常了");
            }
            return  map;
        }

    }
