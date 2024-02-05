package SuperHeroes24.BigHero8.superhero.antihero.repository;

import SuperHeroes24.BigHero8.superhero.antihero.entity.AntiHeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;
public interface AntiHeroRepository extends JpaRepository<AntiHeroEntity, UUID> {
}

