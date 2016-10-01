package com.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Owner on 9/28/2016.
 */

public class calcTime {
    public static void main(String[] args) {
        System.out.println("make serializable class and params for api: (input \"done\" to generate the output)"); // Display the string.

        String wholeString;
        String year;
        String month;
        String day;
        String hour;
        String minute;
        String second;

        Scanner in = new Scanner(System.in);

        wholeString = in.nextLine();
        year = wholeString.split("\\-")[0];

        month = wholeString.split("\\-")[1];
        day = wholeString.split("\\-")[2].split(" ")[0];

        hour = wholeString.split(" ")[1].split(":")[0];
        minute = wholeString.split(":")[1].split(":")[0];
        second = wholeString.split(":")[2];
        System.out.println(year+", "+
                month+", "+
                day+",\n"+
                hour+","+
                minute+", "+
                second+", "
        );


//        while (in.hasNext()) {
//            s = in.nextLine();
//            String input = s;
//            if (input.equals("done")) {
//                System.out.println("program terminated");
//                break;
//            }
//
//            String string = s.split(split_string)[0];
//            if (string.contains("*")) {
//
//            }
//        }


    }
}
