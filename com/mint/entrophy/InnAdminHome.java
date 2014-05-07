/**
 * 
 * Author:Varadharajan.R File Name:Handling Redirection
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InnAdminHome {

    private static final String ALPHA_NUMERIC_STRING = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";

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

    @RequestMapping(value = "/upload-excel", method = RequestMethod.POST)
    public String uploadExcel(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException,
        IOException, JSONException {
        JSONObject foodMenuJson = new JSONObject();
        HashMap<String, String> foodMnuMap = new HashMap<String, String>();
        foodMenuJson = InnAdminHome.extractExcelContentByColumnIndex(0, foodMnuMap);
        System.out.println("ItemInJson:" + foodMenuJson);
        return "uploadExcel";
    }

    

    @SuppressWarnings("unchecked")
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

            Iterator rowIterator = sheet.rowIterator();
            columndata = new ArrayList<>();

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
                    foodMenuJson.put(InnAdminHome.randomAlphaNumeric(24), foodMnuMap);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodMenuJson;
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    private static final String getExtension(final String filename) {
        if (filename == null)
            return null;
        final String afterLastSlash = filename.substring(filename.lastIndexOf('/') + 1);
        final int afterLastBackslash = afterLastSlash.lastIndexOf('\\') + 1;
        final int dotIndex = afterLastSlash.indexOf('.', afterLastBackslash);
        return (dotIndex == -1) ? "" : afterLastSlash.substring(dotIndex + 1);
    }
}
