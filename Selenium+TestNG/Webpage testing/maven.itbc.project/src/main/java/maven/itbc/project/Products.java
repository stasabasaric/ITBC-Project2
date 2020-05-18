package maven.itbc.project;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Products {
	
	//Methods for managing functions in product section  

	private static Map<String, String> m = Locators.map;

	public static String openProductsTab(WebDriver wd) {

		wd.findElement(By.xpath(m.get("PRODUCTS_TAB_XPATH"))).click();
		return wd.getCurrentUrl();
	}

	public static String AddProduct(WebDriver wd) {

		wd.findElement(By.xpath(m.get("ADD_PRODUCT_XPATH"))).click();
		return wd.getCurrentUrl();

	}

	public String productName(WebDriver wd, String productName) {

		wd.findElement(By.xpath(m.get("PRODUCT_NAME_XPATH"))).sendKeys(productName);
		return wd.findElement(By.xpath(m.get("PRODUCT_NAME_XPATH"))).getAttribute("value");

	}

	public String productID(WebDriver wd, String id) {

		wd.findElement(By.name(m.get("PRODUCT_ID_NAME"))).sendKeys(id);
		return wd.findElement(By.name(m.get("PRODUCT_ID_NAME"))).getAttribute("value");
	}

	public String productPrice(WebDriver wd, String price) {

		wd.findElement(By.name(m.get("PRICE_NAME"))).sendKeys(price);
		return wd.findElement(By.name(m.get("PRICE_NAME"))).getAttribute("value");
	}

	public String saveProduct(WebDriver wd) {

		wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		wd.findElement(By.name(m.get("SAVE_NAME"))).click();
		return wd.findElement(By.xpath(m.get("UPDATE_SUCCESS_XPATH"))).getText();
	}

	public static String openEditPage(WebDriver wd) {

		wd.findElement(By.xpath(m.get("EDIT_XPATH"))).click();
		return wd.getCurrentUrl();
	}


	public static List increasePrices(WebDriver wd, int increase) {

		List<WebElement> elem = wd.findElements(By.xpath("//tbody//tr//td//input"));
		WebElement price;

		for (int i = 0; i < 5; i++) {

			price = elem.get(i);
			double inc = (Double.parseDouble(price.getAttribute("value"))) + increase;
			price.clear();
			price.sendKeys(Double.toString(inc));

		}

		return wd.findElements(By.xpath("//tbody//tr//td//input"));

	}

	public static String saveChanges(WebDriver wd) {
		wd.findElement(By.xpath(m.get("SAVE_CHANGES_XPATH"))).click();
		return wd.getCurrentUrl();
	}

	public static String viewProducts(WebDriver wd) {
		wd.findElement(By.xpath(m.get("VIEW_PRODUCTS_XPATH"))).click();
		return wd.getCurrentUrl();
	}

	public static void deleteAllProducts(WebDriver wd) {
		List<WebElement> delete = wd.findElements(By.name(m.get("DELETE_CHECKBOX_NAME")));

		for (int i = 0; i < delete.size(); i++) {

			delete.get(i).click();

		}
		
		wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		wd.findElement(By.xpath(m.get("DELETE_SAVE_XPATH"))).click();
		wd.findElement(By.xpath(m.get("CONFIRM_DELETE_XPATH"))).click();

	}

}
