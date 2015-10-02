package test.steps;


import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.NumberFormat;
import java.text.ParseException;

public class TruecarSteps {
    private WebDriver driver = new FirefoxDriver();
    private int difference;
    private int actualdifference;
    private int currentPrice;
    private int colorPrice;
    private int optionPrice;

    Actions action = new Actions(driver);


    @Given("^the homepage at \"([^\"]*)\" is displayed$")
    public void the_homepage_at_is_displayed(String url) throws InterruptedException {

        driver.get(url);
        Thread.sleep(8000);

    }

    @When("^the user enters \"([^\"]*)\" for the make$")
    public void the_user_enters_for_the_make(String carMake) {
        WebElement make = driver.findElement(By.id("s2id_home_select_make"));


        action.clickAndHold(make).sendKeys(carMake).sendKeys(Keys.RETURN).build().perform();
        action.release().perform();

    }

    @And("^selects \"([^\"]*)\" for the model$")
    public void selects_for_the_model(String carModel) {
        WebElement model = driver.findElement(By.id("s2id_home_select_model"));
        action.clickAndHold(model).sendKeys(carModel).sendKeys(Keys.RETURN).build().perform();
        action.release().perform();
    }

    @And("^enters \"([^\"]*)\" for the zip code$")
    public void enters_for_the_zip_code(String zipCcode) {
        WebElement zip = driver.findElement(By.id("home_zip"));
        action.clickAndHold(zip).sendKeys(Keys.chord(Keys.CONTROL, "a"), zipCcode).build().perform();
        action.release().perform();
        WebElement go = driver.findElement(By.id("home_cta"));
        action.clickAndHold(go).sendKeys(Keys.RETURN).build().perform();
        action.release().perform();
    }


    @Then("^the page should load with the correct car model$")
    public void the_page_should_load_with_the_correct_car_model() throws InterruptedException {

        Thread.sleep(11000);

        WebElement mmCheck = driver.findElement(By.id("config-header"));
        WebElement zipCheck = driver.findElement(By.className("zip-view"));
        boolean carCheck = mmCheck.getText().equalsIgnoreCase("2015 Lincoln MKS");
        boolean locCheck = zipCheck.getText().contains("90401");
        Assert.assertTrue(carCheck);
        Assert.assertTrue(locCheck);
    }

    @Given("^the trim has been updated to \"([^\"]*)\"$")
    public void the_trim_has_been_updated_to(String trim) throws ParseException, InterruptedException {
        WebElement style = driver.findElement(By.id("s2id_select_style"));
        action.clickAndHold(style).sendKeys(trim).sendKeys(Keys.RETURN).build().perform();
        Thread.sleep(10000);
        String price = driver.findElement(By.className("update-price")).getText();
        NumberFormat format = NumberFormat.getCurrencyInstance();
        Number number = format.parse(price);
        currentPrice = number.intValue();

    }

    @And("^the user updates the color to one that costs money$")
    public void the_user_updates_the_color_to_one_that_costs_money() throws InterruptedException, ParseException {
        Thread.sleep(7000);
        WebElement color = driver.findElement(By.id("edit_color"));
        action.click(color).build().perform();
        WebElement colorChange = driver.findElement(By.id("tc_option_9"));
        action.click(colorChange).build().perform();
        WebElement update = driver.findElement(By.cssSelector("span.button.b10.oo-continue"));
        action.click(update).build().perform();
        Thread.sleep(3000);
        String price = driver.findElement(By.className("update-price")).getText();
        NumberFormat format = NumberFormat.getCurrencyInstance();
        Number number = format.parse(price);
        colorPrice = number.intValue();

    }

    @Then("^the prices should update to reflect the cost of the color$")
    public void the_prices_should_update_to_reflect_the_cost_of_the_color() {
        actualdifference = colorPrice - currentPrice;
        difference = 451;
        Assert.assertEquals(difference, actualdifference);
        currentPrice = colorPrice;

    }

    @When("^the user adds a new option that costs money$")
    public void the_user_adds_a_new_option_that_costs_money() throws InterruptedException, ParseException {
        WebElement option = driver.findElement(By.id("edit_opts"));
        action.click(option).build().perform();
        WebElement optionChange = driver.findElement(By.id("tc_option_27"));
        action.click(optionChange).build().perform();
        WebElement update = driver.findElement(By.cssSelector("span.button.b10.oo-continue"));
        action.click(update).build().perform();
        Thread.sleep(7000);
        String oPrice = driver.findElement(By.className("update-price")).getText();
        NumberFormat format = NumberFormat.getCurrencyInstance();
        Number number = format.parse(oPrice);
        optionPrice = number.intValue();

    }

    @Then("^the price should update to reflect the cost of the option$")
    public void the_price_should_update_to_reflect_the_cost_of_the_option() throws InterruptedException {
        actualdifference = optionPrice - currentPrice;
        difference = 82;
        Assert.assertEquals(difference, actualdifference);
        Thread.sleep(8000);
        WebElement pricing = driver.findElement(By.cssSelector("a.button.b10.reg_cta.reg-action"));
        action.click(pricing).build().perform();
    }

    @When("^the user registers with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_user_registers_with_and(String firstname, String lastname) throws InterruptedException {
        Thread.sleep(7000);
        WebElement first = driver.findElement(By.id("given_name"));
        action.click(first).sendKeys(firstname).build().perform();
        WebElement last = driver.findElement(By.id("family_name"));
        action.click(last).sendKeys(lastname).build().perform();
        WebElement address = driver.findElement(By.id("street_address"));
        action.click(address).sendKeys("1234 Vine St").build().perform();
        WebElement phone = driver.findElement(By.id("phone_number"));
        action.click(phone).sendKeys("3102314567").build().perform();
        WebElement email = driver.findElement(By.id("email_address"));
        action.click(email).sendKeys("XZ@gmail.com").build().perform();
        WebElement getPrice = driver.findElement(By.cssSelector("button.button.b10.dealer-pricing"));
        action.click(getPrice).build().perform();
    }

    @And("^selects the cheapest dealer price$")
    public void selects_the_cheapest_dealer_price() throws InterruptedException {
        Thread.sleep(7000);
        WebElement checkedPrice = driver.findElement(By.id("dealers-0"));
        action.click(checkedPrice).build().perform();
        checkedPrice = driver.findElement(By.id("leadgen_dealers-0"));
        action.click(checkedPrice).build().perform();
        checkedPrice = driver.findElement(By.id("leadgen_dealers-1"));
        action.click(checkedPrice).build().perform();
        WebElement cheapClick = driver.findElement(By.id("dealers-2"));
        action.click(cheapClick).build().perform();
        WebElement next = driver.findElement(By.cssSelector("button.button.b10.get-cert"));
        action.click(next).build().perform();
    }

    @Then("^the dealer and car info should be output$")
    public void the_dealer_and_car_info_should_be_outputed() throws InterruptedException {
        Thread.sleep(10000);
        driver.switchTo().frame(driver.findElement(By.id("ucr_container")));
        String dealerName = driver.findElement(By.cssSelector("h2.dealer-name")).getText();
        dealerName= dealerName.substring(0, dealerName.length() - 7);
        String dealerInfo = driver.findElement(By.cssSelector("dt.contact-name")).getText();
        String finalPrice = driver.findElement(By.cssSelector("li.offer-pricing-price")).getText();
        System.out.println("Dealer Name: " + dealerName);
        System.out.println("Dealer Info: " + dealerInfo);
        System.out.println(finalPrice);
    }

    @After("@End")
    public void close_driver(){
        driver.quit();

    }
}
