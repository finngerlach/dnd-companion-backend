package de.finngerlach.dndcompanionbackend.individual;

import de.finngerlach.dndcompanionbackend.location.Location;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Individual {
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @ManyToMany
    @JoinTable(name = "location_individual", joinColumns = @JoinColumn(name = "individual_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
    @Setter(AccessLevel.NONE)
    private Set<Location> locations = new HashSet<>();

    public void addLocation(Location location) {
        locations.add(location);
        location.getIndividuals().add(this);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
        location.getIndividuals().remove(this);
    }
}
