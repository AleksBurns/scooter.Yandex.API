/**
 *Разорвать зависимости в тестах с применением Mock
 */

import classes.Courier;
import core.Steps;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static core.scooterURI.*;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static core.RandomGenerator.*;

public class CourierTest extends Steps {
    Courier courierData = randomCourierData();
    String json = "application/json; charset=utf-8";
    @Before
    public void setUp(){
        baseURI = SCOOTER_BASE_URI;
    }
    @Test
    //Создание курьера
    public void responseBodyWithValidData(){
        Response response = createCourier(courierData);
        boolean actualResponse = response.jsonPath().getBoolean("ok") && response.getStatusLine().contains("201");
        assertTrue(actualResponse);
        Response login = loginCourier(courierData);
        deleteCourierWithId(getCourierId(login));
    }

    @Test
    //Создание дубликата
    public void messageDuplicateError(){
        createCourier(courierData);
        Response response = createCourier(courierData);
        String statusLine2 = response.getStatusLine();
        System.out.println(statusLine2);
        String actualMessage = response.jsonPath().getString("message");
        assertEquals("Неверное сообщение в теле ответа",
                "Этот логин уже используется. Попробуйте другой.", actualMessage);
        Response login = loginCourier(courierData);
        deleteCourierWithId(getCourierId(login));
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

    @Test
    //Повторное удаление курьера
    public void deleteAlreadyDeletedCourier(){
        createCourier(courierData);
        Response login = loginCourier(courierData);
        int idCourier = getCourierId(login);
        deleteCourierWithId(idCourier);
        Response deleteCourierResponse = deleteCourierWithId(idCourier);
        String actualMessageInResponseBody = (deleteCourierResponse.jsonPath().getString("message"));
        assertEquals("Неверное сообщение в теле ответа",
                "Курьера с таким id нет.", actualMessageInResponseBody);
    }

    @Test
    //Логин несозданного курьера
    public void loginUnknowCourier(){
        Response login = loginCourier(courierData);
        assertTrue(login.getStatusLine().contains("404 Not Found")
                && login.jsonPath().getString("message").equals("Учетная запись не найдена"));
    }

    @Test
    //Время отклика на запрос
    public void responseTime(){
        Response createCourier = createCourier(courierData);
        Response login = loginCourier(courierData);
        Response delete = deleteCourierWithId(getCourierId(login));
        assertTrue("Скорость отклика сервера ниже заявленной",
                createCourier.getTime() <= 1000
                && login.getTime() <= 1000
                && delete.getTime() <= 1000
        );
    }

    @Test
    //Возвращаемый тип данных в заголовках ответа
    public void contentTypeShouldBeJson(){
        Response createCourier = createCourier(courierData);
        Response login = loginCourier(courierData);
        Response delete = deleteCourierWithId(getCourierId(login));
        assertTrue("Полученный в заголовках ответа тип данных не совпадает с ожидаемым!",
                createCourier.contentType().equals(json) &&
                        login.contentType().equals(json) &&
                        delete.contentType().equals(json)
                );
    }

    @Test
    //Запрос на удаление с несуществующим id
    public void deleteWithUnknowId(){
        Response response = deleteCourierWithId(0);
        assertEquals(404, response.statusCode());
        assertEquals("Курьера с таким id нет.", response.jsonPath().getString("message"));
    }

}