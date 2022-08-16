package de.finngerlach.dndcompanionbackend.individual;

import de.finngerlach.dndcompanionbackend.Exception.NotFoundException;
import de.finngerlach.dndcompanionbackend.individual.DTO.IndividualCreate;
import de.finngerlach.dndcompanionbackend.individual.DTO.IndividualResponse;
import de.finngerlach.dndcompanionbackend.location.DTO.LocationResponse;
import de.finngerlach.dndcompanionbackend.location.Location;
import de.finngerlach.dndcompanionbackend.location.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/individuals")
@AllArgsConstructor
public class IndividualController {

    IndividualService individualService;
    LocationService locationService;

    @GetMapping("/{id}")
    public IndividualResponse get(@PathVariable UUID id) {
        Optional<Individual> individual = individualService.getById(id);
        return new IndividualResponse(individual.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public IndividualResponse create(@RequestBody IndividualCreate individualCreate) {
        Individual individual = new Individual();
        individual.setName(individualCreate.getName());
        individual.setDescription(individualCreate.getDescription());
        List<UUID> locations = individualCreate.getLocationIds();
        if (locations != null) {
            try {
                for (UUID id : individualCreate.getLocationIds()) {
                    individual.addLocation(locationService.getById(id).orElseThrow(NotFoundException::new));
                }
            } catch (NotFoundException e) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        return new IndividualResponse(individualService.create(individual));
    }

    @GetMapping()
    public List<IndividualResponse> getList(@RequestParam(required = false) UUID locationId) {

        Optional<Location> location = Optional.ofNullable(locationId).flatMap(id -> locationService.getById(id));
        return individualService.getList(location).stream().map(IndividualResponse::new).collect(Collectors.toList());
    }

//    @GetMapping("/list")
//    public List<IndividualResponse> getList(@RequestParam(required = false) UUID locationId) {
//        return individualService.getList(locationId).stream().map(IndividualResponse::new).collect(Collectors.toList());
//    }
//
//    @GetMapping("/location/{id}/list")
//    public List<IndividualResponse> getListByLocationId(@PathVariable UUID id) {
//        return individualService.getListByLocationId(id).stream().map(IndividualResponse::new).collect(Collectors.toList());
//    }
}
