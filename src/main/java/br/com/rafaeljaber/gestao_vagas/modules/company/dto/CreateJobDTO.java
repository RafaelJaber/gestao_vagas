package br.com.rafaeljaber.gestao_vagas.modules.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateJobDTO {

    @NotBlank
    private String description;

    @NotBlank
    private String benefits;

    @NotBlank
    private String level;
}
