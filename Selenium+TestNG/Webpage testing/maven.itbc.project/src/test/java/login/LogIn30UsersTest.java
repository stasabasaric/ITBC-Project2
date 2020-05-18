package login;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import maven.itbc.project.LogIn;
import utility.DataSetExcel;
import utility.ExcelUtils;

public class LogIn30UsersTest extends BaseClass {

	String xl_path = DataSetExcel.XLSX_PATH;
	String sheet = DataSetExcel.SHEET_NAME_2;

	@BeforeClass
	public void openXL() {
		ExcelUtils.setExcel(xl_path, sheet);

	}

	@AfterClass
	public void closeXL() {
		ExcelUtils.closeExcel();

	}

	@Test
	public void Log30Users() {
		
		SoftAssert sa = new SoftAssert();

		for (int i = 1; i < ExcelUtils.getRowCount(sheet); i++) {
			
			String name = ExcelUtils.getCellData(i, 1);
			String pass = ExcelUtils.getCellData(i, 2);
			
			LogIn user = new LogIn(wd, name ,pass);
			
			user.submit(wd);
			
			sa.assertEquals(wd.getCurrentUrl(), "https://sandbox.2checkout.com/sandbox/home/dashboard");
			
			wait(2000);
			user.signOut(wd);
			

		}
		
		sa.assertAll();
	}
}
