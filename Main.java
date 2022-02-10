package com.company;
import java.sql.*;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connectToDb("blooddrive");
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        String action;
        do {
            System.out.println("BLOODDRIVE\n1.Donors\n2.Trucks\n3.Drives\n4.One Particular Drive\n[quit] to quit");
            action = in.nextLine();
            switch (action) {
                case "1":
                    System.out.println("DONORS\n1.Add\n2.Edit\n3.Delete\n4.View\n5.Search\n[quit] to quit");
                    action = in.nextLine();
                    switch (action) {
                        case "1":
                            System.out.println("Enter name: ");
                            String name = in.nextLine();
                            System.out.println("Enter type: ");
                            String type = in.nextLine();
                            System.out.println("Enter number: ");
                            String number = in.nextLine();
                            db.addDonor(conn, name, type, number);
                            break;
                        case "2":
                           //edit
                            break;
                        case "3":
                            System.out.println("id?");
                            int theID = in2.nextInt();
                            db.deleteRowById(conn, "donors", theID);
                            break;
                        case "4":
                            db.read(conn, "donors");
                            break;
                        case "5":
                            System.out.println("ID?");
                            int searchID = in2.nextInt();
                            db.searchById(conn, "donors",searchID);
                            break;
                    }
                    break;
                case "2":
                    String action3;
                    do {
                        System.out.println("TRUCKS\n1.Add\n2.Edit\n3.Delete\n4.View\n5.Search\n[quit] to quit");
                        action3 = in.nextLine();
                        switch (action) {
                            case "1":
                                System.out.println("Enter model: ");
                                String model = in.nextLine();
                                db.newTruck(conn, model);
                                break;
                            case "2":
                                //edit
                                break;
                            case "3":
                                System.out.println("id?");
                                int theID = in2.nextInt();
                                db.deleteRowById(conn, "donors", theID);
                                break;
                            case "4":
                                db.read(conn, "trucks");
                                break;
                            case "5":
                                System.out.println("ID?");
                                int searchID = in2.nextInt();
                                db.searchById(conn, "trucks",searchID);
                                break;
                        }
                    }while(!action3.equals("quit"));
                    break;
                case "3":
                    String action1;
                    do{
                        System.out.println("DRIVES\n1.Add\n2.Edit\n3.Delete\n4.View\n5.Search\n[quit] to quit");
                        action1 = in.nextLine();
                        switch (action) {
                            case "1":
                                System.out.println("Enter date: ");
                                String date = in.nextLine();
                                System.out.println("Enter time: ");
                                String time = in.nextLine();
                                System.out.println("Enter truck num: ");
                                int truck = in2.nextInt();
                                System.out.println("Enter goal ounces: ");
                                int goal = in2.nextInt();
                                System.out.println("Enter location: ");
                                String location = in.nextLine();
                                db.addDrive(conn, date, time, truck, goal, location);
                                break;
                            case "2":
                                //edit
                                break;
                            case "3":
                                System.out.println("id?");
                                int theID = in2.nextInt();
                                db.deleteRowById(conn, "drive", theID);
                                break;
                            case "4":
                                db.read(conn, "drive");
                                break;
                            case "5":
                                System.out.println("ID?");
                                int searchID = in2.nextInt();
                                db.searchById(conn, "drive",searchID);
                                break;
                        }
                    } while (!action1.equals("quit"));
                    break;
                case "4":
                    String action2;
                    do{
                        System.out.println("PARTICULAR DRIVES\n1.Donors\n2.Trucks\n[quit] to quit");
                        action2 = in.nextLine();
                    }while (!action2.equals("quit"));
                    break;
                case "5":
                    db.updateOldDrives(conn);
                    break;
                case "6":
                    db.editDrive(conn);

            }
        } while (!action.equals("quit"));
        db = new DbFunctions();
    }
}
