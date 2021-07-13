package br.com.elo.steps;


import br.com.elo.config.TestConfig;
import br.com.elo.fixture.FixtureCleaner;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
public class ConfigSteps {

    @Autowired
    private FixtureCleaner cleaner;

    /**
     * Faz a limpeza dos cenários antes da execução de cada cenário.
     */
    @Before
    public void cleanup() {
        this.cleaner.cleanup();
    }

}