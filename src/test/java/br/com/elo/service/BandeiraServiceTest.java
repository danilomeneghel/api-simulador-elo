package br.com.elo.service;



import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.controller.util.DadosMockUtil;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.dto.request.BandeiraRequestCriteriaDTO;
import br.com.elo.domain.dto.request.BandeiraRequestDTO;
import br.com.elo.domain.dto.response.BandeiraResponseDTO;
import br.com.elo.model.Bandeira;
import br.com.elo.repository.BandeiraRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BandeiraServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    BandeiraService service;

    @Mock
    private SequencesGeneratorService sequenceGenerator;

    @Mock
    private BandeiraRepository repository;

    private BandeiraRequestDTO bandeiraRequestDTO = DadosMockUtil.criaNovaBandeiraRequestDTO();
    private BandeiraRequestCriteriaDTO bandeiraRequestCriteriaDTO = DadosMockUtil.criaNovaBandeiraRequestCriteriaDTO();
    private Bandeira bandeira = DadosMockUtil.criaNovaBandeira();
    private Bandeira bandeiraDb = DadosMockUtil.bandeiraDB();
    private BandeiraResponseDTO bandeiraResponse = DadosMockUtil.bandeiraResponseDTO();

    @Test
    public void saveTest(){
        Long codigoSequence = 1L;
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;
        Bandeira bandeiraMock = bandeira;
        Integer codigoBandeira = bandeiraRequestMock.getCodigoBandeira();


        Mockito.when(repository.existsByCodigoBandeira(codigoBandeira)).thenReturn(false);
        Mockito.when( modelMapper.map(bandeiraRequestMock, Bandeira.class)).thenReturn(bandeiraMock);

        Bandeira bandeiraDbMock  = bandeiraDb;
        Mockito.when(repository.save(bandeiraMock)).thenReturn(bandeiraDbMock);

        BandeiraResponseDTO bandeiraResponseMock = bandeiraResponse;
        Mockito.when(modelMapper.map(bandeiraDbMock, BandeiraResponseDTO.class)).thenReturn(bandeiraResponseMock);

        //Execução
        BandeiraResponseDTO bandeiraResponse = service.save(bandeiraRequestMock);

        //verificação
        assertThat(bandeiraResponse.getId()).isNotNull();
        assertThat(bandeiraResponse.getCodigoBandeira()).isEqualTo(7);
        assertThat(bandeiraResponse.getDescricao()).isEqualTo("Bandeira ELO");
    }

    @Test
    public void saveDuplicadoTest(){
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;
        Integer codigoBandeira = bandeiraRequestMock.getCodigoBandeira();


        Mockito.when(repository.existsByCodigoBandeira(codigoBandeira)).thenReturn(true);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.save(bandeiraRequestMock);
        });

        String expectedMessage = "Não foi possível processar a solicitação, codigo bandeira existente";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));
    }


    @Test
    public void updateTest(){
        Integer codigoBandeira = 7;
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;
        Bandeira bandeiraMock = bandeira;

        Mockito.when( modelMapper.map(bandeiraRequestMock, Bandeira.class)).thenReturn(bandeiraMock);
        Mockito.when( repository.findByCodigoBandeira(codigoBandeira)).thenReturn(bandeiraMock);
        Bandeira bandeiraDbMock  = bandeiraDb;
        Mockito.when(repository.save(bandeiraMock)).thenReturn(bandeiraDbMock);

        BandeiraResponseDTO bandeiraResponseMock = bandeiraResponse;
        Mockito.when(modelMapper.map(bandeiraDbMock, BandeiraResponseDTO.class)).thenReturn(bandeiraResponseMock);

        //Execução
        BandeiraResponseDTO bandeiraResponse = service.update(codigoBandeira,bandeiraRequestMock);

        //verificação
        assertThat(bandeiraResponse.getId()).isNotNull();
        assertThat(bandeiraResponse.getCodigoBandeira()).isEqualTo(7);
        assertThat(bandeiraResponse.getDescricao()).isEqualTo("Bandeira ELO");
    }

    @Test
    public void findByIdTest(){
        Long codigoSequence = 1L;
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;
        Bandeira bandeiraDbMock  = bandeiraDb;

        Mockito.when( repository.findById("5feb955b37b9fb7770ec3155") ).thenReturn(Optional.ofNullable(bandeiraDbMock));
        BandeiraResponseDTO bandeiraResponseSalvoMock = bandeiraResponse;
        Mockito.when(modelMapper.map(bandeiraDb, BandeiraResponseDTO.class)).thenReturn(bandeiraResponseSalvoMock);

        //Execução
        BandeiraResponseDTO bandeiraResponse = service.findById("5feb955b37b9fb7770ec3155");

        //verificação
        assertThat(bandeiraResponse.getId()).isEqualTo("5feb955b37b9fb7770ec3155");
    }

    @Test
    public void notFoundByIdTest(){
        Long codigoSequence = 1L;
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;
        Bandeira bandeiraDbMock  = bandeiraDb;

        Mockito.when( repository.findById("5feb955b37b9fb7770ec3155") ).thenReturn(Optional.empty());

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findById("5feb955b37b9fb7770ec3155");
        });

        String expectedMessage = "Não foi possível localizar a bandeira informada";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage));

        //Execução


        //verificação
        assertThat(bandeiraResponse.getId()).isEqualTo("5feb955b37b9fb7770ec3155");
    }

    @Test
    public void updateCodigoInvalidoTest(){
        Integer codigoBandeira = 0;
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;
        Bandeira bandeiraMock = bandeira;

        Mockito.when(modelMapper.map(bandeiraRequestMock, Bandeira.class)).thenReturn(bandeiraMock);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoBandeira, bandeiraRequestMock);
        });

        String expectedMessage = MensagensRetorno.BANDEIRA_BAD_REQUEST.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void updateBandeiraNotFound(){
        Integer codigoBandeira = 1;
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;
        Bandeira bandeiraMock = bandeira;

        Mockito.when(modelMapper.map(bandeiraRequestMock, Bandeira.class)).thenReturn(bandeiraMock);
        Mockito.when(repository.findByCodigoBandeira(codigoBandeira)).thenReturn(null);


        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.update(codigoBandeira, bandeiraRequestMock);
        });

        String expectedMessage = MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void updateBandeiraJaExistente(){
        Integer codigoBandeira = 1;
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;
        Bandeira bandeiraMock = bandeira;

        Mockito.when(modelMapper.map(bandeiraRequestMock, Bandeira.class)).thenReturn(bandeiraMock);
        Mockito.when(repository.existsByCodigoBandeira(bandeiraRequestMock.getCodigoBandeira())).thenReturn(true);
        Bandeira bandeiraDbMock =bandeiraDb;
        bandeiraDbMock.setCodigoBandeira(10);
        Mockito.when( repository.findByCodigoBandeira(codigoBandeira) ).thenReturn(bandeiraDbMock);

        Exception exception = assertThrows(ParametroInvalidoException.class, () -> {
            service.update(codigoBandeira, bandeiraRequestMock);
        });

        String expectedMessage = MensagensRetorno.BANDEIRA_COD_BAD_EXISTE.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void findBandeiraNotFound(){
        Integer codigoBandeira = 999;
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;


        Mockito.when(repository.findByCodigoBandeira(codigoBandeira)).thenReturn(null);


        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            service.findByCodigoBandeira(codigoBandeira);
        });

        String expectedMessage = MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao();
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);

    }

    @Test
    public void findByCodigoTest(){
        Integer codigoBandeira = 7;
        BandeiraRequestDTO bandeiraRequestMock = bandeiraRequestDTO;
        Bandeira bandeiraDbMock  = bandeiraDb;

        Mockito.when( repository.findByCodigoBandeira(codigoBandeira) ).thenReturn(bandeiraDbMock);
        BandeiraResponseDTO bandeiraResponseSalvoMock = bandeiraResponse;
        Mockito.when(modelMapper.map(bandeiraDb, BandeiraResponseDTO.class)).thenReturn(bandeiraResponseSalvoMock);

        //Execução
        BandeiraResponseDTO bandeiraResponse = service.findByCodigoBandeira(codigoBandeira);

        //verificação
        assertThat(bandeiraResponse.getCodigoBandeira()).isEqualTo(codigoBandeira);
    }

    @Test
    public void findAllTest(){
        Long codigoSequence = 1L;
        BandeiraRequestCriteriaDTO bandeiraRequestCriteriaMock = bandeiraRequestCriteriaDTO;

        Bandeira bandeiraMock  = bandeira;

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(bandeira, matcher);

        List<Bandeira> listaBuscaBandeira = new ArrayList<>();
        listaBuscaBandeira.add(bandeiraMock);

       List<BandeiraResponseDTO> listaBuscaBandeiraResponse = new ArrayList<>();
       listaBuscaBandeiraResponse.add(bandeiraResponse);

        Mockito.when( modelMapper.map(bandeiraRequestCriteriaMock, Bandeira.class)).thenReturn(bandeiraMock);
        Mockito.when(repository.findAll(example)).thenReturn(listaBuscaBandeira);
        Mockito.when(modelMapper.map(listaBuscaBandeira, new TypeToken<List<BandeiraResponseDTO>>() {}.getType())).thenReturn(listaBuscaBandeiraResponse);

        //Execução
        List<BandeiraResponseDTO> bandeiraResponseList = service.findAll(bandeiraRequestCriteriaMock);

        //verificação
        assertThat(bandeiraResponseList.size()).isEqualTo(1);
    }

}
