package nl.ordina.lesson.webbasedSap;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;

public class SourcingLogin implements SourcingInterface {

	private String url;
	String username;
	String password;

	public SourcingLogin(String url) {
		this.url = url;

	}

	@Override
	public void test(String url, WebDriver driver) throws Exception {
		System.out.println("Running");
	}

	@Test
	public void setLoginCredentials(String newUsername, String newPassword) {
		this.username = newUsername;
		this.password = newPassword;
	}

	@Test
	public void getSourcing(String username, String password, WebDriver driver) {
		WebElement foundUsername = driver.findElement(By.id("sap-user"));
		foundUsername.click();
		foundUsername.sendKeys(username);
		WebElement foundPassword = driver.findElement(By.id("sap-password"));
		foundPassword.click();
		foundPassword.clear();
		foundPassword.sendKeys(password); // used sendkeys("greenpeace")
		return;
	}

	@Test
	public void continueButton(WebDriver driver) {

		WebElement button = driver.findElement(By.id("LOGON_BUTTON"));
		button.click();
		try {
			// pushing Logon button
			WebElement continueButton = driver.findElement(By.id("SESSION_QUERY_CONTINUE_BUTTON"));
			if (continueButton != null) {
				continueButton.click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			clickOnResourcePlanningButton(driver);
		}

	}
	// driver.get(System.getProperty("login.url"));

	@Test
	public void clickOnResourcePlanningButton(WebDriver driver) {
		try {
			WebElement resourcePlanningButton = driver.findElement(By.cssSelector("#ul_nav_1 li:nth-child(4) a"));
			resourcePlanningButton.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

		} catch (Exception e) {
			WebElement resourcePlanningButton = driver.findElement(By.xpath("//span[text()='Resourceplanning']"));
			resourcePlanningButton.isDisplayed();
			resourcePlanningButton.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			System.out.println("exception");
		}
	}

	@Test
	public void clickOnCreateFSRequest(WebDriver driver) {
		// webdriver pushing button CreateFSRequest
		WebElement CreateFSRequest = driver.findElement(By.id("WD51"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		CreateFSRequest.click();
	}

	@Test
	public void detailRequest(WebDriver driver) {
		WebElement entryTest = driver.findElement(By.id("WD01D4"));
		entryTest.clear();
		entryTest.click();
		entryTest.sendKeys("Naam order");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Click checkbox "Registratieve aanvr." + check if it is not selected
		// already
		if (!driver.findElement(By.id("WD023B-img")).isSelected()) {
			driver.findElement(By.id("WD023B-img")).click();
		}
	}

	@Test
	public void clientDetails(WebDriver driver) {
		WebElement customerName = driver.findElement(By.id("WD025E")); 
		customerName.click();
		customerName.sendKeys("Naam van klant");

		//Inzetverantwoordelijke:
		//WebElement responsible = driver.findElement(By.xpath(".//*[@id='WD0279' and contains(@title, 'Inzetverantwoordelijke')]"));
		WebElement responsible = driver.findElement(By.id("WD0279")); //id of input field "Inzetverantwoordelijke:"
		responsible.click();
		//!! doesn't always insert a String in the input field
		responsible.sendKeys("verantwoordelijke");
		
		//CS (klantorder/interneorder/kostenplaats)
		//WebElement cs = driver.findElement(By.xpath(".//*[@id='WD0280-img']"));
		WebElement cs = driver.findElement(By.id("WD0280-img")); //select radio(img) button "CS" 
		//!!!!! Check if the img is already selected. I am not sure if this works as it should!
		if(!cs.isSelected()){
			cs.click();
		}
		//id of input field "Inzetverantwoordelijke:"
		WebElement cs2 = driver.findElement(By.id("WD0285")); 
		cs2.click();
		//mantelcontract // value must excist can not be dummy text.
		cs2.sendKeys("734-10");  

		System.out.println("Page title is: " + driver.getTitle());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*
 * "ROL" tab
 */
	
	@Test
	public void clickOnRolTab(WebDriver driver) {
		WebElement rol = driver.findElement(By.id("WD035A")); //click tab "Rollen"
		rol.click();
	}
	
	@Test
	public void rolDetails(WebDriver driver) {
		//"Omschrijving rol:"
		WebElement rolDescription = driver.findElement(By.id("WD0444")); //id of input field "Omschrijving rol:"
		rolDescription.click();
		rolDescription.sendKeys("Dit is een omschrijving van de rol");

		//"Rolstandplaats:"
		WebElement rolPlace = driver.findElement(By.id("WD0453")); //id of input field "Rolstandplaats:"
		rolPlace.click();
		rolPlace.sendKeys("standplaats");
		
		//"AI-rolnaam:"
		WebElement rolNameAI = driver.findElement(By.id("WD0511")); //id of input field "AI-rolnaam:"
		rolNameAI.click();
		rolNameAI.sendKeys("rolnaam fieldmanager");
	}
	
	public void timeRules(WebDriver driver) {
		//Add new "Tijdregels"
		//click button "Nieuw"
		WebElement addNewTimeRule = driver.findElement(By.id("WD0536")); 
		addNewTimeRule.click();
		
		//Begindatum regel1
		WebElement startDate = driver.findElement(By.id("WD07BC"));
		startDate.click();
		startDate.sendKeys("02.12.2015");

		//Einddatum regel1
		WebElement endDate = driver.findElement(By.id("WD07BE"));
		endDate.click();
		endDate.sendKeys("03.12.2015");
		
		//check if hours are correct
		WebElement checkHour = driver.findElement(By.id("WD07C1"));
		String checkHourStr = checkHour.getAttribute("value");
		Assert.assertEquals("8,00", checkHourStr);
	}

	public void requiredCompetences(WebDriver driver) {
		//Find + clear + "Talen" and insert skill
		WebElement skill = driver.findElement(By.id("WD0639"));
		skill.click();
		skill.clear();
		skill.sendKeys("Nederlands");
		/*
		* Still needs to be added:
		* DROPDOWN "Jobtekst:" input field id = WD063F (for button WD063F-btn)
		*/
		
		//Missing the dropdown to be able to save
		//Send rol (click button)
		WebElement sendRol = driver.findElement(By.id("WD03AE"));
		sendRol.click();
	}
	
	@Test
	public void clickOnCandidatesTab(WebDriver driver) {
		WebElement kandidaat = driver.findElement(By.id("WD0794-focus")); //click tab "Kandidaten"
		kandidaat.click();
	}

	public void addCandidates(WebDriver driver) {
		WebElement addEmployee = driver.findElement(By.id("WD0806")); //click "Toevoegen"
		addEmployee.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//!!!! Let op: mits de aangeboden kandidaat een externe kandidaat is, moet je tevens bij het veld BU t.b.v. 
//!!!! inzetcontract de correcte Business Unit selecteren
		
		//Insert details
		//WD0879	//WD0892 WD0892 //WD0894
		//WebElement addDetailsEmp = driver.findElement(By.xpath(".//*[@id='WD0879']/span/input"));
		WebElement addDetailsEmp = driver.findElement(By.id("WD0894"));
		addDetailsEmp.click();
		addDetailsEmp.sendKeys("Naam"); // zie orderlijst????

//mist dropdowns om te saven
		//Save
		WebElement saveRol = driver.findElement(By.id("WD0151")); 
		saveRol.click();
	}

	@Override
	public String getUrl() {
		return url;
	}

}
/*
 * 
 * 
 *
 * driver.findElement(By.cssSelector(
 * "input[type=’password’][name=’sap-password’]")).sendKeys("Greenpeace_1985");
 * //driver.findElement(By.cssSelector(
 * "input#sap-password.urEdf2TxRadius.urEdf2TxtEnbl.urBorderBox")).sendKeys(
 * "Greenpeace_1985"); //
 * driver.findElement(By.className("urEdf2Whl")).sendKeys("Greenpeace_1985");
 * 
 * 
 * 
 * 
 * 
 * 
 * WebElement element2 = driver.findElement(By.cssSelector(
 * "#ul_nav_1 li:nth-child(4) a")); element2.click();
 * 
 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 * driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
 * 
 * // webdriver pushing button CreateFSRequest WebElement CreateFSRequest=
 * driver.findElement(By.id("WD51")); CreateFSRequest.click(); WebElement
 * entryTest = driver.findElement(By.id("WD01D1")); entryTest.clear();
 * entryTest.click(); entryTest.sendKeys("Invoer gegevens");
 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 * System.out.println("Page title is: " + driver.getTitle());
 * Thread.sleep(10000); driver.quit();
 * 
 * 
 * 
 */
