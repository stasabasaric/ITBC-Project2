package maven.itbc.project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class LogIn {
	
	//Methods for login page 
	
	Map <String, String> m = Locators.map;
	
	
	public LogIn() {
		
	}
	
	public LogIn(WebDriver wd, String name, String pass) {
		inputUsername(wd, name);
		inputPassword(wd, pass);
	}
	
	
	public String inputUsername(WebDriver wd, String name) {
			wd.findElement(By.name(m.get("LOG_USERNAME_NAME"))).sendKeys(name);
			return wd.findElement(By.name(m.get("LOG_USERNAME_NAME"))).getAttribute("value");
		
	}
	
	public String inputPassword(WebDriver wd, String pass) {
		wd.findElement(By.id(m.get("LOG_PASS_ID"))).sendKeys(pass);
		return wd.findElement(By.id(m.get("LOG_PASS_ID"))).getAttribute("value");
		
	}
	
	public String submit(WebDriver wd) {
		wd.findElement(By.xpath(m.get("LOG_SUBMIT_XPATH"))).submit();
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
