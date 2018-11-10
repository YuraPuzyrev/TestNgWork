package org.itstep.qa.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DevByTask {

    WebDriver driver;

    @BeforeClass
    public void initSetDriver(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void startUp(){
        driver.navigate().to("https://dev.by/registration");
        WebElement pageHeader = driver.findElement(By.cssSelector(".header-logo"));
        Assert.assertTrue(pageHeader.isDisplayed(), "Окно регистрации не открывается");
    }

    @AfterClass
    public void closeBrowser(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    public void openPage(){
        Assert.assertTrue(driver.findElement(By.id("user_username")).isDisplayed(), "Поле юзернейма не отображается");
        Assert.assertTrue(driver.findElement(By.id("user_email")).isDisplayed(), "Поле емейла не отображается");
        Assert.assertTrue(driver.findElement(By.id("user_password")).isDisplayed(), "Поле пароля не отображается");
        Assert.assertTrue(driver.findElement(By.id("user_password_confirmation")).isDisplayed(), "Поле подтверждения пароля не отображается");
        Assert.assertTrue(driver.findElement(By.id("user_first_name")).isDisplayed(), "Поле имени не отображается");
        Assert.assertTrue(driver.findElement(By.id("user_last_name")).isDisplayed(), "Поле фамилии не отображается");
        Assert.assertTrue(driver.findElement(By.cssSelector(".select2-chosen")).isDisplayed(), "Всплывающее окно компаний не отображается");
        Assert.assertTrue(driver.findElement(By.id("user_agreement")).isEnabled(), "Чекбокс подтверждения не отображается");
        Assert.assertTrue(driver.findElement(By.cssSelector(".l-for-r-avatar")).isDisplayed(), "Окно загрузки аватара не отображается");
        Assert.assertTrue(driver.findElement(By.name("commit")).isDisplayed(), "Кнопка подтверждения не отображается");
    }

    @Test
    public void passConfirm(){
        WebElement pass = driver.findElement(By.id("user_password"));
        Assert.assertTrue(pass.isDisplayed(), "Поле пароля не отображается");
        WebElement confirm = driver.findElement(By.id("user_password_confirmation"));
        Assert.assertTrue(confirm.isDisplayed(), "Поле подтверждения пароля не отображается");
        pass.sendKeys("123456");
        confirm.sendKeys("1234");
        WebElement formError = driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/div/div/div[1]/div[1]"));
        Assert.assertTrue(formError.isDisplayed(), "Ошибка несовпадения паролей не отображается");
        Assert.assertEquals("* Поля не совпадают", formError.getText());
    }

    @Test
    public void emailCheck(){
        WebElement email = driver.findElement(By.id("user_email"));
        Assert.assertTrue(email.isDisplayed(), "Поле емейла не отображается");
        email.sendKeys("test");
        WebElement formError = driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[4]/div/div/div/div[1]"));
        Assert.assertTrue(formError.isDisplayed(), "Ошибка поля емейла не отображается");
        Assert.assertEquals("* Неверный формат email", formError.getText());
    }

    @Test
    public void usernameCheck(){
        WebElement username = driver.findElement(By.id("user_username"));
        Assert.assertTrue(username.isDisplayed(), "Поле юзернейма не отображается");
        username.sendKeys("fffffffffffffffff");
        WebElement formError = driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[3]/div/div/div/div[1]"));
        Assert.assertTrue(formError.isDisplayed(), "Ошибка юзернейма не отображается");
        Assert.assertEquals("* Максимум 16 символа(ов)", formError.getText());
    }

    @Test
    public void errorsAreDisplaing(){
        WebElement commit = driver.findElement(By.name("commit"));
        Assert.assertTrue(commit.isDisplayed(), "Кнопка подтверждения не отображается");

    }


}
