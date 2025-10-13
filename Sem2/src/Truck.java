public class Truck extends Vehicle {

    public Truck(String name) {
        super(name);
    }

    @Override
    public void drive() {
        System.out.println(name + "transportiert Ware");
    }
}
