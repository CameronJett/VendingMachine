import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private String display;
    private String dispensedItem;
    private BigDecimal currentAmount;
    private List<String> coinReturn;
    //assuming we want to return actual customer coins instead of coins equal to total coins
    private List<String> customerCoins;
    private Map<String, BigDecimal> items;
    private Map<String, Integer> inventory;

    public VendingMachine() {
        display = "INSERT COIN";
        coinReturn = new ArrayList<>();
        customerCoins = new ArrayList<>();
        currentAmount = new BigDecimal("0.00");

        items = new HashMap<>();
        items.put("COLA", new BigDecimal("1.00"));
        items.put("CHIPS", new BigDecimal("0.50"));
        items.put("CANDY", new BigDecimal("0.65"));

        inventory = new HashMap<>();
        inventory.put("COLA", 1);
        inventory.put("CHIPS", 1);
        inventory.put("CANDY", 1);
    }

    public String getDisplay() {
        //price is only displayed once then changes
        if (display.contains("PRICE") || display.contains("THANK") || display.contains(("SOLD"))) {
            String tempDisplay = display;
            display = "";
            return tempDisplay;
        }

        display = "INSERT COIN";
        if (currentAmount.compareTo(BigDecimal.ZERO) > 0) {
            display = "$ " + currentAmount.toString();
        }
        return display;
    }

    public void insert(String coin) {
        if (coin.equals("NICKEL")) {
            currentAmount = currentAmount.add(new BigDecimal(".05"));
            customerCoins.add(coin);
        } else if (coin.equals("DIME")) {
            currentAmount = currentAmount.add(new BigDecimal(".10"));
            customerCoins.add(coin);
        } else if (coin.equals("QUARTER")) {
            currentAmount = currentAmount.add(new BigDecimal(".25"));
            customerCoins.add(coin);
        } else {
            rejectCoin(coin);
        }
    }

    private void rejectCoin(String coin) {
        coinReturn.add(coin);
    }

    public List<String> getCoinReturn() {
        List<String> coins = new ArrayList<>(coinReturn);
        coinReturn.clear();
        return coins;
    }

    public void selectItem(String item) {
        for (Map.Entry<String, BigDecimal> e : items.entrySet())
        {
            if (e.getKey().equals(item)) {
                Integer itemInventory = inventory.get(item);
                if (itemInventory > 0) {
                    if (currentAmount.compareTo(e.getValue()) < 0) {
                        display = "PRICE: $" + e.getValue().toString();
                    } else {
                        display = "THANK YOU";
                        dispensedItem = e.getKey();
                        inventory.put(item, itemInventory-1);
                        currentAmount = currentAmount.subtract(e.getValue());
                        MakeChange(currentAmount);
                    }
                } else {
                    display = "SOLD OUT";
                }
            }
        }
    }

    private void MakeChange(BigDecimal  changeAmount) {
        while (changeAmount.compareTo(BigDecimal.ZERO) != 0) {
            if (changeAmount.compareTo(new BigDecimal(".25")) >= 0) {
                coinReturn.add("QUARTER");
                changeAmount = changeAmount.subtract(new BigDecimal(".25"));
            } else if (changeAmount.compareTo(new BigDecimal(".10")) >= 0) {
                coinReturn.add("DIME");
                changeAmount = changeAmount.subtract(new BigDecimal(".10"));
            } else if (changeAmount.compareTo(new BigDecimal(".05")) >= 0) {
                coinReturn.add("NICKEL");
                changeAmount = changeAmount.subtract(new BigDecimal(".05"));
            }
        }
        customerCoins.clear();
    }

    public String getDispensedItem() {
        return dispensedItem;
    }

    public void selectReturnCoins() {
        for (String e : customerCoins) {
            rejectCoin(e);
        }
    }
}
