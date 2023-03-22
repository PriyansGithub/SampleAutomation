package driverManagers;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class EdgeDriverManager extends DriverManager {

    private EdgeDriverService chService;

    @Override
    public void startService() {
        if (null == chService) {
            try {
                chService = new EdgeDriverService.Builder()
                        .usingDriverExecutable(new File("EdgeDriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.edge();
        EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(chService, capabilities);
    }

}
