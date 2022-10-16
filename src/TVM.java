import java.util.Scanner;

public class TVM {
    public int one_cent_coin;
    public int two_cents_coin;
    public int five_cents_coin;
    public int ten_cents_coin;
    public int twenty_cents_coin;
    public int fifty_cents_coin;
    public int one_euros_coin;
    public int two_euros_coin;
    public int five_euros_banknote;
    public int ten_euros_banknote;
    public int twenty_euros_banknote;

    String station;

    float amount_give;

    PaymentTerminal paymentTerminal = new PaymentTerminal();

    TVM() {
        System.out.println("Welcome to RATP TVM\n");

        this.initialMoney();
    }

    public double ticketSelection() {
        System.out.println("Wich ticket do you want ?");
        System.out.printf("1. Day Ticket (%s euros)\n", DayTicket.price);
        System.out.printf("2. Week Ticket (%s euros)\n", WeekTicket.price);
        System.out.printf("3. Ticket+ (%s euros)\n", TicketPlus.price);
        System.out.printf("4. Single Travel Ticket (%s euros)\n", SingleTravelTicket.price);

        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        String strTicket;
        String str = "You choose ";
        double price;
        switch (selection) {
            case 1:
                price = DayTicket.price;
                strTicket = "Day Ticket";
                System.out.println(str + strTicket);
                break;
            case 2:
                price = WeekTicket.price;
                strTicket = "Week Ticket";
                System.out.println(str + strTicket);
                break;
            case 3:
                price = TicketPlus.price;
                strTicket = "Ticket +";
                System.out.println(str + strTicket);
                break;
            case 4:
                price = SingleTravelTicket.price;
                strTicket = "Single Travel Ticket";
                System.out.println(str + strTicket);
                System.out.println("Select destination");
                scanner.next();
                break;

            default:
                System.out.println("Wrong Selection");
                return this.ticketSelection();
        }
        System.out.println("\nHow many ticket do you want");
        int numTicket = scanner.nextInt();
        double amountDue = price * numTicket;
        System.out.printf("Total: %.2f euros\n", amountDue);
        System.out.println("Confirm ? [y/n]");
        String confimation = scanner.next();
        if (confimation == "n")
            this.ticketSelection();
        return amountDue;
    }

    public void initialMoney() {
        one_cent_coin = 100;
        two_cents_coin = 100;
        five_cents_coin = 100;
        ten_cents_coin = 100;
        twenty_cents_coin = 100;
        fifty_cents_coin = 100;
        one_euros_coin = 100;
        two_euros_coin = 100;
        five_euros_banknote = 100;
        ten_euros_banknote = 100;
        twenty_euros_banknote = 100;
    }

    public void choosePaymentMethod(double amountDue) {
        System.out.println("How do u want to pay ?");
        System.out.println("1. Pay in Cash");
        System.out.println("2. Pay by Card");
        Scanner scanner = new Scanner(System.in);
        int paymentMethod = scanner.nextInt();

        switch (paymentMethod) {
            case 1:
                // Cash
                this.payInCash(amountDue);
                break;
            case 2:
                // Card
                this.payByCard(amountDue);
                break;
            default:
                System.out.println("Wrong Selection");
                this.choosePaymentMethod(amountDue);
                return;
        }
    }

    public void payByCard(double amountDue) {
        this.paymentTerminal.sendInfo();
        System.out.println("Payment Validated");
        System.out.println("Printing Tickets...");
    }
    public void payInCash(double amountDue) {
        // TODO
        System.out.println("Printing Tickets...");
    }
}
