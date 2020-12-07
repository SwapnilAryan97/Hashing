package com.company;

import java.io.*;
import java.util.Scanner;
//import com.company.Hashtable;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // write your code here


        // *** READING THE FILE WITH THE CODE BELOW ***
        //------------------------------------------------------

        String[] line;
        String value = "";

        try {
            Hashtable T = null;
            /*
            Read from terminal ot cmd
             */
            //File myObj = new File(args[0]);

            /*
            Read from a file
             */
            File myObj = new File("file.txt");
            //System.out.println(myObj.getAbsolutePath());
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                line = data.split(" ", 2);

                int n = Integer.parseInt(line[0]); //int value

                //System.out.println(line[1]);

                if(n != 13){
                    value = line[1].toLowerCase(); //string value
                }

                switch(n){
                    case 14: //creating hashtable with "num" slots
                        T = new Hashtable(Integer.parseInt(value));
                        T.createArray(Integer.parseInt(value)); // 15*value implemented in the method
                        continue;
                    case 10: //inserting string into hashtable
                        T.insert(value);
                        continue;
                    case 11: //deletion
                        T.delete(value);
                        continue;
                    case 12: //search
                        T.search(value);
                        continue;
                    case 13: //print hashtable
                        T.print();
                        continue;
                    case 15: //comment
                        //code here
                        continue;
                    default:
                        throw new IllegalStateException("Unexpected value: " + n);
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("error occurred");

        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Provide valid input");
        }
    }
}

