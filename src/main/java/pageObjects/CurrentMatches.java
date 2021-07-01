package pageObjects;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/*
 *This class is for accessing "Current Matches" page's webelement in base URL
 *
 *@author Saravanan, Arumgam
 */
public class CurrentMatches {
	
	//"scorecard" is an instance for "Scorecard" 
	@FindBy(how=How.XPATH, using="//nav[@class='cb-nav-bar']/a[text()='Scorecard']")
	private WebElement scorecard;
	
	// "driver" is private instance for WebDriver Class
	private WebDriver driver = null;
				
	//"builder" is private instance for Actions Class
	private Actions builder = null;
	
	//Current Innings Variable
	String innings = null;
	
	/*
	 * The constructor  "Current Matches" does initialize the webdriver and
	 * action class objects, initialize the PageFactory for this
	 * web page
	 * 
	 * @param driver - Receives webdriver object
	 */
	public CurrentMatches(WebDriver driver, String curMatch) {
		this.driver = driver;
		builder = new Actions(driver);
		PageFactory.initElements(driver, this);
		builder.moveToElement(driver.findElement(By.xpath("//h3/a[@title='"+curMatch+"']"))).click().build().perform();
		builder.moveToElement(scorecard).click().build().perform();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		innings = driver.findElement(By.xpath("//div[@class='cb-col cb-col-67 cb-scrd-lft-col html-refresh ng-isolate-scope']/div[2]")).getAttribute("id");
	}
	
	/*
	 * To get and send squads details
	 * 
	 * @param i - To choose the options

	 * 
	 * @return <Any> - Returns value based on value it processed
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <Any> Any toSendDetails( char i) {
		switch(i) {
			case 'a': return (Any)((String)driver.findElement(By.xpath("//h1")).getText());
			case 'b': return (Any)((String)driver.findElement(By.xpath("//div[@class='cb-col cb-col-67 cb-scrd-lft-col html-refresh ng-isolate-scope']/div[1]")).getText());
			case 'c': return (Any)((String)driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div/span[1]")).getText());
			case 'd': return (Any)((String)driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div/span[2]")).getText());
			case 'e': return (Any)((List)driver.findElements(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col cb-col-27 ']/a")));
			case 'f': return (Any)((List)driver.findElements(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col cb-col-27 text-bold']/a")));
			case 'g': return (Any)((String)driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col cb-col-8 text-bold cb-text-black text-right']")).getText());
			case 'h': return (Any)((String)driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col cb-col-8 text-bold text-black text-right']")).getText());
			case 'i': return (Any)((String)driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[text()='Extras']/following-sibling::div[@class='cb-col-32 cb-col']")).getText());
			case 'j': return (Any)((String)driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[text()='Total']/following-sibling::div[@class='cb-col-32 cb-col']")).getText());
			case 'k': return (Any)((String)driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col cb-col-27']")).getText());
			case 'l': return (Any)((String)driver.findElement(By.xpath("//div[@id='"+innings+"']/div[@class='cb-col cb-col-100 cb-col-rt cb-font-13']")).getText());
			case 'm':  return (Any)((List)driver.findElements(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms ']"
					+ "/div[@class='cb-col cb-col-40']/a")));
		}
		return null;
	}
	
	/*
	 * To get and send current match details
	 * 
	 * @param i - To choose the options
	 * @param j - Index for WebElement List
	 * 
	 * @return String - Returns value
	 */
	public String toSendDetails( char i, int j) {
		switch(i) {
			case 'a': return driver.findElement(By.xpath("//div[@class='cb-nav-subhdr cb-font-12']/a["+(j+1)+"]")).getAttribute("title");
			case 'b': return driver.findElement(By.xpath("//div[@class='cb-nav-subhdr cb-font-12']/a["+(j+1)+"]")).getAttribute("href");
			case 'c': return driver.findElement(By.xpath("//div[@class='cb-nav-subhdr cb-font-12']/span[4]/span["+(j+1)+"]")).getText();
			case 'd': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms']"
					+ "/div[@class='cb-col-73 cb-col']/a["+j+"]")).getText()+",";
		}
		
		return null;
	}
	
	/*
	 * To get and send current match details
	 * 
	 * @param  i - Index for choosing
	 * @param search - Player's Name
	 */
	public String toSendDetails( char i,String search) {
		switch(i) {
			case 'a': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div"
					+ "[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 ']/a[contains((text()),'"
					+search+"')]/parent::div/following-sibling::div[1]/span")).getText();
			case 'b': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div"
					+ "[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 text-bold']/a[contains((text()),'"
					+search+"')]/parent::div/following-sibling::div[1]/span")).getText();
		}
		return null;
	}
	
	/*
	 * To get and send current match details
	 * 
	 *  @param  i - Index for choosing
	 *  @param search - Webelement for player
	 */
	public String toSendDetails( char i,WebElement search) {
		switch(i) {
			case 'a': return driver.findElement(By.xpath("//div[@id='"+innings+"']"
					+ "/div/div[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 ']"
					+ "/a[@title='"+search.getAttribute("title")+"']")).getText();
			case 'b': return search.getAttribute("href");
			case 'c': return driver.findElement(By.xpath("//div[@id='"+innings+"']"
					+ "/div/div[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 text-bold']"
					+ "/a[@title='"+search.getAttribute("title")+"']")).getText();
			case 'd': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms ']/div[@class='cb-col cb-col-40']"
					+ "/a[@title='"+search.getAttribute("title")+"']")).getText();
		}
		return null;
	}
	
	/*
	 * To get and send current match details
	 * 
	 *  @param  i - Index for choosing
	 *  @param search - Player's Name
	 *  @param j - Index for WebElement List
	 */
	public String toSendDetails( char i,String search, int j) {
		switch(i) {
		case 'a': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div"
				+ "[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 ']"
				+ "/a[contains((text()),'"+search+"')]/parent::div/following-sibling::div["+j+"]")).getText();
		case 'b': return driver.findElement(By.xpath("//div[@id='"+innings+"']/div/div"
				+ "[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-27 text-bold']"
				+ "/a[contains((text()),'"+search+"')]/parent::div/following-sibling::div["+j+"]")).getText();
		case 'c': return driver.findElement(By.xpath(" //div[@id='"+innings+"']/div/div[@class='cb-col cb-col-100 cb-scrd-itms ']"
				+ "/div[@class='cb-col cb-col-40']/a[contains(text(),'"+search+"')]/parent::div/following-sibling::div["+j+"]")).getText();
		}
		return null;
	}
	
	
}
