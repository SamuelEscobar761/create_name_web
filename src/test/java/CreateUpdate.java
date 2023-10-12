import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateUpdate {
    ChromeDriver chrome;
    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        // implicit
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        chrome.get("http://todo.ly/");
    }
    //  .basic("upbapi@upbapi.com", "12345")
    @Test
    public void verifyLoginTest() throws InterruptedException {
        // click login button
        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        // set email
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("samueb8@gmail.com");
        // set password
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("1234");
        // click login
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        // verificar si existe el control del logout

        Assertions.assertTrue((chrome.findElements(By.xpath("//a[text()='Logout']")).size() == 1),
                "ERROR no se pudo ingresar a la sesion");
    }

    @Test
    public void createAndRenameProject() throws InterruptedException {
        verifyLoginTest();
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10)); // Espera con un tiempo máximo de 10 segundos
        chrome.findElement(By.xpath("//div[@class=\"AddProjectLiDiv\"]")).click();
        chrome.findElement(By.id("NewProjNameInput")).sendKeys("miproyecto");
        chrome.findElement(By.id("NewProjNameButton")).click();
        String elementoId = chrome.findElement(By.xpath("//td[text()='miproyecto']")).getAttribute("itemid");
        System.out.println(elementoId);
        renameProject(elementoId);

    }

    @Test
    public void renameProject(String elementoId) throws InterruptedException {
        chrome.findElement(By.xpath("//li[@itemid='" + elementoId + "']")).click();
        chrome.findElement(By.xpath("//div[@class=\"ProjItemMenu\" and @style=\"display: block;\" and itemid=\"" + elementoId + "\"]")).click();
        chrome.findElement(By.xpath("//a[@href=\"#edit\"]")).click();
        chrome.findElement(By.id("ItemEditTextbox")).sendKeys("Proyecto renombrado");
        chrome.findElement(By.id("ItemEditSubmit")).click();
    }


    @AfterEach
    public void closeBrowser(){
        chrome.quit();
    }

}