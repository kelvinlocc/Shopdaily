package com.example;

import java.util.Arrays;
import java.util.Scanner;

class makeSerializedDataClass {
    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.
        int a;
        float b;
        String s;
        String whole_string = "";
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a string2");

        while (in.hasNext()) {
            s = in.nextLine();
            System.out.println("You entered string " + s);
            whole_string = whole_string +"@SerializedName(\""+s.split(":")[0]+"\")"+"\n"+" public String "+s.split(":")[0]+";\n" ;

            System.out.println("the whole string\n" + whole_string);
        }




    }
}