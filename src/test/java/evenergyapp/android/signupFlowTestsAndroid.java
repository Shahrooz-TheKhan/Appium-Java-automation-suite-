package evenergyapp.android;


import evenergyapp.Setup.DataWriter;
import evenergyapp.Setup.TestDataGenerator;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.touch.WaitOptions;


import java.time.Duration;



import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class signupFlowTestsAndroid {
    private static AndroidDriver driver;
    private static TestDataGenerator dataGenerator = new TestDataGenerator(); //Created a class in Setup folder to register with randomized data

    @BeforeEach
    public void setupApp() throws InterruptedException, MalformedURLException {
        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
            options.setDeviceName("Pixel7");
            options.setApp(System.getProperty("user.dir") + "/apps/universal.apk");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            Thread.sleep(4000);
        } else {
            // Reinitialize or reset the app as needed for each test
            driver.quit(); // Close the current app session
            // Reinitialize the driver to launch the app again
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
            options.setDeviceName("Pixel7");
            options.setApp(System.getProperty("user.dir") + "/apps/universal.apk");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            Thread.sleep(4000);
        }
    }


    @AfterAll
    public static void closeAndroidApp() {
        if (driver != null) {
            driver.quit();
        }
    }
    private void tapByCoordinates(int x, int y) {
        new TouchAction<>(driver)
                .tap(PointOption.point(x, y))
                .perform();
    }

    private void scrollDown(int startX, int startY, int endX, int endY) {
        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    @Test
    public void navigateToSignupPage() {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-landing-createAccountInfo\"]"))
                .click();

    }


    @Test
    public void signupDetailsSuccesful() throws InterruptedException {
        String fullName = dataGenerator.generateFullName();
        String email = dataGenerator.generateEmail();
        String password = dataGenerator.generatePassword();

        String dataToSave = String.format("FullName: %s, Email: %s, Password: %s", fullName, email, password);
        DataWriter.writeToFile("src/test/testdata/signupData.txt", dataToSave);
        //saves used data to signupData.txt file for reference

        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-landing-createAccountInfo\"]"))
                .click();
        Thread.sleep(3000);
        //enter full name
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-username\"]"))
                .sendKeys(fullName);
        //enter email
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-email\"]"))
                .sendKeys(email);
        //enter pass
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-password\"]"))
                .sendKeys(password);
        //agree to terms and conditions
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"switch-register-privacy\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-register-continue\"]"))
                .click();
        Thread.sleep(3000);

        //assertions to verify redirected to next page in flow
        WebElement nextStepElement =
                driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"To smart charge, add details about your\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(nextStepElement, "Failed to navigate to the next step after signup.");
        assertTrue(nextStepElement.isDisplayed(), "Next step element is not displayed.");


    }

    @Test
    public void chargingLocation() throws InterruptedException {
        String fullName = dataGenerator.generateFullName();
        String email = dataGenerator.generateEmail();
        String password = dataGenerator.generatePassword();

        String dataToSave = String.format("FullName: %s, Email: %s, Password: %s", fullName, email, password);
        DataWriter.writeToFile("src/test/testdata/signupData.txt", dataToSave);

        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-landing-createAccountInfo\"]"))
                .click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-username\"]"))
                .sendKeys(fullName);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-email\"]"))
                .sendKeys(email);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-password\"]"))
                .sendKeys(password);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"switch-register-privacy\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-register-continue\"]"))
                .click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-welcome-continue\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"need-help-button\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]"))
                .click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("(//android.view.ViewGroup[@class=\"android.view.ViewGroup\"])[26]"))
                .click();
        Thread.sleep(2000);


        //assertions to verify redirected to next page in flow
        WebElement nextStepElement =
                (WebElement) driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Set your home address\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(nextStepElement, "Failed to navigate to the next step after location permissions.");
        assertTrue(nextStepElement.isDisplayed(), "Next step element is not displayed.");

    }

    @Test
    public void setHomeAddress() throws InterruptedException {
        String fullName = dataGenerator.generateFullName();
        String email = dataGenerator.generateEmail();
        String password = dataGenerator.generatePassword();

        String dataToSave = String.format("FullName: %s, Email: %s, Password: %s", fullName, email, password);
        DataWriter.writeToFile("src/test/testdata/signupData.txt", dataToSave);

        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-landing-createAccountInfo\"]"))
                .click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-username\"]"))
                .sendKeys(fullName);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-email\"]"))
                .sendKeys(email);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-password\"]"))
                .sendKeys(password);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"switch-register-privacy\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-register-continue\"]"))
                .click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-welcome-continue\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"need-help-button\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]"))
                .click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("(//android.view.ViewGroup[@class=\"android.view.ViewGroup\"])[26]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"address1-input-field\"]"))
                .sendKeys("123 Main st");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"city-input-field\"]"))
                .sendKeys("San Jose");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postalCode-input-field\"]"))
                .sendKeys("94088");
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"submit-form-button\"]"))
                .click();
        Thread.sleep(2000);


        //assertions to verify redirected to next page in flow
        WebElement nextStepElement =
                (WebElement) driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Your charger location and home address are set up\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(nextStepElement, "Failed to navigate to the next step after location permissions.");
        assertTrue(nextStepElement.isDisplayed(), "Next step element is not displayed.");

    }

    @Test
    public void connectYourCar() throws InterruptedException {
        String fullName = dataGenerator.generateFullName();
        String email = dataGenerator.generateEmail();
        String password = dataGenerator.generatePassword();

        String dataToSave = String.format("FullName: %s, Email: %s, Password: %s", fullName, email, password);
        DataWriter.writeToFile("src/test/testdata/signupData.txt", dataToSave);

        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-landing-createAccountInfo\"]"))
                .click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-username\"]"))
                .sendKeys(fullName);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-email\"]"))
                .sendKeys(email);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-password\"]"))
                .sendKeys(password);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"switch-register-privacy\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-register-continue\"]"))
                .click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-welcome-continue\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"need-help-button\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]"))
                .click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("(//android.view.ViewGroup[@class=\"android.view.ViewGroup\"])[26]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"address1-input-field\"]"))
                .sendKeys("123 Main st");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"city-input-field\"]"))
                .sendKeys("San Jose");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postalCode-input-field\"]"))
                .sendKeys("94088");
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"submit-form-button\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Continue\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("(//android.view.ViewGroup[@class=\"android.view.ViewGroup\"])[23]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"e-NV200-supplier-item\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"Acenta (40kWh)-supplier-item\"]"))
                .click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Continue\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(7000);
        //Needed to use tap command to get past web view blocking element, since elements could not be loaded into the page.
        tapByCoordinates(714, 2597);
        Thread.sleep(3000);
        tapByCoordinates(683, 1346);
        driver.switchTo().activeElement()
                .sendKeys("Johndoe@gmail.com");
        tapByCoordinates(666,1498);
        driver.switchTo().activeElement()
                .sendKeys("Secretpass123");
        tapByCoordinates(766, 2010);
        Thread.sleep(10000);
        //Needed to scroll down to see the button element, this command captures screen size
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        // this then calculates the bottom and top of the screen for the stert and end point of scrolling
        int startX = width / 2;  // middle of the screen
        int startY = (int) (height * 0.8);  // 80% from the top
        int endY = (int) (height * 0.2);  // 20% from the top
        scrollDown(startX, startY, startX,startY);
        tapByCoordinates(731, 2706);
        Thread.sleep(2000);

        //assertion to verify car setup is complete
        WebElement nextStepElement =
                (WebElement) driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Nice! We’ve set up your\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(nextStepElement, "Failed to navigate to the next step after location permissions.");
        assertTrue(nextStepElement.isDisplayed(), "Next step element is not displayed.");

    }

    @Test
    public void howYouCharge () throws InterruptedException {
        String fullName = dataGenerator.generateFullName();
        String email = dataGenerator.generateEmail();
        String password = dataGenerator.generatePassword();

        String dataToSave = String.format("FullName: %s, Email: %s, Password: %s", fullName, email, password);
        DataWriter.writeToFile("src/test/testdata/signupData.txt", dataToSave);

        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-landing-createAccountInfo\"]"))
                .click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-username\"]"))
                .sendKeys(fullName);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-email\"]"))
                .sendKeys(email);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-register-password\"]"))
                .sendKeys(password);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"switch-register-privacy\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-register-continue\"]"))
                .click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-welcome-continue\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"need-help-button\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]"))
                .click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath("(//android.view.ViewGroup[@class=\"android.view.ViewGroup\"])[26]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"address1-input-field\"]"))
                .sendKeys("123 Main st");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"city-input-field\"]"))
                .sendKeys("San Jose");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postalCode-input-field\"]"))
                .sendKeys("94088");
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"submit-form-button\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Continue\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("(//android.view.ViewGroup[@class=\"android.view.ViewGroup\"])[23]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"e-NV200-supplier-item\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"Acenta (40kWh)-supplier-item\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Continue\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(7000);
        tapByCoordinates(714, 2597);
        Thread.sleep(3000);
        tapByCoordinates(683, 1346);
        driver.switchTo().activeElement()
                .sendKeys("Johndoe@gmail.com");
        tapByCoordinates(666,1498);
        driver.switchTo().activeElement()
                .sendKeys("Secretpass123");
        tapByCoordinates(766, 2010);
        Thread.sleep(10000);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);
        scrollDown(startX, startY, startX,startY);
        tapByCoordinates(731, 2706);
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-carDetail-continue\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"text-chargerAtHome-homeCharger\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"text-chargerList-footerTitle\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"input-howYouCharge\"]"))
                .sendKeys("Tesla home");
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-howYouCharge-continue\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"text-energySetup-skip\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Skip\" and @class=\"android.widget.TextView\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Turn on\" and @class=\"android.widget.TextView\"]"))
                .click();

        //assertions to verify Home charger setup is complete
        WebElement nextStepElement =
                (WebElement) driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Plug in your car to charge\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(nextStepElement, "Failed to navigate to the next step after location permissions.");
        assertTrue(nextStepElement.isDisplayed(), "Next step element is not displayed.");

    }


}




