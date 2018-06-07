import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RunTest {


    public static void main(String[] args) {

        //set up chromedriver
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");

        // open mailinator
        driver.get("https://www.mailinator.com");

        //get a random email
        By b = By.xpath("/html/body/section[1]/div/div[3]/div[2]/div[2]/div[2]/h4[3]/span/a");
        String randomEmail = driver.findElement(b).getText();
        String username = randomEmail.substring(0, randomEmail.indexOf("@"));
        System.out.println(username);

        // need a random domain so that we can create new accounts on hunchme.com each time
        driver.get("https://www.freevaluator.com/domain-name-generator/random-domain-generator");
        By randomDomain = By.xpath("//*[@id='wrapper']/section[2]/div/div/div/div[2]/table/tbody/tr[1]/td[1]/a/strong");
        String randDomain = driver.findElement(randomDomain).getText();

        //fill in all hunchme.com sign up fields
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
        domainField.sendKeys("http://" + randDomain + ".com");

        By dropdown = By.xpath("//*[@id='estimatedMonthlyPageviews_signup']");
        WebElement dropdownMenu = driver.findElement(dropdown);
        Select ddown = new Select(dropdownMenu);
        ddown.selectByIndex(2);

        By signUpButton = By.xpath("//*[@id='submit_signup']");
        WebElement button = driver.findElement(signUpButton);
        button.click();

        // wait a few seconds so that the activation email has time to be sent
        try {
            Thread.sleep(5000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        driver.get("https://www.mailinator.com/v2/inbox.jsp?zone=public&query=" + username);
        try {
            Thread.sleep(2000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        By inbox = By.xpath("//*[@id='inboxpane']/li");
        driver.findElement(inbox).click();

        //wait for message frame to show up
        try {
            Thread.sleep(2000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        // switch driver to message frame in email
        try {
            driver.switchTo().frame("msg_body");
        } catch (Exception e) {
            System.out.println("Unable to open message body from inbox");
        }

        //click activation link
        try {
            By activateButton = By.xpath("/html/body/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td/a");
            driver.findElement(activateButton).click();
        } catch (Exception e) {
            System.out.println("Unable to click activation link on email");
        }

        try {
            Thread.sleep(5000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        try {
            driver.findElement(By.xpath("//*[@id='setAdunitsBtn']")).click();
        } catch (Exception e) {
            System.out.println("Not able to click 'Create' button");
        }
        try {
            Thread.sleep(5000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        try {
            driver.findElement(By.xpath("//*[@id='logoutTab']/a")).click();
        } catch (Exception e) {
            System.out.println("unable to log out");
        }

        //close Chrome
        System.out.println("task completed!");
        driver.close();

    }

}