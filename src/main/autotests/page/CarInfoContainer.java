package page;

import datamodel.CarInfo;
import datamodel.SeatInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebElementContainer;

import java.util.ArrayList;
import java.util.List;

public class CarInfoContainer extends WebElementContainer {
    @FindBy(tagName = "a")
    private WebElement carNumber;

    @FindBy(xpath = ".//*[contains(@class, 's-type-seat') or contains(@class, 's-type-dn ') or contains(@class, 's-type-up')]")
    private List<WebElement> seatsList;

    public CarInfoContainer(WebElement webElement, WebDriver webDriver) {
        super(webElement, webDriver);
    }

    public CarInfo getCarInfo() {
        return new CarInfo(getCarNumber(), getAllSeats());
    }

    public String getCarNumber() {
        return carNumber.getText();
    }

    public void openCarInfo() {
        waitForElementClickable(carNumber);
        carNumber.click();
    }

    public List<SeatInfo> getAllSeats() {
        waitForElementsVisible(seatsList);
        List<SeatInfo> seats = new ArrayList<SeatInfo>();
        for (WebElement seat : seatsList) {
            SeatInfo seatInfo = new SeatInfo(seat.getText(), !seat.getAttribute("class").contains("s-occup"));
            seats.add(seatInfo);
        }
        return seats;
    }
}
