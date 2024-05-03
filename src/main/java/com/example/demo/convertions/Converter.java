package com.example.demo.convertions;
import com.aspose.words.Document;

public class Converter {

    public void convert(String fileInput, String fileOutput){
        try {
            Document convert = new Document(fileInput);    
            convert.save(fileOutput);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro durante a conversão "+e.getMessage());
        }
        
    }
    
}
