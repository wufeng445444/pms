
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AppTest {
//    测试分页插件
/*
    @Test
    public void test03(){
        ClassPathXmlApplicationContext  context=new ClassPathXmlApplicationContext("beans.xml");
        GoodsServiceImpl bean = context.getBean(GoodsServiceImpl.class);
        List<Goods> goodsList = bean.getGoodsList();
        System.out.println(goodsList.size());
    }
*/





//    导入excel
    @Test
    public void test02(){
    try {
    //        根据地址导入表格
    Workbook wb = WorkbookFactory.create(new File("D:\\Desktop\\Book1.xls"));
    //      根据索引获取sheet
    Sheet sheet = wb.getSheetAt(0);
    //        获取第一行的数据
    int firstRowNum = sheet.getFirstRowNum();
        //        获取最后一行的数据
    int lastRowNum = sheet.getLastRowNum();
    System.out.println("第一行行号为："+firstRowNum);
    System.out.println("最后一行行号为："+lastRowNum);
//遍历行
    for (int i=sheet.getFirstRowNum();i<=sheet.getLastRowNum();i++){
//        获取到行
        Row row = sheet.getRow(i);
//        遍历列
        for (int j=row.getFirstCellNum();j<row.getLastCellNum();j++){
//            获取到列
              Cell cell = row.getCell(j);
//              转换类型
              String value=parseExcelValueToString(cell);
            if (i>0&&j==0){
                 value = value.substring(0, value.indexOf("."));
            }
              System.out.print(value+"  ");

        }
        System.out.println();
    }


    }catch (Exception ex){
        System.out.println("出异常了");
    }
    }

    private String parseExcelValueToString(Cell cell) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String result="";
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                result= cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result=String.valueOf(cell.getBooleanCellValue());
                break;
            case  Cell.CELL_TYPE_FORMULA:
                result=cell.getCellFormula();
                break;
            case  Cell.CELL_TYPE_NUMERIC:
               if(HSSFDateUtil.isCellDateFormatted(cell)){
                   Date date =cell.getDateCellValue();
                   result=sdf.format(cell.getNumericCellValue());
               }else {

                   BigDecimal decimal=new BigDecimal(String.valueOf(cell.getNumericCellValue()));
                  result= decimal.toPlainString();

               }
                break;
            default:
                result ="";
        }
        return  result;
    }

//导出excel
    @Test
    public void test01(){
//1，创建excel表格
        Workbook wb = new HSSFWorkbook();
//        2，在表格里面创建表sheet
        Sheet sheet1=wb.createSheet("sheet one");
        Sheet sheet2=wb.createSheet("sheet two");
        Sheet sheet3=wb.createSheet("sheet three");
        Sheet sheet4=wb.createSheet("sheet four");
//3,创建第一行
        Row row =sheet1.createRow(0);
//        4,创建第一列
        Cell cell0=row.createCell(0);
        Cell cell1=row.createCell(1);
        Cell cell2=row.createCell(2);
//5,给单元格设置值
        cell0.setCellValue("id");
        cell1.setCellValue("序号");
        cell2.setCellValue("联系人");




        FileOutputStream fos=null;
        try  {
            fos= new FileOutputStream(new File("D:\\Desktop\\customers.xls"));
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
