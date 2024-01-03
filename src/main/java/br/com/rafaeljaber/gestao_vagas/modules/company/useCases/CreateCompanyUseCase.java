package br.com.rafaeljaber.gestao_vagas.modules.company.useCases;

import br.com.rafaeljaber.gestao_vagas.exceptions.UserFoundException;
import br.com.rafaeljaber.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.rafaeljaber.gestao_vagas.modules.company.repositories.ICompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private ICompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.companyRepository.save(companyEntity);
    }

}
