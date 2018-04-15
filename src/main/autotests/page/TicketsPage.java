package page;

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
        waitForElementVisible(trainsListContainer);
        for (TrainInfoContainer train : trainsList) {
            if (train.getTrainNumber().equals(trainNumber)) {
                return train.getTrainInfoByCarType(carType);
            }
        }
        return null;
    }
}
