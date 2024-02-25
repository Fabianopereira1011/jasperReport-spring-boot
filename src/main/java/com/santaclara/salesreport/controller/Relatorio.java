package com.santaclara.salesreport.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class Relatorio {

    private final ResourceLoader resourceLoader;

    private Map<String, Object> params = new HashMap<>();

    private static final String JASPER_DIRETORIO = "classpath:reports/";

    public void addParams(String key, Object value) {
        //this.params.put("IMAGEM_DIRETORIO", JASPER_DIRETORIO); 
        //this.params.put("REPORT_LOCALE", new Locale("pt", "BR"));
        this.params.put(key, value);
    }

    public byte[] gerarPdf() {
        byte[] bytes = null;
        try {                                                                         //proposta.jasper
            Resource resource = resourceLoader.getResource(JASPER_DIRETORIO.concat("proposta.jasper"));
            InputStream stream = resource.getInputStream();
            System.out.println("#############################################################");
            System.out.println("O relatorio vai se preenchido agora.");
            JasperPrint print = JasperFillManager.fillReport(stream, params, new JREmptyDataSource());
            System.out.println("#############################################################");
            System.out.println("Preparando o relatorio para ser um array de bytes.");
            bytes = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            log.error("Jasper Reports ::: ", e.getCause());
            throw new RuntimeException(e);
        }
        return bytes;
    }
}

