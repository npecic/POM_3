package selenium_core;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(String type) {
        DriverManager driverManager;

        switch (type) {
            case "CHROME": {
                driverManager = new ChromeDriverManager();
            }

            break;
            case "FIREFOX": {
                driverManager = new FirefoxDriverManager();

            }
            break;
            default:{
                driverManager = new ChromeDriverManager();
        //        new Exception("No such browser");
            }
            break;
        }
        return driverManager;
    }
}





