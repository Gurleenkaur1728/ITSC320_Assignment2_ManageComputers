/**
 * Immutable Computer class for ITSC320 Assignment 2.
 *
 * - All fields are private and final (immutable).
 * - Constructor validates inputs against whitelists.
 * - No setters provided: object is immutable after construction.
 * - Provides getters, toString, equals and hashCode.
 *
 * Allowed (whitelist) values:
 *  CPU: "i5", "i7"
 *  RAM: "16", "32"      (values represent GB)
 *  Disk: "512", "1024" (values represent GB)
 *
 * If invalid values are passed the constructor throws IllegalArgumentException.
 */
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Computer {
    // Whitelists (modifiable here if needed)
    public static final Set<String> VALID_CPUS;
    public static final Set<String> VALID_RAM;
    public static final Set<String> VALID_DISKS;

    static {
        Set<String> cpus = new HashSet<>();
        cpus.add("i5");
        cpus.add("i7");
        VALID_CPUS = Collections.unmodifiableSet(cpus);

        Set<String> ram = new HashSet<>();
        ram.add("16");
        ram.add("32");
        VALID_RAM = Collections.unmodifiableSet(ram);

        Set<String> disks = new HashSet<>();
        disks.add("512");
        disks.add("1024");
        VALID_DISKS = Collections.unmodifiableSet(disks);
    }

    private final String cpu;   // e.g. "i5"
    private final String ram;   // e.g. "16"
    private final String disk;  // e.g. "512"

    /**
     * Constructs an immutable Computer object.
     * All parameters are validated against whitelists.
     *
     * @param cpu  CPU name (allowed examples: "i5", "i7")
     * @param ram  RAM size in GB (allowed: "16", "32")
     * @param disk Disk size in GB (allowed: "512", "1024")
     * @throws IllegalArgumentException if any parameter is null or not in the whitelist
     */
    public Computer(String cpu, String ram, String disk) {
        // Basic null checks
        if (cpu == null || ram == null || disk == null) {
            throw new IllegalArgumentException("CPU, RAM and Disk must not be null.");
        }

        // Normalize inputs: trim and lower-case so small differences in case/whitespace don't break things
        String cpuNorm = cpu.trim().toLowerCase();
        String ramNorm = ram.trim().toLowerCase();
        String diskNorm = disk.trim().toLowerCase();

        // Accept some human-friendly forms optionally:
        // - Allow "intel i5" or "i5" -> normalized to "i5"
        // - Allow values with units like "16gb" -> normalized to "16"
        cpuNorm = normalizeCpu(cpuNorm);
        ramNorm = normalizeNumber(ramNorm);
        diskNorm = normalizeNumber(diskNorm);

        // Validate using the whitelists
        if (!VALID_CPUS.contains(cpuNorm)) {
            throw new IllegalArgumentException("Invalid CPU value: '" + cpu + "'. Valid options: " + VALID_CPUS);
        }
        if (!VALID_RAM.contains(ramNorm)) {
            throw new IllegalArgumentException("Invalid RAM value: '" + ram + "'. Valid options: " + VALID_RAM);
        }
        if (!VALID_DISKS.contains(diskNorm)) {
            throw new IllegalArgumentException("Invalid Disk value: '" + disk + "'. Valid options: " + VALID_DISKS);
        }

        // assign canonical stored values (use normalized, canonical strings)
        this.cpu = cpuNorm;
        this.ram = ramNorm;
        this.disk = diskNorm;
    }

    // --- Normalization helpers ---

    // Normalize CPU strings to simple token like "i5" or "i7"
    private static String normalizeCpu(String input) {
        // remove common prefixes, whitespace
        String s = input.replaceAll("\\s+", ""); // remove whitespace
        s = s.replace("intel", ""); // remove common vendor prefix if present
        s = s.replace("amd", "");   // also strip vendor if someone typed it
        // now s might be like "i5" or "i7" or "i5processor"
        // extract the common token i5/i7 by simple matching
        if (s.contains("i5")) return "i5";
        if (s.contains("i7")) return "i7";
        // fallback: return input unchanged (validation will fail afterwards)
        return input;
    }

    // Normalize numbers like "16gb" -> "16", " 1024 " -> "1024"
    private static String normalizeNumber(String input) {
        // Remove non-digit characters (like "GB", "gb", etc.)
        String s = input.replaceAll("[^0-9]", "");
        return s.isEmpty() ? input : s;
    }

    // --- Accessors (read-only) ---

    /**
     * Returns the CPU value (canonical form: "i5" or "i7").
     */
    public String getCPU() {
        return cpu;
    }

    /**
     * Returns RAM size in GB (canonical form: "16" or "32").
     */
    public String getRAM() {
        return ram;
    }

    /**
     * Returns Disk size in GB (canonical form: "512" or "1024").
     */
    public String getDisk() {
        return disk;
    }

    /**
     * Human-friendly string representation used by the application when printing computers.
     * Example: "CPU:i5\tRAM:16\tDisk:512"
     */
    @Override
    public String toString() {
        return "CPU:" + cpu + "\tRAM:" + ram + "\tDisk:" + disk;
    }

    // --- equals/hashCode so objects behave well in collections if needed ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Computer)) return false;
        Computer other = (Computer) o;
        return Objects.equals(cpu, other.cpu)
                && Objects.equals(ram, other.ram)
                && Objects.equals(disk, other.disk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpu, ram, disk);
    }
}