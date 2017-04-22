package info.kivid.controller;

import info.kivid.model.RockApiResult;
import info.kivid.service.RockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RockControllerTest {

    @MockBean
    private RockService rockService;

    @Autowired
    private TestRestTemplate restTemplate;

    private RockApiResult rockApiResult;

    @Before
    public void setUp() throws Exception {
        rockApiResult = new RockApiResult();
        rockApiResult.setName("rockName");
        rockApiResult.setName_en("rockNameInEnglish");
        rockApiResult.setDescription("rockDescription");
        rockApiResult.setDescription_en("rockDescriptionInEnglish");
        rockApiResult.setDescription_usage("rockDescriptionUsage");
        rockApiResult.setDescription_usage_en("rockDescriptionUsageInEnglish");
        given(this.rockService.getRock("1")).willReturn(rockApiResult);
    }

    @Test
    public void shouldReturnInformationAboutRock() {
        String body = this.restTemplate.getForObject("/rock/1", String.class);
        assertThat(body).contains("rockName");
        assertThat(body).contains("rockDescription");
    }

    @Test
    public void shouldReturnInformationAboutRockInEnglish() {
        String body = this.restTemplate.getForObject("/rock/1?lang=en", String.class);
        assertThat(body).contains("rockNameInEnglish");
        assertThat(body).contains("rockDescriptionInEnglish");
    }
}