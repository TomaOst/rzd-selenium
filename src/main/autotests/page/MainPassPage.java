package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Button;
import selenium.CalendarTextField;
import selenium.TextField;

public class MainPassPage extends Page {
    public MainPassPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(id = "name0")
    private TextField departureStationField;

    @FindBy(id = "name1")
    private TextField arrivalStationField;

    @FindBy(id = "date0")
    private CalendarTextField departureDateField;

    @FindBy(id = "date1")
    private CalendarTextField departureBackDateField;

    @FindBy(id = "Submit")
    private Button buyTicketButton;

    public void fillDepartureStationField(String departureStation) {
        departureStationField.fill(departureStation);
    }

    public void fillArrivalStationField(String arrivalStation) {
        arrivalStationField.fill(arrivalStation);
    }

    public void fillDepartureDateField(String departureDate) {
        departureDateField.fill(departureDate);
    }

    public void fillDepartureBackDateField(String departureBackDate) {
        departureBackDateField.fill(departureBackDate);
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

