package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;
import java.util.Random;

public class FormPage {

    @FindBy(id = "inputFirstName3")
    private WebElement firstName;

    @FindBy(id = "inputLastName3")
    private WebElement lastName;

    @FindBy(id = "inputEmail3")
    private WebElement email;

    @FindBy(id = "inputAge3")
    private WebElement age;

    @FindBy(name = "gridRadiosSex")
    private List<WebElement> sex;

    @FindBy(name = "gridRadiosExperience")
    private List<WebElement> yearsOfExperience;

    @FindBy(id = "validator-message")
    private WebElement actualMessage;

    @FindBy(id = "gridCheckAutomationTester")
    private WebElement professionAutoTester;

    @FindBy(id = "selectContinents")
    private WebElement continents;

    @FindBy(css = "option[value=switch-commands]")
    private WebElement seleniumCommands1;

    @FindBy(css = "option[value=wait-commands]")
    private WebElement seleniumCommands2;

    @FindBy(id = "chooseFile")
    private WebElement chooseFile;

    @FindBy(id = "additionalInformations")
    private WebElement additionalInfo;

    @FindBy(css = ".btn-primary")
    private WebElement signInButton;

    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static WebElement getRandomElement(List<WebElement> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }

    public FormPage setFirstName(String name) {
        firstName.sendKeys(name);
        return this;
    }

    public FormPage setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }


    public FormPage setEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public FormPage setAge(int age) {
        this.age.clear();
        this.age.sendKeys(String.valueOf(age));
        return this;
    }

    public FormPage selectRandomSex() {
        getRandomElement(sex).click();
        return this;
    }

    public FormPage selectRandomExperience() {
        getRandomElement(yearsOfExperience).click();
        return this;
    }

    public String getActualMessage() {
        return actualMessage.getText();
    }

    public FormPage setProfession() {
        professionAutoTester.click();
        return this;
    }

    public FormPage sendFile() {
        File file = new File("src/main/resources/file.txt");
        chooseFile.sendKeys(file.getAbsolutePath());
        return this;
    }

    public FormPage signIn() {
        signInButton.click();
        return this;
    }

    public FormPage setRandomContinent() {
        List<WebElement> listOfContinents = continents.findElements(By.cssSelector("option"));

        if (listOfContinents.get(0).getText().equals("Choose...")) {
            listOfContinents.remove(0);
        }
        getRandomElement(listOfContinents).click();
        return this;
    }

    public FormPage setCommands(WebDriver driver) {
        Actions actionProvider = new Actions(driver);
        Action selectWithCtrl = actionProvider.keyDown(Keys.CONTROL).click(seleniumCommands1).click(seleniumCommands2).build();
        selectWithCtrl.perform();
        return this;
    }

    public FormPage fillAdditionalInfo(String info) {
        additionalInfo.click();
        additionalInfo.sendKeys(info);
        return this;
    }
}
