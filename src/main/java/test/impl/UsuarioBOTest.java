package test.impl;

import com.example.pursuitservice.bo.EnderecoBO;
import com.example.pursuitservice.bo.UsuarioBO;
import com.example.pursuitservice.domain.sql.Usuario;
import com.example.pursuitservice.dto.EnderecoDTO;
import com.example.pursuitservice.dto.UsuarioDTO;
import com.example.pursuitservice.enums.MensagensErro;
import com.example.pursuitservice.helpers.GeocodingHelper;
import com.example.pursuitservice.impl.EnderecoBOImpl;
import com.example.pursuitservice.impl.UsuarioBOImpl;
import com.example.pursuitservice.repositories.sql.EnderecoRepository;
import com.example.pursuitservice.repositories.sql.UsuarioRepository;
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
        UsuarioBOImpl.class,
        UsuarioRepository.class,
        EnderecoBOImpl.class,
        EnderecoRepository.class,
        UsuarioBO.class})
@ExtendWith(SpringExtension.class)
public class UsuarioBOTest {
    @Autowired
    UsuarioBO usuarioBO;
    @MockBean
    UsuarioRepository usuarioRepository;
    @MockBean
    GeocodingHelper geocodingHelper;
    @MockBean
    EnderecoBO enderecoBO;
    @MockBean
    EnderecoRepository enderecoRepository;

    @Test
    public void validateParamsUser(){
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> usuarioBO.saveByDTO(usuarioDTO));
        assertEquals(MensagensErro.NOME_EMPTY.getDescricao(), runtimeException.getMessage());

        usuarioDTO.setNome("Nome Teste");
        runtimeException = assertThrows(RuntimeException.class, () -> usuarioBO.saveByDTO(usuarioDTO));
        assertEquals(MensagensErro.EMAIL_EMPTY.getDescricao(), runtimeException.getMessage());

        usuarioDTO.setEmail("Email Teste");
        runtimeException = assertThrows(RuntimeException.class, () -> usuarioBO.saveByDTO(usuarioDTO));
        assertEquals(MensagensErro.ENDERECO_IMOVEL_EMPTY.getDescricao(), runtimeException.getMessage());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        usuarioDTO.setEnderecoDTO(enderecoDTO);
        assertDoesNotThrow(() -> usuarioBO.saveByDTO(usuarioDTO));

    }
    @Test
    public void validateDelete(){
        Optional<Usuario> usuario = Optional.of(new Usuario());
        Mockito.when(usuarioRepository.findById(Mockito.anyLong())).thenReturn(usuario);
        assertTrue(Objects.isNull(usuario.get().getDataExclusao()));
        usuarioBO.deleteById(1L);
        assertTrue(Objects.nonNull(usuario.get().getDataExclusao()));
    }

}
