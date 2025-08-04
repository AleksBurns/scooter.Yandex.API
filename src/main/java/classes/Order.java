package classes;

//{
//    "firstName": "Имя",
//    "lastName": "Фамилия",
//    "address": "Test-1234",
//    "metroStation": 4,
//    "phone": "+78003553535",
//    "rentTime": 1,
//    "deliveryDate": "2022-07-27",
//    "comment": "",
//    "color": [
//        "BLACK"
//    ]
//        "cancelled": false,
//        "finished": false,
//        "inDelivery": false,
//        "createdAt": "2025-07-16T17:32:17.114Z",
//        "updatedAt": "2025-07-16T17:32:17.114Z",
//        "status": 0
//}

import static core.ScooterURI.*;
import static io.restassured.RestAssured.*;

public class Order {
    private int id;
    private int track;
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
    private boolean canceled;
    private boolean finished;
    private boolean inDelivery;
    private String createdAt;
    private String updatedAt;
    private int status;

    public Order(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String[] color){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.color = color;
    }

    public Order(){

    }
    public int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public int getMetroStation() {
        return metroStation;
    }
    public String getPhone() {
        return phone;
    }
    public int getRentTime() {
        return rentTime;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public int getTrack(){
        return track;
    }
    public String getComment() {
        return comment;
    }
    public String[] getColor() {
        return color;
    }
    public boolean isCanceled() {
        return canceled;
    }
    public boolean isFinished() {
        return finished;
    }
    public boolean isInDelivery() {
        return inDelivery;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public int getStatus() {
        return status;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public void setTrack(int track){
        this.track = track;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setColor(String[] color) {
        this.color = color;
    }
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    public void setInDelivery(boolean inDelivery) {
        this.inDelivery = inDelivery;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public void cancelOrder(int trackNumber){
        String jsonTrackNumber = "{\"track\" : " + trackNumber + "}";
        given()
                .contentType("application/json")
                .body(jsonTrackNumber)
                .put(CANCEL_ORDER);
    }

    @Override
    public String toString(){
        return
                "firstName: " + firstName + "\n" +
                "lastName: " + lastName + "\n" +
                "address: " + address + "\n" +
                "metroStation: " + metroStation + "\n" +
                "phone: " + phone + "\n" +
                "rentTime: " + rentTime + "\n" +
                "deliveryDate: " + deliveryDate + "\n" +
                "comment: " + comment + "\n" +
                "color: " + color[0];
    }
}
