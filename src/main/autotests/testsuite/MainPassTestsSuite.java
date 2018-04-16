package testsuite;

import datamodel.CarInfo;
import datamodel.CarType;
import datamodel.SeatInfo;
import datamodel.TrainInfo;
import org.junit.Assert;
import org.junit.Test;
import page.MainPassPage;
import page.TicketsPage;

public class MainPassTestsSuite extends TestSetup {
    @Test
    public void checkAllPresentedCarsHaveFreeSeats() {
        initMainPassPage().searchTickets("Москва", "Тула", "18.04.2018");

        TrainInfo trainByNumber = initTicketsPage().getTrainInfoByNumberAndCarType("119А", CarType.STATEROOM);
        Assert.assertTrue(trainByNumber.getCars().stream().anyMatch(car ->
                car.getSeats().stream().anyMatch(SeatInfo::isSeatFree)));
    }

    @Test
    public void checkSelectedCarHasExpectedFreeSeatsNumber(){
        initMainPassPage().searchTickets("Москва", "Тула", "18.04.2018");

        CarInfo currentCur = initTicketsPage().getCarInfoByNumberAndCarType("119А", "11", CarType.STATEROOM);
        Assert.assertEquals(27, currentCur.getSeats().stream().filter(SeatInfo::isSeatFree).count());
    }


    private MainPassPage initMainPassPage() {
        MainPassPage mainPassPage = new MainPassPage(webDriver);
        mainPassPage.init();
        return mainPassPage;
    }

    private TicketsPage initTicketsPage() {
        TicketsPage ticketsPage = new TicketsPage(webDriver);
        ticketsPage.init();
        ticketsPage.waitForLoad();
        return ticketsPage;
    }
}
