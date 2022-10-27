import com.techelevator.Purchase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class addMoneyTest {

    Purchase purchase = new Purchase();

    @Test
    public void add_money_test_1() {

        BigDecimal moneyAmount = new BigDecimal("5.50");

        purchase.addMoney(moneyAmount);
        BigDecimal result = purchase.getMoneyInput();

        Assert.assertEquals(new BigDecimal("0.00"), result);
    }

    @Test
    public void add_money_test_2() {

        BigDecimal moneyAmount = new BigDecimal("-1");

        purchase.addMoney(moneyAmount);
        BigDecimal result = purchase.getMoneyInput();

        Assert.assertEquals(new BigDecimal("0.00"), result);
    }

}
