import classes.Courier;
import classes.Order;
import core.Steps;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static core.ScooterURI.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static core.RandomGenerator.*;

public class AcceptOrderTest extends Steps {
    Courier courier = randomCourierData();
    Order order = randomOrderData();
    
    @Before
    public void setUp(){
        baseURI = SCOOTER_BASE_URI;
    }

    //Статус заказа меняется после принятия его курьером
    //Имя курьера в заказе совпадает с именем курьера, принявшим заказ
    @Test
    public void orderStatusMustBeChangedAfterAccept(){
        createCourier(courier);
        Response createdOrder = createOrder(order);
        int orderTrack = getOrderTrack(createdOrder);
        int courierId = getCourierId(loginCourier(courier));
        Order orderDataBeforeAccept = getOrderData(orderTrack);
        int statusBeforeAccept = orderDataBeforeAccept.getStatus();
        int orderId = getOrderData(orderTrack).getId();
        acceptOrder(orderId, courierId);
        Order orderDataAfterAccept = getOrderData(orderTrack);
        int statusAfterTaking = orderDataAfterAccept.getStatus();
        assertTrue(statusBeforeAccept != statusAfterTaking);
        assertTrue(orderDataAfterAccept.isInDelivery());
        assertEquals(courier.getFirstName(), orderDataAfterAccept.getCourierFirstName());
    }

    @Test
    //Принять заказ без id заказа невозможно, выводится сообщение об ошибке
    public void acceptOrderWithoutOrderId(){
        createCourier(courier);
        int courierId = getCourierId(loginCourier(courier));
        Response unAcceptedOrder = given().put("/api/v1/orders/accept/:orderId"+"courierId=" + courierId);
        assertEquals("Недостаточно данных для поиска", unAcceptedOrder.jsonPath().getString("message"));
    }

    //Невозможно принять заказ с некорректным id заказа:
    //Статус код ответа 404
    //Выводится сообщение об ошибке
    @Test
    public void acceptOrderWithUnknownOrderId(){
        createCourier(courier);
        int courierId = getCourierId(loginCourier(courier));
        int orderId = 0;
        Response takeOrderResponse = acceptOrder(orderId, courierId);
        assertEquals(404, takeOrderResponse.statusCode());
        assertEquals("Заказа с таким id не существует", takeOrderResponse.jsonPath().getString("message"));
    }

    //Невозможно принять заказ с некорректным id курьера
    //Статус код ответа 404
    //Выводится сообщение об ошибке
    @Test
    public void acceptOrderWithUnknownCourierId(){
        int courierId = 0;
        Response createdOrder = createOrder(order);
        Order testOrder = getOrderData(getOrderTrack(createdOrder));
        Response acceptedOrder = acceptOrder(testOrder.getId(), courierId);
        assertEquals(404, acceptedOrder.statusCode());
        assertEquals("Курьера с таким id не существует", acceptedOrder.jsonPath().getString("message"));
    }

    @Test
    //При попытке принятия заказа, который уже в работе выводится сообщение об ошибке, статус-код 409
    public void acceptAlreadyAcceptedOrder(){
        createCourier(courier);
        Response createdOrder  = createOrder(order);
        int orderId = getOrderId(createdOrder);
        int courierId = getCourierId(loginCourier(courier));
        acceptOrder(orderId, courierId);
        Response secondAccept = acceptOrder(orderId, courierId);
        assertEquals("Этот заказ уже в работе", secondAccept.jsonPath().getString("message"));
        assertEquals(409, secondAccept.statusCode());
    }


    @After
    public void deleteCourier(){
       deleteCourierAfterTest(courier);
    }
}
