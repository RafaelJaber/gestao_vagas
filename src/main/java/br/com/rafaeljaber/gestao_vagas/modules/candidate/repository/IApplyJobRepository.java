package br.com.rafaeljaber.gestao_vagas.modules.candidate.repository;

import br.com.rafaeljaber.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
