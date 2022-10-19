import java.text.DecimalFormat;
import java.util.Scanner;

public class TVM {
    public int one_cent_coin;
    public int two_cents_coin;
    public int five_cents_coin;
    public int ten_cents_coin;
    public int twenty_cents_coin;
    public int fifty_cents_coin;
    public int one_euro_coin;
    public int two_euros_coin;
    public int five_euros_banknote;
    public int ten_euros_banknote;
    public int twenty_euros_banknote;

    private static final DecimalFormat dfZero = new DecimalFormat("0.00");

    String station;

    float amount_give;

    PaymentTerminal paymentTerminal = new PaymentTerminal();

    TVM() {
        System.out.println("Welcome to RATP TVM\n");

        this.initialMoney();
    }

    public double ticketSelection() {
        System.out.println("Which ticket do you want ?");
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
        double amountDue = Double.parseDouble(dfZero.format(price * numTicket)); //To make double round up to 2 decimal places
        System.out.printf("Total: %.2f euros\n", amountDue);
        System.out.println("Confirm ? [y/n]");
        String confirmation = scanner.next();
        if (confirmation == "n")
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
        one_euro_coin = 100;
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
        }
    }

    public void payByCard(double amountDue) {
        this.paymentTerminal.sendInfo(amountDue);
    }

    public void payInCash(double amountDue) {
        do {
            System.out.println(amountDue+" due");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please, enter cash.");
            double moneyEntered = scanner.nextDouble();
            if(amountAccepted(moneyEntered)){
                amountDue = difference(amountDue, moneyEntered);
            }
        } while (amountDue>=0.01);
        System.out.println("Printing Tickets...");
    }

    public boolean amountAccepted(double amount){
        double[] moneyAccepted = {0.01, 0.02, 0.05, 0.10, 0.20, 0.50, 1.00, 2.00, 5.00, 10.00, 20.00};
        for (double money : moneyAccepted) {
            if (amount == money) {
                System.out.println("You've entered "+amount);
                return true;
            }
        }
        System.out.println("Invalid. Please, enter a valid banknote or coin.");
        return false;
    }

    public double difference(double amountDue, double given){
        double difference = Double.parseDouble(dfZero.format(amountDue-given)); //To keep 2 decimal places
        if(difference<0) {
            giveBackChange(Math.abs(difference));
            System.out.println("In total, "+Math.abs(difference)+"€ given back.");
            return 0;
        }
        if(given == 20.00){
            this.twenty_euros_banknote++;
        }
        else if(given == 10.00){
            this.ten_euros_banknote++;
        }
        else if(given == 5.00){
            this.five_euros_banknote++;
        }
        else if(given == 2.00){
            this.two_euros_coin++;
        }
        else if(given == 1.00){
            this.one_euro_coin++;
        }
        else if(given == 0.50){
            this.fifty_cents_coin++;
        }
        else if(given == 0.20){
            this.twenty_cents_coin++;
        }
        else if(given == 0.10){
            this.ten_cents_coin++;
        }
        else if(given == 0.05){
            this.five_cents_coin++;
        }
        else if(given == 0.02){
            this.two_cents_coin++;
        }
        else if(given == 0.01){
            this.one_cent_coin++;
        }
        return difference;
    }

    public boolean checkMoneyChange(double money){
        if(money == 10.00){
            return this.ten_euros_banknote>0;
        }
        if(money == 5.00){
            return this.five_euros_banknote>0;
        }
        if(money == 2.00){
            return this.two_euros_coin>0;
        }
        if(money == 1.00){
            return this.one_euro_coin>0;
        }
        if(money == 0.50){
            return this.fifty_cents_coin>0;
        }
        if(money == 0.20){
            return this.twenty_cents_coin>0;
        }
        if(money == 0.10){
            return this.ten_cents_coin>0;
        }
        if(money == 0.05){
            return this.five_cents_coin>0;
        }
        if(money == 0.02){
            return this.two_cents_coin>0;
        }
        if(money == 0.01){
            return this.one_cent_coin>0;
        }
        return false;
    }

    public void giveBackChange(double amount){
        boolean noMoreChange = false;
        do {
            if(amount>=10 && checkMoneyChange(10.00)){
                amount = Double.parseDouble(dfZero.format(amount-10.00));
                this.ten_euros_banknote--;
                System.out.println("10.00€ banknote given back.");
            }
            else if (amount>=5 && checkMoneyChange(5.00)){
                amount = Double.parseDouble(dfZero.format(amount-5.00));
                this.five_euros_banknote--;
                System.out.println("5.00€ banknote given back.");
            }
            else if (amount>=2 && checkMoneyChange(2.00)){
                amount = Double.parseDouble(dfZero.format(amount-2.00));
                this.two_euros_coin--;
                System.out.println("2.00€ coin given back.");
            }
            else if (amount>=1 && checkMoneyChange(1.00)){
                amount = Double.parseDouble(dfZero.format(amount-1.00));
                this.one_euro_coin--;
                System.out.println("1.00€ coin given back.");
            }
            else if (amount>=0.5 && checkMoneyChange(0.50)){
                amount = Double.parseDouble(dfZero.format(amount-0.50));
                this.fifty_cents_coin--;
                System.out.println("0.50€ coin given back.");
            }
            else if (amount>=0.2 && checkMoneyChange(0.20)){
                amount = Double.parseDouble(dfZero.format(amount-0.20));
                this.twenty_cents_coin--;
                System.out.println("0.20€ coin given back.");
            }
            else if (amount>=0.1 && checkMoneyChange(0.10)){
                amount = Double.parseDouble(dfZero.format(amount-0.10));
                this.ten_cents_coin--;
                System.out.println("0.10€  coin given back.");
            }
            else if (amount>=0.05 && checkMoneyChange(0.05)){
                amount = Double.parseDouble(dfZero.format(amount-0.05));
                this.five_cents_coin--;
                System.out.println("0.05€ coin given back.");
            }
            else if (amount>=0.02 && checkMoneyChange(0.02)){
                amount = Double.parseDouble(dfZero.format(amount-0.02));
                this.twenty_cents_coin--;
                System.out.println("0.02€ coin given back.");
            }
            else if (amount>=0.01 && checkMoneyChange(0.01)){
                amount = Double.parseDouble(dfZero.format(amount-0.01));
                this.one_cent_coin--;
                System.out.println("0.01€ coin given back.");
            }
            else {
                System.out.println("Sorry no more change, please go to the reception desk and claim the rest of your money change. ");
                System.out.println("You're due "+amount);
                noMoreChange = true;
            }
        } while(amount>=0.01 || noMoreChange);
    }
}
