package uni.isw.sigconbackend.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigconbackend.dto.PersonaRequest;
import uni.isw.sigconbackend.dto.PersonaResponse;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.model.TipoDocumento;
import uni.isw.sigconbackend.model.Ubigeo;
import uni.isw.sigconbackend.repository.PersonaRepository;
import uni.isw.sigconbackend.repository.TipoDocumentoRepository;
import uni.isw.sigconbackend.repository.UbigeoRepository;

@Service
public class PersonaService {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    UbigeoRepository ubigeoRepository;
    
    public List<PersonaResponse> listPersonas(){        
        return PersonaResponse.fromEntities(personaRepository.findAll());
    }
    public PersonaResponse findPersona(Long id){
        return PersonaResponse.fromEntity(personaRepository.findById(id).get());                
    }
    
    public PersonaResponse insertPersona(PersonaRequest personaRequest){        
        Integer idTipoDocumento=personaRequest.getIdTipoDocumento();
        TipoDocumento tipoDocumento=tipoDocumentoRepository.findById(idTipoDocumento).get();
        logger.info(">insertPersona-tipoDocumento" +  tipoDocumento.toString());
        if (tipoDocumento==null) return new PersonaResponse();
        
        String idUbigeo=personaRequest.getIdUbigeo();
        Ubigeo ubigeo=ubigeoRepository.findById(idUbigeo).get();
        logger.info(">insertPersona-ubigeo" +  ubigeo.toString());
        if (ubigeo==null) return new PersonaResponse();
        
        Persona persona=new Persona(
                personaRequest.getIdPersona(),
                personaRequest.getApellidoPaterno(),
                personaRequest.getApellidoMaterno(),
                personaRequest.getNombres(),                
                personaRequest.getFechaNacimiento(),
                personaRequest.getNDocumento(),
                personaRequest.getDireccion(),
                tipoDocumento,
                ubigeo);
        persona=personaRepository.save(persona);
        PersonaResponse personaResponse=PersonaResponse.fromEntity(persona);
        return personaResponse;
    } 
    
    public PersonaResponse updatePersona(PersonaRequest personaRequest){        
        Integer idTipoDocumento=personaRequest.getIdTipoDocumento();
        TipoDocumento tipoDocumento=tipoDocumentoRepository.findById(idTipoDocumento).get();        
        if (tipoDocumento==null) return new PersonaResponse();
        
        String idUbigeo=personaRequest.getIdUbigeo();
        Ubigeo ubigeo=ubigeoRepository.findById(idUbigeo).get();
        if (ubigeo==null) return new PersonaResponse();
        
        Persona persona=new Persona(
                personaRequest.getIdPersona(),
                personaRequest.getApellidoPaterno(),
                personaRequest.getApellidoMaterno(),
                personaRequest.getNombres(),                
                personaRequest.getFechaNacimiento(),
                personaRequest.getNDocumento(),
                personaRequest.getDireccion(),
                tipoDocumento,
                ubigeo);
        persona=personaRepository.save(persona);
        PersonaResponse personaResponse=PersonaResponse.fromEntity(persona);
        return personaResponse;
    }   
    
    public void deletePersona(Long id){
        personaRepository.deleteById(id);
    }
    
    
}
