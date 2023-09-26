package br.com.aesthetic.aesthetic.academia.service.impl;

import br.com.aesthetic.aesthetic.academia.domain.model.*;
import br.com.aesthetic.aesthetic.academia.domain.repository.*;
import br.com.aesthetic.aesthetic.academia.service.*;
import br.com.aesthetic.aesthetic.academia.service.exceptions.DuplicateEnrollmentException;
import br.com.aesthetic.aesthetic.academia.service.exceptions.EntityNotFoundExceptions;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private TreinoRepository treinoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private ProfessorService professorService;

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
    public Aluno save(Aluno aluno) throws MessagingException {
        if (alunoRepository.existsByMatricula(aluno.getMatricula())) {
            throw new DuplicateEnrollmentException("Matrícula já existe: " + aluno.getMatricula());
        }
        aluno.setAtivo(true);
        enviaEmailService.enviarBoasVindas(aluno,"Bem vindo(a) a academia aesthetic " + aluno.getNome(),"Estamos muito feliz em termos você conosco " + aluno.getNome());

        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno update(Aluno aluno, Long id) {
         Aluno alunoOptional = alunoRepository.findById(id).orElseThrow(
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
    public Aluno createDieta(Long idAluno, Long idNutricionista, Dieta dieta) throws MessagingException{
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

        enviaEmailService.enviarEmailDeDietas(aluno,"Sua dieta foi Atualizada ",
                "Parabens!! sua dieta foi atualizada!! "+
                        "\n", aluno.getDietas());

        return aluno;
    }

    @Override
    public Aluno createTreino(Long idAluno, Long idProfessor, Treino treino) throws MessagingException {
        // Encontre o aluno pelo ID
        Aluno aluno = findById(idAluno);

        Professor professor = professorService.findById(idProfessor);
        // Associe o treino ao aluno
        treino.setAluno(aluno);

        // Salve o treino no repositório
        Treino savedTreino = treinoRepository.save(treino);

        // Associe os exercícios ao treino (você pode fazer isso da maneira que preferir)
        List<Exercicio> exercicios = treino.getExercicio();
        for (Exercicio exercicio : exercicios) {
            exercicio.setTreino(savedTreino);
            exercicioRepository.save(exercicio);
        }

        // Atualize o aluno com o novo treino (se necessário)
        aluno.getTreinos().add(savedTreino);
        alunoRepository.save(aluno);

        enviaEmailService.enviarEmailDeTreinos(aluno,"Seu Treino foi Atualizado ",
                "Parabens!! seu Treino foi atualizado!! "+
                        "\n", aluno.getTreinos());

        // Retorne o aluno atualizado
        return aluno;
    }

}
