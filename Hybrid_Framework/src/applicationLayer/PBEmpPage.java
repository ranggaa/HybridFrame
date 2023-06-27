package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class PBEmpPage {
WebDriver driver;
public PBEmpPage(WebDriver driver)
{
	this.driver=driver;
}

@FindBy(xpath = "(//img)[8]")
WebElement Click_Employees;
@FindBy(xpath = "//input[@id='BtnNew']")
WebElement Click_newEmploye;
@FindBy(name = "txtUname")
WebElement EnterEmpname;
@FindBy(name = "txtLpwd")
WebElement EnterLoginPass;
@FindBy(name = "lst_Roles")
WebElement SelectRole;
@FindBy(name = "lst_Branch")
WebElement SelectBranch;
@FindBy(name = "BtnSubmit")
WebElement clicksubmit;
public boolean verify_Emp(String EmployeName,String Loginpass,String Role,String Branch) throws Throwable
{
	this.Click_Employees.click();
	this.Click_newEmploye.click();
	this.EnterEmpname.sendKeys(EmployeName);
	this.EnterLoginPass.sendKeys(Loginpass);
	new Select(this.SelectRole).selectByVisibleText(Role);
	new Select(this.SelectBranch).selectByVisibleText(Branch);
	this.clicksubmit.click();
	String Expected = driver.switchTo().alert().getText();
	Thread.sleep(3000);
	driver.switchTo().alert().accept();
	String Actual ="New Employer";
	if(Expected.toLowerCase().contains(Actual.toLowerCase()))
	{
		Reporter.log(Expected,true);
		return true;
	}
	else
	{
		Reporter.log("Unable to create new Employee",true);
		return false;
	}
}
}
