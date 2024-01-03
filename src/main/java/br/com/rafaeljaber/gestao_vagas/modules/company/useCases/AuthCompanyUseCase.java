package br.com.rafaeljaber.gestao_vagas.modules.company.useCases;

import br.com.rafaeljaber.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.rafaeljaber.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.rafaeljaber.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.rafaeljaber.gestao_vagas.modules.company.repositories.ICompanyRepository;
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
import java.util.List;

@Service
public class AuthCompanyUseCase {

    @Value("${javagas.security.token.secret}")
    private String secretKey;

    @Autowired
    private ICompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        CompanyEntity company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username/Password incorrect")
                );

        // Verificar a senha
        boolean isPasswordMatched = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!isPasswordMatched) {
            throw new AuthenticationException();
        }

        // Gerar token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(company.getId().toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);

        return AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }

}
