import com.techelevator.Purchase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class GetQuartersTest {

    @Test

    public void get_quarters_test() {

        Purchase purchase = new Purchase();
        purchase.setMoneyInput(new BigDecimal("25"));
        BigDecimal result = purchase.getQuarters();

        Assert.assertEquals(new BigDecimal("100"), result.setScale(0));
    }
}
