package de.finngerlach.dndcompanionbackend.location.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationCreate {
    String name;
    String description;
    List<UUID> parentLocationIds = new ArrayList<>();
    List<UUID> childLocationIds = new ArrayList<>();
}
