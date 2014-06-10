package com.vail.foodcourt.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;

public  final class  TemplateMethods {

    @SuppressWarnings("unused")
    private static final String getExtension(final String filename) {
        if (filename == null)
            return null;
        final String afterLastSlash = filename.substring(filename.lastIndexOf('/') + 1);
        final int afterLastBackslash = afterLastSlash.lastIndexOf('\\') + 1;
        final int dotIndex = afterLastSlash.indexOf('.', afterLastBackslash);
        return (dotIndex == -1) ? "" : afterLastSlash.substring(dotIndex + 1);
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * GeneralConstants.ALPHA_NUMERIC_STRING.length());
            builder.append(GeneralConstants.ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    /**
     * Returns a reference to a file with the specified name that is located
     * somewhere on the classpath.
     */
    public static File findFileOnClassPath(final String fileName) {

System.out.println("calling methods");
  final String classpath = System.getProperty("java.class.path");
  final String pathSeparator = System.getProperty("path.separator");
  final StringTokenizer tokenizer = new StringTokenizer(classpath, pathSeparator);
  while (tokenizer.hasMoreTokens()) {
final String pathElement = tokenizer.nextToken();
final File directoryOrJar = new File(pathElement);
final File absoluteDirectoryOrJar = directoryOrJar.getAbsoluteFile();
if (absoluteDirectoryOrJar.isFile()) {
    final File target = new File(absoluteDirectoryOrJar.getParent(), fileName);
    if (target.exists()) {
  return target;
    }
} else {
final File target = new File(directoryOrJar, fileName);
if (target.exists()) {
 return target;
    }
}
  }

  return null;
    
    }

    public static JSONObject extractExcelContentByColumnIndex(int columnIndex, HashMap<String, String> foodMnuMap) {
        ArrayList<String> columndata = null;
        JSONObject foodMenuJson = new JSONObject();
        try {
            List<String> arrayKeyPool = new ArrayList<String>();
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
            File f = new File("D:/itemDatas.xls");
            FileInputStream ios = new FileInputStream(f);
            foodMnuMap = new HashMap<String, String>();
            HSSFWorkbook workbook = new HSSFWorkbook(ios);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            columndata = new ArrayList<String>();
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int j = 0;
                while (cellIterator.hasNext()) {
                    HSSFCell cell = (HSSFCell) cellIterator.next();
                    if (row.getRowNum() > 0) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                columndata.add(cell.getNumericCellValue() + "");
                                foodMnuMap
                                    .put((String) arrayKeyPool.get(j), String.valueOf(cell.getNumericCellValue()));
                                break;
                            case Cell.CELL_TYPE_STRING:
                                columndata.add(cell.getStringCellValue());
                                foodMnuMap.put((String) arrayKeyPool.get(j), cell.getStringCellValue());
                                break;
                        }
                    }
                    j++;
                }
                if (foodMnuMap.size() != 0) {
                    foodMenuJson.put(TemplateMethods.randomAlphaNumeric(24), foodMnuMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodMenuJson;
    }
    public static Properties readPropertyFleFrmClsPth(String propertyFileName) {
    	Properties folderCreationproperties=null;
    	try {
			
			InputStream inputStream = TemplateMethods.class.getClass().getClassLoader()
					.getResourceAsStream(propertyFileName);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '"
						+ propertyFileName + "' not found in the classpath");
			}
			folderCreationproperties = new Properties();
			folderCreationproperties.load(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return folderCreationproperties;
	}

}
