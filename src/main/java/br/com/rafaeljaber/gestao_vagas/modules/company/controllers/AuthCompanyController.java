package br.com.rafaeljaber.gestao_vagas.modules.company.controllers;

import br.com.rafaeljaber.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.rafaeljaber.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company/")
    public String login(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        return this.authCompanyUseCase.execute(authCompanyDTO);
    }

}
