package it2b.paparon.purok;
import java.util.Scanner;

public class IT2BPAPARONPUROK {

    public static void main(String[] args) {
    
        int cho = 0; 
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("  |---------------------------------------------|");
            System.out.println("1.|                   Members                   |");
            System.out.println("2.|                  Activities                 |");
            System.out.println("3.|                  Attendance                 |");
            System.out.println("4.|                    Exit                     |");
            System.out.println("  |---------------------------------------------|");
            System.out.print("Enter choice: ");

           
            if (sc.hasNextInt()) {
                cho = sc.nextInt();
                sc.nextLine(); 
                
                if (cho == 1) {
                    members.handleMenu(sc);
                } else if (cho == 2) {
                    activities.handleMenu(sc);
                } else if (cho == 3) {
                    attendance.handleMenu(sc);
                } else if (cho == 4) {
                    System.out.println("Thank you! Exiting program.");
                } else {
                    System.out.println("Invalid choice, please enter a number between 1 and 4.");
                }
            } else {
                
                System.out.println("Invalid input, please enter a number.");
                sc.nextLine();  
            }
        } while (cho != 4);  

        sc.close();
    }
}
