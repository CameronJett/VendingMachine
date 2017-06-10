
public class VendingMachine {
    private String display;
    private double currentAmount;
    private String coinReturn;

    public VendingMachine() {
        this.display = "INSERT COIN";
    }

    public String getDisplay() {
        display = "INSERT COIN";
        if (currentAmount > 0) {
            display = "$ " + String.format("%.2f", currentAmount);
        }
        return display;
    }

    public void insert(String coin) {
        if (coin.equals("NICKEL")) {
            currentAmount += .05;
        } else if (coin.equals("DIME")) {
            currentAmount += .10;
        } else if (coin.equals("QUARTER")) {
            currentAmount += .25;
        } else {
            rejectCoin(coin);
        }
    }

    private void rejectCoin(String coin) {
        coinReturn = coin;
    }

    public String getCoinReturn() {
        return coinReturn;
    }
}
