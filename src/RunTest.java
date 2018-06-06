import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RunTest {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
//        System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
        //comment the above 2 lines and uncomment below 2 lines to use Chrome
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");

        String baseUrl = "https://www.mailinator.com";

        driver.get(baseUrl);

        By b = By.xpath("/html/body/section[1]/div/div[3]/div[2]/div[2]/div[2]/h4[3]/span/a");
        String randomEmail = driver.findElement(b).getText();
        String username = randomEmail.substring(0, randomEmail.indexOf("@"));
        System.out.println(username);

        driver.get("https://dashboard.hunchme.com/signup");

        By firstName = By.xpath("//*[@id='firstName_signup']");
        WebElement firstNameField = driver.findElement(firstName);
        firstNameField.sendKeys(username);

        By lastName = By.xpath("//*[@id='lastName_signup']");
        WebElement lastNameField = driver.findElement(lastName);
        lastNameField.sendKeys(username);

        By email = By.xpath("//*[@id='email_signup']");
        WebElement emailField = driver.findElement(email);
        emailField.sendKeys(randomEmail);

        By password = By.xpath("//*[@id='password_signup']");
        WebElement passwordField = driver.findElement(password);
        passwordField.sendKeys(username + "1234");

        By domain = By.xpath("//*[@id='domain_signup']");
        WebElement domainField = driver.findElement(domain);
        domainField.sendKeys("http://mailinator.com");

        By dropdown = By.xpath("//*[@id='estimatedMonthlyPageviews_signup']");
        WebElement dropdownMenu = driver.findElement(dropdown);
        Select ddown = new Select(dropdownMenu);
        ddown.selectByIndex(2);

        By signUpButton = By.xpath("//*[@id='submit_signup']");
        WebElement button = driver.findElement(signUpButton);
        button.click();

        driver.get("https://www.mailinator.com/v2/inbox.jsp?zone=public&query=" + username);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.querySelector(\"//*[@id='inboxpane']/li/div\").click()");

        driver.findElement(By.cssSelector("li.all_message-item")).click();
        //By inbox = By.xpath("//*[@id='inboxpane']/li/div");

        //driver.findElement(inbox).click();

//        List<WebElement> emails = driver.findElements(By.xpath("//*[@id='inboxpane']"));
//        System.out.println(emails);
//        for (WebElement e : emails){
//            e.click();
//        }
        //Activation email sent

//*[@id="row_cluemcgee-1528308146-23758"]/div

        //close Chrome
        //driver.close();

    }

}