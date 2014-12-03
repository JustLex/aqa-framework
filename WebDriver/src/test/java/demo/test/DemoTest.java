package demo.test;

import org.openqa.selenium.By;
import webdriver.BaseTest;
import demo.test.forms.*;
import webdriver.PropertiesResourceManager;

public class DemoTest extends BaseTest {
	public void runTest() {
        PropertiesResourceManager prm = new PropertiesResourceManager("params.properties");
		logger.step(1);
		browser.navigate(prm.getProperty("url"));
		logger.step(2);
		OnlinerCatalogForm ocf = new OnlinerCatalogForm();
		ocf.clickNavbarElement();
        ocf.assertIsPresent(By.xpath(prm.getProperty("catalog_id")));
		logger.step(3);
		ocf.clickSection();
        ocf.assertIsPresent(By.xpath(prm.getProperty("tv_id")));
		logger.step(4);
        ocf.inputParams();
        logger.step(5);
        ocf.clickSubmit();
        ocf.assertIsPresent(By.xpath(prm.getProperty("tv_id")));
        logger.step(6);
        browser.waitForPageToLoad();
        ocf.assertElementsCorrect();
        browser.exit();
	}



}
