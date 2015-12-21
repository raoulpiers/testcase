package Nexo.FindElementBy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class FindElement {
	public static void main(String[] args) throws InterruptedException {
		// Create a new instance of the html unit driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
		WebDriver driver = new FirefoxDriver();

		// And now use this to visit Google
		driver.get("https://ordsap202.beheer.lan:44310/nwbc/?sap-nwbc-node=root&sap-client=100&sap-theme=sap_corbu");

		//Find + insert username
		WebElement username = driver.findElement(By.id("sap-user"));
		username.click();
		username.sendKeys("mme21011");

		//Find + clear + insert password
		WebElement password = driver.findElement(By.id("sap-password"));
		password.click();
		password.clear();
		password.sendKeys("Greenpeace_1985");

		// pushing Logon button
		WebElement button = driver.findElement(By.id("LOGON_BUTTON"));
		button.click();
		
		//check if already logged in
		boolean check = driver.getPageSource().contains("U bent in het systeem al aangemeld met de volgende sessies:");
		if(check){
			driver.findElement(By.id("SESSION_QUERY_CONTINUE_BUTTON")).click();
		}
		//menu link
		driver.findElement(By.xpath("//span[text()='Resourceplanning']")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//sub menu link
		driver.findElement(By.id("WD51")).click();
		//driver.findElement(By.xpath("//span/a[text()='Order buitendienst creëren']")).click(); // ID = WD51

/*
 * Still needs to be added:
 * DROPDOWN "SrtAanvr:" input field id = WD017E (for button WD017E-btn)
 */
		WebElement checkName = driver.findElement(By.id("WD018D"));
		String checkNameStr = checkName.getAttribute("value");
		Assert.assertEquals("Mekmassi, Jalal", checkNameStr);

/*
 * Still needs to be added:
 * DROPDOWN "ID aanvr. BA:" input field id = WD0195 (for button WD0195-btn)
 */
			
		WebElement checkCountry = driver.findElement(By.id("WD01BE"));
		String checkCountryStr = checkCountry.getAttribute("value");
		Assert.assertEquals("Nederland", checkCountryStr);

		WebElement orderName = driver.findElement(By.id("WD01D4")); //id input field "Naam order:"
		orderName.click();
		orderName.sendKeys("opdrachtomschrijving/referentie");

/*
 * Still needs to be added:
 * DROPDOWN "Verantw. RM-org.:" input field id = WD0201 (for button WD0201-btn)
 */		
		
		//Click checkbox "Registratieve aanvr." + check if it is not selected already
		if ( !driver.findElement(By.id("WD023B-img")).isSelected() )
		{
		     driver.findElement(By.id("WD023B-img")).click();
		}
		
/*
 * KLANTDETAILS  HEADER
 */

		//Klantnaam:
		WebElement customerName = driver.findElement(By.id("WD025E")); //id of input field "Klantnaam:"
		customerName.click();
		customerName.sendKeys("FA28 GAT 2");

//!!!!!		
		//Inzetverantwoordelijke:
		WebElement responsible = driver.findElement(By.xpath(".//*[@id='WD0279' and contains(@title, 'Inzetverantwoordelijke')]"));
		//WebElement responsible = driver.findElement(By.id("WD0279")); //id of input field "Inzetverantwoordelijke:"
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
		WebElement cs2 = driver.findElement(By.id("WD0285")); //id of input field "Inzetverantwoordelijke:"
		cs2.click();
		cs2.sendKeys("734-10"); //mantelcontract // nummer moet bestaan kan geen dummy tekst doen.
		
		
		WebElement rol = driver.findElement(By.id("WD035A")); //click tab "Rollen"
		rol.click();
		
/*
 * ROLLEN TAB
 */
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

/*
 * TIJDREGELS HEADER
 */
		
		//Add new "Tijdregels"
		WebElement addNewTimeRule = driver.findElement(By.id("WD0536")); //click button "Nieuw"
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

/*
 * VEREISTE COMPETENTIES HEADER
 */
		//Find + clear + "Talen" and insert skill
		WebElement skill = driver.findElement(By.id("WD0639"));
		skill.click();
		skill.clear();
		skill.sendKeys("Nederlands");
/*
 * Still needs to be added:
 * DROPDOWN "Jobtekst:" input field id = WD063F (for button WD063F-btn)
 */

//mist dropdowns om te saven
		//Send rol (click button)
//		WebElement sendRol = driver.findElement(By.id("WD03AE"));
//		sendRol.click();
		
		WebElement kandidaat = driver.findElement(By.id("WD0794-focus")); //click tab "Kandidaten"
		kandidaat.click();
/*
 * KANDIDATEN TAB
 */
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
		//WebElement saveRol = driver.findElement(By.id("WD0151")); 
		//saveRol.click();
/*
 * DETAIL ROL TAB
 */
		WebElement detailRolTab = driver.findElement(By.id("WD0806"));
		detailRolTab.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//Kan pas als er gesaved kan worden
		//change rol status
		//WebElement changeRolStatus = driver.findElement(By.id("WD0AF5"));
		//changeRolStatus.click();
		//changeRolStatus.sendKeys("Kand. Intern Aangebod.");

		//WebElement getOrderNr = driver.findElement(By.id("WD26DC"));
		//getOrderNr.getText();
		
		System.out.println("success");
		//driver.quit();
	}
}
