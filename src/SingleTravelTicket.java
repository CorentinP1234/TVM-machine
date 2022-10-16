import java.util.Date;

public class SingleTravelTicket extends Ticket{
    String departureStation;
    String arrivalStation;

    SingleTravelTicket(String departureStation, String arrivalStation) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }
}
