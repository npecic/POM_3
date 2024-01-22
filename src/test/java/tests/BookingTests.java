package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pom_classes.BookingHomePage;


import java.io.IOException;


public class BookingTests extends BaseTest {

    String TEST_DATA = "src/test/test_data/Booking.xlsx";

    @BeforeMethod
    @Parameters({"SHEET", "TC_ID"})
    public void setup(String SHEET, String TC_ID) throws IOException {
        baseSetUP("CHROME", "99", 5);
        loadTestData(TEST_DATA,SHEET, TC_ID, "1");
    }

    @AfterMethod
    public void tearDown() {
        baseTearDown();
    }

    @Test
    @Parameters({"TC_ID"})
    public void booking(String TC_ID) throws InterruptedException, IOException {

        mergeTestData(TEST_DATA,"ChildData", "Children");
        for (int i = 1;i <= data.get("Children").split(",").length;i++){
            mergeTestData(TEST_DATA,"AdditionalData", "Data_"+i,i);

        }

        BookingHomePage bookingHomePage = new BookingHomePage(driver);

        bookingHomePage.setWhereAreYouGoing(data.get("Location"));
        bookingHomePage.checkInCheckOut(data);
        bookingHomePage.addQuests(data);
        bookingHomePage.search();
        bookingHomePage.clicks();


        /*
        Assertation
         */


        String adultsCount = bookingHomePage.getAdultsCount();
        String childrenCount = bookingHomePage.getChildrenCount();
        String roomCount = bookingHomePage.getRoomCount();


        Assert.assertEquals(adultsCount, data.get("adultsCount")); // Replace "3" with the expected value
        Assert.assertEquals(childrenCount, data.get("childrenCount")); // Replace "2" with the expected value
        Assert.assertEquals(roomCount, data.get("roomCount")); // Replace "2" with the expected value
    }
}



