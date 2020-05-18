package registration;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;

import maven.itbc.project.Locators;
import maven.itbc.project.Registration;

public class RegisterUserTest extends BaseClass {
	
	Registration user;
	
	@BeforeClass
	public void object() {
		 user = new Registration();
	}
	
  @Test (priority = 0)
  public void openSignUpPageTest() {
	 String actURL = Registration.openSignUpPage(wd);
	 String expURL = "https://sandbox.2checkout.com/sandbox/signup";
	 
	 Assert.assertEquals(actURL, expURL);
	 
	 
  } 
	 

  @Test (priority = 1) 
  public void inputNameTest() {
	  String actInput = user.inputUsername(wd, "StasaBas12");
	  Assert.assertEquals(actInput, "StasaBas12");
  }
  
  @Test(priority = 2) 
  public void inputEmailTest() {
	  String actInput = user.inputEmail(wd, "stasa@bas1.com");
	  Assert.assertEquals(actInput, "stasa@bas1.com");
  }
  
  @Test(priority = 3) 
  public void inputPassTest() {
	  String actInput = user.inputPassword(wd, "Stasa123");
	  Assert.assertEquals(actInput, "Stasa123");
	  
  }
  
  @Test(priority = 4) 
  public void inputConfirmPassTest() {
	  String actInput = user.inputConfirmPass(wd, "Stasa123");
	  Assert.assertEquals(actInput, "Stasa123");
	  
  }
  
  @Test(priority = 5) 
  public void selectAboutOptionTest() {
	  
	  int opt = 1;
	  String selected = user.selectAboutOption(wd, opt);
	  SoftAssert sa = new SoftAssert();
	  
	  if(opt == 1) {
	  sa.assertEquals(selected, "www.stasastore.com");
	  wait(3000);
	  user.selectAboutOption(wd, 2);
  }
	  else
		  sa.assertEquals(selected, "I don't have a website");
	  
	  sa.assertAll();
		  
  }
  
 
  
  @Test(priority = 6) 
  public void constructorTest() {
	  wd.navigate().refresh();
	  Registration user = new Registration(wd, "StasaBas12","stasa@bas12.rs", "Stasa123", "Stasa123",5);
	  
	  SoftAssert sa = new SoftAssert();
	  sa.assertEquals(wd.findElement(By.id(m.get("REG_USERNAME_ID"))).getAttribute("value"), "StasaBas12");
	  sa.assertEquals(wd.findElement(By.id(m.get("REG_EMAIL_ID"))).getAttribute("value"), "stasa@bas12.rs");
	  sa.assertEquals(wd.findElement(By.id(m.get("REG_PASS_ID"))).getAttribute("value"), "Stasa123");
	  sa.assertEquals(wd.findElement(By.id(m.get("REG_CONFIRM_PASS_ID"))).getAttribute("value"), "Stasa123");
	  sa.assertEquals((wd.findElement(By.id(m.get("REG_ABOUT_ID")))).getAttribute("value"), "I just want to play in the sandbox");
  
	  sa.assertAll();
  }
  
  
  @Test(priority = 7)
  public void submitTest() {
	 String actURL = user.submit(wd);
	 String expURL = "https://sandbox.2checkout.com/sandbox/home/dashboard";
	 
	 Assert.assertEquals(actURL, expURL);
	 
  }
  
  @Test(priority = 8)
  public void signOutTest() {
	  String actURL = user.signOut(wd);
	  String expURL = "https://sandbox.2checkout.com/sandbox";
	  
	  Assert.assertEquals(actURL, expURL);
	  
  }
  
	  
}
