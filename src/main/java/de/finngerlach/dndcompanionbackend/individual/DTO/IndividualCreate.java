package de.finngerlach.dndcompanionbackend.individual.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCreate {
    String name;
    String description;
    List<UUID> locationIds;
}
