import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class VendingMachineTest {

    @Test
    public void whenVendingMachineHasNoCoinsItDisplaysInsertCoin() {
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
    }

    @Test
    public void whenVendingMachineReceivesNickelItDisplaysFiveCents() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insert("NICKEL");
        assertEquals("$ 0.05", vendingMachine.getDisplay());
    }
}
