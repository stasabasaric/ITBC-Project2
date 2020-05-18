package login;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import maven.itbc.project.LogIn;

public class LogUnregisteredUserTest extends BaseClass {

	@Test
	public void UnRegLogTest() {

		LogIn user = new LogIn(wd, "Stasa2105", "Stasa123");
		user.submit(wd);

		String inc = wd.findElement(By.xpath(m.get("INCORRECT_USER_XPATH"))).getText();
		Assert.assertTrue(inc.contains("Incorrect username"));

	}
}
