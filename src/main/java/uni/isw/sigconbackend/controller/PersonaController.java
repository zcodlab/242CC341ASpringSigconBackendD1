package uni.isw.sigconbackend.controller;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigconbackend.dto.PersonaRequest;
import uni.isw.sigconbackend.dto.PersonaResponse;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.service.PersonaService;
import uni.isw.sigconbackend.utils.ErrorResponse;

@RestController
@RequestMapping(path="api/v1/persona")
public class PersonaController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonaService personaService;        
    
    @GetMapping()
    public ResponseEntity<?> getPersonas(){
        List<PersonaResponse> listaPersonaResponse=null;
        try{
            listaPersonaResponse=personaService.listPersonas();
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaPersonaResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Personas not found").build());           
        return ResponseEntity.ok(listaPersonaResponse);        
    }
    
    @GetMapping("/find")
    public ResponseEntity<?> findPersonaById(@RequestBody Optional<PersonaRequest> personaRequest){
        logger.info(">find" +  personaRequest.toString());
        PersonaResponse personaResponse;
        try{
            personaResponse=personaService.findPersona(personaRequest.get().getIdPersona());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());           
        return ResponseEntity.ok(personaResponse);        
        
    }
    
    @PostMapping()
    public ResponseEntity<?> insertPersona(@RequestBody PersonaRequest personaResquest){
        logger.info(">insert" +  personaResquest.toString());
        PersonaResponse personaResponse;
        try{            
            personaResponse=personaService.insertPersona(personaResquest);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not insert").build());           
        return ResponseEntity.ok(personaResponse);        
    }    
    
    @PutMapping()
    public ResponseEntity<?> updatePersona(@RequestBody PersonaRequest personaRequest){
        logger.info(">update" +  personaRequest.toString());
        PersonaResponse personaResponse;
        try{
            personaResponse=personaService.findPersona(personaRequest.getIdPersona());
            if (personaResponse==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());           
            
            personaResponse=personaService.updatePersona(personaRequest);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not update").build());           
        return ResponseEntity.ok(personaResponse);  
    }    
    
    @DeleteMapping()
    public ResponseEntity<?> deletePersona(@RequestBody Optional<Persona> personaRequest){
        logger.info(">delete" +  personaRequest.toString());
        PersonaResponse personaResponse;
        try{            
            personaResponse=personaService.findPersona(personaRequest.get().getIdPersona());
            if(!personaRequest.isPresent())                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found for delete").build());  
            personaService.deletePersona(personaRequest.get().getIdPersona());                        
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        return ResponseEntity.ok(personaResponse);  
    }
    
    
}
