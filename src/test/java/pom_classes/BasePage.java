package pom_classes;



import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BasePage {
    WebDriver driver;

    // Constructor to initialize the WebDriver and PageFactory
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    int waitTime = 30;


    /**
     * Clicks on a WebElement
     */
    public void clickElement(WebElement element){
        element.click();
    }

    /**
     * Clicks on a WebElement with logging
     */
    public void clickElement(WebElement element, String log){
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(element));
            wdWait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();

            System.out.println("Clicked " + log);

        } catch(StaleElementReferenceException e){
            // Retry the click in case of a StaleElementReferenceException
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();

            System.out.println("Clicked " + log);
        }
    }

    /**
     * Sends keys to a WebElement with logging
     */
    public void sendKeys(WebElement element, String text, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(element));

            element.clear();
            element.sendKeys(text);
            System.out.println("Typed text: " + text+ " into " + log);
        } catch (StaleElementReferenceException e) {
            element.clear();
            element.sendKeys(text);
            
            System.out.println("Typed text: " + text+ " into " + log);
        }
    }

    /**
     * Retrieves text from a WebElement with logging
     */
    public String getText(WebElement element, String log){
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(element));

            System.out.println("Get text from: " + log);

            return element.getText();

        } catch(StaleElementReferenceException e){
            // Retry the getText in case of a StaleElementReferenceException
            System.out.println("Get text from: " + log);

            return element.getText();
        }
    }

    /**
     * Checks if a list of WebElements is present
     */
    public boolean isElementPresent(List<WebElement> elements){
        return elements.size() > 0;
    }

    public void selectByText(WebElement element, String text, String log){
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(element));
            wdWait.until(ExpectedConditions.elementToBeClickable(element));

            Select select = new Select(element);
            select.selectByVisibleText(text);

            System.out.println("Selected " + text + log);

        } catch(StaleElementReferenceException e){
            // Retry the select in case of a StaleElementReferenceException
            Select select = new Select(element);
            select.selectByVisibleText(text);

            System.out.println("Selected " + text + log);
        }
    }

    public void selectByValue(WebElement element, String text, String log){
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(element));
            wdWait.until(ExpectedConditions.elementToBeClickable(element));

            Select select = new Select(element);
            select.selectByValue(text);

            System.out.println("Selected " + text + log);

        } catch(StaleElementReferenceException e){
            // Retry the select in case of a StaleElementReferenceException
            Select select = new Select(element);
            select.selectByVisibleText(text);

            System.out.println("Selected " + text + log);
        }
    }

    public boolean hasValue(String text){
        try {
            return !text.equals("") || !text.equals(" ");
        }catch (Exception e){
            return false;
        }
    }
}
