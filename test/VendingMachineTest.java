import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static junit.framework.TestCase.assertEquals;

public class VendingMachineTest {
    VendingMachine vendingMachine;

    @Before
    public void setUp() {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put(Consts.COLA, 1);
        inventory.put(Consts.CHIPS, 1);
        inventory.put(Consts.CANDY, 1);
        vendingMachine = new VendingMachine(Consts.STARTING_CHANGE, inventory);
    }

    @Test
    public void whenVendingMachineHasNoCoinsItDisplaysInsertCoin() {
        assertEquals(Consts.INSERT_COIN, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesNickelItDisplaysFiveCents() {
        vendingMachine.insert(Consts.NICKEL);
        assertEquals(Consts.DOLLAR_SIGN + Consts.NICKEL_VALUE, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesDimeItDisplaysTenCents() {
        vendingMachine.insert(Consts.DIME);
        assertEquals(Consts.DOLLAR_SIGN + Consts.DIME_VALUE, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesQuarterItDisplaysTwentyFiveCents() {
        vendingMachine.insert(Consts.QUARTER);
        assertEquals(Consts.DOLLAR_SIGN + Consts.QUARTER_VALUE, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesPennyItIsReturned() {
        List<String> expectedCoins = new ArrayList<>();
        expectedCoins.add(Consts.PENNY);
        vendingMachine.insert(Consts.PENNY);
        assertEquals(expectedCoins, vendingMachine.getCoinReturn());
    }

    @Test
    public void whenVendingMachineReceivesPennyItDoesNotChangeDisplay() {
        vendingMachine.insert(Consts.PENNY);
        assertEquals(Consts.INSERT_COIN, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesTwoPenniesTheyAreBothReturned() {
        List<String> expectedCoins = new ArrayList<>();
        expectedCoins.add(Consts.PENNY);
        expectedCoins.add(Consts.PENNY);
        vendingMachine.insert(Consts.PENNY);
        vendingMachine.insert(Consts.PENNY);
        assertEquals(expectedCoins, vendingMachine.getCoinReturn());
    }

    @Test
    public void whenVendingMachineColaIsSelectedAndThereIsNotEnoughMoneyInsertedPriceIsDisplayed() {
        vendingMachine.selectItem(Consts.COLA);
        assertEquals(Consts.PRICE + Consts.COLA_PRICE, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineChipsAreSelectedAndThereIsNotEnoughMoneyInsertedPriceIsDisplayed() {
        vendingMachine.selectItem(Consts.CHIPS);
        assertEquals(Consts.PRICE + Consts.CHIPS_PRICE, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineCandyIsSelectedAndThereIsNotEnoughMoneyInsertedPriceIsDisplayed() {
        vendingMachine.selectItem(Consts.CANDY);
        assertEquals(Consts.PRICE + Consts.CANDY_PRICE, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDisplaysAPriceItThenDisplaysInsertCoinWhenNoCoinsWereInserted() {
        vendingMachine.selectItem(Consts.COLA);
        vendingMachine.getDisplay();
        assertEquals(Consts.INSERT_COIN, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDisplaysAPriceItThenDisplaysFiveCentsWhenANickelWasInserted() {
        vendingMachine.insert(Consts.NICKEL);
        vendingMachine.selectItem(Consts.COLA);
        vendingMachine.getDisplay();
        assertEquals(Consts.DOLLAR_SIGN + Consts.NICKEL_VALUE, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineColaIsSelectedAndThereIsEnoughMoneyInsertedTheProductIsDispensed() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.COLA);
        assertEquals(Consts.COLA, vendingMachine.getDispensedItem());
    }

    @Test
    public void whenVendingMachineColaIsSelectedAndThereIsEnoughMoneyInsertedThankYouIsDisplayed() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.COLA);
        assertEquals(Consts.THANK_YOU, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineChipsIsSelectedAndThereIsEnoughMoneyInsertedTheProductIsDispensed() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.CHIPS);
        assertEquals(Consts.CHIPS, vendingMachine.getDispensedItem());
    }

    @Test
    public void whenVendingMachineChipsIsSelectedAndThereIsEnoughMoneyInsertedThankYouIsDisplayed() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.CHIPS);
        assertEquals(Consts.THANK_YOU, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineCandyIsSelectedAndThereIsEnoughMoneyInsertedTheProductIsDispensed() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.CANDY);
        assertEquals(Consts.CANDY, vendingMachine.getDispensedItem());
    }

    @Test
    public void whenVendingMachineCandyIsSelectedAndThereIsEnoughMoneyInsertedThankYouIsDisplayed() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.CANDY);
        assertEquals(Consts.THANK_YOU, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineSellsColaAndDisplaysThankYouItThenDisplaysInsertCoin() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.COLA);
        vendingMachine.getDisplay();
        assertEquals(Consts.INSERT_COIN, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineCandyIsSelectedAndExtraMoneyWasInsertedChangeIsReturned() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.CANDY);
        List<String> expectedCoins = new ArrayList<>();
        expectedCoins.add(Consts.DIME);
        assertEquals(expectedCoins, vendingMachine.getCoinReturn());
    }

    @Test
    public void whenVendingMachineReturnCoinsButtonIsPressedCoinsAreReturned() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.DIME);
        vendingMachine.insert(Consts.NICKEL);
        List<String> expectedCoins = new ArrayList<>();
        expectedCoins.add(Consts.QUARTER);
        expectedCoins.add(Consts.DIME);
        expectedCoins.add(Consts.NICKEL);
        vendingMachine.selectReturnCoins();
        assertEquals(expectedCoins, vendingMachine.getCoinReturn());
    }

    @Test
    public void whenVendingMachineReturnCoinsButtonIsPressedCoinsAreReturnedAfterBuyingAnItem() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.DIME);
        vendingMachine.insert(Consts.NICKEL);
        vendingMachine.selectItem(Consts.CHIPS);
        vendingMachine.getCoinReturn();
        vendingMachine.insert(Consts.QUARTER);
        List<String> expectedCoins = new ArrayList<>();
        expectedCoins.add(Consts.QUARTER);
        vendingMachine.selectReturnCoins();
        assertEquals(expectedCoins, vendingMachine.getCoinReturn());
    }

    @Test
    public void whenVendingMachineHasNoColaLeftItDisplaysSoldOut() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.COLA);
        vendingMachine.selectItem(Consts.COLA);
        assertEquals(Consts.SOLD_OUT, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDisplaysSoldOutItThenDisplaysInsertCoin() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.COLA);
        vendingMachine.selectItem(Consts.COLA);
        vendingMachine.getDisplay();
        assertEquals(Consts.INSERT_COIN, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDisplaysSoldOutItThenDisplaysCurrentAmount() {
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.COLA);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.COLA);
        vendingMachine.getDisplay();
        assertEquals(Consts.DOLLAR_SIGN + Consts.QUARTER_VALUE, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDoesNotHaveEnoughChangeItDisplaysExactChangeOnlyInsteadOfInsertCoin() {
        vendingMachine = new VendingMachine(Consts.ZERO_DOLLARS);
        assertEquals(Consts.EXACT_CHANGE, vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDoesNotHaveEnoughChangeAndReceivesEnoughMoneyToHaveChangeItDisplaysInsertCoin() {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put(Consts.COLA, 1);
        vendingMachine = new VendingMachine(Consts.ZERO_DOLLARS, inventory);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.insert(Consts.QUARTER);
        vendingMachine.selectItem(Consts.COLA);
        vendingMachine.getDisplay();
        assertEquals(Consts.INSERT_COIN, vendingMachine.getDisplay());
    }

    @Test
    public void whenCoinReturnIsCheckedItIsEmptied() {
        vendingMachine.insert(Consts.PENNY);
        List<String> noCoins = new ArrayList<>();
        vendingMachine.getCoinReturn();
        assertEquals(noCoins, vendingMachine.getCoinReturn());
    }
}
