package br.com.rafaeljaber.gestao_vagas.modules.candidate.useCases;

import br.com.rafaeljaber.gestao_vagas.exceptions.CandidateNotFoundException;
import br.com.rafaeljaber.gestao_vagas.exceptions.JobNotFoundException;
import br.com.rafaeljaber.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.rafaeljaber.gestao_vagas.modules.candidate.repository.ICandidateRepository;
import br.com.rafaeljaber.gestao_vagas.modules.company.repositories.IJobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private ICandidateRepository candidateRepository;
    @Mock
    private IJobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(CandidateNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(JobNotFoundException.class);
        }
    }
}
