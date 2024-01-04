package br.com.rafaeljaber.gestao_vagas.modules.candidate.useCases;

import br.com.rafaeljaber.gestao_vagas.exceptions.ProfileNotFoundException;
import br.com.rafaeljaber.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.rafaeljaber.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.rafaeljaber.gestao_vagas.modules.candidate.repository.ICandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private ICandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        CandidateEntity candidate = this.candidateRepository.findById(idCandidate).orElseThrow(
                () -> new ProfileNotFoundException()
        );

        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
    }
}
