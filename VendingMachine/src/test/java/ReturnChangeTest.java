import com.techelevator.Purchase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ReturnChangeTest {
    Purchase purchase = new Purchase();

    @Test

    public void change_test_1() {

       purchase.setMoneyInput(new BigDecimal("1"));

       String result = purchase.returnChange();
       String expected = "Customer change: " +new BigDecimal("4") + " quarter(s), "
               + new BigDecimal("0") +" dime(s), " + new BigDecimal("0") + " nickel(s) ";

        Assert.assertEquals(expected, result);

    }
}
