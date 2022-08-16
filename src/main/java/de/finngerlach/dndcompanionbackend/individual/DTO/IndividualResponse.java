package de.finngerlach.dndcompanionbackend.individual.DTO;

import de.finngerlach.dndcompanionbackend.individual.Individual;
import de.finngerlach.dndcompanionbackend.location.Location;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class IndividualResponse {
    UUID id;
    String name;
    String description;
    List<UUID> locationIds;

    public IndividualResponse(Individual individual) {
        this.id = individual.getId();
        this.name = individual.getName();
        this.description = individual.getDescription();
        this.locationIds = individual.getLocations() != null ? individual.getLocations().stream().map(Location::getId).collect(Collectors.toList()) : null;
    }
}
