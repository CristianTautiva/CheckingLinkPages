import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Main {
     static WebDriver driver;
     static ChekingLinkPages page;
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
       driver = new ChromeDriver();
       page = new ChekingLinkPages(driver);
        driver.get("https://es-la.facebook.com/");

        //assertTrue(page.chekingPageLinks(),"Hay algunos links rotos");
        if(page.chekingPageLinks()){
            System.out.println("No hay links rotos");
        }else{
            System.out.println("Encontre links rotos");
        }
    }


}
