package org.sid.serviceplanningexamen.repositories;

import org.sid.serviceplanningexamen.entities.PlanningExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlanningExamenRepository extends JpaRepository<PlanningExamen, Long> {
}
