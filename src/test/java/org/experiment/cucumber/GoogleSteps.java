package org.experiment.cucumber;

import com.epam.driverProvider.WebDriverProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class GoogleSteps {

    WebDriverProvider webDriverProvider = WebDriverProvider.getWebDriverProvider();

    WebDriver webDriver;
    @Given("I have opened gmail application")
    public void openApplication()
    {
        webDriverProvider.intializeDriver("CHROME");
        webDriver = WebDriverProvider.getWebDriverProvider().get();
        webDriver.get("https://gmail.com");
        System.out.format("Thread ID - %2d",
                Thread.currentThread().getId());
    }

    @Given("I logged in as {string} and {string}")
    public void enterUserNameAndPassword(final String userName,final String password)
    {
        webDriver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys(userName);

    }

    @Then("I should be able to login successfully")
    public void iShouldBeAbleToLoginSuccessfully() {
    }

    @When("I click on login button")
    public void iClickOnLoginButton() {
    }
    @Then("I quit application")
    public void quit()
    {
        ((JavascriptExecutor) webDriver).executeScript("sauce:job-result= "+"passed");
        webDriver.quit();

    }
}
