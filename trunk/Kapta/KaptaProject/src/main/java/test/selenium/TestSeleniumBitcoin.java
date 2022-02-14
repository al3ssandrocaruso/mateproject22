package test.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.NumberFormat;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

//DA RUNNARE SENZA L'IMPOSTAZIONE Build,Execution, Deployment -> Build Tools-> Maven -> Runner -> Delegate IDE build/run actions to Maven
//Nella cartella driver si trovano sia il driver per windoes che per macos
public class TestSeleniumBitcoin {

     /*
     Il test è:
       -Fallito quando il valore attuale del bitcoin è sotto i 90000$
       -Superato quando il valore attuale del bitcoin è sopra i 90000$
    */

    @Test
    public void testBitcoin() throws InterruptedException, ParseException {
        String actual = getValue();
        NumberFormat format = NumberFormat.getInstance();
        Number value = format.parse(actual);
        double d = value.doubleValue();
        int ret = 0;
        if(d > 90000.00)
        {
            ret = 1;
        }
        assertEquals(1,ret,0);
    }

    public static String getValue() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/java/test/selenium/driver/chromedrivermacos");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/search?q=quotazione+bitcoin&rlz=1C5CHFA_enIT965IT965&sxsrf=APq-WBvJmqgwICMso8LNdYjFLNO30dXTgA%3A1644692326015&ei=ZgMIYoQf9JLFzw-Xiq74BQ&oq=quotazione+bitconi&gs_lcp=Cgdnd3Mtd2l6EAMYADIPCAAQsQMQgwEQChBGEIICMgoIABCxAxCDARAKMgoIABCxAxCDARAKMgQIABAKMgQIABAKMgQIABAKMgQIABAKMgQIABAKMgQIABAKMgQIABAKOgcIIxDqAhAnOgQIIxAnOgsIABCABBCxAxCDAToICAAQgAQQsQM6BQguEIAEOggIABCxAxCDAToICC4QsQMQgwE6BAgAEEM6BAguEEM6CAguEIAEELEDOgoIABCxAxCDARBDOhAILhCxAxCDARDHARDRAxBDOgcIABCxAxBDOg8IABCxAxCDARBDEEYQ-gE6BQgAEIAEOhAIABCABBCxAxCDARBGEIICSgQIQRgASgQIRhgAUABYsLkBYIvBAWgBcAF4AIABqQGIAb0RkgEEMS4xN5gBAKABAbABCsABAQ&sclient=gws-wiz");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click();
        String value = driver.findElement(By.xpath("//*[@id=\"crypto-updatable_2\"]/div/div[2]/span[1]")).getText();



        Thread.sleep(2000);
        driver.close();

        return value;
    }
}
