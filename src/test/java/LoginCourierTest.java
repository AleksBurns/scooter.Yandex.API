import classes.Courier;
import core.Steps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static core.ScooterURI.*;
import static core.RandomGenerator.*;
import static org.junit.Assert.*;

public class LoginCourierTest extends Steps {
    Courier courier = randomCourierData();

    @Before
    public void setUp(){
        RestAssured.baseURI = SCOOTER_BASE_URI;
    }

    @Test
    //Логин с валидными данными
    public void loginCourierWithValidData(){
        createCourier(courier);
        Response login = loginCourier(courier);
        assertEquals(200, login.statusCode());
        assertFalse(login.jsonPath().get("id").toString().isBlank());
    }

    @Test
    //Логин с несуществующей парой логин-пароль
    public void loginUnknowCourier(){
        Response login = loginCourier(courier);
        assertTrue(login.getStatusLine().contains("404 Not Found")
                && login.jsonPath().getString("message").equals("Учетная запись не найдена"));
    }

    @Test
    //Попытка залогиниться без передачи логина
    public void loginWithoutLoginData(){
        Courier testCourierWithoutLogin = new Courier("", randomString(6));
        Response login = loginCourier(testCourierWithoutLogin);
        assertEquals(400, login.statusCode());
        assertEquals("Недостаточно данных для входа", login.jsonPath().getString("message"));
    }

    @Test
    //Попытка залогиниться без передачи пароля
    public void loginWithoutPasswordData(){
        Courier testCourierWithoutPassword = new Courier(randomString(6),"");
        Response login = loginCourier(testCourierWithoutPassword);
        assertEquals(400, login.statusCode());
        assertEquals("Недостаточно данных для входа", login.jsonPath().getString("message"));
    }


    @After
    public void deleteCourierAfterCreate(){
       deleteCourierAfterTest(courier);
    }
}
