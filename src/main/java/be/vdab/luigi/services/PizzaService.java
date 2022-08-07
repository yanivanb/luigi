package be.vdab.luigi.services;

import be.vdab.luigi.domain.Pizza;
import be.vdab.luigi.dto.AantalPizzasPerPrijs;
import be.vdab.luigi.repositories.PizzaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
    @Transactional
    public long create(Pizza pizza) {
        return pizzaRepository.create(pizza);
    }
    @Transactional
    public void update(Pizza pizza) {
        pizzaRepository.update(pizza);
    }
    @Transactional
    public void delete(long id) {
        pizzaRepository.delete(id);
    }
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }
    public Optional<Pizza> findById(long id) {
        return pizzaRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public List<AantalPizzasPerPrijs> findAantalPizzasPerPrijs() {
        return pizzaRepository.findAantalPizzasPerPrijs();
    }
    public List<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot){
        return pizzaRepository.findByPrijsBetween(van, tot);
    }
    public long findAantal() {
        return pizzaRepository.findAantal();
    }
    public List<BigDecimal> findUniekePrijzen() {
        return pizzaRepository.findUniekePrijzen();
    }
    public List<Pizza> findByPrijs(BigDecimal prijs) {
        return pizzaRepository.findByPrijs(prijs);
    }
    public List<Pizza> findByIds(Set<Long> ids) {
        return pizzaRepository.findByIds(ids);
    }
}
