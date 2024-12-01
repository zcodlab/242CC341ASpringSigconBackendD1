package uni.isw.sigconbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Date;
import uni.isw.sigconbackend.dto.PersonaRequest;
import uni.isw.sigconbackend.dto.PersonaResponse;
import uni.isw.sigconbackend.service.PersonaService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;

import static org.mockito.BDDMockito.given;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import uni.isw.sigconbackend.model.TipoDocumento;
import uni.isw.sigconbackend.model.Ubigeo;

@WebMvcTest(controllers = PersonaController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PersonaControllerTest {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaService personaService;
    
    TipoDocumento tipodocumento;
    Ubigeo ubigeo;
    PersonaRequest personaRequest;
    PersonaResponse personaResponse,personaResponse2,personaResponse3;
    
    @BeforeEach
    public void init() {
        tipodocumento = TipoDocumento.builder()
                .idTipoDocumento(1)
                .descripcion("DNI").build();               
        
        Integer id_tipo_documento=tipodocumento.getIdTipoDocumento();   
        
        ubigeo=Ubigeo.builder()
                .idUbigeo("070104")
                .departamento("Callao")
                .provincia("La Perla")
                .distrito("La Perla").build();                
   
        String idubigeo=ubigeo.getIdUbigeo();        
        
        personaRequest=PersonaRequest.builder()
                .idPersona(null)
                .apellidoPaterno("Cavero")
                .apellidoMaterno("Alva")
                .nombres("Alejandro")
                .fechaNacimiento(new Date(2000-04-05))                
                .nDocumento("33356667")
                .direccion("Av. Los Fresnos 865")
                .idTipoDocumento(id_tipo_documento)
                .idUbigeo(idubigeo).build();          
        
        
        personaResponse=PersonaResponse.builder()
                .idPersona(1L)
                .apellidoPaterno("Cavero")
                .apellidoMaterno("Alva")
                .nombres("Alejandro")
                .fechaNacimiento(new Date(2000-04-05))                
                .nDocumento("33356667")
                .direccion("Calle Luna 987")
                .tipoDocumento(tipodocumento)
                .ubigeo(ubigeo).build(); 
        
        personaResponse2=PersonaResponse.builder()
                .idPersona(2L)
                .apellidoPaterno("Chirinos")
                .apellidoMaterno("Soto")
                .nombres("Maria")
                .fechaNacimiento(new Date(2000-04-05))                
                .nDocumento("33356777")
                .direccion("Av.Javier Prado 758")
                .tipoDocumento(tipodocumento)
                .ubigeo(ubigeo).build(); 
        personaResponse3=PersonaResponse.builder()
                .idPersona(3L)
                .apellidoPaterno("Musk")
                .apellidoMaterno("Soto")
                .nombres("Elon")
                .fechaNacimiento(new Date(2000-04-05))                
                .nDocumento("33356888")
                .direccion("Av.Conquistadores 658")
                .tipoDocumento(tipodocumento)
                .ubigeo(ubigeo).build(); 
    }
    
    @Test
    public void PersonaController_insert() throws Exception {    
        Mockito.when(personaService.insertPersona(personaRequest)).thenReturn(personaResponse);
        
        logger.info(">Test-insertPersona1: " +  personaResponse.toString());
        
        MockHttpServletResponse response = mockMvc
                .perform(post("/api/v1/persona")
                        .content((new ObjectMapper()).writeValueAsString(personaRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();
        
        logger.info(">Test-insertPersona2: " +  response.getClass().toString());
        Assertions.assertEquals(response.getStatus(),200);
        Assertions.assertEquals(personaRequest.getNombres(), 
                (new ObjectMapper()).readValue(response.getContentAsString(), PersonaResponse.class).getNombres());                
    }   
    @Test
    public void PersonaController_delete() throws Exception {    
        logger.info(">Test-PersonaController_delete: ");
        given(personaService.findPersona(ArgumentMatchers.anyLong())).willReturn(personaResponse);
        doNothing().when(personaService).deletePersona(ArgumentMatchers.anyLong());

        ResultActions response = mockMvc.perform(delete("/api/v1/persona")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper()).writeValueAsString(personaRequest)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void PersonaController_update() throws Exception { 
        logger.info(">Test-PersonaController_update: ");
        personaRequest.setIdPersona(1L);       
        
        given(personaService.findPersona(ArgumentMatchers.anyLong())).willReturn(personaResponse);
        
        personaRequest.setDireccion("Calle Luna 987");
        
        Mockito.when(personaService.updatePersona(personaRequest)).thenReturn(personaResponse);
        
        MockHttpServletResponse response = mockMvc.perform(put("/api/v1/persona")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper()).writeValueAsString(personaRequest)))
                .andReturn().getResponse();
        
        logger.info(">Test-updatePersona1: " +  MockMvcResultMatchers.status()+" "+ personaResponse.toString());
        //Assertions.assertEquals(response.getStatus(),MockMvcResultMatchers.status().isOk());
        Assertions.assertEquals(personaRequest.getDireccion(), 
                (new ObjectMapper()).readValue(response.getContentAsString(), PersonaResponse.class).getDireccion());        
    }
    @Test
    public void PersonaController_getPersonas() throws Exception { 
        logger.info(">Test-PersonaController_getPersonas: ");
        when(personaService.listPersonas()).thenReturn(Arrays.asList(personaResponse2, personaResponse3));

        ResultActions response = mockMvc.perform(get("/api/v1/persona")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(2)))
                .andExpect(jsonPath("$[0].nombres", CoreMatchers.is(personaResponse2.getNombres())));
    }
    
    @Test
    public void PersonaController_findPersonaById() throws Exception {
        logger.info(">Test-PersonaController_findPersonaById: ");                
        when(personaService.findPersona(personaRequest.getIdPersona())).thenReturn(personaResponse);
        
        ResultActions response = mockMvc.perform(get("/api/v1/persona/find")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper()).writeValueAsString(personaRequest)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres", CoreMatchers.is(personaResponse.getNombres())));
    }
}
