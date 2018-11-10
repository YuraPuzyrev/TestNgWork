package org.itstep.qa.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WeatherGomelChrome {

    WebDriver driver;

    @BeforeClass
    public void initSetDriver(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void closeBrowser(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    public void searchWeatherGoogle(){
        driver.navigate().to("http://www.google.com");
        //проверка открытия сайта
        WebElement pageHeader = driver.findElement(By.id("hplogo"));
        Assert.assertTrue(pageHeader.isDisplayed(), "Страница не отображается");
        //поиск строки ввода
        WebElement field = driver.findElement(By.id("lst-ib"));
        Assert.assertTrue(field.isDisplayed(), "Поле ввода не отображается");
        //поиск кнопки "поиск"
        WebElement button = driver.findElement(By.name("btnK"));
        Assert.assertTrue(button.isDisplayed(), "Кнопка поиска не отображается");
        //отправка запроса
        field.sendKeys("Погода в Гомеле");
        button.click();
        //проверка результата
        WebElement resultPage = driver.findElement(By.id("wob_wc"));
        Assert.assertTrue(resultPage.isDisplayed(), "Итоговая страница не отображается");
    }

    @Test
    public void searchWeatherYandex(){
        driver.navigate().to("https://www.yandex.by/");
        //проверка открытия сайта
        WebElement pageHeader = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div/div[1]/div/a/div"));
        Assert.assertTrue(pageHeader.isDisplayed(), "Страница не отображается");
        //поиск строки ввода
        WebElement field = driver.findElement(By.id("text"));
        Assert.assertTrue(field.isDisplayed(), "Поле ввода не отображается");
        //поиск кнопки "найти"
        WebElement button = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div/div[2]/div/div[2]/div/form/div[2]/button"));
        Assert.assertTrue(button.isDisplayed(), "Кнопка поиска не отображается");
        //отправка запроса
        field.sendKeys("Погода в Гомеле");
        button.click();
        //проверка результата
        WebElement resultPage = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div[1]/div[2]/ul/li[1]/div"));
        Assert.assertTrue(resultPage.isDisplayed(), "Итоговая страница не отображается");
    }
}
