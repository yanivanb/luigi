package be.vdab.luigi.services;

import be.vdab.luigi.domain.Gast;
import be.vdab.luigi.domain.Pizza;
import be.vdab.luigi.repositories.GastenboekRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GastService {
    private final GastenboekRepository gastenboekRepository;

    public GastService(GastenboekRepository gastenboekRepository) {
        this.gastenboekRepository = gastenboekRepository;
    }

    @Transactional
    public long create(Gast gast) {
        return gastenboekRepository.create(gast);
    }

    public List<Gast> findAll() {
        return gastenboekRepository.findAll();
    }
}
