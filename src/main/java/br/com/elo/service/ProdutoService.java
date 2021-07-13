package br.com.elo.service;

import br.com.elo.common.exception.ObjectNotFoundException;
import br.com.elo.common.exception.ParametroInvalidoException;
import br.com.elo.domain.MensagensRetorno;
import br.com.elo.domain.SchemaVersion;
import br.com.elo.domain.dto.request.ProdutoRequestCriteriaDTO;
import br.com.elo.domain.dto.request.ProdutoRequestDTO;
import br.com.elo.domain.dto.response.ProdutoResponseDTO;
import br.com.elo.model.Produto;
import br.com.elo.repository.BandeiraRepository;
import br.com.elo.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private BandeiraRepository bandeiraRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private SequencesGeneratorService sequenceGenerator;

    public ProdutoService(ModelMapper modelMapper, ProdutoRepository repository, BandeiraRepository bandeiraRepository, SequencesGeneratorService sequenceGenerator) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.bandeiraRepository = bandeiraRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public ProdutoResponseDTO save(ProdutoRequestDTO dto) {
        if (!bandeiraRepository.existsByCodigoBandeira(dto.getCodigoBandeira())) {
            throw new ParametroInvalidoException(MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao());
        }

        if (repository.existsByCodigoBandeiraAndCodigoProduto(dto.getCodigoBandeira(), dto.getCodigoProduto())) {
            throw new ParametroInvalidoException(MensagensRetorno.PRODUTO_COD_BAD_EXISTE.getDescricao());
        }

        Produto produto = modelMapper.map(dto, Produto.class);
        produto.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        produto.setDataCriacao(LocalDateTime.now());
        produto = repository.save(produto);
        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    public ProdutoResponseDTO findById(String id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(MensagensRetorno.PRODUTO_NAO_LOCALIZADO.getDescricao()));
        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    public ProdutoResponseDTO findByCodigoBandeiraAndCodigoProduto(Integer codigoBandeira, Integer codigoProduto) {
        Produto produto = repository.findByCodigoBandeiraAndCodigoProduto(codigoBandeira, codigoProduto);

        if (produto == null)
            throw new ObjectNotFoundException(MensagensRetorno.PRODUTO_NAO_LOCALIZADO.getDescricao());

        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    public ProdutoResponseDTO update(Integer codigoBandeira, Integer codigoProduto, ProdutoRequestDTO dto) {
        Produto produto = modelMapper.map(dto, Produto.class);

        if (!bandeiraRepository.existsByCodigoBandeira(dto.getCodigoBandeira())) {
            throw new ParametroInvalidoException(MensagensRetorno.BANDEIRA_NAO_LOCALIZADA.getDescricao());
        }

        //se o codigoBandeira e codigoPorduto forem alterados, entao verifica se tem um objeto cadastrado.
        if ((!dto.getCodigoBandeira().equals(codigoBandeira)) || (!dto.getCodigoProduto().equals(codigoProduto))) {
            if (repository.existsByCodigoBandeiraAndCodigoProduto(dto.getCodigoBandeira(), dto.getCodigoProduto())) {
                throw new ParametroInvalidoException(MensagensRetorno.PRODUTO_COD_BAD_EXISTE.getDescricao());
            }
        }

        Produto produtoAux = repository.findByCodigoBandeiraAndCodigoProduto(codigoBandeira, codigoProduto);

        if (produtoAux == null) {
            throw new ObjectNotFoundException(MensagensRetorno.PRODUTO_NAO_LOCALIZADO.getDescricao());
        }


        produto.setId(produtoAux.getId());
        produto.setDataCriacao(produtoAux.getDataCriacao());
        produto.setSchemaVersion(SchemaVersion.SCHEMA_VERSION_1.getVersion());
        produto.setDataAlteracao(LocalDateTime.now());
        produto = repository.save(produto);
        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    public List<ProdutoResponseDTO> findAll(ProdutoRequestCriteriaDTO dto) {

        Produto produto = modelMapper.map(dto, Produto.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(produto, matcher);

        List<ProdutoResponseDTO> produtoList = modelMapper.map(repository.findAll(example), new TypeToken<List<ProdutoResponseDTO>>() {
        }.getType());

        return produtoList;
    }
}
