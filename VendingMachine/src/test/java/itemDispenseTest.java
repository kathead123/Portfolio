import com.techelevator.AllSnacks;
import com.techelevator.Purchase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class itemDispenseTest {

    Purchase purchase = new Purchase();
    AllSnacks allSnacks = new AllSnacks();

    @Test

    public void invalid_code_test1() {

        String result = allSnacks.itemDispense("F2", purchase);
        String expected = null;

        Assert.assertEquals(expected,result);
    }
    @Test

    public void valid_code_test1() {
        purchase.setMoneyInput(new BigDecimal("5"));
        allSnacks.populateDisplayItemsMap();
        allSnacks.itemsForPurchase();
        allSnacks.populateItemsWithPriceMap();
        String result = allSnacks.itemDispense("B2", purchase);
        String expected = "Item Name: " + "Cowtales" + ", Item Price: $1.50" + ", Money Remaining: $"
                + purchase.getMoneyInput() + " -Munch Munch, Yum!";

        Assert.assertEquals(expected, result);
    }
    @Test

    public void insufficient_funds_test() {
        purchase.setMoneyInput(new BigDecimal("0"));
        allSnacks.populateDisplayItemsMap();
        allSnacks.itemsForPurchase();
        allSnacks.populateItemsWithPriceMap();
        String result = allSnacks.itemDispense("B2", purchase);
        String expected = "Insufficient funds available. Please feed more money.";

        Assert.assertEquals(expected, result);
    }
    @Test

    public void valid_code_test2() {
        purchase.setMoneyInput(new BigDecimal("5"));
        allSnacks.populateDisplayItemsMap();
        allSnacks.itemsForPurchase();
        allSnacks.populateItemsWithPriceMap();
        String result = allSnacks.itemDispense("A1", purchase);
        String expected = "Item Name: " + "Potato Crisps" + ", Item Price: $3.05" + ", Money Remaining: $"
                + purchase.getMoneyInput() + " -Crunch Crunch, Yum!";

        Assert.assertEquals(expected, result);
    }
    @Test

    public void valid_code_test3() {
        purchase.setMoneyInput(new BigDecimal("5"));
        allSnacks.populateDisplayItemsMap();
        allSnacks.itemsForPurchase();
        allSnacks.populateItemsWithPriceMap();
        String result = allSnacks.itemDispense("C1", purchase);
        String expected = "Item Name: " + "Cola" + ", Item Price: $1.25" + ", Money Remaining: $"
                + purchase.getMoneyInput() + " -Glug Glug, Yum!";

        Assert.assertEquals(expected, result);
    }
    @Test

    public void valid_code_test4() {
        purchase.setMoneyInput(new BigDecimal("5"));
        allSnacks.populateDisplayItemsMap();
        allSnacks.itemsForPurchase();
        allSnacks.populateItemsWithPriceMap();
        String result = allSnacks.itemDispense("D1", purchase);
        String expected = "Item Name: " + "U-Chews" + ", Item Price: $0.85" + ", Money Remaining: $"
                + purchase.getMoneyInput() + " -Chew Chew, Yum!";

        Assert.assertEquals(expected, result);

    }

}
