package br.com.aesthetic.aesthetic.academia.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DietScheduler {

    @Scheduled(cron = "0 0 0 * * ?") // Executa todos os dias à meia-noite
    public void verificarDietas() {
        // Coloque aqui a lógica para verificar as dietas dos alunos
        // e enviar um email para o nutricionista quando necessário
    }
}
