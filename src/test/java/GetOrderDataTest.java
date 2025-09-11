import classes.Order;
import core.Steps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static core.RandomGenerator.*;
import static core.ScooterURI.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetOrderDataTest extends Steps {
    Order expectedOrder = randomOrderData();

    @Before
    public void setUp(){
        RestAssured.baseURI = SCOOTER_BASE_URI;
    }


    @Test
    //Данные заказа сохраняются и отображаются корректно
    public void checkOrderData(){
        Response createdOrder = createOrder(expectedOrder);
        Order actualOrder = getOrderData(createdOrder.jsonPath().getInt("track"));
        assertEquals("Поле firstName заказа отображается неверно", expectedOrder.getFirstName(), actualOrder.getFirstName());
        assertEquals("Поле lastName заказа отображается неверно", expectedOrder.getLastName(), actualOrder.getLastName());
        assertEquals("Поле address заказа отображается неверно", expectedOrder.getAddress(), actualOrder.getAddress());
        assertEquals("Поле metroStation заказа отображается неверно", expectedOrder.getMetroStation(), actualOrder.getMetroStation());
        assertEquals("Поле phone заказа отображается неверно", expectedOrder.getPhone(), actualOrder.getPhone());
        assertEquals("Поле rentTime заказа отображается неверно", expectedOrder.getRentTime(), actualOrder.getRentTime());
        assertTrue("Поле deliveryDate заказа отображается неверно",actualOrder.getDeliveryDate().contains(expectedOrder.getDeliveryDate()));
        assertEquals("Поле comment заказа отображается неверно", expectedOrder.getComment(), actualOrder.getComment());
        assertEquals("Поле color заказа отображается неверно", expectedOrder.getFirstColor(), actualOrder.getFirstColor());
    }

    @Test
    public void statusCodeOrderWithUnknowTrackShouldBe404(){
        Response orderDataWithoutTrack = given().get(GET_ORDER + 0);
        assertEquals(404, orderDataWithoutTrack.statusCode());
    }

    @Test
    public void checkMessageInResponseWithoutTrack(){
        Response orderDataWithoutTrack = given().get(GET_ORDER + 0);
        assertEquals("Заказ не найден",orderDataWithoutTrack.jsonPath().getString("message"));
    }

}
