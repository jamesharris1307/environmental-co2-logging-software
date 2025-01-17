package com.example.envlog.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInfo {
    private String userId;
    private String name;
    private String username;
    private String password;

    public UserInfo(String userId, String name, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getUserId() {return userId;}

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return userId + "," + name + "," + username + "," + password;
    }

    public static String showCsvData() {
        StringBuffer dataBuilder = new StringBuffer();
        String fileNameDefined = "src/resources/CO2Data.csv";
        File file = new File(fileNameDefined);

        try(Scanner inputStream = new Scanner(file)) {


            //loops line-by-line


            while (inputStream.hasNextLine()) {

//              reading line by line and print this data in to console
                String data = inputStream.nextLine();
                dataBuilder.append(data).append("\n");
            }
            //  close scanner
            inputStream.close();


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return dataBuilder.toString();
    }
}


