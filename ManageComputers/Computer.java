//Computer class: manages computer CPU, RAM and Disk information - IMMUTABLE VERSION
public final class Computer {
    private final String CPU;
    private final String RAM;
    private final String disk;

    //Constructor only - no setters for immutability
    public Computer(String CPU, String RAM, String disk) {
        this.CPU = CPU;
        this.RAM = RAM;
        this.disk = disk;
    }

    //Getters only - no setters
    public String getCPU() {
        return this.CPU;
    }

    public String getRAM() {
        return this.RAM;
    }

    public String getDisk() {
        return this.disk;
    }
}
