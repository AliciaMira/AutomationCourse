package testBlazeDemosinPOM;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class TestComprarVuelo {

    private WebDriver driver;

    By listaCiudadDesde=new By.ByName("fromPort");
    By listaCiudadHasta =new By.ByName("toPort");
    By botonFindFlights=new By.ByXPath("//input[@type='submit'][@value='Find Flights']");
    By botonChooseThisFlightF1=new By.ByXPath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input[@type='submit']");
    By inputName=new By.ByName("inputName");
    By inputAddress=new By.ByName("address");
    By botonPurchaseFlight=new By.ByXPath("//input [@type='submit'] [@value='Purchase Flight']");
    String nombreCdadDesde="Boston";
    String nombreCdadHasta="London";
    String mensaje="";
    String mensaje2="Flights from " + nombreCdadDesde +" to " + nombreCdadHasta +":" ;
    By mjeP1_Ok=new By.ByXPath("//*/h1[contains(text(),'Welcome')]");
    By mjeP2_Ok=new By.ByXPath("//*/h3[contains(text(),mensaje)]");
    By mjeP3_Ok=new By.ByXPath("//*/h1[contains(text(),'Thank you for your purchase')]");

    @Before
    public void SetUp(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://blazedemo.com/");
    }

    @Test
    public void comprarVuelo() throws InterruptedException {
        mensaje="Welcome to the Simple Travel Agency!";
        Thread.sleep(2000);

        System.out.println(driver.findElement(mjeP1_Ok).getText());
        assertEquals(mensaje, driver.findElement(mjeP1_Ok).getText());

        Select ciudadDesde=new Select(driver.findElement(listaCiudadDesde));
        ciudadDesde.selectByVisibleText(nombreCdadDesde);

        Select ciudadHasta=new Select(driver.findElement(listaCiudadHasta));
        ciudadHasta.selectByVisibleText(nombreCdadHasta);

        driver.findElement(botonFindFlights).click();
        assertEquals(mensaje2, driver.findElement(mjeP2_Ok).getText());

        driver.findElement(botonChooseThisFlightF1).click();
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys("Alicia Mira");

        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys("Mario Grandi 4238");

        driver.findElement(botonPurchaseFlight).click();
        mensaje="Thank you for your purchase today!";
        assertEquals(mensaje, driver.findElement(mjeP3_Ok).getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
