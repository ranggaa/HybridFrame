package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PBLogoutPage {
@FindBy(xpath = "(//img)[4]")
WebElement ClickLogout;
public void verify_Logout()
{
	ClickLogout.click();
}
}
