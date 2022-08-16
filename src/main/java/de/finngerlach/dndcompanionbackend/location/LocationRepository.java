package de.finngerlach.dndcompanionbackend.location;

import de.finngerlach.dndcompanionbackend.individual.Individual;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends CrudRepository<Location, UUID> {
    List<Location> findLocationsByIndividualsContaining(Individual individual);
}
