package test.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.NumberFormat;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

//DA RUNNARE SENZA L'IMPOSTAZIONE Build,Execution, Deployment -> Build Tools-> Maven -> Runner -> Delegate IDE build/run actions to Maven
public class TestSeleniumCovid {

    /*
   Il test è:
       -Fallito quando il numero di nuovi positivi giornalieri è maggiore di 1000
       -Superato quando il numero di nuovi positivi giornalieri è minore di 1000
    */

    @Test
    public void testCovid() throws InterruptedException, ParseException {
        String strCasi = getPositivi();
        NumberFormat format = NumberFormat.getInstance();
        Number value = format.parse(strCasi);
        double d = value.doubleValue();
        assertEquals(0,d,1000);
    }

    public static String getPositivi() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/java/test/selenium/driver/chromedrivermacos");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/search?q=casi+covid&rlz=1C5CHFA_enIT965IT965&oq=casi+covid&aqs=chrome..69i57.1268j0j7&sourceid=chrome&ie=UTF-8");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click();
        String casi = driver.findElement(By.xpath("//*[@id=\"eTST2\"]/div[3]/div[1]/table/tbody/tr/td[1]/div[3]/div/span")).getText();
        String valoreAssolutoCasi = casi.substring(1);  //Server per togliere il segno che sta all'inizio del numero
        Thread.sleep(2000);
        driver.close();

        return valoreAssolutoCasi;
    }

}
