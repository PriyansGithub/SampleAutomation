package driverManagers;

public class DriverManagerFactory {

    public static DriverManager getManager(String type) {

        DriverManager driverManager;
        type= type.toUpperCase();
        switch (type) {
            case "CHROME":
                driverManager = new ChromeDriverManager();
                break;
            case "EDGE":
                driverManager = new EdgeDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;

    }
}
