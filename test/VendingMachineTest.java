import org.junit.Before;
import org.junit.Test;

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
}
