package demo.test.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import webdriver.BaseForm;
import webdriver.PropertiesResourceManager;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aleksei on 28.11.2014.
 */
public class OnlinerCatalogForm extends BaseForm {
    private static PropertiesResourceManager prm = new PropertiesResourceManager("params.properties");
    private static String formLocator = prm.getProperty("main_id");

    private Label lbCatalog = new Label(By.xpath(prm.getProperty("nav_catalog")),
            "navbar elem");
    private Label lbSection = new Label(By.xpath(prm.getProperty("tv_section")),
            "tv section link");
    private TextBox txtProducer = new TextBox(By.id(prm.getProperty("producer_text_id")), "producer");
    private TextBox txtMaxPrice = new TextBox(By.id(prm.getProperty("maxPrice_id")), "max price");
    private TextBox txtYear = new TextBox(By.id(prm.getProperty("year_id")), "year");
    private TextBox txtMinDiagonal = new TextBox(By.id(prm.getProperty("minDiagonal_id")), "max diagonal");
    private TextBox txtMaxDiagonal = new TextBox(By.id(prm.getProperty("maxDiagonal_id")), "min diagonal");
    private Button btnSubmit = new Button(By.xpath(prm.getProperty("btn_submit")), "submit button");
    private Button btnUsd = new Button(By.xpath(prm.getProperty("btn_usd")));
    public void clickNavbarElement(){
        lbCatalog.click();
    }

    public void assertIsPresent(By by){
        Label lb = new Label(by);
        assert lb.isPresent();
    }

    public void clickSection(){
        lbSection.click();
    }

    public void assertElementsCorrect(){
        WebElement form = browser.getDriver().findElementByXPath(prm.getProperty("form_result"));
        List<WebElement> names = form.findElements(By.className(prm.getProperty("name_class")));
        for (WebElement elem : names){
            assert prm.getProperty("producer").toLowerCase().contains(elem.getText().toLowerCase());
        }
        logger.info(prm.getProperty("correct_names"));
        List<WebElement> prices = form.findElements(By.className(prm.getProperty("price_class")));
        for (WebElement elem : prices){
            String[] res = elem.getText().split(" ");
            int price = Integer.parseInt(res[0]);
            assert price < Integer.parseInt(prm.getProperty("maxPrice"));
        }
        logger.info(prm.getProperty("correct_prices"));
        List<WebElement> descriptions = form.findElements(By.className(prm.getProperty("descr_class")));
        for (WebElement elem : descriptions){
            String pattern = prm.getProperty("diagonal_regex");
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(elem.getText());
            if (matcher.find()){
                String res = matcher.group().replace("\"","");
                double d = Double.parseDouble(res);
                assert (d >= Double.parseDouble(prm.getProperty("minDiagonal"))
                        && d <= Double.parseDouble("maxDiagonal"));
            }
        }
        logger.info(prm.getProperty("correct_diagonals"));
    }

    public void clickSubmit(){
        btnSubmit.click();
    }

    public void inputParams(){
        btnUsd.click();
        new Select(txtProducer.getElement()).selectByValue(prm.getProperty("producer"));
        txtMaxPrice.type(prm.getProperty("maxCost"));
        txtYear.type(prm.getProperty("year"));
        new Select(txtMinDiagonal.getElement()).selectByValue(prm.getProperty("minDiagonal"));
        new Select(txtMaxDiagonal.getElement()).selectByValue(prm.getProperty("maxDiagonal"));
    }

    public OnlinerCatalogForm() {
        super(By.xpath(formLocator), "Onliner catalog form");
    }
}
