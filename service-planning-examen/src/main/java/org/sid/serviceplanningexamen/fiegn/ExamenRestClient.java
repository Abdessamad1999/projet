package org.sid.serviceplanningexamen.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-EXAMEN")

public interface ExamenRestClient {

    @DeleteMapping(path = "/supprimerExamens/{idPlanning}")
    boolean deleteExamensByPlanningId(@PathVariable Long idPlanning);

}
