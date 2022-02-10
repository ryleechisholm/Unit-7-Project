package com.company;
import javax.xml.transform.Result;
import java.time.*;
import java.sql.*;
import java.util.Scanner;

public class DbFunctions {
    public Connection connectToDb(String dbname){
        Connection conn=null;
        try {
            Class.forName("org.postgresql.Driver");
            conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname);
            if (conn!=null){
                System.out.println("Connection established");
            }
            else {
                System.out.println("Failed");
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public void newTruck(Connection conn, String model){
        Statement statement;
        ResultSet rs;
        try{
            String query = "select * from trucks order by id desc limit 1";
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            int newID = 0;
            while (rs.next()) {
                newID = (rs.getInt("id") + 1);
            }
            query = String.format("insert into trucks(id,model) values('%s','%s');", newID, model);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Truck added");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void addDonor(Connection conn, String name, String type, String address){
        Statement statement;
        ResultSet rs;
        try {
            String query = "select * from donors order by id desc limit 1";
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            int newID = 0;
            while (rs.next()) {
                newID = (rs.getInt("id") + 1);
            }
            query=String.format("insert into donors(id, name, type, number) values('%s','%s','%s','%s');",newID, name,type, address);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Donor added");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void addDrive(Connection conn, String date, String time, int truck, int goal, String location){
        Statement statement;
        ResultSet rs;
        try {
            String query = "select * from drive order by id desc limit 1";
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            int newID = 0;
            while (rs.next()) {
                newID = (rs.getInt("id") + 1);
            }
            query=String.format("insert into drive(id,date, time, truck, goal, location) values('%s','%s','%s','%s','%s','%s');", newID, date, time, truck, goal, location);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Donor added");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void read(Connection conn, String tableName){
        Statement statement;
        ResultSet rs=null;
        try{
            String query=String.format("select * from %s",tableName);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            switch (tableName) {
                case "trucks":
                    while (rs.next()) {
                        System.out.print(rs.getString("id") + " ");
                        System.out.println(rs.getString("model") + " ");
                    }
                    break;
                case "donors":
                    while (rs.next()) {
                        System.out.print(rs.getString("id") + "  ");
                        System.out.print(rs.getString("name") + "  ");
                        System.out.print(rs.getString("type") + "  ");
                        System.out.println(rs.getString("number") + "  ");
                    }
                    break;
                case "drive":
                    while (rs.next()) {
                        System.out.print(rs.getString("id") + "  ");
                        System.out.print(rs.getString("date") + "  ");
                        System.out.print(rs.getString("time") + "  ");
                        System.out.print(rs.getString("truck") + "  ");
                        System.out.print(rs.getString("goal") + "  ");
                        System.out.println(rs.getString("location") + "  ");
                    }
                    break;
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void searchById(Connection conn, String tableName, int id) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s where id='%s'", tableName, id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            switch (tableName) {
                case "trucks":
                    while (rs.next()) {
                        System.out.print(rs.getString("id") + " ");
                        System.out.println(rs.getString("model") + " ");
                    }
                    break;
                case "donors":
                    while (rs.next()) {
                        System.out.print(rs.getString("id") + "  ");
                        System.out.print(rs.getString("name") + "  ");
                        System.out.print(rs.getString("type") + "  ");
                        System.out.println(rs.getString("number") + "  ");
                    }
                    break;
                case "drive":
                    while (rs.next()) {
                        System.out.print(rs.getString("id") + "  ");
                        System.out.print(rs.getString("date") + "  ");
                        System.out.print(rs.getString("time") + "  ");
                        System.out.print(rs.getString("truck") + "  ");
                        System.out.print(rs.getString("goal") + "  ");
                        System.out.println(rs.getString("location") + "  ");
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteRowById(Connection conn, String tableName, int id){
        Statement statement;
        try {
            String query=String.format("delete from %s where id='%s'", tableName, id);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateOldDrives(Connection conn){
        Statement statement;
        ResultSet rs;
        try {
            Date date = Date.valueOf(LocalDate.now());
            String query=String.format("select * from drive where date < cast('%s' as date);", date);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                create_table(conn, rs.getInt("id"));
            }
            System.out.println("Drive table completed");
        } catch(Exception e){
            System.out.print("");
        }
    }

    public void create_table(Connection conn, int id){
        Statement statement;
        int random_int = (int)Math.floor(Math.random()*(1+100-1)+1);
        try{
            String query = String.format("create table drive%s(id BIGSERIAL, donorID int, given int, primary key(id), foreign key(donorID) references donors(id));", id);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            do{
                int randomID = (int)Math.floor(Math.random()*(1+100-1)+1);
                int randomID2 = (int)Math.floor(Math.random()*(1+3-1)+1);
                query = String.format("insert into drive%s(donorID, given) values( %s, %s);", id, randomID, randomID2);
                statement=conn.createStatement();
                statement.executeUpdate(query);
                random_int -= 1;
            }while (random_int > 0);
        } catch (Exception e){
            System.out.print("");
        }
    }

    public void editDrive(Connection conn){
        Statement statement;
        ResultSet rs;
        String choice;
        Scanner in = new Scanner(System.in);
        System.out.println("Drive id?");
        int driveID = in.nextInt();
        try{
            String query = String.format("SELECT TABLE_NAME FROM blooddrive.INFORMATION_SCHEMA.TABLES where TABLE_NAME='drive%s';",driveID);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                String thisTable = rs.getString("TABLE_NAME");
                System.out.println(thisTable);
                do {
                    System.out.println("view all: 1\nview by pint: 2\nview by type: 3\nquit");
                    choice = in.nextLine();
                    switch (choice) {
                        case "1":
                            //join donors, drive#, and drive tables to display names, types, pints, and drive date
                            break;
                        case "2":
                            //sort that join by pint
                            break;
                        case "3":
                            //sort that join by type
                            break;
                    }
                } while (!choice.equals("quit"));
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
