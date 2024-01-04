package br.com.rafaeljaber.gestao_vagas.modules.company.useCases;

import br.com.rafaeljaber.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.rafaeljaber.gestao_vagas.modules.company.entities.JobEntity;
import br.com.rafaeljaber.gestao_vagas.modules.company.repositories.ICompanyRepository;
import br.com.rafaeljaber.gestao_vagas.modules.company.repositories.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private IJobRepository jobRepository;

    @Autowired
    private ICompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(
                () -> new CompanyNotFoundException()
        );
        return this.jobRepository.save(jobEntity);
    }
}
