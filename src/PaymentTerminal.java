import java.text.DecimalFormat;
import java.util.Scanner;

public class PaymentTerminal {

    private static final DecimalFormat dfZero = new DecimalFormat("0.00");

    public void sendInfo(double amountDue) {
        System.out.println("Communicating with Credit Card Company..");
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much does the user have in Bank Account? ");
        double userSavings = 0;
        try {
            String capture = scanner.next();
            if(Double.parseDouble(capture)>0){
                userSavings = Double.parseDouble(capture);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter the user bank account savings. ");
        }
        if(userSavings-amountDue<0){
            System.out.println("You have "+userSavings+" left in your bank account.");
            System.out.println("Payment refused.");
        }
        else {
            userSavings = Double.parseDouble(dfZero.format(userSavings-amountDue));
            System.out.println("You have "+userSavings+" left in your bank account.");
            System.out.println("Payment accepted");
            System.out.println("Printing Tickets...");
        }
    }
}
