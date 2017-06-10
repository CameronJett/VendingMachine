import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class VendingMachineTest {
    VendingMachine vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void whenVendingMachineHasNoCoinsItDisplaysInsertCoin() {
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesNickelItDisplaysFiveCents() {
        vendingMachine.insert("NICKEL");
        assertEquals("$ 0.05", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesDimeItDisplaysTenCents() {
        vendingMachine.insert("DIME");
        assertEquals("$ 0.10", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesQuarterItDisplaysTwentyFiveCents() {
        vendingMachine.insert("QUARTER");
        assertEquals("$ 0.25", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesPennyItIsReturned() {
        List<String> expectedCoins = new ArrayList<>();
        expectedCoins.add("PENNY");
        vendingMachine.insert("PENNY");
        assertEquals(expectedCoins, vendingMachine.getCoinReturn());
    }

    @Test
    public void whenVendingMachineReceivesPennyItDoesNotChangeDisplay() {
        vendingMachine.insert("PENNY");
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesTwoPenniesTheyAreBothReturned() {
        List<String> expectedCoins = new ArrayList<>();
        expectedCoins.add("PENNY");
        expectedCoins.add("PENNY");
        vendingMachine.insert("PENNY");
        vendingMachine.insert("PENNY");
        assertEquals(expectedCoins, vendingMachine.getCoinReturn());
    }

    @Test
    public void whenVendingMachineColaIsSelectedAndThereIsNotEnoughMoneyInsertedPriceIsDisplayed() {
        vendingMachine.selectItem("COLA");
        assertEquals("PRICE: $1.00", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineChipsAreSelectedAndThereIsNotEnoughMoneyInsertedPriceIsDisplayed() {
        vendingMachine.selectItem("CHIPS");
        assertEquals("PRICE: $0.50", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineCandyIsSelectedAndThereIsNotEnoughMoneyInsertedPriceIsDisplayed() {
        vendingMachine.selectItem("CANDY");
        assertEquals("PRICE: $0.65", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDisplaysAPriceItThenDisplaysInsertCoinWhenNoCoinsWereInserted() {
        vendingMachine.selectItem("COLA");
        vendingMachine.getDisplay();
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDisplaysAPriceItThenDisplaysFiveCentsWhenANickelWasInserted() {
        vendingMachine.insert("NICKEL");
        vendingMachine.selectItem("COLA");
        vendingMachine.getDisplay();
        assertEquals("$ 0.05", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineColaIsSelectedAndThereIsEnoughMoneyInsertedTheProductIsDispensed() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("COLA");
        assertEquals("COLA", vendingMachine.getDispensedItem());
    }

    @Test
    public void whenVendingMachineColaIsSelectedAndThereIsEnoughMoneyInsertedThankYouIsDisplayed() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("COLA");
        assertEquals("THANK YOU", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineChipsIsSelectedAndThereIsEnoughMoneyInsertedTheProductIsDispensed() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("CHIPS");
        assertEquals("CHIPS", vendingMachine.getDispensedItem());
    }

    @Test
    public void whenVendingMachineChipsIsSelectedAndThereIsEnoughMoneyInsertedThankYouIsDisplayed() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("CHIPS");
        assertEquals("THANK YOU", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineCandyIsSelectedAndThereIsEnoughMoneyInsertedTheProductIsDispensed() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("CANDY");
        assertEquals("CANDY", vendingMachine.getDispensedItem());
    }

    @Test
    public void whenVendingMachineCandyIsSelectedAndThereIsEnoughMoneyInsertedThankYouIsDisplayed() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("CANDY");
        assertEquals("THANK YOU", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineSellsColaAndDisplaysThankYouItThenDisplaysInsertCoin() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("COLA");
        vendingMachine.getDisplay();
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineCandyIsSelectedAndExtraMoneyWasInsertedChangeIsReturned() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("CANDY");
        List<String> expectedCoins = new ArrayList<>();
        expectedCoins.add("DIME");
        assertEquals(expectedCoins, vendingMachine.getCoinReturn());
    }

    @Test
    public void whenVendingMachineReturnCoinsButtonIsPressedCoinsAreReturned() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("DIME");
        vendingMachine.insert("NICKEL");
        List<String> expectedCoins = new ArrayList<>();
        expectedCoins.add("QUARTER");
        expectedCoins.add("DIME");
        expectedCoins.add("NICKEL");
        vendingMachine.selectReturnCoins();
        assertEquals(expectedCoins, vendingMachine.getCoinReturn());
    }

    @Test
    public void whenVendingMachineHasNoColaLeftItDisplaysSoldOut() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("COLA");
        vendingMachine.selectItem("COLA");
        assertEquals("SOLD OUT", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDisplaysSoldOutItThenDisplaysInsertCoin() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("COLA");
        vendingMachine.selectItem("COLA");
        vendingMachine.getDisplay();
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineDisplaysSoldOutItThenDisplaysCurrentAmount() {
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("COLA");
        vendingMachine.insert("QUARTER");
        vendingMachine.selectItem("COLA");
        vendingMachine.getDisplay();
        assertEquals("$ 0.25", vendingMachine.getDisplay());
    }
}
