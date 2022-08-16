package de.finngerlach.dndcompanionbackend;

import de.finngerlach.dndcompanionbackend.individual.Individual;
import de.finngerlach.dndcompanionbackend.individual.IndividualRepository;
import de.finngerlach.dndcompanionbackend.location.Location;
import de.finngerlach.dndcompanionbackend.location.LocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DndCompanionBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DndCompanionBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner createDemoData(IndividualRepository individualRepository, LocationRepository locationRepository) {
		String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

		return args -> {
			if (locationRepository.count() == 0) {
				System.out.println("Generating locations...");
				Location location1 = new Location();
				location1.setName("Deutschland");
				location1 = locationRepository.save(location1);

				Location location2 = new Location();
				location2.setName("Schleswig-Holstein");
				location2.addParentLocation(location1);
				location2 = locationRepository.save(location2);

				Location location3 = new Location();
				location3.setName("Kiel");
				location3.addParentLocation(location2);
				location3 = locationRepository.save(location3);

				Location location4 = new Location();
				location4.setName("Flensburg");
				location4.addParentLocation(location3);
				location4 = locationRepository.save(location4);

				if (individualRepository.count() == 0) {
					System.out.println("Generating individuals...");
					Individual individual1 = new Individual();
					individual1.setName("Detlef DeSoost");
					individual1.setDescription(lorem);
					individual1.addLocation(location3);
					individualRepository.save(individual1);

					Individual individual2 = new Individual();
					individual2.setName("Dieter Jauch");
					individual2.setDescription(lorem);
					individual2.addLocation(location3);
					individualRepository.save(individual2);

					Individual individual3 = new Individual();
					individual3.setName("Günther Blatt");
					individual3.setDescription(lorem);
					individual3.addLocation(location4);
					individualRepository.save(individual3);
				}
				System.out.println("Done!");
			}
		};
	}

}
