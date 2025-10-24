//Manage Computers program: maintains an ArrayList of Computer objects with INPUT VALIDATION

import java.util.ArrayList;
import java.util.Scanner;

public class ManageComputers {
    
    // Whitelist definitions for input validation
    private static final String[] VALID_CPUS = {"i5", "i7"};
    private static final String[] VALID_RAMS = {"16", "32"};
    private static final String[] VALID_DISKS = {"512", "1024"};
    private static final String[] VALID_GPUS = {"Nvidia", "AMD"};
    private static final String[] VALID_SCREENS = {"13", "14"};

    public static void main(String args[]) {
        // ArrayList now holds Object type to handle both Desktop and Laptop through composition
        ArrayList<Object> computers = new ArrayList<Object>(); 

        Scanner s = new Scanner(System.in);
        String menuOption = "";

        do {
            showComputers(computers);
            menuOption = getMenuSelection(s);

            switch(menuOption) {
                case "a": 
                    addComputer(computers, s);
                    break;
                case "d": 
                    deleteComputer(computers, s);
                    break;
                case "e": 
                    editComputer(computers, s);
                    break;
            }
        } while (!menuOption.equals("x"));

        s.close();
    }

    //-----------------------------
    // Input validation methods
    private static String validateInput(Scanner s, String prompt, String[] validValues) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = s.nextLine().trim();
            for (String valid : validValues) {
                if (valid.equalsIgnoreCase(input)) {
                    return input;
                }
            }
            System.out.println("Invalid input! Valid values are: " + String.join(", ", validValues));
        }
    }

    private static String getValidCPU(Scanner s) {
        return validateInput(s, "Enter CPU (i5/i7): ", VALID_CPUS);
    }

    private static String getValidRAM(Scanner s) {
        return validateInput(s, "Enter RAM (16/32): ", VALID_RAMS);
    }

    private static String getValidDisk(Scanner s) {
        return validateInput(s, "Enter Disk (512/1024): ", VALID_DISKS);
    }

    private static String getValidGPU(Scanner s) {
        return validateInput(s, "Enter GPU (Nvidia/AMD): ", VALID_GPUS);
    }

    private static String getValidScreen(Scanner s) {
        return validateInput(s, "Enter Screen Size (13/14): ", VALID_SCREENS);
    }

    //-----------------------------
    //Display menu and get user selection
    private static String getMenuSelection(Scanner s) {
        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        System.out.print("Enter menu selection:");
        return s.nextLine().toLowerCase();
    }

    //-----------------------------
    //Show data for all laptops and desktops
    private static void showComputers(ArrayList<Object> computers) {
        System.out.println("=========");
        System.out.println("LIST OF COMPUTERS:-");

        for (int i = 0; i < computers.size(); i++) {
            System.out.println((i + 1) + ": " + computers.get(i).toString());
        }

        System.out.println("=========");
    }

    //-----------------------------
    //Add a new Laptop or Desktop computer
    private static void addComputer(ArrayList<Object> computers, Scanner s) {
        System.out.println("ADDING COMPUTER:-");
        System.out.println("Enter type of computer to add ('L' for Laptop, 'D' for Desktop):");
        String computerType = s.nextLine().toLowerCase();

        // Get common validated computer data
        String cpu = getValidCPU(s);
        String ram = getValidRAM(s);
        String disk = getValidDisk(s);

        switch(computerType) {
            case "l": 
                String screenSize = getValidScreen(s);
                computers.add(new Laptop(cpu, ram, disk, screenSize));
                System.out.println("Laptop added successfully!");
                break;
            case "d": 
                String gpu = getValidGPU(s);
                computers.add(new Desktop(cpu, ram, disk, gpu));
                System.out.println("Desktop added successfully!");
                break;
            default:
                System.out.println("Invalid computer type entered!");
        }
    }

    //-----------------------------
    //Delete a specified computer
    private static void deleteComputer(ArrayList<Object> computers, Scanner s) {
        System.out.println("DELETE COMPUTER:-");
        System.out.print("Enter number of computer to delete:");
        
        try {
            int computerListNumberToDelete = Integer.parseInt(s.nextLine());
            if (computerListNumberToDelete >= 1 && computerListNumberToDelete <= computers.size()) {
                computers.remove(computerListNumberToDelete - 1);
                System.out.println("Computer deleted successfully!");
            } else {
                System.out.println("Invalid computer number entered!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    //-----------------------------
    //Edit a computer - since objects are immutable, we replace the entire object
    private static void editComputer(ArrayList<Object> computers, Scanner s) {
        System.out.println("EDIT COMPUTER:-");
        System.out.print("Enter number of computer to edit:");
        
        try {
            int computerListNumberToEdit = Integer.parseInt(s.nextLine());
            if (computerListNumberToEdit < 1 || computerListNumberToEdit > computers.size()) {
                System.out.println("Invalid computer number entered!");
                return;
            }

            int index = computerListNumberToEdit - 1;
            Object computerToEdit = computers.get(index);

            if (computerToEdit instanceof Laptop) {
                System.out.println("Editing a Laptop:");
                String cpu = getValidCPU(s);
                String ram = getValidRAM(s);
                String disk = getValidDisk(s);
                String screenSize = getValidScreen(s);
                
                computers.set(index, new Laptop(cpu, ram, disk, screenSize));
                System.out.println("Laptop updated successfully!");
            } else if (computerToEdit instanceof Desktop) {
                System.out.println("Editing a Desktop:");
                String cpu = getValidCPU(s);
                String ram = getValidRAM(s);
                String disk = getValidDisk(s);
                String gpu = getValidGPU(s);
                
                computers.set(index, new Desktop(cpu, ram, disk, gpu));
                System.out.println("Desktop updated successfully!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
}
