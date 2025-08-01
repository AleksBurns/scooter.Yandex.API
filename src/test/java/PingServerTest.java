/**
 * Проверка статус-кода
 * Проверка типа данных в ответе
 * Проверка тела ответа
 * Проверка времени ответа
 */

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static core.scooterURI.*;
import static org.junit.Assert.*;

public class PingServerTest {
    @Before
    public void setUp(){
        baseURI = SCOOTER_BASE_URI;
    }

    @Test
    //Проверка статус-кода
    public void responseBodyStatusCodeShouldBe200(){
        given()
                .when()
                .get()
                .then()
                .statusCode(200);
    }

    @Test
    //Проверка типа данных в заголовках ответа
    public void responseContentTypeShouldBeText(){
        Response response = get(PING_SERVER);
        assertEquals("Полученный в заголовках ответа тип данных не совпадает с ожидаемым!",
                "text/html; charset=utf-8", response.contentType());

    }

    @Test
    //Проверка тела ответа
    public void responseBodyEqualsText(){
        Response response = get(PING_SERVER);
        String actualResponse = response.body().asString();
        assertEquals("pong;", actualResponse);
    }

    @Test
    //Проверка времени ответа
    public void responseTimeTest(){
        Response response = get(PING_SERVER);
        double responseTime = response.time();
        assertTrue(responseTime <= 1000.0);
    }
}
