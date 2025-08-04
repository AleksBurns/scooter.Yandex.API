import core.Steps;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static core.ScooterURI.*;
import static org.junit.Assert.*;

public class MetroStationTest extends Steps {
@Before
    public void setUp(){
    baseURI = SCOOTER_BASE_URI;
}

@Test
    public void getMSListStatusCodeShouldBe200(){
    Response getMSList = getMetroStationsList();
    assertEquals("Статус код должен быть 200", 200, getMSList.statusCode());
}

@Test
    public void metroStationsListIsNotEmpty(){
    Response getMSList = getMetroStationsList();
    assertFalse("Список станций не должен быть пустым", getMSList.getBody().toString().isEmpty());
}

@Test
    public void printRandomStationFromMSList(){
        System.out.println(getRandomMetroStation().toString());
    }
@Test
    public void getMSListResponseTime(){
    Response getMSList = getMetroStationsList();
    assertTrue(getMSList.getTime() < 1200);
    }
}