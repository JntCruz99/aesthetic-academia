package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class EnviaEmailService {

    @Autowired
    private  JavaMailSender javaMailSender;
    @Autowired
    private  PdfService pdfService;

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
    @Transactional
    public void enviar(Aluno aluno, String titulo, String conteudo) throws MessagingException {
        log.info("Enviando email com anexo");

        // Gere o arquivo PDF após a atualização do contexto
        String nomeArquivo = "aluno-"+aluno.getNome();

        var mensagem = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(aluno.getEmail());
        helper.setSubject(titulo);
        helper.setText(conteudo, true);

        // Adicione o anexo após o arquivo PDF ser gerado
        helper.addAttachment("aluno.pdf", new ClassPathResource("PDFS/"+nomeArquivo + ".pdf"));

        javaMailSender.send(mensagem);
        log.info("Email com anexo enviado com sucesso.");
    }

}
