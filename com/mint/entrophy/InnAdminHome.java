/**
 * 
 * Author:Varadharajan.R
 * File Name:Handling Redirection 
 * 
 */
package mint.entrophy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.Package;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.rowset.internal.Row;

@Controller
public class InnAdminHome {
    @RequestMapping(value = "/")
    public String adminPage() {
       
        System.out.println("-()-()-()-()-()-()-()-(\"STARTUP\")-()-()-()-()-()-()-()");
       
        return "uploadExcel";
    }

    @RequestMapping(value = "/revealCibos")
    public String revealCibos() {
        System.out.println("-(\".\")-(\".\")-(\".\")--(\"REVEALCIBOS\")-(\".\")-(\".\")-(\".\")-");
        return "revealCibos";
    }
    @RequestMapping(value="/upload-excel",method=RequestMethod.POST)
    public String uploadExcel(HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException, JSONException
    {
        int count=0;
        String firstcolumnValue = null;   
        String secondcolumnValue = null;
       System.out.println(request.getParameter("userName"));
      String excelfile="D:/itemDatas.xls";
      JSONObject foodMenuJson=new JSONObject();
     HashMap<String, String> foodMnuMap=new HashMap<String, String>();
         foodMnuMap=InnAdminHome.extractExcelContentByColumnIndex(0,foodMnuMap);
      foodMenuJson.put("81708134058", foodMnuMap);
     // foodMenuJson.put(key, foodMenuJson);
      System.out.println("===="+foodMenuJson);
    
      
      try {
       File file = new File(excelfile);
       String fileExtension=InnAdminHome.getExtension(file.getName());
       System.out.println(fileExtension);
       POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
     
      HSSFWorkbook wb = new HSSFWorkbook(fs);
      HSSFSheet sheet = wb.getSheetAt(0);
      Iterator rowIteration=sheet.rowIterator();
      HSSFRow sheetRow=(HSSFRow) rowIteration.next();
      List<Double> values = new ArrayList<Double>();
      for (org.apache.poi.ss.usermodel.Row myrow : sheet) {
        
          Cell c = myrow.getCell(3);
          if(c != null) {
             if(c.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                values.add(c.getNumericCellValue());
             } else if(c.getCellType() == Cell.CELL_TYPE_FORMULA && c.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
                values.add(c.getNumericCellValue());
             }
          }
      }
      Iterator cellIteration=sheetRow.cellIterator();
      /*while(cellIteration.hasNext())
      {
          count++;
          System.out.println(count);
          HSSFCell cellValues=(HSSFCell) cellIteration.next();
          if(cellValues.getCellType()==cellValues.CELL_TYPE_STRING)
          {
          cellValues.getStringCellValue();
         
            foodMenuJson.append(cellValues.getStringCellValue(), "willComeSone");
         
          
          }
          else if(cellValues.getCellType()==cellValues.CELL_TYPE_NUMERIC)
          {
              foodMenuJson.append(cellValues.getStringCellValue(), "willComeSone");
          }
          else if(cellValues.getCellType()==cellValues.CELL_TYPE_BLANK)
          {
              foodMenuJson.append(cellValues.getStringCellValue(), "willComeSone");
          }
          else if(cellValues.getCellType()==cellValues.CELL_TYPE_BOOLEAN)
          {
              foodMenuJson.append(cellValues.getStringCellValue(), "willComeSone");
          }
          System.out.println(foodMenuJson);
      }*/
    }
      catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      
    /*   int rows; // No of rows
       rows = sheet.getPhysicalNumberOfRows();
       int cols = 0; // No of columns
       int tmp = 0;
       String cellValue;
       int numericCellValue;*/
       
       
       
      
      return "uploadExcel";
    }
    

    private static final String getExtension(final String filename) {
        if (filename == null) return null;
        final String afterLastSlash = filename.substring(filename.lastIndexOf('/') + 1);
        final int afterLastBackslash = afterLastSlash.lastIndexOf('\\') + 1;
        final int dotIndex = afterLastSlash.indexOf('.', afterLastBackslash);
        return (dotIndex == -1) ? "" : afterLastSlash.substring(dotIndex + 1);
      }
    /*for(int i = 0; i < 10 || i < rows; i++) {
        row = sheet.getRow(i);
        if(row != null) {
            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
            if(tmp > cols) cols = tmp;
        }
    }

for(int r = 0; r < rows; r++) {
        row = sheet.getRow(r);
        if(row != null) {
            for(int c = 0; c < cols; c++) {
                cell = row.getCell(c);
                String cellValue=cell.getStringCellValue();
                System.out.println(cellValue);
                if(cell != null) {
                    // Your code here\
                  System.out.println(cell.getRow());
                  cell.getRichStringCellValue();
                }
            }
        }
    }*/
    @SuppressWarnings("unchecked")
    public static HashMap<String, String> extractExcelContentByColumnIndex(int columnIndex, HashMap<String, String> foodMnuMap){
        ArrayList<String> columndata = null;
        try {
            File f = new File("D:/itemDatas.xls");
            FileInputStream ios = new FileInputStream(f);
            foodMnuMap= new HashMap<String, String>();
            HSSFWorkbook workbook = new HSSFWorkbook(ios);
            HSSFSheet sheet = workbook.getSheetAt(0);
           
            Iterator rowIterator=sheet.rowIterator();
            columndata = new ArrayList<>();
              
            while (rowIterator.hasNext()) {
                
                HSSFRow row=(HSSFRow) rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                @SuppressWarnings("rawtypes")
                List arrayKeyPool=new ArrayList();
                arrayKeyPool.add("itmId");
                arrayKeyPool.add("itmName");
                arrayKeyPool.add("itmType");
                arrayKeyPool.add("itmCst");
                arrayKeyPool.add("itmImge");
                arrayKeyPool.add("itmSumry");
                arrayKeyPool.add("itmCalore");
                arrayKeyPool.add("itmDlvryChrge");
                arrayKeyPool.add("itmPostedRstrntId");
                arrayKeyPool.add("ItmDlvryTime");
                     int j=0;    
                while (cellIterator.hasNext()) {
                  HSSFCell cell= (HSSFCell) cellIterator.next();
                      
                    if(row.getRowNum() > 0){ 
                       System.out.println(row.getLastCellNum());
                        
                            switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                columndata.add(cell.getNumericCellValue()+"");
                                foodMnuMap.put((String) arrayKeyPool.get(j), String.valueOf(cell.getNumericCellValue()));
                               break;
                            case Cell.CELL_TYPE_STRING:
                                columndata.add(cell.getStringCellValue());
                                foodMnuMap.put((String) arrayKeyPool.get(j), cell.getStringCellValue());
                                break;
                            }
                        
                    }
                j++;}
                
                System.out.println(foodMnuMap);
            }
           
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodMnuMap;
    }
}