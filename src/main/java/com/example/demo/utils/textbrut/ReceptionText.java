package com.example.demo.utils.textbrut;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class ReceptionText {
    public static void inputAndOutput(String textInput,String textOutput){
        try{
            InputStream in = new FileInputStream(textInput);
            byte b [] = new byte[in.available()];
            in.read(b);
            String textReaded = new String(b);
            String sorted [] = textReaded.split("\n");
            Arrays.sort(sorted);
            PrintStream ps = new PrintStream(textOutput);
            for(int i=0;i<sorted.length;i++){
                ps.print(sorted[i].toUpperCase().charAt(0)+sorted[i].substring(1, sorted[i].length()));
                ps.println();
            }
            ps.close();
        }catch(IOException e){

        }
        

    }

    
}
