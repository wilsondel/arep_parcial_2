package org.example;

import java.util.ArrayList;

import static spark.Spark.*;


public class RestService {

    private static ArrayList<Integer> myList = new ArrayList<Integer>();

    public static void main(String... args){
        staticFiles.location("/public");
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
        get("collatzsequence", (req,res) -> {
            String value = req.queryParams("value");
            res.type("application/json");
            return "{\n" +
                    "\n" +
                    " \"operation\": \"collatzsequence\",\n" +
                    "\n" +
                    " \"input\":  "+ value +",\n" +
                    "\n" +
                    " \"output\": \"" + collatzSequence(value) +
                    "\"\n" +
                    "}";
        });

        post("collatzsequence", (req,res) -> {
            String value = req.queryParams("value");
            res.type("application/json");
            return "{\n" +
                    "\n" +
                    " \"operation\": \"collatzsequence\",\n" +
                    "\n" +
                    " \"input\":  "+ value +",\n" +
                    "\n" +
                    " \"output\": \"" + collatzSequence(value) +
                    "\"\n" +
                    "}";
        });

    }



    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String collatzSequence(String number) {
        myList = new ArrayList<Integer>();
        int num = Integer.parseInt(number);

        if (num == 0) return "0";

        myList.add(num);
        buildListSequence(num);
        String sequence = buildStringSequence();

        return sequence;

    }


    private static ArrayList<Integer> buildListSequence(int num) {


        if (num == 1) return myList;

        if (num % 2 == 0) {
            num = num /2;
        } else {
            num = 3 * num + 1;
        }

        myList.add(num);
        return buildListSequence(num);

    }

    private static String buildStringSequence() {
        String sequence = "";
        for(int i = 0; i < myList.size()-1; i++) {
            sequence += myList.get(i) + " -> ";
        }
        sequence += myList.get( myList.size()-1 );
        return sequence;
    }


    }
