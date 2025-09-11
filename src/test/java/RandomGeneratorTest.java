import classes.Order;
import org.junit.Test;

import java.util.Arrays;

import static core.RandomGenerator.*;
import static org.junit.Assert.*;

public class RandomGeneratorTest {
    @Test
    public void idGeneratorTest(){
        int randomId = randomIdOrTrackNumber();
        System.out.println(randomId);
        assertTrue(randomId > 99999 && randomId < 1000000);
    }

    @Test
    public void phoneGeneratorTest(){
        String phoneNumber = randomPhoneNumber();
        System.out.println(phoneNumber);
    }

    @Test
    public void colorGeneratorTest(){
        String[] color = randomColor();
        System.out.println(Arrays.toString(color));
    }

    @Test
    public void dateGeneratorTest(){
        String date = randomDeliveryDate();
        System.out.println(date);
    }

    @Test
    public void randomOrderDataTest(){
        Order randomOrder = randomOrderData();
        System.out.println(randomOrder);
    }
}
