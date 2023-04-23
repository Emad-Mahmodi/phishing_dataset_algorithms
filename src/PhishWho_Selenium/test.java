package PhishWho_Selenium;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test {

		public static void main(String[] args) {
			String temp= "www.irs.gov";
			String[] words = temp.split(Pattern.quote("."));
			System.out.println(words);
		}
}
