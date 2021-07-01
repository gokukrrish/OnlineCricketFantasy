package pageObjects;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/*
 *This class is for accessing "First" page's webelement in base URL
 *
 *@author Saravanan, Arumgam
 */
public class FirstPage {
	// "driver" is an instance for WebDriver Class
	WebDriver driver = null;
		
	//"builder" is an instance for Actions Class
	Actions builder = null;
	
	//"schedule" is an instance for "Schedule" tab
	@FindBy(how=How.XPATH, using="//a[text()='Schedule']")
	private static WebElement schedule;
	
	//"cMatches" is an instance for "Current Matches" sub-tab
	@FindBy(how=How.XPATH, using="//a[text()='Current Matches']")
	private static WebElement cMatches;
	
	//"cfMatches" is an instance for "Current & Future Matches" sub-tab
	@FindBy(how=How.XPATH, using="//a[text()='Current & Future Series']")
	private static WebElement cfMatches;
	
	//"sArchive" is an instance for "Series Archive" sub-tab
	@FindBy(how=How.XPATH, using="//a[text()='Series Archive']")
	private static WebElement sArchive;
	
	/*
	 * The constructor  "HomePage" does initialize the webdriver and
	 * action class objects, initialize the PageFactory for this
	 * web page
	 * 
	 * @param driver - Receives webdriver object
	 */
	public FirstPage(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Clicks the corresponding link required based on parameter
	 * received
	 * 
	 * @param flag - To choose specific link for clicking
	 * 
	 * @throws NoSuchElementException if webdriver unable to find the required element
	 */
	public static void toClick(char flag) {
		switch(flag) {
			case '1': schedule.click(); break;
			case '2': cMatches.click(); break;
			case '3': cfMatches.click(); break;
			case '4': sArchive.click(); break;
		}
	}
}
