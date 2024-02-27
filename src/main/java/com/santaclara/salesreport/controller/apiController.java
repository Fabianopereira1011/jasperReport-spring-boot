package com.santaclara.salesreport.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@Data
@RestController
@RequestMapping("/api/v1/sale")
public class apiController {

    private final Relatorio jasperReport;
//api/v1/sale/relatorio
    @GetMapping("/relatorio")
    public ResponseEntity<Void> getRelatorio(HttpServletResponse response) throws IOException {
        //String cpf = clienteService.buscarPorUsuarioId(user.getId()).getCpf();
        System.out.println("##################################################################################");
		System.out.println("GetMapping Relatorio"); 
        jasperReport.addParams("projetorFabricante", "Projetor: Chriestie");
        jasperReport.addParams("projetorModelo", "DP2K-23B");
        jasperReport.addParams("lente", "1,35");
        
        System.out.println("##################################################################################");
		System.out.println("Parametros carregado."); 

        byte[] bytes = jasperReport.gerarPdf();
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "inline; filename=" + System.currentTimeMillis() + ".pdf");
        response.getOutputStream().write(bytes);
        System.out.println("##################################################################################");
		System.out.println("Enviando Relatorio"); 

        return ResponseEntity.ok().build();
    }
// /api/v1/sale/test
    @GetMapping("/test")
    public String test(){
        //String cpf = clienteService.buscarPorUsuarioId(user.getId()).getCpf();
        System.out.println("##################################################################################");
		System.out.println("GetMapping test"); 
        
        return "teste ok";
    }

}

