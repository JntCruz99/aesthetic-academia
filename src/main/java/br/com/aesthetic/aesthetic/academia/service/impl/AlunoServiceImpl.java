package br.com.aesthetic.aesthetic.academia.service.impl;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import br.com.aesthetic.aesthetic.academia.domain.repository.AlunoRepository;
import br.com.aesthetic.aesthetic.academia.service.AlunoService;
import br.com.aesthetic.aesthetic.academia.service.exceptions.DuplicateEnrollmentException;
import br.com.aesthetic.aesthetic.academia.service.exceptions.EntityNotFoundExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private final AlunoRepository alunoRepository;

    public AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno findById(Long id) {
        return alunoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));
    }

    @Override
    public Aluno save(Aluno aluno) {
        if (alunoRepository.existsByMatricula(aluno.getMatricula())) {
            throw new DuplicateEnrollmentException("Matrícula já existe: " + aluno.getMatricula());
        }
        aluno.setAtivo(true);
        log.info("Enviando email simples");

        var mensagem = new SimpleMailMessage();
        mensagem.setTo(aluno.getEmail());

        mensagem.setSubject("Bem vindo(a) a academia aesthetic " + aluno.getNome());
        mensagem.setText("Estamos muito feliz em termos você conosco " + aluno.getNome());
        javaMailSender.send(mensagem);
        log.info("email Enviado!!");
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno update(Aluno aluno, Long id) {
         Aluno  alunoOptional = alunoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));
         if (alunoOptional != null){
             if (alunoOptional.getNome() != null){
                 alunoOptional.setNome(aluno.getNome());
             } else if (alunoOptional.getEmail() != null) {
                 alunoOptional.setEmail(aluno.getEmail());
             }else if (alunoOptional.getDataDeCadastro() !=null){
                 alunoOptional.setDataDeCadastro(aluno.getDataDeCadastro());
             }else if(alunoOptional.getDataDeNascimento() != null){
                 alunoOptional.setDataDeNascimento(aluno.getDataDeNascimento());
             }else if (alunoOptional.getEndereco() != null){
                 alunoOptional.setEndereco(aluno.getEndereco());
             }else if (alunoOptional.getIdade() != 0){
                 alunoOptional.setIdade(aluno.getIdade());
             }else if(alunoOptional.getTelefone() != null){
                 alunoOptional.setTelefone(aluno.getTelefone());
             }else if (!alunoOptional.isAtivo())
                 alunoOptional.setAtivo(aluno.isAtivo());
         }

        return alunoRepository.save(alunoOptional);
    }


}
