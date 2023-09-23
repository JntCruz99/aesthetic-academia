package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class EnviaEmailService {

    @Value("${pdf.output.directory}")
    private String pdfOutputDirectory;
    private final JavaMailSender javaMailSender;

    public EnviaEmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private PdfService pdfService;

    @Async
    public void enviarR(String para, String titulo, String conteudo) {
        log.info("Enviando email simples");

        var mensagem = new SimpleMailMessage();
        mensagem.setTo(para);

        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        javaMailSender.send(mensagem);
        log.info("Email enviado com sucesso!");
    }

    @Async
    public void enviar(Aluno aluno, String titulo, String conteudo) throws MessagingException {
        log.info("Enviando email com anexo");
        var mensagem = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true);
        String nomeArquivo = "aluno_" + aluno.getNome() + ".pdf";

        // Gere o PDF e obtenha o caminho completo
        String caminhoCompleto = pdfOutputDirectory + File.separator + nomeArquivo;
        pdfService.gerarPDF(aluno, caminhoCompleto);

        helper.setTo(aluno.getEmail());
        helper.setSubject(titulo);
        helper.setText(conteudo, true);

        // Use o caminho completo ao adicionar o anexo
        helper.addAttachment(nomeArquivo, new FileSystemResource(caminhoCompleto));

        javaMailSender.send(mensagem);
        log.info("Email com anexo enviado com sucesso.");
    }

}
