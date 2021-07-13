package br.com.elo;

import br.com.elo.domain.dto.MappingToModel;
import br.com.elo.domain.dto.request.BandeiraRequestDTO;
import br.com.elo.repository.BandeiraRepository;
import br.com.elo.repository.SequencesApiParamsRepository;
import br.com.elo.service.BandeiraService;
import br.com.elo.service.SequencesGeneratorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static springfox.documentation.builders.PathSelectors.any;


@SpringBootApplication
@EnableOpenApi
public class SimuladorEloApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SimuladorEloApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(MappingToModel.toStringDate);
        return modelMapper;
    }

    @Bean
    public Docket parametrizacaoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("novo-simulador-api-parametrizacao")
                .apiInfo(apiInfo())
                .select()
                .paths(any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Parametrização - Novo Simulador")
                .description("API para cadastro de parâmetros de configuração e administrativos do novo simulador Elo.")
//				.termsOfServiceUrl("")
                .contact(new Contact("Equipe dev - Novo Simulador", "https://gitlab.elocloud.ninja/cp.05689/novo-simulador-api-parametrizacao", "marcelo.lopes.cps@elo.com.br"))
//				.license("Apache License Version 2.0")
//				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("1.0")
                .build();
    }


}