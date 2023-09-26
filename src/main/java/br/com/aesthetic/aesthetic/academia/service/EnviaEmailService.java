package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import br.com.aesthetic.aesthetic.academia.domain.model.Dieta;
import br.com.aesthetic.aesthetic.academia.domain.model.Treino;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Slf4j
public class EnviaEmailService {

    @Autowired
    private  JavaMailSender javaMailSender;
    @Autowired
    private  PdfService pdfService;

    @Async
    public void enviarTextoSimples(String para, String titulo, String conteudo) {
        log.info("Enviando email simples");

        var mensagem = new SimpleMailMessage();
        mensagem.setTo(para);

        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        javaMailSender.send(mensagem);
        log.info("Email enviado com sucesso!");
    }

    @Async
    public void enviarBoasVindas(Aluno aluno, String titulo, String conteudo) throws MessagingException {
        log.info("Enviando email com anexo");

        String nomeArquivo = "aluno_BOASV" + aluno.getMatricula();
        // Gere o PDF e obtenha o caminho completo
        pdfService.gerarBoasVindasPdf(aluno,nomeArquivo);

        var mensagem = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(aluno.getEmail());
        helper.setSubject(titulo);
        helper.setText(conteudo, true);
        String caminhoCompleto = "PDFS/" + nomeArquivo+".pdf";

        // Adicione o anexo após o arquivo PDF ser gerado
        helper.addAttachment("aluno.pdf", new File(caminhoCompleto));

        javaMailSender.send(mensagem);
        pdfService.deletarPDF(nomeArquivo);
        log.info("Email com anexo enviado com sucesso.");
    }

    @Async
    public void enviarEmailDeDietas(Aluno aluno, String titulo, String conteudo, List<Dieta> dietas) throws MessagingException {
        log.info("Enviando email com anexo");

        String nomeArquivo = "aluno_DIETAS" + aluno.getMatricula();
        // Gere o PDF e obtenha o caminho completo
        pdfService.gerarPdfsDietas(aluno,dietas,nomeArquivo);

        var mensagem = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(aluno.getEmail());
        helper.setSubject(titulo);
        helper.setText(conteudo, true);
        String caminhoCompleto = "PDFS/" + nomeArquivo+".pdf";

        // Adicione o anexo após o arquivo PDF ser gerado
        helper.addAttachment("aluno.pdf", new File(caminhoCompleto));

        javaMailSender.send(mensagem);
        pdfService.deletarPDF(nomeArquivo);
        log.info("Email com anexo enviado com sucesso.");
    }

    @Async
    public void enviarEmailDeTreinos(Aluno aluno, String titulo, String conteudo, List<Treino> treinos) throws MessagingException {
        log.info("Enviando email com anexo");

        String nomeArquivo = "aluno_TREINOS" + aluno.getMatricula();
        // Gere o PDF e obtenha o caminho completo
        pdfService.gerarPdfsTreinos(aluno,treinos,nomeArquivo);

        var mensagem = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(aluno.getEmail());
        helper.setSubject(titulo);
        helper.setText(conteudo, true);
        String caminhoCompleto = "PDFS/" + nomeArquivo+".pdf";

        // Adicione o anexo após o arquivo PDF ser gerado
        helper.addAttachment("aluno.pdf", new File(caminhoCompleto));

        javaMailSender.send(mensagem);
        pdfService.deletarPDF(nomeArquivo);
        log.info("Email com anexo enviado com sucesso.");
    }

}
