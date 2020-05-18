package registration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import maven.itbc.project.Registration;
import utility.DataSetExcel;
import utility.ExcelUtils;

public class Reg30UsersTest extends BaseClass {

	String xl_path = DataSetExcel.XLSX_PATH;
	String sheet = DataSetExcel.SHEET_NAME_1;

	@BeforeClass
	public void openXL() {
		ExcelUtils.setExcel(xl_path, sheet);

	}

	@AfterClass
	public void closeXL() {
		wait(1000);
		ExcelUtils.closeExcel();

	}

	@Test
	public void Reg30Test() {

		SoftAssert sa = new SoftAssert();
		
		for (int i = 1; i < ExcelUtils.getRowCount(sheet); i++) {

			Registration.openSignUpPage(wd);
			String[] inputs = new String[ExcelUtils.getColCount(i) - 2];
			for (int j = 1; j < ExcelUtils.getColCount(i) - 1; j++) {
				inputs[j - 1] = ExcelUtils.getCellData(i, j);
			}

			int j = 0;
			Registration user = new Registration(wd, inputs[j], inputs[++j], inputs[++j], inputs[++j],
					Integer.parseInt(inputs[++j]));
			wait(2000);
			String success = user.submit(wd);
			sa.assertEquals(success, "https://sandbox.2checkout.com/sandbox/home/dashboard");
			user.signOut(wd);
			
		}
		
		
		
		sa.assertAll();

	}
}
