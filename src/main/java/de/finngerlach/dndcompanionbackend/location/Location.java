package de.finngerlach.dndcompanionbackend.location;

import de.finngerlach.dndcompanionbackend.individual.Individual;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue
    private UUID id;
    @NonNull
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @ManyToMany
    @JoinTable(name = "parent_child_location", joinColumns = @JoinColumn(name = "child_id"), inverseJoinColumns = @JoinColumn(name = "parent_id"))
    @Setter(AccessLevel.NONE)
    private Set<Location> parentLocations = new HashSet<>();

    @ManyToMany(mappedBy = "parentLocations")
    @Setter(AccessLevel.NONE)
    private Set<Location> childLocations = new HashSet<>();

    @ManyToMany(mappedBy = "locations")
    @Setter(AccessLevel.NONE)
    private Set<Individual> individuals = new HashSet<>();

    public void addParentLocation(Location parentLocation) {
        parentLocations.add(parentLocation);
        parentLocation.getChildLocations().add(this);
    }

    public void removeParentLocation(Location parentLocation) {
        parentLocations.remove(parentLocation);
        parentLocation.getChildLocations().remove(this);
    }

    public void addChildLocation(Location childLocation) {
        childLocations.add(childLocation);
        childLocation.getParentLocations().add(this);
    }

    public void removeChildLocation(Location childLocation) {
        childLocations.remove(childLocation);
        childLocation.getParentLocations().remove(this);
    }

    public void addIndividual(Individual individual) {
        individuals.add(individual);
        individual.getLocations().add(this);
    }

    public void removeIndividual(Individual individual) {
        individuals.remove(individual);
        individual.getLocations().remove(this);
    }
}
