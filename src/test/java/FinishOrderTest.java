import core.Steps;
import classes.Order;
import classes.Courier;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static core.ScooterURI.*;
import static core.RandomGenerator.*;
import static org.junit.Assert.*;

public class FinishOrderTest extends Steps {
    Order orderData = randomOrderData();
    Courier courierData = randomCourierData();

    @Before
    public void setUp(){
        RestAssured.baseURI = SCOOTER_BASE_URI;
    }

    @Test
    // Заказ успешно завершен:
    //- Статус код ответа 200
    //- В теле ответа ключ 'ok' со значением 'true'
    //- Статус заказа изменился на '2'
    //- значение finished в данных заказа = true
    public void finishOrderWithValidData(){
        createCourier(courierData);//создание курьера
        Response createdOrder = createOrder(orderData); //создание заказа
        int orderTrack = getOrderTrack(createdOrder);//получение трека заказа
        acceptOrder(getOrderId(createdOrder), getCourierId(loginCourier(courierData)));
        finishOrder(getOrderId(createdOrder));
            //БАГ! После первого запроса на завершение заказа, его id меняется
            //БАГ! Для успешного завершения необходимо 2 запроса
        Response finishedOrder = finishOrder(getOrderId(createdOrder));
        Order orderDataAfterFinish = getOrderData(orderTrack);
        assertEquals(200, finishedOrder.statusCode());//проверка статус кода ответа
        assertTrue(finishedOrder.jsonPath().getBoolean("ok"));//проверка тела ответа
        assertEquals(2, orderDataAfterFinish.getStatus());//
        assertTrue(orderDataAfterFinish.isFinished());
    }

    @Test
    //Заказ нельзя завершить, передав несуществующий ID заказа
    //Статус-код ответа 404
    //В теле ответа сообщение об ошибке
    public void finishOrderWithUnknownId(){
        createCourier(courierData);
        Response finishedOrder = finishOrder(0);
        assertEquals(404, finishedOrder.statusCode());
        assertEquals("Заказа с таким id не существует", finishedOrder.jsonPath().getString("message"));
    }

    @Test
    //Заказ нельзя завершить дважды
    //Статус-код ответа 409
    //В теле ответа сообщение об ошибке

    @After
    public void deleteCourier(){
        deleteCourierAfterTest(courierData);
    }

}
