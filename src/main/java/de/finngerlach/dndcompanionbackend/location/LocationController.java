package de.finngerlach.dndcompanionbackend.location;

import de.finngerlach.dndcompanionbackend.Exception.NotFoundException;
import de.finngerlach.dndcompanionbackend.individual.Individual;
import de.finngerlach.dndcompanionbackend.individual.IndividualService;
import de.finngerlach.dndcompanionbackend.location.DTO.LocationCreate;
import de.finngerlach.dndcompanionbackend.location.DTO.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class LocationController {
    LocationService locationService;
    IndividualService individualService;

    @GetMapping("/{id}")
    public LocationResponse getById(@PathVariable UUID id) {
        return new LocationResponse(locationService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public LocationResponse create(@RequestBody LocationCreate locationCreate) {
        Location location = new Location();
        location.setName(locationCreate.getName());
        location.setDescription(locationCreate.getDescription());
        try {
            for (UUID id : locationCreate.getParentLocationIds()) {
                location.addParentLocation(locationService.getById(id).orElseThrow(NotFoundException::new));
            }

            for (UUID id : locationCreate.getChildLocationIds()) {
                location.addChildLocation(locationService.getById(id).orElseThrow(NotFoundException::new));
            }
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new LocationResponse(locationService.create(location));
    }

    @GetMapping()
    public List<LocationResponse> getList(@RequestParam(required = false) UUID individualId) {
        Optional<Individual> individual = Optional.ofNullable(individualId).flatMap(id -> individualService.getById(id));
        return locationService.getList(individual).stream().map(LocationResponse::new).collect(Collectors.toList());
    }
}
