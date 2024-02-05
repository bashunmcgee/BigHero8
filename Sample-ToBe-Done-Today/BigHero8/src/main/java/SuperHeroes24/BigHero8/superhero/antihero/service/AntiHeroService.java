package SuperHeroes24.BigHero8.superhero.antihero.service;


import java.util.UUID;


import SuperHeroes24.BigHero8.superhero.antihero.entity.AntiHeroEntity;
import SuperHeroes24.BigHero8.superhero.antihero.repository.AntiHeroRepository;
import SuperHeroes24.BigHero8.superhero.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AntiHeroService {

    // This Variable provides details with all CRUD Operations Truly is an Interface
    @Autowired
    private final AntiHeroRepository repo;

    /**
     * Method Finds All Heroes
     * @return
     */
    public Iterable<AntiHeroEntity> findAllAntiHeroes() {
        return repo.findAll();
    }

    /**
     * Method Provides a Search for id Paramater
     * @param id
     * @return
     */
    public AntiHeroEntity findAntiHeroById(UUID id) {
        return findOrThrow(id);
    }

    /**
     * Method Removes id by id
     * @param id
     */
    public void removeAntiHeroById(UUID id) {
        repo.deleteById(id);
    }

    /**
     * Method Adds Hero by hero Object
     * @param antiHero
     * @return
     */
    public AntiHeroEntity addAntiHero(AntiHeroEntity antiHero) {
        return repo.save(antiHero);
    }

    /**
     * Method Updates Hero By ID and Hero Object also id identifying
    // * @param id
     //* @param antiHero
     */
    public void updateAntiHero(UUID id, AntiHeroEntity antiHero) {
        findOrThrow(id);
        repo.save(antiHero);
    }

    /**
     * Error Feed Back if all else Fails
     * @param id
     * @return
     */
    private AntiHeroEntity findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Anti-hero by id " + id + " was not found")
                );
    }
}

