package test.impl;

import com.example.pursuitservice.bo.EnderecoBO;
import com.example.pursuitservice.domain.sql.Endereco;
import com.example.pursuitservice.dto.EnderecoDTO;
import com.example.pursuitservice.enums.MensagensErro;
import com.example.pursuitservice.helpers.GeocodingHelper;
import com.example.pursuitservice.impl.EnderecoBOImpl;
import com.example.pursuitservice.repositories.sql.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {EnderecoBOImpl.class, EnderecoRepository.class, GeocodingHelper.class})
@ExtendWith(SpringExtension.class)
public class EnderecoBOTest {

    @Autowired
    EnderecoBO enderecoBO;
    @MockBean
    EnderecoRepository enderecoRepository;
    @MockBean
    GeocodingHelper geocodingHelper;

    @Test
    public void testeValidateParamsAddress(){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> enderecoBO.saveByDTO(enderecoDTO));
        assertEquals(MensagensErro.LOGRADOURO_EMPTY.getDescricao(), runtimeException.getMessage());

        enderecoDTO.setLogradouro("Rua teste");
        runtimeException = assertThrows(RuntimeException.class, () -> enderecoBO.saveByDTO(enderecoDTO));
        assertEquals(MensagensErro.CEP_EMPTY.getDescricao(), runtimeException.getMessage());

        enderecoDTO.setCep("CEP teste");
        runtimeException = assertThrows(RuntimeException.class, () -> enderecoBO.saveByDTO(enderecoDTO));
        assertEquals(MensagensErro.BAIRRO_EMPTY.getDescricao(), runtimeException.getMessage());

        enderecoDTO.setBairro("Bairro teste");
        runtimeException = assertThrows(RuntimeException.class, () -> enderecoBO.saveByDTO(enderecoDTO));
        assertEquals(MensagensErro.NUMERO_LOGRADOURO_EMPTY.getDescricao(), runtimeException.getMessage());

        enderecoDTO.setNumero("Numero teste");
        assertDoesNotThrow(() -> enderecoBO.saveByDTO(enderecoDTO));
    }

    @Test
    public void getEntityValidate(){
        Optional<Endereco> endereco = Optional.empty();
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(endereco);

        assertThrows(RuntimeException.class, () -> enderecoBO.getById(1L));

        endereco = Optional.of(new Endereco());
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(endereco);
        assertDoesNotThrow(() -> enderecoBO.getById(1L));
    }

    @Test
    public void validateDelete(){
        Optional<Endereco> endereco = Optional.of(new Endereco());
        Mockito.when(enderecoRepository.findById(Mockito.anyLong())).thenReturn(endereco);
        assertTrue(Objects.isNull(endereco.get().getDataExclusao()));
        enderecoBO.deleteByEntity(endereco.get());
        assertTrue(Objects.nonNull(endereco.get().getDataExclusao()));
    }

}
