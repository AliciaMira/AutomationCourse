package loginAutoSauceDemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestLogin {

    private WebDriver driver;

    By usuario = new By.ByName("user-name");
    By contrasenia = new By.ById("password");
    By botonIngresar=new By.ByXPath("//input[@type='submit'][@value='Login']");
    By mjeLoginOK = new By.ByXPath("//*[contains(text(),'Products')]");
    By mjeLogin =new By.ByXPath("//div[3]/h3[contains(text(),'Password is required')]");

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "E:\\Curso QA\\Testing Automatizado\\Login_SauceDemo\\src\\main\\resources\\chromedriver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @Test
    public void loguearseUsuarioExistente() throws InterruptedException {

        Thread.sleep(2000);

        driver.findElement(usuario).clear();
        driver.findElement(usuario).sendKeys("standard_user");

        driver.findElement(contrasenia).clear();
        driver.findElement(contrasenia).sendKeys("secret_sauce");

        driver.findElement(botonIngresar).click();

        Thread.sleep(2000);

        System.out.println(driver.findElement(mjeLoginOK).getText());

        String mensaje = "PRODUCTS";

        assertEquals(mensaje, driver.findElement(mjeLoginOK).getText());
    }
    @Test
    public void loguearseUsuarioSinClave()throws InterruptedException{
        Thread.sleep(2000);

        driver.findElement(usuario).clear();
        driver.findElement(usuario).sendKeys("standard_user");
        driver.findElement(contrasenia).clear();
        driver.findElement(contrasenia).sendKeys("");
        driver.findElement(botonIngresar).click();
        Thread.sleep(2000);
        System.out.println(driver.findElement(mjeLogin).getText());
        String mensaje="Epic sadface: Password is required";
        assertEquals(mensaje, driver.findElement(mjeLogin).getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
