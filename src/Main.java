public class Main {
    public static void main(String[] args) {
        TVM tvm = new TVM();
        tvm.choosePaymentMethod(tvm.ticketSelection());
        tvm.initialMoney();
        //TODO
        // - TVM.payInCash()
    }

}
