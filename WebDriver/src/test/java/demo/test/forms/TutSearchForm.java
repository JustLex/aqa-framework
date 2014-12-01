package demo.test.forms;

import org.openqa.selenium.By;

import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

public class TutSearchForm extends BaseForm {
	private static String formlocator="//body[@class='main-page']";

	private TextBox txbSearchBar = new TextBox(By.id("search_from_str"),"search bar");
	private Label lbLogo = new Label(By.xpath("//img[@id='pageLogo']"),"tut.by logo");	
	private Button btnSubmitSearch = new Button (By.xpath("//input[@name='search']"),"search button");
	private Label lbA1QAAssert = new Label(By.xpath("//a[contains(.,'Тестирование')]"),"label testing");


	public void assertLogo(){
		assert(lbLogo.isPresent());
	}
	
    public void searchFor(String text) {
    	txbSearchBar.type(text);
    	btnSubmitSearch.click();
    }
	
	public TutSearchForm() {
		super(By.xpath(formlocator), "My google form");
	}

	public void searchAssert(){
		assert(lbA1QAAssert.isPresent());
	}
}
