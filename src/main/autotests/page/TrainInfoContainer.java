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
    @FindBy(css = ".route-carType-item .move_tooltip")
    private List<WebElement> carTypeContainers;

    @FindBy(className = "route-cars-wrap")
    private WebElement carsListContainer;

    @FindBy(className = "route-item__cars-list__item")
    private List<CarInfoContainer> carsList;

    @FindBy(className = "route-trnum")
    private WebElement trainNumber;

    public TrainInfoContainer(WebElement webElement, WebDriver webDriver) {
        super(webElement, webDriver);
    }

    public String getTrainNumber() {
        return trainNumber.getText();
    }

    public TrainInfo getTrainInfoByCarType(String carType) {
        selectCarType(carType);
        return new TrainInfo(getTrainNumber(), getCarsInfo());
    }

    public CarInfo getCarInfoByNumber(String carNumber, String carType) {
        selectCarType(carType);
        CarInfoContainer targetCar = carsList.stream().filter(car ->
                car.getCarNumber().equals(carNumber)).findFirst().get();
        targetCar.openCarInfo();
        return targetCar.getCarInfo();
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
                waitForElementVisible(carsListContainer);
            }
        }
    }
}
