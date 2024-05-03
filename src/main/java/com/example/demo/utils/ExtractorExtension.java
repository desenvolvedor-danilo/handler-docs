package com.example.demo.utils;

public class ExtractorExtension {
    public static String extractExtension(String originalFilename) {
        int i = originalFilename.lastIndexOf(".");
        return originalFilename.substring(i+1);
    }
}
