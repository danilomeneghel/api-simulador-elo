package br.com.elo;
import br.com.elo.config.TestConfig;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * Classe de configuracao e execucao dos cenario de teste
 * 
 * @author Eduardo Oliveira
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"./src/test/resources/features"}, 
		tags = "@regression",
		glue = "br.com.elo.steps",
		plugin = {"pretty", "json:target/cucumber.json"},
		dryRun = false)
@ContextConfiguration(classes = TestConfig.class)
public class RunnerComponentTests {
}