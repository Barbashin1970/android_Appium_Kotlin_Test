import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginTest {

    private lateinit var driver: AppiumDriver<WebElement>

    @BeforeAll
    fun setUp() {
        val capabilities = DesiredCapabilities()
        capabilities.setCapability("platformName", "Android")
        capabilities.setCapability("deviceName", "Pixel_4_API_29")
        capabilities.setCapability("platformVersion", "10")
        capabilities.setCapability("automationName", "Appium")
        capabilities.setCapability("appPackage", "com.example.login")
        capabilities.setCapability("appActivity", ".ui.login.LoginActivity")
        capabilities.setCapability("app", "/Users/olegbarbashin/Downloads/AndroidTestKotlin/apks/login.apk")
        driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
    }

    @AfterAll
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun checkTitleScreen() {
        val screenTitle = driver.findElementById("com.example.login:id/toolbar")
        screenTitle.isDisplayed
    }

    @Test
    fun validRegistrationTest() {
        val emailInput = driver.findElementById("com.example.login:id/username")
        emailInput.isDisplayed
        emailInput.click()
        emailInput.sendKeys("admin@admin.ru")
        val passInput = driver.findElementById("com.example.login:id/password")
        passInput.isDisplayed
        passInput.click()
        passInput.sendKeys("1234")
        val signInButton = driver.findElementById("com.example.login:id/login")
        signInButton.isDisplayed
        signInButton.click()
        val successAuthText = driver.findElementByXPath("//*[contains(@text, 'Welcome ! user')]")
        successAuthText.isDisplayed
    }

    @Test
    fun validAuthTest() {
        val emailInput = driver.findElementById("com.example.login:id/username")
        emailInput.isDisplayed
        emailInput.click()
        emailInput.sendKeys("user@user.ru")
        val passInput = driver.findElementById("com.example.login:id/password")
        passInput.isDisplayed
        passInput.click()
        passInput.sendKeys("1111")
        val signInButton = driver.findElementById("com.example.login:id/login")
        signInButton.isDisplayed
        signInButton.click()
        val successAuthText = driver.findElementByXPath("//*[contains(@text, 'Login failed')]")
        successAuthText.isDisplayed
    }

}