package products;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import maven.itbc.project.LogIn;
import maven.itbc.project.Products;
import utility.DataSetExcel;
import utility.ExcelUtils;

public class ProductsTest extends BaseClass {

	String xl_path = DataSetExcel.XLSXP_PATH;
	String sheet = DataSetExcel.SHEET_NAME;

	@BeforeClass
	public void logIn() {

		ExcelUtils.setExcel(xl_path, sheet);
		LogIn user = new LogIn(wd, "StasaBas", "Stasa123");
		user.submit(wd);

	}

	@AfterClass
	public void closeXL() {
		ExcelUtils.closeExcel();
	}

	@Test (priority=0)
	public void Add5ProductsTest() {

		
		Products pr = new Products();
		
		SoftAssert sa = new SoftAssert();
		
		JavascriptExecutor js = (JavascriptExecutor) wd;

		for (int i = 1; i < ExcelUtils.getRowCount(sheet); i++) {
			
			Products.openProductsTab(wd);
			Products.AddProduct(wd);
			
			pr.productID(wd, ExcelUtils.getCellData(i, 0));
			pr.productName(wd, ExcelUtils.getCellData(i, 1));
			
			js.executeScript("window.scrollBy(0,1500)");
			wait(1000);
			
			pr.productPrice(wd, ExcelUtils.getCellData(i, 2));
			
			
			String success = pr.saveProduct(wd);
			
			wait(1500);
			
			sa.assertEquals(success, "Update successful");
			
		}
		
		sa.assertAll();
	}

	@Test(priority = 1)
	public void openEditPageTest() {

		Products.openProductsTab(wd);
		wait(1000);
		String actURL = Products.openEditPage(wd);
		String expURL = "https://sandbox.2checkout.com/sandbox/products/edit_products";

		Assert.assertEquals(actURL, expURL);

	}

	@Test(priority = 2)
	public void increasePricesTest() {

		List<WebElement> elem = Products.increasePrices(wd, 100);

		SoftAssert sa = new SoftAssert();

		for (int i = 0; i < 5; i++) {
			double incPrice = Double.parseDouble(elem.get(i).getAttribute("value"));
			double price = Double.parseDouble(ExcelUtils.getCellData(i + 1, 2)) + 100;

			sa.assertTrue((incPrice == price));

		}

		sa.assertAll();

	}

	@Test(priority = 3)
	public void saveChangesTest() {

		Products.saveChanges(wd);

		List<WebElement> elem = wd.findElements(By.xpath("//tbody//tr//td//input"));

		SoftAssert sa = new SoftAssert();

		for (int i = 0; i < 5; i++) {
			double incPrice = Double.parseDouble(elem.get(i).getAttribute("value"));
			double price = Double.parseDouble(ExcelUtils.getCellData(i + 1, 2)) + 100;

			sa.assertTrue((incPrice == price));

		}
		
		sa.assertAll();
		
		
	}
	
	@Test(priority = 4)
	public void DeleteAllProductsTest() {
		
		Products.viewProducts(wd);
		Products.deleteAllProducts(wd);
		Products.viewProducts(wd);
		
		Assert.assertTrue(wd.findElement(By.xpath(m.get("NO_RECORDS_XPATH"))).getText().contains("No Records"));
	}
	
	

}
