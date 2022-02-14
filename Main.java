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
            System.out.println("\nBLOODDRIVE\n1.Donors\n2.Trucks\n3.Drives\n4.One Particular Drive\n[quit] to quit");
            action = in.nextLine();
            if (action.equals("1")){
                System.out.println("\nDONORS\n1.View\n2.Search By ID\n3.By Type\n[quit] to quit");
                action = in.nextLine();
//                        System.out.println("Enter name: ");
//                        String name = in.nextLine();
//                        System.out.println("Enter type: ");
//                        String type = in.nextLine();
//                        System.out.println("Enter number: ");
//                        String number = in.nextLine();
//                        db.addDonor(conn, name, type, number);
                //MOVE THIS TO AUTO FILL WHEN A NEW PERSON GIVES BLOOD IN THE DRIVE
                if (action.equals("1")) {
                    db.read(conn, "donors");
                } else if (action.equals("2")) {
                    System.out.println("id?");
                    int theID = in2.nextInt();
                    db.deleteRowById(conn, "donors", theID);
                } else if (action.equals("3")) {
                    System.out.println("Type?");
                    String type = in.nextLine();
                    db.searchType(conn,type);
                }
            } else if (action.equals("2")){
                String action3;
                System.out.println("\nTRUCKS\n1.Add\n2.Delete\n3.View\n4.Search\n[quit] to quit");
                action3 = in.nextLine();
                if (action3.equals("1")) {
                    System.out.println("Enter model: ");
                    String model = in.nextLine();
                    db.newTruck(conn, model);
                } else if (action3.equals("2")) {
                    System.out.println("id?");
                    int theID = in2.nextInt();
                    db.deleteRowById(conn, "trucks", theID);
                } else if (action3.equals("3")) {
                    db.read(conn, "trucks");
                } else if (action3.equals("4")){
                    System.out.println("ID?");
                    int searchID = in2.nextInt();
                    db.searchById(conn, "trucks", searchID);
                }
            } else if (action.equals("3")){
                String action1;
                System.out.println("\nDRIVES\n1.Add\n2.Delete\n3.View\n4.Search\n[quit] to quit");
                action1 = in.nextLine();
                if (action1.equals("1")) {
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
                } else if (action.equals("2")) {
                    System.out.println("id?");
                    int theID = in2.nextInt();
                    db.deleteRowById(conn, "drive", theID);
                } else if (action.equals("3")) {
                    db.read(conn, "drive");
                } else if (action.equals("4")){
                    System.out.println("ID?");
                    int searchID = in2.nextInt();
                    db.searchById(conn, "drive", searchID);
                }
            } else if (action.equals("4")){
                String action2;
                System.out.print("Drive ID?");
                int driveid = in2.nextInt();
                int fake = 3;
                if (fake == 3) {
                    do {
                        System.out.println("\nDRIVE " + driveid + "\n1.Donors\n2.Drive Info\n[back] to go back");
                        action2 = in.nextLine();
                        if (action2.equals("1")) {
                            System.out.println("\nDRIVE DONORS\n1.Blood Type\n2.Ounces given\n3.ID");
                            action2 = in.nextLine();
                            if (action2.equals("1")) {
                                System.out.println("Type?");
                                String type = in.nextLine();
                                String driveName = "drive" + driveid;
                                db.searchDriveType(conn,driveName, type);
                            } else if (action2.equals("2")) {
                                System.out.println("Ounces?");
                                int ounces = in2.nextInt();
                                String driveName = "drive" + driveid;
                                db.searchOunces(conn, driveName, ounces);
                            } else if (action2.equals("3")) {
                                //sql find id in db if exists
                            }
                        } else if (action2.equals("2")) {
                            db.printDriveInfo(conn, driveid);
                        }
                    } while (!action2.equals("back"));
                } else {
                    //create table for data and allow writing in it
                    //add new or returning donor
                    //enter donor name
                    //if returning, enter ounces
                    //if new, enter name, blood type, phone number, and ounces
                }
            } else if (action.equals("update")){
                db.updateOldDrives(conn);
            }
        }while (!action.equals("quit"));
        db = new DbFunctions();
    }
}
