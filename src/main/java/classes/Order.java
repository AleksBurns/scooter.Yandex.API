package classes;

import static core.ScooterURI.*;
import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

public class Order {
    private Order order;
    private int id;
    private int track;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
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
    private String courierId;
    private String courierFirstName;
    private int status;


    private List<Order> orders;

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public Order(){
    }

    public Order getOrder(){
        return order;
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
    public String getMetroStation() {
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
    public String getCourierId() {
        return courierId;
    }
    public String getCourierFirstName(){
        return courierFirstName;
    }
    public int getStatus() {
        return status;
    }
    public List<Order> getOrders(){
        return this.orders;
    }

    public void setOrder(Order order){
        this.order = order;
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
    public void setMetroStation(String metroStation) {
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
    public void setCourierId(String  courierId) {
        this.courierId = courierId;
    }
    public void setCourierFirstName(String courierFirstName){
        this.courierFirstName = courierFirstName;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setOrders(List<Order> orders){
        this.orders = orders;
    }

    public String getFirstColor(){
        return color[0];
    }

    @Override
    public String toString(){
        return
                        "id: " + id + "\n" +
                        "courierId: " + "\n" +
                        "firstName: " + firstName + "\n" +
                        "lastName: " + lastName + "\n" +
                        "address: " + address + "\n" +
                        "metroStation: " + metroStation + "\n" +
                        "phone: " + phone + "\n" +
                        "rentTime: " + rentTime + "\n" +
                        "deliveryDate: " + deliveryDate + "\n" +
                        "track: " + track + "\n" +
                        "color: " + color[0] + "\n" +
                        "comment: " + comment + "\n" +
                        "createdAt: " + createdAt + "\n" +
                        "updatedAt: " + updatedAt + "\n" +
                        "status: " + status + "\n" +
                        "courierFirstName" + courierFirstName;
    }
}
