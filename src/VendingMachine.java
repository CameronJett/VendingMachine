
public class VendingMachine {
    private String display;
    private double currentAmount;

    public VendingMachine() {
        this.display = "INSERT COIN";
    }

    public String getDisplay() {
        display = "INSERT COIN";
        if (currentAmount > 0) {
            display = "$ " + currentAmount;
        }
        return display;
    }

    public void insert(String coin) {
        if (coin.equals("NICKEL")) {
            currentAmount += .05;
        }
    }
}
