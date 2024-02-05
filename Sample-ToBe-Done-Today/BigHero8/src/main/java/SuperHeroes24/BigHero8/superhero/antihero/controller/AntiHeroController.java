package SuperHeroes24.BigHero8.superhero.antihero.controller;

import SuperHeroes24.BigHero8.superhero.antihero.dto.AntiHeroDto;
import SuperHeroes24.BigHero8.superhero.antihero.entity.AntiHeroEntity;
import SuperHeroes24.BigHero8.superhero.antihero.service.AntiHeroService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.UUID;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@CrossOrigin(origins = "http://localhost:4000")
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/anti-heroes")
public class AntiHeroController {



    @Autowired
    private final AntiHeroService service;
    private final ModelMapper mapper;


    /**
     * Get Mapping Method returns all heroes list
     * @return List of AntiHero
     */
    @GetMapping
    public List<AntiHeroDto> getAntiHeroes(Pageable pageable) {
        int toSkip = pageable.getPageSize() *
                pageable.getPageNumber();
        var antiHeroList = StreamSupport
                .stream(service.findAllAntiHeroes().spliterator(),
                        false)
                .skip(toSkip).limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return antiHeroList
                .stream()

                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    /**
     * Get Mapping Method
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public AntiHeroDto getAntiHeroById(@PathVariable("id") UUID id) {
        return convertToDto(service.findAntiHeroById(id));
    }

    /**
     * Delete Mappoing Method
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteAntiHeroById(@PathVariable("id") UUID id) {
        service.removeAntiHeroById(id);
    }

    /**
     * Post Mapping Hero Method
     * @param antiHeroDto
     * @return
     */
    @PostMapping
    public AntiHeroDto postAntiHero(@Valid @RequestBody AntiHeroDto antiHeroDto) {
        var entity = convertToEntity(antiHeroDto);
        var antiHero = service.addAntiHero(entity);

        return convertToDto(antiHero);
    }

    /**
     * Update Mapping for ID
     * @param id
     * @param antiHeroDto
     */
    @PutMapping("/{id}")
    public void putAntiHero(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AntiHeroDto antiHeroDto
    ) {
        if (!id.equals(antiHeroDto.getId())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        var antiHeroEntity = convertToEntity(antiHeroDto);
        service.updateAntiHero(id, antiHeroEntity);
    }

    private AntiHeroDto convertToDto(AntiHeroEntity entity) {
        return mapper.map(entity, AntiHeroDto.class);
    }

    private AntiHeroEntity convertToEntity(AntiHeroDto dto) {
        return mapper.map(dto, AntiHeroEntity.class);
    }

}

