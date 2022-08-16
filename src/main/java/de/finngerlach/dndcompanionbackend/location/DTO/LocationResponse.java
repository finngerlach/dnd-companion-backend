package de.finngerlach.dndcompanionbackend.location.DTO;

import de.finngerlach.dndcompanionbackend.individual.Individual;
import de.finngerlach.dndcompanionbackend.location.Location;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class LocationResponse {
    UUID id;
    String name;
    String description;
    List<UUID> parentLocationIds;
    List<UUID> childLocationIds;
    List<UUID> individualIds;

    public LocationResponse(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.description = location.getDescription();
        this.parentLocationIds = location.getParentLocations() != null ? location.getParentLocations().stream().map(Location::getId).collect(Collectors.toList()) : null;
        this.childLocationIds = location.getChildLocations() != null ? location.getChildLocations().stream().map(Location::getId).collect(Collectors.toList()) : null;
        this.individualIds = location.getIndividuals() != null ? location.getIndividuals().stream().map(Individual::getId).collect(Collectors.toList()) : null;
    }
}
