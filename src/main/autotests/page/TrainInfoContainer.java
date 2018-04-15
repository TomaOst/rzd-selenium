package page;

import datamodel.CarInfo;
import datamodel.TrainInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebElementContainer;

import java.util.ArrayList;
import java.util.List;

public class TrainInfoContainer extends WebElementContainer {
    @FindBy(xpath = ".//*[@class='route-carType-item']")
    private List<WebElement> carTypeContainers;

    @FindBy(className = "route-cars-wrap")
    private WebElement carsListContainer;

    @FindBy(className = "route-item__cars-list__item")
    private List<CarInfoContainer> carsList;

    @FindBy(className = "route-trnum")
    private WebElement trainNumber;

    public TrainInfoContainer(WebDriver webDriver, WebElement webElement) {
        super(webDriver, webElement);
    }

    public String getTrainNumber() {
        return trainNumber.getText();
    }

    public TrainInfo getTrainInfoByCarType(String carType) {
        selectCarType(carType);
        waitForElementVisible(carsListContainer);
        return new TrainInfo(getTrainNumber(), getCarsInfo());
    }

    public CarInfo getCarInfoByNumber(String carNumber) {
        CarInfo carInfo = null;
        for (CarInfoContainer car : carsList) {
            if (car.getCarNumber().equals(carNumber)) {
                car.openCarInfo();
                carInfo = car.getCarInfo();
            }
        }
        return carInfo;
    }

    public List<CarInfo> getCarsInfo() {
        List<CarInfo> carsInfo = new ArrayList<>();
        for (CarInfoContainer car : carsList) {
            car.openCarInfo();
            carsInfo.add(car.getCarInfo());
        }
        return carsInfo;
    }

    private void selectCarType(String carType) {
        for (WebElement carTypeContainer : carTypeContainers) {
            if (carTypeContainer.getText().contains(carType)) {
                carTypeContainer.click();
            }
        }
    }
}
