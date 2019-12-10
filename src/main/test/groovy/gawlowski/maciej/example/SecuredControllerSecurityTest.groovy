package gawlowski.maciej.example

import gawlowski.maciej.example.config.SecurityIntegrationTestConfig
import gawlowski.maciej.example.config.ServiceProfile
import gawlowski.maciej.example.controller.InsecureController
import gawlowski.maciej.example.controller.SecuredController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
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
@WebMvcTest(SecuredController.class)
@Import(SecurityIntegrationTestConfig.class)
class SecuredControllerSecurityTest extends Specification {

    @Autowired
    MockMvc mvc

    @WithAnonymousUser
    def "GET #path should be forbidden for anonymous user"() {
        expect:
        mvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(status().isForbidden())

        where:
        path << ["/secured"]
    }

    @WithMockUser(username = "user", password = "password", roles = "service_admin")
    def "GET #path should NOT be forbidden for user with role service_admin"() {
        expect:
        mvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(status().isOk())

        where:
        path << ["/secured"]
    }
}
