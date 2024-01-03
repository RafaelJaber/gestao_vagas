package br.com.rafaeljaber.gestao_vagas.modules.candidate.repository;

import br.com.rafaeljaber.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICandidateRepository extends JpaRepository<CandidateEntity, UUID> {
}