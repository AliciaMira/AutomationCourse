package formExample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import sun.security.x509.OtherName;

import static org.junit.Assert.assertEquals;

public class TestCargaFormulario {

    private WebDriver driver;
    By userName= new By.ByName("username");
    By contrasenia=new By.ByName("password");
    By comentario=new By.ByName("comments");
    By btnFileName=new  By.ByName("filename");
    By ckBox1=new By.ByXPath("//input[@type=\"checkbox\"] [@value='cb1']");
    By ckBox2=new By.ByXPath("//input[@type=\"checkbox\"] [@value='cb2']");
    By ckBox3=new By.ByXPath("//input[@type=\"checkbox\"] [@value='cb3']");
    By optBox1=new By.ByXPath("//input[@name=\"radioval\"] [@value='rd1']");
    By optMultiSel1 =new By.ByXPath("//*/table/tbody/tr[7]/td/select/option[@value=\"ms1\"]");
    By optMultiSel=new By.ByXPath("//*/table/tbody/tr[7]/td/select");
    By cbo=new By.ByXPath("//*/table/tbody/tr[8]/td/select");
    By btnSubmit=new By.ByXPath("//input[@type=\"submit\"][@value=\"submit\"]");
    By msgPasoP2 = new By.ByXPath("/html/body/div/h1[contains(text(),'Processed Form Details')]");

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver/chromedriver.exe");
        driver  =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");
    }

    @Test
    public void cargarFormulario() throws InterruptedException {
        driver.findElement(userName).clear();
        driver.findElement(userName).sendKeys("Alicia");
        driver.findElement(contrasenia).clear();
        driver.findElement(contrasenia).sendKeys("159753");
        driver.findElement(comentario).clear();
        driver.findElement(comentario).sendKeys("Este es el comentario");
       // driver.findElement(btnFileName).click();
        if (driver.findElement(ckBox1).isSelected()==false  )
        {
            driver.findElement(ckBox1).click();
        }
        if (driver.findElement(ckBox2).isSelected()==false  )
        {
            driver.findElement(ckBox2).click();
        }
        if (driver.findElement(ckBox3).isSelected()==false  )
        {
            driver.findElement(ckBox3).click();
        }
        driver.findElement(optBox1).click();
        driver.findElement(optMultiSel1).click();

        Select listaSeleccion=new Select (driver.findElement(optMultiSel));
        listaSeleccion.deselectAll();
        listaSeleccion.selectByVisibleText("Selection Item 3");

        Select comboSeleccion=new Select(driver.findElement(cbo));
        comboSeleccion.selectByVisibleText("Drop Down Item 3");
        Thread.sleep(2000);
        driver.findElement(btnSubmit).click();
        String mensaje="Processed Form Details";
        assertEquals(mensaje, driver.findElement(msgPasoP2).getText());
    }
    @After
    public void tearDown(){driver.quit();}
}
