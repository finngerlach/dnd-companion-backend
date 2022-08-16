package de.finngerlach.dndcompanionbackend.location;

import de.finngerlach.dndcompanionbackend.individual.Individual;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LocationService {
    private LocationRepository locationRepository;

    @Transactional
    public Optional<Location> getById(UUID id) {
        return locationRepository.findById(id);
    }

    @Transactional
    List<Location> getList(Optional<Individual> individual) {
        return individual.map(ind -> locationRepository.findLocationsByIndividualsContaining(ind)).orElse((List<Location>) locationRepository.findAll());
    }

    @Transactional
    Location create(Location location) {
        return locationRepository.save(location);
    }
}
