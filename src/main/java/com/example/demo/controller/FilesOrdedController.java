package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping(value = "/order", produces = {"application/json"})
@Slf4j
@CrossOrigin("*")
public class FilesOrdedController extends HttpServlet {
    @Override
    @GetMapping("/file")
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException{
        File arquivo = new File("./text-sorted-file.txt");
        String nome = arquivo.getName();
        int tamanho = (int) arquivo.length();
        response.setContentType("text/plain;charset=UTF-8");
        response.setContentLength(tamanho);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");
        Path path = Paths.get("./text-sorted-file.txt");
        OutputStream output = response.getOutputStream();
        Files.copy(path, output);
    }
    @GetMapping("/txt")
    public void doGetTxt(HttpServletRequest request,HttpServletResponse response)throws IOException{
        File arquivo = new File("./textSorted.txt");
        String nome = arquivo.getName();
        int tamanho = (int) arquivo.length();
        response.setContentType("text/plain");
        response.setContentLength(tamanho);
        response.setHeader("Content-Disposition", "attachment;filename= "+nome);
        Path path = Paths.get("./textSorted.txt");
        OutputStream output = response.getOutputStream();
        Files.copy(path, output);
    }
}
