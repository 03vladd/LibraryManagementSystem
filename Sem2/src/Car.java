class Car {
    private Engine engine;

    public Car() {
        this.engine = new Engine();  // Car creates its own engine
    }

    public void drive() {
        engine.start();
        System.out.println("Auto fährt.");
    }
}