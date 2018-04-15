package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPassPage extends Page {
    public MainPassPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(id = "name0")
    private WebElement departureStationField;

    @FindBy(id = "name1")
    private WebElement arrivalStationField;

    @FindBy(id = "date0")
    private WebElement departureDateField;

    @FindBy(id = "date1")
    private WebElement departureBackDateField;

    @FindBy(id = "Submit")
    private WebElement buyTicketButton;

    public void fillDepartureStationField(String departureStation) {
        departureStationField.sendKeys(departureStation);
    }

    public void fillArrivalStationField(String arrivalStation) {
        arrivalStationField.sendKeys(arrivalStation);
    }

    public void fillDepartureDateField(String departureDate) {
        departureDateField.clear();
        departureDateField.sendKeys(departureDate);
    }

    public void fillDepartureBackDateField(String departureBackDate) {
        departureBackDateField.clear();
        departureBackDateField.sendKeys(departureBackDate);
    }

    public void clickOnBuyTickets() {
        waitForElementClickable(buyTicketButton);
        buyTicketButton.click();
    }

    public void searchTickets(String departureStation, String arrivalStation, String departureDate) {
        fillDepartureStationField(departureStation);
        fillArrivalStationField(arrivalStation);
        fillDepartureDateField(departureDate);
        clickOnBuyTickets();
    }
}

