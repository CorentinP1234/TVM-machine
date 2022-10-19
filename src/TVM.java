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
        twenty_euros_banknote = 1;
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
        this.paymentTerminal.sendInfo();
        System.out.println("Payment Validated");
        System.out.println("Printing Tickets...");
    }

    public void payInCash(double amountDue) {
        do {
            System.out.println(amountDue+" due");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. 0.01€");
            System.out.println("2. 0.02€");
            System.out.println("3. 0.05€");
            System.out.println("4. 0.10€");
            System.out.println("5. 0.20€");
            System.out.println("6. 0.50€");
            System.out.println("7. 1.00€");
            System.out.println("8. 2.00€");
            System.out.println("9. 5.00€");
            System.out.println("10. 10.00€");
            System.out.println("11. 20.00€");
            int moneySelection = scanner.nextInt();
            switch (moneySelection) {
                case 1:
                    if(youHaveTheMoney(0.01)){
                        System.out.println("You entered 0.01€");
                        amountDue = difference(amountDue, 0.01);
                        this.one_cent_coin--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 2:
                    if(youHaveTheMoney(0.02)){
                        System.out.println("You entered 0.02€");
                        amountDue = difference(amountDue, 0.02);
                        this.two_cents_coin--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 3:
                    if(youHaveTheMoney(0.05)){
                        System.out.println("You entered 0.05€");
                        amountDue = difference(amountDue, 0.05);
                        this.five_cents_coin--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 4:
                    if(youHaveTheMoney(0.10)){
                        System.out.println("You entered 0.10€");
                        amountDue = difference(amountDue, 0.10);
                        this.ten_cents_coin--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 5:
                    if(youHaveTheMoney(0.20)){
                        System.out.println("You entered 0.20€");
                        amountDue = difference(amountDue, 0.20);
                        this.twenty_cents_coin--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 6:
                    if(youHaveTheMoney(0.50)){
                        System.out.println("You entered 0.50€");
                        amountDue = difference(amountDue, 0.50);
                        this.fifty_cents_coin--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 7:
                    if(youHaveTheMoney(1.00)){
                        System.out.println("You entered 1.00€");
                        amountDue = difference(amountDue, 1.00);
                        this.one_euro_coin--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 8:
                    if(youHaveTheMoney(2.00)){
                        System.out.println("You entered 2.00€");
                        amountDue = difference(amountDue, 2.00);
                        this.two_euros_coin--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 9:
                    if(youHaveTheMoney(5.00)){
                        System.out.println("You entered 5.00€");
                        amountDue = difference(amountDue, 5.00);
                        this.five_euros_banknote--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 10:
                    if(youHaveTheMoney(10.00)){
                        System.out.println("You entered 10.00€");
                        amountDue = difference(amountDue, 10.00);
                        this.ten_euros_banknote--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                case 11:
                    if(youHaveTheMoney(20.00)){
                        System.out.println("You entered 20.00€");
                        amountDue = difference(amountDue, 20.00);
                        this.twenty_euros_banknote--;
                    } else {
                        System.out.println("Waiting for you to enter something...");
                    }
                    break;
                default:
                    System.out.println("Wrong Selection");
                    break;
            }
        } while (amountDue>=0.01);
        System.out.println("Printing Tickets...");
    }

    public boolean youHaveTheMoney(double amount){
        if(amount==0.01){
            return this.one_cent_coin>0;
        }
        if(amount==0.02){
            return this.two_cents_coin>0;
        }
        if(amount==0.05){
            return this.five_cents_coin>0;
        }
        if(amount==0.10){
            return this.ten_cents_coin>0;
        }
        if(amount==0.20){
            return this.twenty_cents_coin>0;
        }
        if(amount==0.50){
            return this.fifty_cents_coin>0;
        }
        if(amount==1.00){
            return this.one_euro_coin>0;
        }
        if(amount==2.00){
            return this.two_euros_coin>0;
        }
        if(amount==5.00){
            return this.five_euros_banknote>0;
        }
        if(amount==10.00){
            return this.ten_euros_banknote>0;
        }
        if(amount==20.00){
            return this.twenty_euros_banknote > 0;
        }
        return(false);
    }

    public double difference(double amountDue, double given){
        double difference = Double.parseDouble(dfZero.format(amountDue-given));
        if(difference<0) {
            giveBackChange(Math.abs(difference));
            System.out.println("In total, "+Math.abs(difference)+" given back.");
            return 0;
        }
        return difference;
    }

    public void giveBackChange(double amount){
        do {
            if(amount>10){
                amount -= 10.00;
                this.ten_euros_banknote++;
                System.out.println("10.00€ banknote given back.");
            }
            else if (amount>5){
                amount -= 5.00;
                this.five_euros_banknote++;
                System.out.println("5.00€ banknote given back.");
            }
            else if (amount>2){
                amount -= 2.00;
                this.two_euros_coin++;
                System.out.println("2.00€ coin given back.");
            }
            else if (amount>1){
                amount -= 1.00;
                this.one_euro_coin++;
                System.out.println("1.00€ coin given back.");
            }
            else if (amount>0.5){
                amount -= 0.50;
                this.fifty_cents_coin++;
                System.out.println("0.50€ coin given back.");
            }
            else if (amount>0.2){
                amount -= 0.20;
                this.twenty_cents_coin++;
                System.out.println("0.20€ coin given back.");
            }
            else if (amount>0.1){
                amount -= 0.10;
                this.ten_cents_coin++;
                System.out.println("0.10€  coin given back.");
            }
            else if (amount>0.05){
                amount -= 0.05;
                this.five_cents_coin++;
                System.out.println("0.05€ coin given back.");
            }
            else if (amount>0.02){
                amount -= 0.02;
                this.twenty_cents_coin++;
                System.out.println("0.02€ coin given back.");
            }
            else if (amount>0.01){
                amount -= 0.01;
                this.one_cent_coin++;
                System.out.println("0.01€ coin given back.");
            }
        } while(amount>=0.01);
    }
}
