package br.com.rafaeljaber.gestao_vagas.modules.company.controllers;

import br.com.rafaeljaber.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.rafaeljaber.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.rafaeljaber.gestao_vagas.modules.company.repositories.ICompanyRepository;
import br.com.rafaeljaber.gestao_vagas.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ICompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {

        CompanyEntity company = CompanyEntity.builder()
                .description("COMPANY_TEST")
                .email("COMPANY@COMPANY.COM")
                .password("COMPANY12345")
                .name("COMPANY_NAME")
                .username("COMPANY_USERNAME")
                .build();
        companyRepository.saveAndFlush(company);

        CreateJobDTO createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createJobDTO))
                .header("Authorization", TestUtils.generateToken(UUID.fromString(company.getId().toString()), "JAVAGAS_@123#"))
        )
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }



}
