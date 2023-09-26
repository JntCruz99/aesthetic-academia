package br.com.aesthetic.aesthetic.academia.service.impl;

import br.com.aesthetic.aesthetic.academia.domain.model.Alimento;
import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import br.com.aesthetic.aesthetic.academia.domain.model.Dieta;
import br.com.aesthetic.aesthetic.academia.domain.model.Nutricionista;
import br.com.aesthetic.aesthetic.academia.domain.repository.AlimentoRepository;
import br.com.aesthetic.aesthetic.academia.domain.repository.AlunoRepository;
import br.com.aesthetic.aesthetic.academia.domain.repository.DietaRepository;
import br.com.aesthetic.aesthetic.academia.service.AlunoService;
import br.com.aesthetic.aesthetic.academia.service.EnviaEmailService;
import br.com.aesthetic.aesthetic.academia.service.NutricionistaService;
import br.com.aesthetic.aesthetic.academia.service.PdfService;
import br.com.aesthetic.aesthetic.academia.service.exceptions.DuplicateEnrollmentException;
import br.com.aesthetic.aesthetic.academia.service.exceptions.EntityNotFoundExceptions;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EnviaEmailService enviaEmailService;

    @Autowired
    private final AlunoRepository alunoRepository;

    @Autowired
    private NutricionistaService nutricionistaService;

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Autowired
    private PdfService pdfService;

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
    public Aluno save(Aluno aluno) throws MessagingException, IOException {
        if (alunoRepository.existsByMatricula(aluno.getMatricula())) {
            throw new DuplicateEnrollmentException("Matrícula já existe: " + aluno.getMatricula());
        }
        aluno.setAtivo(true);
        enviaEmailService.enviar(aluno,"Bem vindo(a) a academia aesthetic " + aluno.getNome(),"Estamos muito feliz em termos você conosco " + aluno.getNome());

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

    @Override
    public Aluno createDieta(Long idAluno, Long idNutricionista, Dieta dieta) throws MessagingException, IOException {
        Aluno aluno = findById(idAluno);
        Nutricionista nutricionista = nutricionistaService.findById(idNutricionista);
        List<Dieta> dietaList = new ArrayList<>();
        dietaList.add(dieta);
        aluno.setDietas(dietaList);
        alimentoRepository.saveAll(dieta.getAlmoco());
        alimentoRepository.saveAll(dieta.getJanta());
        alimentoRepository.saveAll(dieta.getCafeDaManha());
        alimentoRepository.saveAll(dieta.getPosTreino());
        alimentoRepository.saveAll(dieta.getPreTreino());
        alimentoRepository.saveAll(dieta.getLancheDaManha());
        alimentoRepository.saveAll(dieta.getLancheDaTarde());
        dietaRepository.save(dieta);
        alunoRepository.save(aluno);

        enviaEmailService.enviar(aluno,"Sua dieta foi Atualizada " + aluno.getNome(),
                "Parabens sua dieta foi atualizada para vc alcançar novas metas " + aluno.getNome()+
                        "\n"+ dieta);

        return aluno;
    }


}
