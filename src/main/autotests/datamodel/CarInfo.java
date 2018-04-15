package datamodel;

import java.util.List;

public class CarInfo {
    private String carNumber;

    private List<SeatInfo> seats;

    public CarInfo(String carNumber, List<SeatInfo> seats) {
        this.carNumber = carNumber;
        this.seats = seats;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public List<SeatInfo> getSeats() {
        return seats;
    }
}
