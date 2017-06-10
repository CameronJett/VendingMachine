import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private String display;
    private String dispensedItem;
    private double currentAmount;
    private List<String> coinReturn;
    private Map<String, Double> items;

    public VendingMachine() {
        display = "INSERT COIN";
        coinReturn = new ArrayList<>();
        items = new HashMap<>();
        items.put("COLA", 1.00);
        items.put("CHIPS", 0.50);
        items.put("CANDY", 0.65);
    }

    public String getDisplay() {
        //price is only displayed once then changes
        if (display.contains("PRICE") || display.contains("THANK")) {
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
        for (Map.Entry<String, Double> e : items.entrySet())
        {
            if (e.getKey().equals(item)) {
                if (currentAmount < e.getValue()) {
                    display = "PRICE: $" + String.format("%.2f", e.getValue());
                } else {
                    display = "THANK YOU";
                    dispensedItem = e.getKey();
                    currentAmount = 0;
                }
            }
        }
    }

    public String getDispensedItem() {
        return dispensedItem;
    }
}
