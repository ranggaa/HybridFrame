package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PBLoginPage {
//define repository
	@FindBy(name = "txtuId")
	WebElement ObjUser;
	@FindBy(name = "txtPword")
	WebElement Objpass;
	@FindBy(name = "login")
	WebElement ObjLogin;
	//write method for login
	public void verify_Login(String username,String password)
	{
		ObjUser.sendKeys(username);
		Objpass.sendKeys(password);
		ObjLogin.click();
	}
}
