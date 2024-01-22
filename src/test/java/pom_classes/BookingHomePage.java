package pom_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class BookingHomePage extends BasePage {
    WebDriver driver;


    public BookingHomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//*[@aria-label='Dismiss sign-in info.']")
    WebElement clickClose;
    @FindBy(xpath = "//input[@name= 'ss']")
    WebElement whereAreYouGoing;
    @FindBy(xpath = "//div[@class= 'a1139161bf']")
    WebElement checkInCheckOut;
    @FindBy(xpath = "//div[@class='d358556c65']")
    List<WebElement> months;
    @FindBy(xpath = "//*[@id='calendar-searchboxdatepicker']/div/div[1]/button[contains(@class,'f073249358')]")
    WebElement nextArrow;
    @FindBy(css = "[type='submit']")
    WebElement submit;
    @FindBy(css = "[data-testid='occupancy-config']")
    WebElement quests;
    @FindBy(xpath = "//label[text()='Adults']/../..//button[2]")
    WebElement adultsAdd;
    @FindBy(xpath = "//label[text()='Adults']/../..//button[1]")
    WebElement adultsRemove;
    @FindBy(xpath = "//label[text()='Children']/../..//button[2]")
    WebElement childrenAdd;
    @FindBy(xpath = "//label[text()='Children']/../..//button[1]")
    WebElement childrenRemove;
    @FindBy(xpath = "//label[text()='Rooms']/../..//button[2]")
    WebElement roomsAdd;
    @FindBy(xpath = "//label[text()='Rooms']/../..//button[1]")
    WebElement roomsRemove;
    @FindBy(css = "[name='age']")
    List<WebElement> chrildrenAge;
    @FindBy(xpath = "//span[@class='e4adce92df' and contains(text(), 'See availability')]/..")
    WebElement property;
    @FindBy(xpath = "//button[@data-testid=\"occupancy-config\" and contains(@class, 'ada2387af8')]")
    WebElement datePick;



    public void setWhereAreYouGoing(String location){
        clickElement(clickClose);
        sendKeys(whereAreYouGoing,location,"into search field");
        clickElement(driver.findElement(By.xpath("//div[text()='"+location+"' and contains(@class, 'a3332d346a')]")),location);
    }

    public void clickDate(String month,String year, String day){
        while(true) {
            if (driver.findElements(By.xpath("//h3[(text() = '"+month+"') and text()='"+year+"']")).size() == 0) {
                clickElement( nextArrow, "Next month");
            }else {
                clickElement(driver.findElement(By.xpath("//h3[(text() = '"+month+"') and text()='"+year+"']/..//span[text() ='"+day+"']")), "Clicked "+month +" "+year+" "+day);
                break;
            }
        }
    }

    public void checkInCheckOut (Map<String,String> data){

        clickDate(data.get("checkInMonth"), data.get("checkInYear"),data.get("checkInDay"));
        clickDate(data.get("checkOutMonth"), data.get("checkOutYear"),data.get("checkOutDay"));

    }


    public void addQuests (Map<String, String> data) throws InterruptedException {
        clickElement(quests,"Guests");
        addAdultsNum(data.get("AdultsNum"));
        addChildrenNum(data);
//        clickElement(quests,"Guests");
        addRoomsNum(data.get("RoomsNum"));

    }
    public void addAdultsNum(String num){
        if(num.equalsIgnoreCase("1")) {
            clickElement(adultsRemove,"Removed Adults");
        }

        for(int i = 2; i<Integer.parseInt(num);i++) {
            clickElement(adultsAdd,"Clicked: Add Adults");

        }
    }
    public void addRoomsNum(String num){
        for(int i = 1; i<Integer.parseInt(num);i++) {
            clickElement(quests);
            clickElement(roomsAdd,"Clicked: Add Rooms");

        }
    }
    public void addChildrenNum(Map<String,String> data) throws InterruptedException {
        if(hasValue(data.get("Children"))){
        for(int i = 1; i<=data.get("Children").split(",").length;i++) {
            clickElement(childrenAdd, "Add Children");
            selectByValue(chrildrenAge.get(i - 1), data.get("Child_Age_" + i), " from child age select");
//            clickElement(quests);
        }

        }
    }
    public void search(){
        clickElement(submit,"Search");
    }
    public void clicks(){
        seeProperty();
        openDate();
    }
    public void seeProperty(){
        clickElement(property," on Property");
    }
    public void openDate(){
        clickElement(datePick," on date picker");
    }




    public String getAdultsCount() {
        return driver.findElement(By.xpath("//input[@id='group_adults']")).getAttribute("value");
    }

    public String getChildrenCount() {
        return driver.findElement(By.xpath("//input[@id='group_children']")).getAttribute("value");
    }

    public String getRoomCount() {
        return driver.findElement(By.xpath("//input[@id='no_rooms']")).getAttribute("value");
    }



}
