package br.com.rafaeljaber.gestao_vagas.modules.company.repositories;

import br.com.rafaeljaber.gestao_vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IJobRepository extends JpaRepository<JobEntity, UUID> {
}
