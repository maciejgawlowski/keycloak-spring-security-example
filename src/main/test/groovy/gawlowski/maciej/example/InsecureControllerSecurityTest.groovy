package gawlowski.maciej.example

import gawlowski.maciej.example.config.SecurityIntegrationTestConfig
import gawlowski.maciej.example.config.ServiceProfile
import gawlowski.maciej.example.controller.InsecureController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Unroll
@ActiveProfiles(ServiceProfile.INTEGRATION_TEST)
@WebMvcTest(InsecureController.class)
@Import(SecurityIntegrationTestConfig.class)
class InsecureControllerSecurityTest extends Specification {

    @Autowired
    MockMvc mvc

    @WithAnonymousUser
    def "GET #path should not be secured by password"() {
        expect:
        mvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(status().isOk())

        where:
        path << ["/insecured"]
    }
}
