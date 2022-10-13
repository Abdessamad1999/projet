package org.sid.serviceplanningexamen.web;

import org.sid.serviceplanningexamen.entities.PlanningExamen;
import org.sid.serviceplanningexamen.fiegn.ExamenRestClient;
import org.sid.serviceplanningexamen.repositories.PlanningExamenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@RestController
public class PlannigExamenRestController {

    private PlanningExamenRepository planningExamenRepository;
    private ExamenRestClient examenRestClient;

    public PlannigExamenRestController(PlanningExamenRepository planningExamenRepository, ExamenRestClient examenRestClient){
        this.planningExamenRepository = planningExamenRepository;
        this.examenRestClient = examenRestClient;
    }

    @GetMapping("/planningExamens")
    public Collection<PlanningExamen> getPlanning(){
        Collection<PlanningExamen> p = planningExamenRepository.findAll();
        p.forEach(e->{
            e.setPlanningExamen(null);
            e.setAvisExamen(null);
        });
        return p;
    }

    @GetMapping("/downloadAvis/{id}")
    public void downloadAvisExamen(@PathVariable Long id, HttpServletResponse response) throws IOException {
        PlanningExamen p = planningExamenRepository.findById(id).get();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Avis Examen "+p.getNomFiliere()+" "+p.getAU()+".pdf";

        response.setHeader(headerKey,headerValue);

        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(p.getAvisExamen());
        servletOutputStream.close();
    }

    @GetMapping("/downloadPlanning/{id}")
    public void downloadPlanningExamen(@PathVariable Long id, HttpServletResponse response) throws IOException {
        PlanningExamen p = planningExamenRepository.findById(id).get();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Planning Examen "+p.getNomFiliere()+" "+p.getAU()+".pdf";

        response.setHeader(headerKey,headerValue);

        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(p.getAvisExamen());
        servletOutputStream.close();
    }

    @PostMapping("/ajouterPlanning")
    public PlanningExamen insertPlannig(@RequestBody PlanningExamen planning){
        planning.setAU("2022-2023");
        return planningExamenRepository.save(planning);
    }

    @PostMapping ("/uploadFiles/{id}")
    public void insertAvis(@PathVariable("id") Long id,@RequestParam("planningExam") MultipartFile planningExam,@RequestParam("avisExam") MultipartFile avisExam) throws IOException {
        PlanningExamen p = planningExamenRepository.findById(id).get();
        p.setPlanningExamen(planningExam.getBytes());
        p.setAvisExamen(avisExam.getBytes());
        planningExamenRepository.save(p);
    }

    @PostMapping("/modifierPlanning")
    public void editPlanning( @RequestParam Long id,@RequestBody PlanningExamen planningExamen){
        planningExamenRepository.save(planningExamen);
    }

    @DeleteMapping("/supprimerPlanning/{id}")
    public void deletPlanningExamen(@PathVariable Long id){
        examenRestClient.deleteExamensByPlanningId(id);
        planningExamenRepository.deleteById(id);
    }
}
