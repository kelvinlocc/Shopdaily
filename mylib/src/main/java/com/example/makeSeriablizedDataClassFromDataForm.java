package com.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Owner on 9/10/2016.
 */
public class makeSeriablizedDataClassFromDataForm {
    public static void main(String[] args) {
        System.out.println("make serializable class and params for api: (input \"done\" to generate the output)"); // Display the string.

        String s;
        String serialized_ClassString = "";
        String string_param = "";
        String string_mapping ="";
        Scanner in = new Scanner(System.in);

        ArrayList<String> myStringList = new ArrayList<String>();

        String split_string = "\\*";

        while (in.hasNext()) {
            s = in.nextLine();
            String input = s;
            if (input.equals("done")) {
                System.out.println("program terminated");
                break;
            }
//            System.out.println("You entered string " + s);

            String string = s.split(split_string)[0];

            serialized_ClassString = serialized_ClassString + "@SerializedName(\"" + string + "\")" + "\n";
            serialized_ClassString = serialized_ClassString + "public String " + string + ";\n";

            string_param = string_param + "final String "+string+",";
            string_mapping = string_mapping + "params.put(\""+string+"\", "+string+");\n";
            // tester:
            myStringList.add(string);


        }
        System.out.println("the whole serialized data class: \n\n" + serialized_ClassString);
        System.out.printf("params: \n" +string_param);
        System.out.printf("maping:\n"+string_mapping);

//        System.out.println("loop the string array list: ");
//        for (int i = 0; i < myStringList.size(); i++) {
//            System.out.println("my string " + myStringList.get(i) + "\n");
//        }


    }
}
