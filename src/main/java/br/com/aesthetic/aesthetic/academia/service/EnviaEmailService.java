package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

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


    public void enviar(Aluno aluno, String titulo, String conteudo) throws MessagingException, IOException {
        log.info("Enviando email com anexo");

        String nomeArquivo = "aluno_" + aluno.getMatricula();

        // Gere o PDF e obtenha o caminho completo
        pdfService.gerarPDF(aluno,nomeArquivo);

        // Aguarde a conclusão da geração do PDF (ou atualização do repositório)
        waitForFileExistence(nomeArquivo);

        var mensagem = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(aluno.getEmail());
        helper.setSubject(titulo);
        helper.setText(conteudo, true);
        String caminhoCompleto = "classpath:/pdfs/" + nomeArquivo;

        // Adicione o anexo após o arquivo PDF ser geradoC:\Users\JONATA.FESVIP\Desktop\aesthetic-academia\src\main\java\br\com\aesthetic\aesthetic\academia\PDF
        helper.addAttachment("aluno.pdf", new File("src/main/java/br/com/aesthetic/aesthetic/academia/PDF"));

        javaMailSender.send(mensagem);

        log.info("Email com anexo enviado com sucesso.");

    }

    private void waitForFileExistence(String nomeArquivo) throws IOException {
        String caminhoCompleto = "pdfs/" + nomeArquivo+".pdf"; // Caminho relativo no classpath

        ClassPathResource resource = new ClassPathResource(caminhoCompleto);

        // Defina um tempo limite máximo para a espera (por exemplo, 5 minutos)
        long tempoLimiteMillis = System.currentTimeMillis() + (1 * 60 * 1000); // 5 minutos

        while (!resource.exists() && System.currentTimeMillis() < tempoLimiteMillis) {
            try {
                Thread.sleep(1000); // Aguarda 1 segundo antes de verificar novamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (!resource.exists()) {
            log.error("Tempo limite atingido. O arquivo não foi encontrado a tempo.");
            // Aqui você pode lançar uma exceção ou tomar outra ação apropriada em caso de falha
        }
    }

}
