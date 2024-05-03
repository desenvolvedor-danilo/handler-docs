package com.example.demo.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.convertions.Converter;
import com.example.demo.utils.ExtractorExtension;
import com.example.demo.utils.textbrut.ReceptionText;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value = "/api", produces = {"application/json"})
@Slf4j
@CrossOrigin("*")
public class FileStorageController extends HttpServlet{
   Converter converterPDF = new Converter();

    @PostMapping("/arquivo")
    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile file){
        log.info("carregando arquivo",file.getOriginalFilename());
        var path ="./";    
        var output="./file-docx-converted.pdf";
        var caminho =path+file.getOriginalFilename()+"."+ExtractorExtension.extractExtension(file.getOriginalFilename());
        log.info("Novo nome do arquivo", caminho);
        try {
            Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            converterPDF.convert(caminho, output);
            return new ResponseEntity<String>("{\"Mensagen\":\"Arquivo carregado com sucesso\"}",HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erro ao processar arquivo", e);

            
        }
        return new ResponseEntity<String>("{\"Mensagen\":\"Erro ao carregar Arquivo\"}",HttpStatus.BAD_REQUEST);
    }
    
    
    @GetMapping("/pdf")
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        File arquivo = new File("./file-docx-converted.pdf");
        String nome = arquivo.getName();
        int tamanho = (int) arquivo.length();

        response.setContentType("application/pdf"); // tipo do conteúdo
        response.setContentLength(tamanho);  // opcional
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "\"");
        Path path = Paths.get("./file-docx-converted.pdf");
        OutputStream output = response.getOutputStream();
        Files.copy(path, output);
    }
    @PostMapping("/ordem")
    public ResponseEntity<String> sortText(@RequestBody String text)throws IOException{
            String sorted [] = text.split("\n");
            PrintStream ps = new PrintStream("./textSorted.txt");
            Arrays.sort(sorted);
            for(int i=0;i<sorted.length;i++){
                ps.print(sorted[i].toUpperCase().charAt(0)+sorted[i].substring(1, sorted[i].length()));
                ps.println();
            }
            ps.close();
        return ResponseEntity.status(HttpStatus.OK).body("{\"mensagem\":\"Enviado com sucesso\"}"); 
    }
    @PostMapping("/organizar")
    public ResponseEntity<String> sortText(@RequestParam("file") MultipartFile file){
        log.info("carregando arquivo "+file.getOriginalFilename());
        var path = "./";
        var output = "./text-sorted-file.txt";
        var where = path+file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), Path.of(where),StandardCopyOption.REPLACE_EXISTING);
            ReceptionText.inputAndOutput(where,output);
            return new ResponseEntity<>("{\"Mensagen\":\"Arquivo carregado com sucesso\"}",HttpStatus.OK);
        } catch (Exception e) {

        }
        return new ResponseEntity<>("{\"Mensagen\":\"Erro ao carregar Arquivo\"}",HttpStatus.BAD_REQUEST);
        
    }
}