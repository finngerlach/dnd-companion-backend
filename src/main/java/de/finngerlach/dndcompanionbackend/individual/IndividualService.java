package de.finngerlach.dndcompanionbackend.individual;

import de.finngerlach.dndcompanionbackend.Exception.NotFoundException;
import de.finngerlach.dndcompanionbackend.location.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IndividualService {
    IndividualRepository individualRepository;

    @Transactional
    public Optional<Individual> getById(UUID id) {
        return individualRepository.findById(id);
    }

    @Transactional
    public Individual create(Individual individual) {
        return individualRepository.save(individual);
    }

    @Transactional
    public List<Individual> getList(Optional<Location> location) {
        return location.map(loc -> individualRepository.findIndividualsByLocationsContaining(loc)).orElse((List<Individual>) individualRepository.findAll());
    }

//    public List<Individual> getList(UUID locationId) {
//        if (locationId != null) {
//            return individualRepository.findAllByLocationId(locationId);
//        } else {
//            return (List<Individual>) individualRepository.findAll();
//        }
//    }
//
//    public List<Individual> getListByLocationId(UUID id) {
//        return individualRepository.findAllByLocationId(id);
//    }
}
