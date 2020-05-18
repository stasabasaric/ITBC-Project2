package login;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import maven.itbc.project.Locators;
import maven.itbc.project.LogIn;

public class LogInUserTest extends BaseClass {
	
	
	LogIn user;
	
	@BeforeClass
	public void User() {
		user = new LogIn();
		m = Locators.map;
	}
	
  @Test (priority = 0)
  public void inputNameTest() {
	 String input = user.inputUsername(wd, "StasaBas");
	 Assert.assertEquals(input, "StasaBas");
	  
	  
  }
  
  @Test (priority = 1)
  public void inputPassTest() {
	  String input = user.inputPassword(wd, "Stasa123");
	  Assert.assertEquals(input, "Stasa123");
  }
  
  
  @Test(priority = 2) 
  public void submitTest() {
	  user.submit(wd);
	  wd.findElement(By.id(m.get("ACCOUNT_ID"))).click();
	  String userN = wd.findElement(By.id(m.get("ACCOUNT_NAME_ID"))).getText();
	  
	  Assert.assertEquals(userN, "StasaBas");
  }
  
  @Test(priority = 3)
  public void signOutTest() {
	  String actURL = user.signOut(wd);
	  String expURL = "https://sandbox.2checkout.com/sandbox";
	  
	  Assert.assertEquals(actURL, expURL);
  }
  
  
}
