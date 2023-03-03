package test.impl;

import com.example.pursuitservice.bo.ImovelBO;
import com.example.pursuitservice.bo.UsuarioBO;
import com.example.pursuitservice.domain.sql.Imovel;
import com.example.pursuitservice.domain.sql.Usuario;
import com.example.pursuitservice.dto.ImovelDTO;
import com.example.pursuitservice.enums.MensagensErro;
import com.example.pursuitservice.enums.TipoImovel;
import com.example.pursuitservice.helpers.GeocodingHelper;
import com.example.pursuitservice.impl.EnderecoBOImpl;
import com.example.pursuitservice.impl.ImovelBOImpl;
import com.example.pursuitservice.repositories.sql.EnderecoRepository;
import com.example.pursuitservice.repositories.sql.ImovelRepository;
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

@SpringBootTest(classes = {
        ImovelBOImpl.class,
        ImovelRepository.class,
        EnderecoBOImpl.class,
        EnderecoRepository.class,
        GeocodingHelper.class,
        UsuarioBO.class})
@ExtendWith(SpringExtension.class)
public class ImovelBOTest {

    @Autowired
    ImovelBO imovelBO;
    @MockBean
    ImovelRepository imovelRepository;
    @MockBean
    EnderecoBOImpl enderecoBO;
    @MockBean
    UsuarioBO usuarioBO;
    @MockBean
    GeocodingHelper geocodingHelper;

    @Test
    public void validateParamsImovel(){
        ImovelDTO imovelDTO = new ImovelDTO();
        String emailUser = "teste@gmail.com";
        Usuario usuario = new Usuario();

        Mockito.when(usuarioBO.getUserByEmail(Mockito.anyString())).thenReturn(usuario);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> imovelBO.saveByDTO(imovelDTO, emailUser));
        assertEquals(MensagensErro.TIPO_IMOVEL_EMPTY.getDescricao(), runtimeException.getMessage());

        imovelDTO.setTipoImovel(TipoImovel.APARTAMENTO);
        runtimeException = assertThrows(RuntimeException.class, () -> imovelBO.saveByDTO(imovelDTO, emailUser));
        assertEquals(MensagensErro.GARAGEM_EMPTY.getDescricao(), runtimeException.getMessage());

        imovelDTO.setGaragem(true);
        runtimeException = assertThrows(RuntimeException.class, () -> imovelBO.saveByDTO(imovelDTO, emailUser));
        assertEquals(MensagensErro.QUANTIDADE_CAMAS_EMPTY.getDescricao(), runtimeException.getMessage());

        imovelDTO.setQuantidadeCamas(1L);
        runtimeException = assertThrows(RuntimeException.class, () -> imovelBO.saveByDTO(imovelDTO, emailUser));
        assertEquals(MensagensErro.QUANTIDADE_QUARTOS_EMPTY.getDescricao(), runtimeException.getMessage());

        imovelDTO.setQuantidadeQuartos(1L);
        assertDoesNotThrow(() -> imovelBO.saveByDTO(imovelDTO, emailUser));
    }

    @Test
    public void validateDelete(){
        Optional<Imovel> imovel = Optional.of(new Imovel());
        Mockito.when(imovelRepository.findById(Mockito.anyLong())).thenReturn(imovel);
        assertTrue(Objects.isNull(imovel.get().getDataExclusao()));
        imovelBO.deleteById(1L);
        assertTrue(Objects.nonNull(imovel.get().getDataExclusao()));
    }

}
