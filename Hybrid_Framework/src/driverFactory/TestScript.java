package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationLayer.PBEmpPage;
import applicationLayer.PBLoginPage;
import applicationLayer.PBLogoutPage;
import utilities.ExcelFileUtil;

public class TestScript {
WebDriver driver;
String inputpath ="./FileInput/EmpData.xlsx";
String outputpath ="./FileOutput/EmpResults.xlsx";
@BeforeTest
public void setUp()
{
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.get("http://primusbank.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	PBLoginPage login =PageFactory.initElements(driver, PBLoginPage.class);
	login.verify_Login("Admin", "Admin");
	
}
@Test
public void startTest() throws Throwable
{
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc =xl.rowCount("Emp");
	Reporter.log("No of rows are::"+rc,true);
	for(int i= 1;i<=rc;i++)
	{
		String EmployeName = xl.getCellData("Emp", i, 0);
		String LoginPass = xl.getCellData("Emp", i, 1);
		String Role = xl.getCellData("Emp", i, 2);
		String Branch = xl.getCellData("Emp", i, 3);
		PBEmpPage emp =PageFactory.initElements(driver, PBEmpPage.class);
		boolean res = 	emp.verify_Emp(EmployeName, LoginPass, Role, Branch);
		if(res)
		{
			xl.setCellData("Emp", i, 4, "Pass", outputpath);
		}
		else
		{
			xl.setCellData("Emp", i, 4, "Fail", outputpath);
		}
	}
	
}
@AfterTest
public void tearDown()
{
	PBLogoutPage logout =PageFactory.initElements(driver, PBLogoutPage.class);
	logout.verify_Logout();
	driver.quit();
}
}













