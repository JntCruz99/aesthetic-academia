package br.com.aesthetic.aesthetic.academia.service.impl;

import br.com.aesthetic.aesthetic.academia.domain.model.Nutricionista;
import br.com.aesthetic.aesthetic.academia.domain.repository.NutricionistaRepository;
import br.com.aesthetic.aesthetic.academia.service.NutricionistaService;
import br.com.aesthetic.aesthetic.academia.service.exceptions.EntityNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutricionistaServiceImpl implements NutricionistaService {

    @Autowired
    private final NutricionistaRepository nutricionistaRepository;

    public NutricionistaServiceImpl(NutricionistaRepository nutricionistaRepository) {
        this.nutricionistaRepository = nutricionistaRepository;
    }

    @Override
    public List<Nutricionista> findAll() {
        return nutricionistaRepository.findAll();
    }

    @Override
    public Nutricionista findById(Long id) {
        return nutricionistaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));
    }

    @Override
    public Nutricionista save(Nutricionista nutricionista) {
        return nutricionistaRepository.save(nutricionista);
    }

    @Override
    public Nutricionista update(Nutricionista nutricionista) {
        return null;
    }
}
