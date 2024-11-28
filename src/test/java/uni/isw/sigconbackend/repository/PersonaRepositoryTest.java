package uni.isw.sigconbackend.repository;

import java.sql.Date;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.model.TipoDocumento;
import uni.isw.sigconbackend.model.Ubigeo;

@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class PersonaRepositoryTest {   
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    UbigeoRepository ubigeoRepository;
    
    private TipoDocumento tipoDocumento;
    private Ubigeo ubigeo;    
    
    @BeforeEach
    public void setUp() {
        init();
    }
    
    private void init(){
        tipoDocumento=TipoDocumento.builder()
                .idTipoDocumento(1)
                .descripcion("DNI")
                .build();
        TipoDocumento newTipoDocumento=tipoDocumentoRepository.save(tipoDocumento);    
        
        Ubigeo ubigeo1=Ubigeo.builder()
                .idUbigeo("150114")
                .departamento("Lima")
                .provincia("Lima")
                .distrito("La Molina").build();
        ubigeo1=ubigeoRepository.save(ubigeo1);
        
        Ubigeo ubigeo2=Ubigeo.builder()
                .idUbigeo("070104")
                .departamento("Callao")
                .provincia("La Perla")
                .distrito("La Perla").build();
        ubigeo2=ubigeoRepository.save(ubigeo2);
        
        Persona persona1=Persona.builder()
                .apellidoPaterno("Cavero")
                .apellidoMaterno("Alva")
                .nombres("Alejandro")
                .fechaNacimiento(new Date(2000-04-05))
                .nDocumento("33356667")
                .direccion("Av. Los Fresnos 865")
                .tipoDocumento(tipoDocumento)
                .ubigeo(ubigeo1).build();
        Persona newPersona1=personaRepository.save(persona1);
        
        Persona persona2=Persona.builder()
                .apellidoPaterno("Chirinos")
                .apellidoMaterno("Vargas")
                .nombres("Patricia")
                .fechaNacimiento(new Date(1992-04-05))
                .nDocumento("55556667")
                .direccion("Av. Guardia Chalaca 565")
                .tipoDocumento(tipoDocumento)
                .ubigeo(ubigeo2).build();
        Persona newPersona2=personaRepository.save(persona2);        
        
    }

    @Test
    public void personaRepository_findAll() {
        List<Persona> personaList=personaRepository.findAll();
        
        Assertions.assertThat(personaList).isNotNull();
        Assertions.assertThat(personaList.size()).isEqualTo(2);    
    }
    @Test
    public void personaRepository_findById() {
        Persona personaResult=personaRepository.findById(personaRepository.findAll().get(0).getIdPersona()).orElse(null);
        Assertions.assertThat(personaResult).isNotNull();
        Assertions.assertThat(personaResult.getApellidoPaterno()).isNotNull();    
        Assertions.assertThat(personaResult.getIdPersona()).isGreaterThan(0);
    }
    @Test
    public void personaRepository_deleteById() {
        Persona personaResult=personaRepository.findAll().get(0);
        Assertions.assertThat(personaResult).isNotNull();
        
        personaRepository.delete(personaResult);
        //verificar si ya lo elimino
        Persona deletePersona=personaRepository.findById(personaResult.getIdPersona()).orElse(null);
        Assertions.assertThat(deletePersona).isNull();        
    }
    @Test
    public void personaRepository_save() {
        Persona persona3=Persona.builder()
                .apellidoPaterno("Musk")
                .apellidoMaterno("Trump")
                .nombres("Elon")
                .fechaNacimiento(new Date(1998-04-05))
                .nDocumento("55556677")
                .direccion("Av. Conquistadores 658")
                .tipoDocumento(tipoDocumento)
                .ubigeo(ubigeoRepository.findById("070104").get())
                .build();
        Persona newPersona3=personaRepository.save(persona3);  
        
        Assertions.assertThat(newPersona3).isNotNull();
        Assertions.assertThat(newPersona3.getIdPersona()).isNotNull();
        Assertions.assertThat(newPersona3.getApellidoPaterno()).isEqualTo("Musk");        
    }
    @Test
    public void personaRepository_update() {
        Persona personaResult=personaRepository.findAll().get(0);
        personaResult.setDireccion("Av. Jose Galvez 745");
        Persona updatePersona=personaRepository.save(personaResult);
        Assertions.assertThat(updatePersona.getDireccion()).isEqualTo("Av. Jose Galvez 745");        
    }
}
