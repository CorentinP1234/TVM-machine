import java.util.Date;

public class SingleTravelTicket extends Ticket{
    static double price = 3;
    String departureStation;
    String arrivalStation;

    SingleTravelTicket(String departureStation, String arrivalStation) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }
}
