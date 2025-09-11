package core;

/**
 * Разорвать зависимости в шагах при помощи Mock
 */

import classes.Courier;
import classes.MetroStation;
import classes.Order;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import java.util.List;

import static core.ScooterURI.*;
import static io.restassured.RestAssured.given;

public class Steps {
    String json = "application/json";

    @Step("Создание курьера")
    public Response createCourier(Courier courierData){
        Response response =
                given()
                .contentType(json)
                .body(courierData)
                .post(CREATE_COURIER);
        if(response.getStatusCode() == 201) {
            System.out.println("Курьер успешно создан!");
        }
        return response;
    }

    @Step("Логин курьера")
    public Response loginCourier(Courier courier) {
                return given()
                        .contentType("application/json")
                        .body(courier)
                        .post(LOGIN_COURIER);
//        if(response.getStatusCode() == 200) {
//            int actualId = response.jsonPath().getInt("id");
//            System.out.println("id = " + actualId);
//        }
    }

    @Step("Получение id курьера")
    public int getCourierId(Response loginResponse){
        return loginResponse.jsonPath().getInt("id");
    }

    @Step("Удаление курьера по ID")
    public Response deleteCourierWithId(int courierId){
         Response response = given().delete(DELETE_COURIER + courierId);
         if (response.getStatusCode() == 200){
             System.out.println("Курьер " + courierId + " успешно удалён.");
         }
         return response;
    }

    @Step("Удаление курьера после теста")
    public void deleteCourierAfterTest(Courier createdCourier){
        Response loginCreatedCourier = loginCourier(createdCourier);
        if(loginCreatedCourier.getStatusCode() == 200) {
            deleteCourierWithId(getCourierId(loginCreatedCourier));
        }
    }

    @Step("Создание нового заказа")
    public Response createOrder(Order orderData){
        Response response = given()
                .contentType(json)
                .body(orderData)
                .post(ORDERS);
        if(response.getStatusCode() == 201){
            System.out.println("Заказ успешно создан c трек-номером: " + response.jsonPath().getString("track"));
        }
        return response;
    }

    @Step("Получение трек-номера заказа")
    public int getOrderTrack(Response responseAfterCreateOrder){
        return responseAfterCreateOrder.jsonPath().getInt("track");
    }

    @Step("Получение Id заказа")
    public int getOrderId(Response responseAfterCreateOrder){
        int orderTrack = responseAfterCreateOrder.jsonPath().getInt("track");
        return getOrderData(orderTrack).getId();
    }

    @Step("Отмена заказа")
    public Response cancelOrder(int trackNumber){
        String requestBody = "{\n \"track\": " + trackNumber + "\n}";
        Response response = given()
                .contentType(json)
                .body(requestBody)
                .put(CANCEL_ORDER);
        if (response.getStatusCode() == 201){
            System.out.println("Заказ с трек-номером " + trackNumber +" отменён.");
        }
        return response;
    }

    @Step("Запрос на получение списка станций метро")
    public Response getMetroStationsList(){
        return given().get(METRO_STATIONS);
    }

    @Step("Получение случайной станции метро из списка")
    public MetroStation getRandomMetroStation(){
        Response getMSList = getMetroStationsList();
        List<MetroStation> stations = getMSList.as(new TypeRef<>(){});
        return RandomGenerator.getRandomStation(stations);
    }

    @Step("Получение списка заказов")
    public List<Order> getOrdersList(){
        Response getOrderList = given()
                .get(ORDERS);
        Order ordersList = getOrderList.as(Order.class);
        return ordersList.getOrders();
    }

    @Step("Получение данных заказа")
    public Order getOrderData(int orderTrack){
        Response getOrderDataResponse = given().get(GET_ORDER + orderTrack);
        Order orderData = getOrderDataResponse.as(Order.class);
        return orderData.getOrder();
    }

    @Step("Принять заказ")
    public Response acceptOrder(int orderId, int courierId){
        Response acceptedOrder = given()
                .put("api/v1/orders/accept/" + orderId + "?courierId=" + courierId);
        if(acceptedOrder.statusCode() == 200){
            System.out.println("Заказ " + orderId + " успешно принят курьером " + courierId);
        }
        return acceptedOrder;
    }

    @Step("Завершить заказ")
    public Response finishOrder(int orderId){
        Response finishedOrder =  given().put(FINISH_ORDER + orderId);
        if(finishedOrder.statusCode() == 200){
            System.out.println("Заказ " + orderId + " успешно завершен");
        }
        return finishedOrder;
    }
}
