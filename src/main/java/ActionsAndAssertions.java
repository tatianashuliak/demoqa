import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static jdk.internal.org.jline.terminal.MouseEvent.Modifier.Shift;

public class ActionsAndAssertions {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            page.navigate("https://demoqa.com/text-box");
            page.locator("//input[@id='userName']").fill("Tatiana Shuliak");
            assertThat(page.locator("//button[@id='submit']")).isEnabled();
            page.locator("//button[@id='submit']").click();
            page.locator("//button[@id='submit']").blur();
            List<String> texts = page.locator("//label[@id='permanentAddress-label']").allInnerTexts();
        }

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            page.navigate("https://demoqa.com/automation-practice-form");
            page.locator("//label[@for='gender-radio-2']").check();
            page.locator("//label[@for='hobbies-checkbox-1']").check();
            String text = page.locator("//label[@for='gender-radio-2']").innerText();
            assertThat(page.locator("//label[@for='hobbies-checkbox-1']")).isChecked();
            assertThat(page.locator("//input[@id='react-select-4-input']")).isDisabled();
            assertThat(page.locator("//label[@id='currentAddress-label']")).hasText("Current Address");
            assertThat(page.locator("//input[@id='gender-radio-1']")).hasValue("Male");
        }

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            page.navigate("https://demoqa.com/select-menu");
            page.locator("//select[@id='oldSelectMenu']").selectOption("Yellow");
            String selectValue = page.locator("//select[@id='oldSelectMenu']").inputValue();
        }

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            page.navigate("https://demoqa.com/menu");
            page.locator("//a[text()='Main Item 2']").hover();
            assertThat(page.locator("//a[text()='SUB SUB LIST »']")).isVisible();
        }

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            page.navigate("https://demoqa.com/upload-download");
            page.getByLabel("Select a file").setInputFiles(Paths.get("src/main/java/myfile.png"));
            assertThat(page.locator("//label[@for='uploadFile']")).containsText("Select");
        }

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            page.navigate("https://demoqa.com/text-box");
            page.locator("//input[@id='userName']").press("Shift+A");
            int count = page.locator("//textarea").count();
            assertThat(page.locator("//input[@id='userName']")).hasAttribute("type", "text");
            assertThat(page.locator("//input")).hasCount(2);
        }

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            page.navigate("https://demoqa.com/droppable");
            page.locator("//div[@id='draggable']").dragTo(page.locator("//div[@aria-hidden='false']//div[@id='droppable']"));
            String attributeValue = page.locator("//div[@aria-hidden='false']//div[@id='droppable']").getAttribute("class");
            assertThat(page).hasTitle("DEMOQA");
        }

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            page.navigate("https://demoqa.com/checkbox");
            page.locator("//span[@class='rct-checkbox']").setChecked(true);
            assertThat(page).hasURL("https://demoqa.com/checkbox");
        }
    }
}
