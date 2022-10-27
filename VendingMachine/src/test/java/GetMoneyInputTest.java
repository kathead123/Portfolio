import com.techelevator.Purchase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class GetMoneyInputTest {

    @Test

    public void money_input_test() {

        Purchase purchase = new Purchase();
        purchase.setMoneyInput(new BigDecimal("10"));

        BigDecimal result = purchase.getMoneyInput();

        Assert.assertEquals(new BigDecimal("10"), result);
    }

}
