package page;

import datamodel.CarInfo;
import datamodel.TrainInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TicketsPage extends Page {
    @FindBy(className = "route-items-cont")
    private WebElement trainsListContainer;

    @FindBy(className = "route-item")
    private List<TrainInfoContainer> trainsList;

    public TicketsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public TrainInfo getTrainInfoByNumberAndCarType(String trainNumber, String carType) {
        TrainInfoContainer t = getTrainByNumber(trainNumber);
        return getTrainByNumber(trainNumber).getTrainInfoByCarType(carType);
    }

    public CarInfo getCarInfoByNumberAndCarType(String trainNumber, String carNumber, String carType) {
        return getTrainByNumber(trainNumber).getCarInfoByNumber(carNumber, carType);
    }

    private TrainInfoContainer getTrainByNumber(String trainNumber) {
        waitForElementVisible(trainsListContainer);
        return trainsList.stream().filter(train ->
                train.getTrainNumber().equals(trainNumber)).findFirst().get();
    }
}
