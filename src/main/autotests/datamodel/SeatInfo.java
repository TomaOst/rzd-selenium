package datamodel;

public class SeatInfo {
    private String seatNumber;

    private boolean isSeatFree;

    public SeatInfo(String seatNumber, boolean isSeatFree) {
        this.seatNumber = seatNumber;
        this.isSeatFree = isSeatFree;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isSeatFree() {
        return isSeatFree;
    }
}
