package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
@Slf4j
public class PdfService {

    @Value("${pdf.output.directory}")
    private String pdfOutputDirectory;

    public void gerarPDF(Aluno aluno, String nomeArquivo) {
        log.info("gerando pdf");
        nomeArquivo = nomeArquivo+".pdf";
        String caminhoCompleto = pdfOutputDirectory + File.separator + nomeArquivo;
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(caminhoCompleto));
            document.open();
            document.add(new Paragraph("Informações do Paciente"));
            document.add(new Paragraph("Nome: " + aluno.getNome()));
            document.add(new Paragraph("Idade: " + aluno.getIdade()));
            document.add(new Paragraph("Endereço: " + aluno.getEndereco()));
            document.close();
            log.info("pdf gerado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
