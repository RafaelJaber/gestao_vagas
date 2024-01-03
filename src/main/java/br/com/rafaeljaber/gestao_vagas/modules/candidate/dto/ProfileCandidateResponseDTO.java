package br.com.rafaeljaber.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example =  "Desenvolvedor Java")
    private String description;

    @Schema(example =  "john_doe")
    private String username;

    @Schema(example =  "john.doe@example.com")
    private String email;

    @Schema(example =  "John Doe")
    private String name;

    private UUID id;
}
