package br.com.rafaeljaber.gestao_vagas.modules.candidate.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ApplyJobRequestDTO {

    @NotNull
    private UUID idJob;
}
