package classes;

public class MetroStation {
    private String number;
    private String name;
    private String color;

    public MetroStation() {
    }

    public MetroStation(String number, String name, String color){
        this.number = number;
        this.name = name;
        this.color = color;
    }

    public String getNumber(){
        return number;
    }
    public String getName(){
        return name;
    }
    public String getColor(){
        return color;
    }

    public void setNumber(String number){
        this.number = number;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setColor(String color){
        this.color = color;
    }


    @Override
    public String toString() {
        return "number: " + number + ", name: " + name + ", color: " + color;
    }

}
