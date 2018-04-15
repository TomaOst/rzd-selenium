package datamodel;

public class TripInfo {
    private String departureStation;

    private String arrivalStation;

    private String departureDate;

    public TripInfo(String departureStation, String arrivalStation, String departureDate) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureDate = departureDate;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public String getDepartureDate() {
        return departureDate;
    }

}
