/* Author : Sency Tharammel
 * Created Date = 25/10/2022
 * Validate the contact us page form  
 */
package i6;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Validate {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// Navigate to our website https://i6.io/ 
		driver.get("https://i6.io/");
		Thread.sleep(2000);
	    
		System.out.println("Navigated to the Home page "+driver.getCurrentUrl()+" page");
		
		//Click on About and Select the Contact Us button

		WebElement about = driver.findElement(By.linkText("About"));
		Actions actions = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		actions.moveToElement(about).click().build().perform();
		System.out.println("Clicked About and navigated to  "+driver.getCurrentUrl()+" page");
		Thread.sleep(5000);

        WebElement contactUs = driver.findElement(By.xpath("//nav[@role='navigation']//a[normalize-space()='Contact']"));
        actions.moveToElement(contactUs).click().build().perform();
		System.out.println("Clicked Contact Us and navigate to "+driver.getCurrentUrl()+" form page");
		
		/*Entering contact details
		First Name:  Test
		Last Name: Quality
		Company:  Leave Blank
		Email:  aaaa88888
		Phone Number:  Leave Blank
		Enquiry Type:  Careers
		Message:  Leave Blank
		Tick box for Email Opt-in */

		 WebElement firstName =driver.findElement(By.xpath("//*[@id=\"First-Name\"]"));
		 firstName.sendKeys("Test");
		 Thread.sleep(1000);
         WebElement lastName =driver.findElement(By.xpath("//*[@id=\"Last-Name\"]"));
         lastName.sendKeys("Quality");
         Thread.sleep(1000);
         WebElement company =driver.findElement(By.xpath("//*[@id=\"Company-2\"]"));
         company.sendKeys("");
         Thread.sleep(1000);
         WebElement emailId =driver.findElement(By.xpath("//*[@id=\"Email\"]"));
         emailId.sendKeys("aaaa88888");
         Thread.sleep(1000);
         WebElement phoneNumber =driver.findElement(By.xpath("//*[@id=\"Number-2\"]"));
         phoneNumber.sendKeys("");
         Thread.sleep(1000);
         WebElement enquiry =driver.findElement(By.xpath("//*[@id=\"Enquiry-2\"]"));
         Select enquiryListBox = new Select(enquiry);
         Thread.sleep(1000);
         enquiryListBox.selectByVisibleText("Careers");
         WebElement message =driver.findElement(By.xpath("//*[@id=\"Message-2\"]"));
         message.sendKeys("");
         Thread.sleep(1000);
         
         actions.sendKeys(Keys.ARROW_DOWN).perform();
         WebElement emailCheckbox = driver.findElement(By.id("Subscribe-to-Email"));
         boolean isSelected = emailCheckbox.isSelected();
         
         if(isSelected==false) {
        	 jse.executeScript("arguments[0].click();", emailCheckbox);
         }
         
         Thread.sleep(1000);
         String alertText="";
         actions.sendKeys(Keys.ARROW_DOWN).perform();
         
        // ----Click submit. Verify you can see the validation messages
         
         WebElement submit = driver.findElement(By.xpath("//*[@id=\"email-form\"]/input"));
         jse.executeScript("arguments[0].click();", submit);
         
        // driver.switchTo().alert(); //--- not working
         
        Thread.sleep(2000);
        try 
         { 
             Alert alert = driver.switchTo().alert();
             System.out.println(" Alert Present    "+alert.getText());
         }  
         catch (NoAlertPresentException e) 
         { 
            // System.out.println("No Alert Present");
         }  
        
        /* ------since validating message using alert is not working 
         * capture all alert message  from each element 
         * and print  */
     
         alertText = (String) jse.executeScript("return arguments[0].validationMessage",firstName);
         if(alertText!="") {
        	  System.out.println("Validating  FirtName failed  :  "+alertText); 
         }
         alertText = (String) jse.executeScript("return arguments[0].validationMessage",lastName);
         if(alertText!="") {
        	  System.out.println("Validating LastName failed   :  "+alertText); 
         }
         alertText = (String) jse.executeScript("return arguments[0].validationMessage",company);
         if(alertText!="") {
        	  System.out.println("Validating Company  failed  :  "+alertText); 
         }
         alertText = (String) jse.executeScript("return arguments[0].validationMessage",emailId);
         if(alertText!="") {
        	  System.out.println("Validating EmailID  failed   :  "+alertText); 
         }
         alertText = (String) jse.executeScript("return arguments[0].validationMessage",enquiry);
         if(alertText!="") {
        	  System.out.println("Validating Enquiry  failed  :  "+alertText); 
         }
         alertText = (String) jse.executeScript("return arguments[0].validationMessage",message);
         if(alertText!="") {
        	  System.out.println("Validating Message  failed  :  "+alertText); 
         }
         alertText = (String) jse.executeScript("return arguments[0].validationMessage",emailCheckbox);
         if(alertText!="") {
        	  System.out.println("Validating Email opt in Checkbox failed  :  "+alertText); 
         }
         
         alertText = (String) jse.executeScript("return arguments[0].validationMessage",submit);
         if(alertText!="") {
        	  System.out.println("Validating Submit Now Button failed :  "+alertText); 
         } 
         
         String expectedAlert ="sucess";
         //----- validating submit.
         if(alertText.toLowerCase().contains(expectedAlert.toLowerCase()))
			{
        	    System.out.println("Contact us Successfully submitted ");
			}else {
				System.out.println("There is a problem in Contact us ");
				
			}
		
		 Thread.sleep(5000);
		 driver.close();

	}

	}
