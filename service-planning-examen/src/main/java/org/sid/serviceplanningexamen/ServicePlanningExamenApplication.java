package org.sid.serviceplanningexamen;

import org.sid.serviceplanningexamen.entities.PlanningExamen;
import org.sid.serviceplanningexamen.fiegn.ExamenRestClient;
import org.sid.serviceplanningexamen.repositories.PlanningExamenRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableFeignClients
public class ServicePlanningExamenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePlanningExamenApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PlanningExamenRepository planningExamenRepository,
                            ExamenRestClient examenRestClient,
                            RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(PlanningExamen.class);
        return args -> {

        };
    }
}
