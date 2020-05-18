package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static FileInputStream fi;
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	private static XSSFCell cell;

	// Method for opening Excel workbook and determined worksheet
	public static boolean setExcel(String path, String sheetName) {
		try {

			fi = new FileInputStream(path); // loading excel file based on path
			wb = new XSSFWorkbook(fi); // opening excel file
			sheet = wb.getSheet(sheetName); // getting sheet in opened excel file

			return true;

		} catch (Exception ex) {
			System.out.println(ex.toString());
			System.out.println("Error at opening file");
			return false;
		}
	}

	// Method for getting total number of rows in determined sheet
	public static int getRowCount(String sheetName) {

		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() + 1; // adds one as first row is 0
		return rowCount;
	}
	
	//Method for getting total number of cells in one row
	public static int getColCount(int rowNum) {
		int colCount = sheet.getRow(rowNum).getLastCellNum() + 1;
		return colCount;
	}

	// Method for getting cell data
	public static String getCellData(int rowNum, int colNum) {

		try {

			cell = sheet.getRow(rowNum).getCell(colNum);
			return cell.toString();

		} catch (NullPointerException ex) {
			System.out.println(ex.toString());
			System.out.println("Something is null!");
		} catch (Exception ex) {
			System.out.println(ex.toString());
			System.out.println("Error occured");
		}
		return "";
	}
	
	public static void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
