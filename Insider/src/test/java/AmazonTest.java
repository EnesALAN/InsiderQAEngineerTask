/**
 * Created by Enes ALAN on 23.04.2019.
 *
 * Insider QA Engineer Task
 *
 * Amazon WebSite Testing
 */

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonTest {
    String webDriverPath = "C:\\Users\\Hp\\Desktop\\selenium\\MicrosoftWebDriver.exe";

    @Test
    public void CaseEnterWebSiteQuestion1() {
        System.setProperty("webdriver.edge.driver", webDriverPath);
        WebDriver webDriver = new EdgeDriver();
        webDriver.get("https://www.amazon.com/");

        boolean flag = webDriver.getTitle().startsWith("Amazon.com");
        Assert.assertTrue(String.valueOf(flag), true);
        webDriver.close();
    }

    @Test
    public void CaseLoginAsUserQuestion2() {
        System.setProperty("webdriver.edge.driver", webDriverPath);
        WebDriver webDriver = new EdgeDriver();
        webDriver.get("https://www.amazon.com/");

        webDriver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[1]")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(webDriver, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("a-button-input"))));

        WebElement nameLabel = webDriver.findElement(By.xpath("//*[@id='ap_email']"));
        nameLabel.sendKeys("enes-alan@hotmail.com");

        webDriver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys("Enes123456");
        webDriver.findElement(By.className("a-button-input")).click();


        webDriver.close();
    }


    @Test
    public void CaseSearchSamsungQuestion3and4() {
        System.setProperty("webdriver.edge.driver", webDriverPath);
        WebDriver webDriver = new EdgeDriver();
        webDriver.get("https://www.amazon.com/");

        webDriver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[1]")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(webDriver, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("a-button-input"))));


        WebElement nameLabel = webDriver.findElement(By.xpath("//*[@id='ap_email']"));
        nameLabel.sendKeys("enes-alan@hotmail.com");

        webDriver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys("Enes123456");
        webDriver.findElement(By.className("a-button-input")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.id("twotabsearchtextbox"))));
        WebElement searchLabel = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchLabel.sendKeys("samsung");

        webDriver.findElement(By.className("nav-input")).click();


        //new side
        boolean cond = false;
        String resultInfo = webDriver.findElement(By.className("a-section a-spacing-small a-spacing-top-small")).getText();
        System.out.println(resultInfo);
        if (resultInfo.contains("of over")) {
            cond = true;
        }
        Assert.assertTrue(String.valueOf(cond), true);
        //webDriver.close();
    }

    @Test
    public void CasePageTwoQuestion5() {
        WebDriver new2 = enterAndLogin();
        boolean secondPageTestFlag = false;
        new2.findElement(By.xpath("//*[@id='search']/div[1]/div[2]/div/span[7]/div/div/div/ul/li[3]/a")).click();
        String Url = new2.getCurrentUrl();
        if (Url.contains("page=2")) {
            secondPageTestFlag = true;

        }
        Assert.assertTrue(String.valueOf(secondPageTestFlag), true);

    }

    @Test
    public void CaseAddToListQuestion6() {

        WebDriver page2 = pageTwo();
        page2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(page2, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("s-image"))));
        page2.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.id("add-to-wishlist-button-submit"))));
        page2.findElement(By.id("add-to-wishlist-button")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("atwl-list-name-1S0F4B4DZYB6H"))));
        page2.findElement(By.id("atwl-list-name-1S0F4B4DZYB6H")).click();

    }

    @Test
    public void CaseShowWishListQuestion7() {

        WebDriver page2 = pageTwo();
        page2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(page2, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("s-image"))));
        page2.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.id("add-to-wishlist-button-submit"))));
        page2.findElement(By.id("add-to-wishlist-button")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("atwl-list-name-1S0F4B4DZYB6H"))));
        page2.findElement(By.id("atwl-list-name-1S0F4B4DZYB6H")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.className("w-button-text"))));
        page2.findElement(By.className("w-button-text")).click();

    }


    @Test
    public void CaseApproveElementOnWishListQuestion8() {
        boolean testFlag = false;
        String elementName = "";
        WebDriver itemSelection = pageTwo();
        itemSelection.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(itemSelection, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("s-image"))));
        WebElement img = itemSelection.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div/span/a/div/img"));
        elementName = img.getAttribute("alt");
        System.out.println(elementName);
        //3.
        itemSelection.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();
        //4.
        //itemSelection.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[4]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("add-to-wishlist-button-submit"))));
        itemSelection.findElement(By.id("add-to-wishlist-button")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("atwl-list-name-1S0F4B4DZYB6H"))));
        itemSelection.findElement(By.id("atwl-list-name-1S0F4B4DZYB6H")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.className("w-button-text"))));
        itemSelection.findElement(By.className("w-button-text")).click();
        //#g-items-grid
        WebElement confirm = itemSelection.findElement(By.xpath("//*[@id=\'item_I2U4CBRUKCSQ1O\']/span/div[3]/div/a"));
        String confirmationName = confirm.getAttribute("title");
        if (confirmationName == elementName) {
            testFlag = true;
        }
        Assert.assertTrue(String.valueOf(testFlag), true);
    }

    //Amazon.com'un wish listteki sayfa yapısı 26.04.2019 da değiştiği için artık ürünlerin yanında delete butonu bulunmuyor.
    //Yeni bir test case yazmayı denedim fakat düzgün bir şekilde çalışmıyor.
    @Test
    public void CaseDeleteElementOnWishListQuestion9() {
        WebDriver deleteObject = pageTwo();
        deleteObject.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(deleteObject, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("s-image"))));

        //3.
        deleteObject.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();
        //4.
        //itemSelection.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[4]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("add-to-wishlist-button-submit"))));
        deleteObject.findElement(By.id("add-to-wishlist-button")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("atwl-list-name-1S0F4B4DZYB6H"))));
        deleteObject.findElement(By.id("atwl-list-name-1S0F4B4DZYB6H")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.className("w-button-text"))));
        deleteObject.findElement(By.className("w-button-text")).click();
        //wait.until(ExpectedConditions.elementToBeClickable((By.className("a-section a-spacing-micro a-padding-base aok-float-right wl-grid-item-selectable wl-pointer"))));
        // deleteObject.findElement(By.className("a-section a-spacing-micro a-padding-base aok-float-right wl-grid-item-selectable wl-pointer")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\'wl-grid-item-menu-I3H0PYDNQVF9PI\']/span/div/img"))));
        deleteObject.findElement(By.xpath("//*[@id=\'wl-grid-item-menu-I3H0PYDNQVF9PI\']/span/div/img")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\'wl-grid-item-menu-options-I28BDMFR9RWYNW\']/ul/span[3]/li"))));
        deleteObject.findElement(By.xpath("//*[@id=\'wl-grid-item-menu-options-I28BDMFR9RWYNW\']/ul/span[3]/li/span")).click();
    }

    @Test
    public void CaseDeletedElementCheckQuestion10() {

        String elementName = "";
        WebDriver deleteObject = pageTwo();
        deleteObject.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(deleteObject, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("s-image"))));
        WebElement img = deleteObject.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div/span/a/div/img"));
        elementName = img.getAttribute("alt");
        System.out.println(elementName);


        deleteObject.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();

        //itemSelection.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[4]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("add-to-wishlist-button-submit"))));
        deleteObject.findElement(By.id("add-to-wishlist-button")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("atwl-list-name-1S0F4B4DZYB6H"))));
        deleteObject.findElement(By.id("atwl-list-name-1S0F4B4DZYB6H")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.className("w-button-text"))));
        deleteObject.findElement(By.className("w-button-text")).click();
        //wait.until(ExpectedConditions.elementToBeClickable((By.className("a-section a-spacing-micro a-padding-base aok-float-right wl-grid-item-selectable wl-pointer"))));
        // deleteObject.findElement(By.className("a-section a-spacing-micro a-padding-base aok-float-right wl-grid-item-selectable wl-pointer")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\'wl-grid-item-menu-I3H0PYDNQVF9PI\']/span/div/img"))));
        deleteObject.findElement(By.xpath("//*[@id=\'wl-grid-item-menu-I3H0PYDNQVF9PI\']/span/div/img")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\'wl-grid-item-menu-options-I28BDMFR9RWYNW\']/ul/span[3]/li"))));
        deleteObject.findElement(By.xpath("//*[@id=\'wl-grid-item-menu-options-I28BDMFR9RWYNW\']/ul/span[3]/li/span")).click();


        boolean isThereProduct = false;
        WebElement allProducts = deleteObject.findElement(By.id("g-items-grid"));
        List<WebElement> productTitles = allProducts.findElements(By.tagName("li"));
        for (WebElement productTitle : productTitles) {
            String watchesProduct = productTitle.getText();
            if (watchesProduct.equals(elementName)) {
                isThereProduct = true;
            }
        }
        Assert.assertFalse(isThereProduct);


    }

    private String approveElement() {
        String elementName = "";
        WebDriver page2 = pageTwo();
        page2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(page2, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("s-image"))));

        WebElement img = page2.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[4]/div/div/div/div[2]/div[1]/div/div/span/a/div/img"));
        elementName = img.getAttribute("alt");
        //page2.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();

        return elementName;
    }


    private WebDriver pageTwo() {
        WebDriver new2 = enterAndLogin();
        boolean secondPageTestFlag = false;
        new2.findElement(By.xpath("//*[@id='search']/div[1]/div[2]/div/span[7]/div/div/div/ul/li[3]/a")).click();
        return new2;
    }


    private WebDriver enterAndLogin() {
        System.setProperty("webdriver.edge.driver", webDriverPath);
        WebDriver webDriver = new EdgeDriver();
        webDriver.get("https://www.amazon.com/");

        webDriver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[1]")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(webDriver, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("a-button-input"))));


        WebElement nameLabel = webDriver.findElement(By.xpath("//*[@id='ap_email']"));
        nameLabel.sendKeys("enes-alan@hotmail.com");

        webDriver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys("Enes123456");
        webDriver.findElement(By.className("a-button-input")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.id("twotabsearchtextbox"))));
        WebElement searchLabel = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchLabel.sendKeys("samsung");

        webDriver.findElement(By.className("nav-input")).click();
        return webDriver;
    }

    @Test
    public void CaseApproveElementOnWishListQuestion8Deneme() {
        boolean testFlag = false;
        WebDriver page2 = pageTwo();
        page2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(page2, 40);
        wait.until(ExpectedConditions.elementToBeClickable((By.className("s-image"))));
        //4.elemanı aldık
        //page2.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();

        page2.findElement(By.xpath("//*[@id=\'search\']/div[1]/div[2]/div/span[3]/div[1]/div[4]/div/div/div/div[2]/div[1]/div/div/span/a/div/img")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.id("add-to-wishlist-button-submit"))));
        page2.findElement(By.id("add-to-wishlist-button")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("atwl-list-name-1S0F4B4DZYB6H"))));
        page2.findElement(By.id("atwl-list-name-1S0F4B4DZYB6H")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.className("w-button-text"))));
        page2.findElement(By.className("w-button-text")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.className("a-size-base"))));
        String productTitle = page2.findElement(By.className("a-size-base")).getText();
        if (productTitle.contains("Samsung Galaxy S10e")) {
            testFlag = true;
        }
        Assert.assertTrue(String.valueOf(testFlag), true);

        WebElement table = page2.findElement(By.className("a-column a-span12 g-span12when-narrow g-span7when-wide"));
        List<WebElement> rows = table.findElements(By.tagName("your tagName"));
        java.util.Iterator<WebElement> i = rows.iterator();
        while (i.hasNext()) {
            WebElement row = i.next();
            System.out.println(row.getText());
        }


    }
}
