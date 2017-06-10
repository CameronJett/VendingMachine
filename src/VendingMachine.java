import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private String display;
    private String dispensedItem;
    private double currentAmount;
    private List<String> coinReturn;

    public VendingMachine() {
        display = "INSERT COIN";
        coinReturn = new ArrayList<>();
    }

    public String getDisplay() {
        //price is only displayed once then changes
        if (display.contains("PRICE")) {
            String tempDisplay = display;
            display = "";
            return tempDisplay;
        }

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
        coinReturn.add(coin);
    }

    public List<String> getCoinReturn() {
        return coinReturn;
    }

    public void selectItem(String item) {
        if (item.equals("COLA") && currentAmount < 1) {
            display = "PRICE: $1.00";
        } else if (item.equals("CHIPS") && currentAmount < .5) {
            display = "PRICE: $0.50";
        } else if (item.equals("CANDY") && currentAmount < .65) {
            display = "PRICE: $0.65";
        }

        if (item.equals("COLA") && currentAmount >= 1) {
            dispensedItem = "COLA";
        }
    }

    public String getDispensedItem() {
        return dispensedItem;
    }
}
