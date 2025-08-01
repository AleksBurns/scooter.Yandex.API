package core;

/**
 * Разорвать зависимости в шагах при помощи Mock
 */

import classes.Courier;
import classes.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static core.scooterURI.*;
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
    public Response loginCourier(Courier courier){
        Response response =
                given()
                        .contentType("application/json")
                        .body(courier)
                        .post(LOGIN_COURIER);
        if(response.getStatusCode() == 200) {
            int actualId = response.jsonPath().getInt("id");
            System.out.println("id = " + actualId);
        }
        return response;
    }

    @Step("Получение id курьера")
    public int getCourierId(Response loginResponse){
        return loginResponse.jsonPath().getInt("id");
    }

    @Step("Удаление курьера по ID")
    public Response deleteCourierWithId(int id){
         Response response = given().delete(DELETE_COURIER + id);
         if (response.getStatusCode() == 200){
             System.out.println("Курьер успешно удалён.");
         }
         return response;
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
    public int getTrackNumber(Response createdOrderResponse){
        return createdOrderResponse.jsonPath().getInt("track");
    }

    @Step("Отмена заказа")
    public Response cancelOrder(int trackNumber){
        String requestBody = "{\n \"track\": 858409\n}";
        Response response = given()
                .contentType(json)
                .body(requestBody)
                .put(CANCEL_ORDER);
        if (response.getStatusCode() == 201){
            System.out.println("Заказ с трек-номером " + trackNumber +" отменён.");
        }
        return response;
    }
}
