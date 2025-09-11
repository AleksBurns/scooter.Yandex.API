/**
 *Разорвать зависимости в тестах с применением Mock
 */

import classes.Courier;
import core.Steps;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static core.ScooterURI.*;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static core.RandomGenerator.*;

public class CreateCourierTest extends Steps {
    Courier courier = randomCourierData();
    String json = "application/json; charset=utf-8";

    @Before
    public void setUp(){
        baseURI = SCOOTER_BASE_URI;
    }

    @Test
    //Создание курьера
    public void responseBodyWithValidData(){
        Response response = createCourier(courier);
        boolean actualResponse = response.jsonPath().getBoolean("ok") && response.getStatusLine().contains("201");
        assertTrue(actualResponse);
    }

    @Test
    //Создание дубликата
    public void messageDuplicateError(){
        createCourier(courier);
        Response response = createCourier(courier);
        String statusLine2 = response.getStatusLine();
        System.out.println(statusLine2);
        String actualMessage = response.jsonPath().getString("message");
        assertEquals("Неверное сообщение в теле ответа",
                "Этот логин уже используется. Попробуйте другой.", actualMessage);
    }

    @Test
    //Создание без логина
    public void messageCourierWithoutLogin(){
        Courier courierDataWithoutLogin = new Courier(null, randomString(6));
        Response response = createCourier(courierDataWithoutLogin);
        assertEquals(400, response.statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.jsonPath().getString("message"));
    }

    @Test
    //Создание без пароля
    public void messageCourierWithoutPassword(){
        Courier courierDataWithoutLogin = new Courier(randomString(6), null);
        Response response = createCourier(courierDataWithoutLogin);
        assertEquals(400, response.statusCode());
        assertEquals("Недостаточно данных для создания учетной записи", response.jsonPath().getString("message"));
    }

    @After
    public void deleteCourierAfterCreate(){
        deleteCourierAfterTest(courier);
    }
}