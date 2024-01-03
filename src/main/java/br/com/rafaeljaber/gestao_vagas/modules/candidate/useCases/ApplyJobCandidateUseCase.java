package br.com.rafaeljaber.gestao_vagas.modules.candidate.useCases;

import br.com.rafaeljaber.gestao_vagas.exceptions.CandidateNotFoundException;
import br.com.rafaeljaber.gestao_vagas.exceptions.JobNotFoundException;
import br.com.rafaeljaber.gestao_vagas.modules.candidate.repository.ICandidateRepository;
import br.com.rafaeljaber.gestao_vagas.modules.company.repositories.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private ICandidateRepository candidateRepository;

    @Autowired
    private IJobRepository jobRepository;


    public void execute(UUID idCandidate, UUID idJob) {
        // Validar se candidato existe
        this.candidateRepository.findById(idCandidate).orElseThrow(
                () -> new CandidateNotFoundException()
        );
        // Validar se a vaga existe
        this.jobRepository.findById(idJob).orElseThrow(
                () -> new JobNotFoundException()
        );
        // Candidato se inscrever na vaga
    }
}
