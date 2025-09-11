import core.Steps;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static core.ScooterURI.*;
import static io.restassured.RestAssured.*;
import static core.RandomGenerator.*;
import static org.junit.Assert.*;

public class CancelOrderTest extends Steps {
@Before
    public void setUp(){
    baseURI = SCOOTER_BASE_URI;
}

@Test
    public void statusCodeCancelOrderWithInvalidTrackNumberShouldBe400() {
    Response cancelOrderResponse = cancelOrder(randomIdOrTrackNumber());
    assertEquals("Неверный код ответа", 400, cancelOrderResponse.statusCode());
}

@Test
    public void messageCancelOrderWithInvalidTN(){
    Response cancelOrderResponse = cancelOrder(randomIdOrTrackNumber());
    assertEquals("Неверное сообщение об ошибке в теле ответа.",
            "Недостаточно данных для поиска",
            cancelOrderResponse.jsonPath().getString("message"));
}
}
