package test.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

//DA RUNNARE SENZA L'IMPOSTAZIONE Build, Execution, Deployment -> Build Tools-> Maven -> Runner -> Delegate IDE build/run actions to Maven
public class TestSeleniumMeteo {

    /*
    Il test è:
        -Fallito quando l'attuale temperatura a Roma è minore di 25 gradi Celsius
        -Superato quando l'attuale temperatura a Roma è superiore ai 25 gradi Celsius
     */

    @Test
    public void testMeteo() throws InterruptedException {
        int temp = getTemperature();
        int tempLim = 25;
        if(temp >= tempLim)
        {
            tempLim = temp;
        }
        assertEquals(tempLim,temp,0);
    }

    public static int getTemperature() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/java/test/selenium/driver/chromedrivermacos");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/search?q=meteo+roma&rlz=1C5CHFA_enIT965IT965&sxsrf=APq-WBs3syjI-_oY0J8TxhShTALT8usEPg%3A1644691935045&ei=3wEIYrLxAdCP9u8Pi4uBiA8&ved=0ahUKEwjy3LX06vr1AhXQh_0HHYtFAPEQ4dUDCA4&uact=5&oq=meteo+roma&gs_lcp=Cgdnd3Mtd2l6EAMyBwgjELADECcyBwgAEEcQsAMyBwgAEEcQsAMyBwgAEEcQsAMyBwgAEEcQsAMyBwgAEEcQsAMyBwgAEEcQsAMyBwgAEEcQsAMyBwgAEEcQsAMyEgguEMcBENEDEMgDELADEEMYADISCC4QxwEQ0QMQyAMQsAMQQxgAMgwILhDIAxCwAxBDGABKBAhBGABKBAhGGABQAFgAYN0BaAFwAXgAgAEAiAEAkgEAmAEAyAEMwAEB2gEECAAYCA&sclient=gws-wiz");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click();
        int temp = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"wob_tm\"]")).getText());
        Thread.sleep(2000);
        driver.close();

        return temp;
    }


}

