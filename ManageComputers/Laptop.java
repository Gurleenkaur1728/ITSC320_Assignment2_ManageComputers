//Laptop computer: uses COMPOSITION with Computer class - IMMUTABLE VERSION
public final class Laptop {
    private final Computer computer;
    private final String screenSize;

    //Constructor
    public Laptop(String CPU, String RAM, String disk, String screenSize) {
        this.computer = new Computer(CPU, RAM, disk);
        this.screenSize = screenSize;
    }

    //Getters - no setters for immutability
    public String getCPU() {
        return computer.getCPU();
    }

    public String getRAM() {
        return computer.getRAM();
    }

    public String getDisk() {
        return computer.getDisk();
    }

    public String getScreenSize() {
        return this.screenSize;
    }

    //Return formatted version of data
    public String toString() {
        return "Type:Laptop\tCPU:" + getCPU() + "\tRAM:" + getRAM() + "\tDisk:" + getDisk() + "\tScreen:" + this.screenSize;
    }
}
