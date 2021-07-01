package pageObjects;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/*
 *This class is for accessing "Current & Future Matches" page's
 * webelement in base URL
 *
 *@author Saravanan, Arumgam
 */
public class CFSeries {
	
	// "driver" is private instance for WebDriver Class
	private WebDriver driver = null;
			
	//"builder" is private instance for Actions Class
	private Actions builder = null;

	//"elements" is public instance for List of "Series Name" web elements
	@FindBys(@FindBy(how=How.XPATH, using="//span[@class='text-black']"))
	public List<WebElement> elements;
	
	//"squads" is public instance for "Squads" tab
	@FindBy(how=How.XPATH, using="//a[@title='Squads']")
	public WebElement squads;
	
	//"sNme" is public instance for "Series Name"
	@FindBy(how=How.XPATH, using="//h1")
	public WebElement sNme;
	
	//"sMnth"  is public instance for "Series Month"
	@FindBy(how=How.XPATH, using="//div[@class='text-gray']")
	public WebElement sMnth;
	
	//"tSqdNme" is public instance for total squads and names
	@FindBys(@FindBy(how=How.XPATH, using="//div[@class='cb-col-100 cb-col cb-sqd-lst']/div[@class='cb-col-33 cb-col']"))	
	public List<WebElement> tSqdNme;
	
	//"sqdPlyrs" is instance for List of player in each squad
	@FindBys(@FindBy(how=How.XPATH, using="//div[@class='cb-col-67 cb-col']"))
	public List<WebElement> sqdPlyrs;
	

	/*
	 * The constructor  "Current & Future Matches" does initialize the webdriver and
	 * action class objects, initialize the PageFactory for this
	 * web page
	 * 
	 * @param driver - Receives webdriver object
	 */
	public CFSeries(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * To get and send squads details
	 * 
	 * @param i - To choose the options
	 * @param j - Index for WebElement List
	 * 
	 * @return <Any> - Returns value based on value it processed
	 */
	@SuppressWarnings("unchecked")
	public <Any> Any toSendDetails( char i, int j) {
		switch(i) {
		case 'a': squads.click(); break;
		case 'b': {
			String toClick = "//span[@class='text-black'][text()='"+elements.get(j).getText()+"']";
			builder.moveToElement(driver.findElement(By.xpath(toClick))).click().build().perform();
			//driver.findElement(By.xpath(toClick)).click(); break;
		}
		case 'c': return (Any)((String)sNme.getText()); 
		case 'd': return (Any)((String)sMnth.getText()); 
		case 'e':{
			try {
			return (Any)((String)tSqdNme.get(j).getText());
			}
			catch(IndexOutOfBoundsException e) {
				return (Any)((Boolean)true);
			}
		}
		case 'f': return (Any)((String)sqdPlyrs.get(j).getText());
		case 'g': return (Any)((Integer)elements.size());
		case 'h': return (Any)((Integer)tSqdNme.size());
		}
		return null;
	}
}
