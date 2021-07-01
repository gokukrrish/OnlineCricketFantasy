package testCode;



import utilities.ExcelWrite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.FirstPage;

/*
 *This class to create test base for all test cases
 * 
 * @author Sravanan, Arumugam
 */
public class testBase {
	// "driver" is an instance for WebDriver Class
	static WebDriver driver = null;
		
		
	//"ELObj" is an instance for ExcelWirte Class
	static ExcelWrite ELObj = null;
	
	/*
	 * The "setUp", which is before class method is to make
	 * default set up like driver initialization and maximize
	 * browser and initialize ELObj
	 * 
	 */
	public void setUP(String bURL) {
		 System.setProperty("webdriver.chrome.driver","C:\\TestingJar\\chromedriver_win32\\chromedriver.exe");
		 //System.setProperty("webdriver.chrome.driver","G:\\IT_Software\\chromedriver_win32\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get(bURL);
		 PageFactory.initElements(driver,FirstPage.class);
		 ELObj = new ExcelWrite();
		 new FirstPage(driver);
	}
	
	public void toCloseWD() {
		 driver.close();
	}

}
