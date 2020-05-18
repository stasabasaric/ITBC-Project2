package base;

import org.testng.annotations.BeforeSuite;

import maven.itbc.project.Locators;
import utility.DataSetExcel;
import utility.ExcelUtils;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;


public class BaseClass {

	public static WebDriver wd;
	public static Map<String, String> m;

	@BeforeSuite
	public void beforeSuite() {

		Locators.getInstance();
		m = Locators.map;

		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		wd = new ChromeDriver();
		wd.get("https://sandbox.2checkout.com/sandbox");

	}

	@AfterSuite
	public void afterSuite() {
		wait(2000);
		wd.close();
		ExcelUtils.closeExcel();
	}

	public static void wait(int milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
