package datamodel;

import java.util.List;

public class TrainInfo {
    private String trainNumber;

    private List<CarInfo> cars;

    public TrainInfo(String trainNumber, List<CarInfo> cars) {
        this.trainNumber = trainNumber;
        this.cars = cars;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public List<CarInfo> getCars() {
        return cars;
    }
}
