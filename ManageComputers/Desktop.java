//Desktop computer: uses COMPOSITION with Computer class - IMMUTABLE VERSION
public final class Desktop {
    private final Computer computer;
    private final String GPUType;

    //Constructor
    public Desktop(String CPU, String RAM, String disk, String GPUType) {
        this.computer = new Computer(CPU, RAM, disk);
        this.GPUType = GPUType;
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

    public String getGPUType() {
        return this.GPUType;
    }

    //Return formatted version of data
    public String toString() {
        return "Type:Desktop\tCPU:" + getCPU() + "\tRAM:" + getRAM() + "\tDisk:" + getDisk() + "\tGPU:" + this.GPUType;
    }
}
