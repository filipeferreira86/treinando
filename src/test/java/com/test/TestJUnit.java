package com.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestJUnit {
    WebDriver driver = new ChromeDriver();

    @Before
    public void setUp(){
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void sucessoLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();
        Assert.assertEquals("URL não é igual: ",
                "https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        Assert.assertEquals("Título da página não é igual: ", "Swag Labs",
                driver.findElement(By.className("app_logo")).getText());
    }

    @Test
    public void usuarioIncorreto(){
        driver.findElement(By.id("user-name")).sendKeys("abc123");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();
        Assert.assertEquals("URL não é igual: ",
                "https://www.saucedemo.com/", driver.getCurrentUrl());
        Assert.assertEquals("Mensagem de erro não é igual: ",
                "Epic sadface: Username and password do not match any user in this service",
                driver.findElement(By.xpath("//h3[@data-test='error']")).getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
