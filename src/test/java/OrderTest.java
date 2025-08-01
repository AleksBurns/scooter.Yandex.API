import classes.Order;
import core.Steps;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static core.scooterURI.*;
import static io.restassured.RestAssured.*;
import static core.RandomGenerator.*;

/**
 * Создание нового заказа
 * Получение списка заказов
 * Получение заказа по номеру
 * Принятие заказа
 * Завершение заказа
 * Отмена заказа
 *Оптимизация при помощи Mock
 */


public class OrderTest extends Steps {
    Order order = new Order();
    String json = "application/json";
@Before
    public void setUp(){
    baseURI = SCOOTER_BASE_URI;
}
/*
  Отмена заказа
  Проверка статус-кода при успешной отмене
  Проверка типа данных в ответе
  Проверка сообщения при успешной отмене
  Проверка статус-кода при передаче невалидного трек-номера
  Проверка сообщения при передаче невалидного трек-номера
  Проверка тела ответа при передаче пустого трек-номера
 */
@Test
    public void statusCodeCancelOrderWithInvalidTrackNumberShouldBe400() {
    String jsonTrackNumber = "{\"track\": " + "000000" + "}";
    given()
            .contentType(json)
            .body(jsonTrackNumber)
            .put(CANCEL_ORDER)
            .then()
            .statusCode(400);
}
@Test
    public void createOrder(){
    Response response = createOrder(randomOrderData());
    getTrackNumber(response);
}

@Test
    public void getOrderTest(){

}
//UnitTests
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
    Order randomOrderData = randomOrderData();
    System.out.println(randomOrderData.toString());
}


}
