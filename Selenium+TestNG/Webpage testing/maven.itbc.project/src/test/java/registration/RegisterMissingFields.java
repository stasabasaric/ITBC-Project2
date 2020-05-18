package registration;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import maven.itbc.project.Registration;

public class RegisterMissingFields extends BaseClass {

	//Testing if user can be registered without filling all mandatory fields in the form registration

	String successURL = "https://sandbox.2checkout.com/sandbox/home/dashboard"; //success registration

	@BeforeClass
	public void user() {
		Registration.openSignUpPage(wd);

	}

	@BeforeMethod
	public void refresh() {
		wd.navigate().refresh();
	}

	@Test
	public void MissingFieldsTest1() {
		// the method does not select any option for the last field (drop down menu:
		// About yourself)
		Registration user = new Registration(wd, "stasa222", "stasa222@gmail.com", "Stasa123", "Stasa123", 0);
		Assert.assertNotEquals(successURL, user.submit(wd));

	}

	@Test
	public void MissingFieldsTest2() {

		// if the first option is selected in drop down menu, a new field appears
		// requesting the website address
		// for the window popup (requesting the web address, try without typing anything
		// and just press enter)
		Registration user = new Registration(wd, "stasa222", "stasa222@gmail.com", "Stasa123", "Stasa123", 1);
		Assert.assertNotEquals(successURL, user.submit(wd));
	}

	@Test
	public void MissingFieldsTest3() {

		// confirm password field missing
		Registration user = new Registration(wd, "stasa222", "stasa222@gmail.com", "Stasa123", "", 2);
		Assert.assertNotEquals(successURL, user.submit(wd));
	}

	@Test
	public void MissingFieldsTest4() {

		// password field missing
		Registration user = new Registration(wd, "stasa222", "stasa222@gmail.com", "", "Stasa123", 3);
		Assert.assertNotEquals(successURL, user.submit(wd));
	}

	@Test
	public void MissingFieldsTest5() {

		// email field missing
		Registration user = new Registration(wd, "stasa222", "", "Stasa123", "Stasa123", 4);
		Assert.assertNotEquals(successURL, user.submit(wd));
	}

	@Test
	public void MissingFieldsTest6() {

		// username field missing
		Registration user = new Registration(wd, "", "stasa222@gmail.com", "Stasa123", "Stasa123", 5);
		Assert.assertNotEquals(successURL, user.submit(wd));
	}
}
