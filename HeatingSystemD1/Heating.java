public class Heating {
    private int temperature;
    private int min;
    private int max;
    private int increment;
    private String roomName;


    public Heating(String roomName, int temperature, int min, int max, int increment) {
        this.roomName = roomName;
        this.temperature = temperature;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }


    public void warmer() {
        if (temperature + increment <= max) {
            temperature += increment;
            System.out.println(roomName + " temperature increased to " + temperature + "°C");
        } else {
            System.out.println("Cannot increase temperature in " + roomName + ". Maximum limit reached.");
        }
    }


    public void colder() {
        if (temperature - increment >= min) {
            temperature -= increment;
            System.out.println(roomName + " temperature decreased to " + temperature + "°C");
        } else {
            System.out.println("Cannot decrease temperature in " + roomName + ". Minimum limit reached.");
        }
    }


    public int getTemperature() {
        return temperature;
    }


    public void displayTemperature() {
        System.out.println(roomName + " current temperature: " + temperature + "°C");
    }
}
