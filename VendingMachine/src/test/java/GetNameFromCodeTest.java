import com.techelevator.AllSnacks;
import org.junit.Assert;
import org.junit.Test;

public class GetNameFromCodeTest {

@Test

    public void get_name_from_code_test() {

    AllSnacks allSnacks = new AllSnacks();
    allSnacks.itemsForPurchase();
    String result = allSnacks.getNameFromCode("B2");

    Assert.assertEquals("Cowtales", result);
}

}
