package de.finngerlach.dndcompanionbackend.individual;

import de.finngerlach.dndcompanionbackend.location.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IndividualRepository extends CrudRepository<Individual, UUID> {
    List<Individual> findIndividualsByLocationsContaining(Location location);
}
