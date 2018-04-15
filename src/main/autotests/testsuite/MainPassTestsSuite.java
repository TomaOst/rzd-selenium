package testsuite;

import datamodel.CarType;
import datamodel.TrainInfo;
import org.junit.Test;
import page.MainPassPage;
import page.TicketsPage;

public class MainPassTestsSuite extends TestSetup {
    @Test
    public void testTicketSelection() {
        MainPassPage mainPassPage = new MainPassPage(webDriver);
        mainPassPage.init();
        mainPassPage.searchTickets("Москва", "Тула", "18.04.2018");

        TicketsPage ticketsPage = new TicketsPage(webDriver);
        ticketsPage.init();
        ticketsPage.waitForLoad();
        TrainInfo trainByNumber = ticketsPage.getTrainInfoByNumberAndCarType("119А", CarType.STATEROOM);
    }
}
