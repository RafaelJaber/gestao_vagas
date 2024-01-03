package br.com.rafaeljaber.gestao_vagas.modules.company.controllers;

import br.com.rafaeljaber.gestao_vagas.modules.company.entities.JobEntity;
import br.com.rafaeljaber.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/company/{companyUUID}/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity jobEntity, @PathVariable UUID companyUUID) {
        try {
            jobEntity.setCompanyId(companyUUID);
            var result = this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
