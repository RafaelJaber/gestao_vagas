package br.com.rafaeljaber.gestao_vagas.modules.candidate.useCases;

import br.com.rafaeljaber.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.rafaeljaber.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.rafaeljaber.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.rafaeljaber.gestao_vagas.modules.candidate.repository.ICandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Value("${javagas.security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private ICandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {

        CandidateEntity candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username/password incorrect")
                );

        boolean isPasswordMatched =
                this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!isPasswordMatched) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expiryTime = Instant.now().plus(Duration.ofMinutes(30));
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("candidate"))
                .withExpiresAt(expiryTime)
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expiresIn(Date.from(expiryTime))
                .build();
    }
}
