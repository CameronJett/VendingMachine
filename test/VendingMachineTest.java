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
}
