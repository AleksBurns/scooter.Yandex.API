import core.Steps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import classes.Courier;

import static core.ScooterURI.*;
import static core.RandomGenerator.*;
import static org.junit.Assert.*;

public class DeleteCourierTest extends Steps {
    Courier courier = randomCourierData();

    @Before
    public void setUp(){
        RestAssured.baseURI = SCOOTER_BASE_URI;
    }

    @Test
    //Удаление с валидными данными
    public void deleteCourierWithValidData(){
        createCourier(courier);
        Response login = loginCourier(courier);
        Response deleteTestCourier = deleteCourierWithId(getCourierId(login));
        assertEquals(200, deleteTestCourier.statusCode());
        assertTrue(deleteTestCourier.jsonPath().getBoolean("ok"));
    }

    @Test
    //Повторное удаление курьера
    public void deleteAlreadyDeletedCourier(){
        createCourier(courier);
        Response login = loginCourier(courier);
        int idCourier = getCourierId(login);
        deleteCourierWithId(idCourier);
        Response deleteCourierResponse = deleteCourierWithId(idCourier);
        String actualMessageInResponseBody = (deleteCourierResponse.jsonPath().getString("message"));
        assertEquals("Неверное сообщение в теле ответа",
                "Курьера с таким id нет.", actualMessageInResponseBody);
    }

    @Test
    //Запрос на удаление с несуществующим id
    public void deleteWithUnknowId(){
        Response response = deleteCourierWithId(0);
        assertEquals(404, response.statusCode());
        assertEquals("Курьера с таким id нет.", response.jsonPath().getString("message"));
    }


}
