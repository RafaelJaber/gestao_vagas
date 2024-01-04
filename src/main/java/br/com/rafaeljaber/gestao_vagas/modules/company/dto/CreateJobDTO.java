package br.com.rafaeljaber.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {

    @NotBlank
    @Schema(example = "Vaga para pessoa desenvolvedora júnior")
    private String description;

    @NotBlank
    @Schema(example = "GYMPass, Plano de saúde")
    private String benefits;

    @NotBlank
    @Schema(example = "Júnior")
    private String level;
}
