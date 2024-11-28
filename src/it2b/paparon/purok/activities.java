package it2b.paparon.purok;

import java.util.Scanner;

public class activities {

    public static void handleMenu(Scanner sc) {
        int choice;

        do {
            System.out.println("|---------------------------------------------|");
            System.out.println("|                  Activities                 |");
            System.out.println("|---------------------------------------------|");
            System.out.println("1. Add Activity");
            System.out.println("2. View Activities");
            System.out.println("3. Update Activity");
            System.out.println("4. Delete Activity");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input, please enter a valid number.");
                sc.nextLine();  
               System.out.print("Enter choice: ");  
        }
            
            choice = sc.nextInt();
            sc.nextLine();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice, please enter a number between 1 and 5.");
            } else {
                switch (choice) {
                    case 1:
                        addActivity(sc);
                        break;
                    case 2:
                        viewActivities(sc);
                        break;
                    case 3:
                        updateActivities(sc);
                        break;
                    case 4:
                        deleteActivities(sc);
                        break;
                    case 5:
                        System.out.println("Back to Main Menu.");
                        break;
                }
            }
        } while (choice != 5);
    }

    private static void addActivity(Scanner sc) {
        System.out.print("How many activities would you like to add?: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input, please enter a valid number.");
            sc.nextLine();
           System.out.print("Enter activities to add: "); 
        
        }
        int act = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= act; i++) {
            System.out.println("Enter details for activity #" + i + ":");

            System.out.print("Enter Activity Name: ");
            String activityName = sc.nextLine();
            System.out.print("Enter Activity Date: ");
            String activityDate = sc.nextLine();
            System.out.print("Enter Activity Location: ");
            String activityLocation = sc.nextLine();

            config conf = new config();
            String sql = "INSERT INTO tbl_activities (s_name, s_date, s_loc) VALUES (?, ?, ?)";
            conf.addRecord(sql, activityName, activityDate, activityLocation);
        }
    }

    public static void viewActivities(Scanner sc) {
        System.out.println("|---------------------------------------------|");
        System.out.println("1. View All Activities");
        System.out.println("2. View Activity by ID");
        System.out.print("Enter your choice: ");
        
        int choice = -1;
        while (choice != 1 && choice != 2) {
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input, please enter a valid number.");
                sc.nextLine();
            } else {
                choice = sc.nextInt();
                sc.nextLine(); 
                if (choice == 1) {
                    viewAllActivities();
                } else if (choice == 2) {
                    System.out.print("Enter Activity ID: ");
                    if (sc.hasNextInt()) {
                        int id = sc.nextInt();
                        sc.nextLine(); 
                        viewActivityById(id);
                    } else {
                        System.out.println("Invalid input for Activity ID. Please enter a valid integer.");
                        sc.nextLine();  
                    }
                } else {
                    System.out.println("Invalid choice. Returning to menu.");
                }
            }
        }
    }

    public static void viewAllActivities() {
        
        String sqlQuery = "SELECT * FROM tbl_activities";
        String[] columnHeaders = {"ID", "Activity Name", "Date", "Location"};
        String[] columnNames = {"s_id", "s_name", "s_date", "s_loc"};

        config conf = new config();
        conf.viewRecords(sqlQuery, columnHeaders, columnNames);
    }

    private static void viewActivityById(int id) {
        String sqlQuery = "SELECT * FROM tbl_activities WHERE s_id = ?";
        String[] columnHeaders = {"ID", "Activity Name", "Date", "Location"};
        String[] columnNames = {"s_id", "s_name", "s_date", "s_loc"};

        config conf = new config();
        conf.viewRecordsById(sqlQuery, columnHeaders, columnNames, id);
    }

  private static void updateActivities(Scanner sc) {
    activities ac = new activities();
    ac.viewAllActivities();  

    System.out.print("Enter the Activity ID to Update: ");
    while (!sc.hasNextInt()) {
        System.out.println("Invalid input, please enter a valid integer for Activity ID.");
        sc.nextLine();  
        System.out.print("Enter Activity ID: ");  
    }
    int id = sc.nextInt();
    sc.nextLine(); 
    
    System.out.println("Selected Activity ID: " + id);

    System.out.print("Enter new Activity Name: ");
    String newName = sc.nextLine();
    while (newName.trim().isEmpty()) {
        System.out.println("Activity name cannot be empty. Please enter a valid name.");
        newName = sc.nextLine();
    }

    System.out.print("Enter new Activity Date (YYYY-MM-DD): ");
    String newDate = sc.nextLine();
    while (!newDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
        System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
        newDate = sc.nextLine();
    }

    System.out.print("Enter new Activity Location: ");
    String newLocation = sc.nextLine();
    while (newLocation.trim().isEmpty()) {
        System.out.println("Activity location cannot be empty. Please enter a valid location.");
        newLocation = sc.nextLine();
    }

   
    String qry = "UPDATE tbl_activities SET s_name = ?, s_date = ?, s_loc = ? WHERE s_id = ?";
    config conf = new config();
    conf.updateRecord(qry, newName, newDate, newLocation, id);

    System.out.println("Activity ID " + id + " has been updated successfully.");
}

    private static void deleteActivities(Scanner sc) {
        activities ac = new activities();
    ac.viewAllActivities();  
        System.out.print("Enter the Activity ID to Delete: ");
        
    
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input, please enter a valid integer for Activity ID.");
            sc.nextLine();  
        }
        int id = sc.nextInt();
        sc.nextLine();

        String qry = "DELETE FROM tbl_activities WHERE s_id = ?";
        config conf = new config();
        conf.deleteRecord(qry, id);
    }
}
