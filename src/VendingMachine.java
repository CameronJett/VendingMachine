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
    private BigDecimal availableChange;

    public VendingMachine(String startingChange) {
        this(startingChange, new HashMap<String, Integer>());
    }

    public VendingMachine(String startingChange, Map<String, Integer> startingInventory) {
        display = Consts.INSERT_COIN;
        coinReturn = new ArrayList<>();
        customerCoins = new ArrayList<>();
        currentAmount = new BigDecimal(Consts.ZERO_DOLLARS);
        availableChange = new BigDecimal(startingChange);

        items = new HashMap<>();
        items.put(Consts.COLA, new BigDecimal(Consts.COLA_PRICE));
        items.put(Consts.CHIPS, new BigDecimal(Consts.CHIPS_PRICE));
        items.put(Consts.CANDY, new BigDecimal(Consts.CANDY_PRICE));

        inventory = new HashMap<>(startingInventory);
    }

    public String getDisplay() {
        //price is only displayed once then changes
        if (display.contains(Consts.PRICE) || display.contains(Consts.THANK_YOU) || display.contains((Consts.SOLD_OUT))) {
            String tempDisplay = display;
            display = Consts.INSERT_COIN;
            return tempDisplay;
        }

        display = Consts.INSERT_COIN;
        if (availableChange.compareTo(new BigDecimal(Consts.MINIMUM_CHANGE_NEEDED)) < 0) {
            display = Consts.EXACT_CHANGE;
        }
        if (currentAmount.compareTo(BigDecimal.ZERO) > 0) {
            display = Consts.DOLLAR_SIGN + currentAmount.toString();
        }
        return display;
    }

    public void insert(String coin) {
        String coinValue = Consts.ZERO_DOLLARS;
        switch (coin) {
            case Consts.NICKEL:
                coinValue = Consts.NICKEL_VALUE;
                break;
            case Consts.DIME:
                coinValue = Consts.DIME_VALUE;
                break;
            case Consts.QUARTER:
                coinValue = Consts.QUARTER_VALUE;
                break;
            default:
                rejectCoin(coin);
                break;
        }
        currentAmount = currentAmount.add(new BigDecimal(coinValue));
        customerCoins.add(coin);
        availableChange = availableChange.add(new BigDecimal(coinValue));
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
                        display = Consts.PRICE + e.getValue().toString();
                    } else {
                        display = Consts.THANK_YOU;
                        dispensedItem = e.getKey();
                        inventory.put(item, itemInventory-1);
                        currentAmount = currentAmount.subtract(e.getValue());
                        MakeChange(currentAmount);
                        currentAmount = new BigDecimal(Consts.ZERO_DOLLARS);
                    }
                } else {
                    display = Consts.SOLD_OUT;
                }
            }
        }
    }

    public String getDispensedItem() {
        String itemToReturn = dispensedItem;
        dispensedItem = "";
        return itemToReturn;
    }

    public void selectReturnCoins() {
        for (String e : customerCoins) {
            rejectCoin(e);
        }
    }

    private void rejectCoin(String coin) {
        coinReturn.add(coin);
    }

    private void MakeChange(BigDecimal  changeAmount) {
        availableChange = availableChange.subtract(changeAmount);

        while (changeAmount.compareTo(BigDecimal.ZERO) != 0) {
            if (changeAmount.compareTo(new BigDecimal(Consts.QUARTER_VALUE)) >= 0) {
                coinReturn.add(Consts.QUARTER);
                changeAmount = changeAmount.subtract(new BigDecimal(Consts.QUARTER_VALUE));
            } else if (changeAmount.compareTo(new BigDecimal(Consts.DIME_VALUE)) >= 0) {
                coinReturn.add(Consts.DIME);
                changeAmount = changeAmount.subtract(new BigDecimal(Consts.DIME_VALUE));
            } else if (changeAmount.compareTo(new BigDecimal(Consts.NICKEL_VALUE)) >= 0) {
                coinReturn.add(Consts.NICKEL);
                changeAmount = changeAmount.subtract(new BigDecimal(Consts.NICKEL_VALUE));
            }
        }
        customerCoins.clear();
    }
}
