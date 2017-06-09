import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class VendingMachineTest {
    @Test
    public void whenVendingMachineHasNoCoinsItDisplaysInsertCoin() {
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
    }
}
