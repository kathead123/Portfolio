import com.techelevator.AllSnacks;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class GetPriceFromCodeTest {

    @Test

    public void get_price_from_code_test (){

        AllSnacks allSnacks = new AllSnacks();
        allSnacks.itemsForPurchase();
        allSnacks.populateItemsWithPriceMap();
        BigDecimal result = allSnacks.getPriceFromCode("B2");

        Assert.assertEquals(new BigDecimal("1.50"), result.setScale(2));
    }
}
