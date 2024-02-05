package SuperHeroes24.BigHero8.superhero.antihero.entity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@Entity
@Table(name = "AntiHero")
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("AntiHero")
public class AntiHeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "first_Name")
    @NotNull(message = "First Name is required")
    private String firstName;
    @Column(name= "last_Name")
    private String lastName;
    @Column(name = "house")
    private String house;
    @Column(name = "known_As")
    private String knownAs;
    @Column(name = "Date")
    private String createdAt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
            .format(new Date());
}
