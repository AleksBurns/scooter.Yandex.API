package core;

public interface ScooterURI {
    final static String SCOOTER_BASE_URI = "https://qa-scooter.praktikum-services.ru";
    final static String PING_SERVER = "/api/v1/ping";
    final static String CREATE_COURIER = "/api/v1/courier";
    final static String LOGIN_COURIER = "/api/v1/courier/login";
    final static String DELETE_COURIER = "/api/v1/courier/";
    final static String ORDERS = "/api/v1/orders";
    final static String METRO_STATIONS = "/api/v1/stations/search";
    final static String TAKE_ORDER = "/api/v1/orders/accept/1?courierId=1";
    final static String FINISH_ORDER = "/api/v1/orders/finish/1";
    final static String CANCEL_ORDER = "/api/v1/orders/cancel";
}
