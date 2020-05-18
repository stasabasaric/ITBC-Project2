package maven.itbc.project;

import java.util.Map;


import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Registration {
	
	//Methods for registering a user 
	// ... unfortunately this page on the site has been canceled

	private static Map<String, String> m = Locators.map;
	
	//two constructors, one is used for calling methods through object and filling out
	// the form one by one field
	// while the other constructor calls all methods for filling the whole registeration form

	public Registration() {

	}

	public Registration(WebDriver wd, String name, String email, String password, String confirmp, int aboutOption) {
		inputUsername(wd, name);
		inputEmail(wd, email);
		inputPassword(wd, password);
		inputConfirmPass(wd, confirmp);
		selectAboutOption(wd, aboutOption);

	}

	public static String openSignUpPage(WebDriver wd) {
		wd.findElement(By.xpath(Locators.map.get("SIGNUP_XPATH"))).click();
		wait(1500);
		return wd.getCurrentUrl();
	}

	public String inputUsername(WebDriver wd, String name) {
		wd.findElement(By.id(m.get("REG_USERNAME_ID"))).sendKeys(name);
		return wd.findElement(By.id(m.get("REG_USERNAME_ID"))).getAttribute("value");

	}

	public String inputEmail(WebDriver wd, String email) {
		wd.findElement(By.id(m.get("REG_EMAIL_ID"))).sendKeys(email);
		return wd.findElement(By.id(m.get("REG_EMAIL_ID"))).getAttribute("value");

	}

	public String inputPassword(WebDriver wd, String pass) {
		wd.findElement(By.id(m.get("REG_PASS_ID"))).sendKeys(pass);
		return wd.findElement(By.id(m.get("REG_PASS_ID"))).getAttribute("value");

	}

	public String inputConfirmPass(WebDriver wd, String confirm) {
		wd.findElement(By.id(m.get("REG_CONFIRM_PASS_ID"))).sendKeys(confirm);
		return wd.findElement(By.id(m.get("REG_CONFIRM_PASS_ID"))).getAttribute("value");

	}

	public String selectAboutOption(WebDriver wd, int option) {

		if (option < 1 || option > 5) {
			System.out.println("The choosen option is not valid");
			return "ERROR";
		}

		Select opt = new Select(wd.findElement(By.id(m.get("REG_ABOUT_ID"))));
		opt.selectByIndex(option);
		if (option == 1) {

			String websiteURL = JOptionPane.showInputDialog("Enter the Website address for the chosen option");
			wd.findElement(By.id(m.get("WEBSITE_URL_ID"))).sendKeys(websiteURL);
			return wd.findElement(By.id(m.get("WEBSITE_URL_ID"))).getAttribute("value");
		}

		return opt.getFirstSelectedOption().getText();

	}

	public String submit(WebDriver wd) {
		wd.findElement(By.id(m.get("REG_SUBMIT_ID"))).submit();
		wait(2000);
		return wd.getCurrentUrl();

	}

	public String signOut(WebDriver wd) {
		wd.findElement(By.id(m.get("ACCOUNT_ID"))).click();
		wd.findElement(By.id(m.get("LOGOUT_ID"))).click();
		wait(1500);

		return wd.getCurrentUrl();
	}

	private static void wait(int milisec) {

		try {
			Thread.sleep(milisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
