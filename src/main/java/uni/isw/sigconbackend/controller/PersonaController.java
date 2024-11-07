package uni.isw.sigconbackend.controller;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
        List<Persona> listaPersonas=null;
        try{
            listaPersonas=personaService.listPersonas();
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaPersonas.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Ubigeos not found").build());           
        return ResponseEntity.ok(listaPersonas);        
    }
    
    @GetMapping("/find")
    public ResponseEntity<?> findPersonaById(@RequestBody Optional<Persona> persona){
        logger.info(">find" +  persona.toString());
        try{
            persona=personaService.findPersona(persona.get().getIdPersona());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (persona==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());           
        return ResponseEntity.ok(persona.get());        
        
    }
    
    @PostMapping()
    public ResponseEntity<?> insertPersona(@RequestBody Persona persona){
        logger.info(">insert" +  persona.toString());
        Persona newpersona;
        try{            
            newpersona=personaService.insertPersona(persona);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (newpersona==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not insert").build());           
        return ResponseEntity.ok(newpersona);        
    }    
    
    @PutMapping()
    public ResponseEntity<?> updatePersona(@RequestBody Persona persona){
        logger.info(">update" +  persona.toString());
        Persona newpersona;
        try{
            newpersona=personaService.updatePersona(persona);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (newpersona==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not update").build());           
        return ResponseEntity.ok(newpersona);  
    }    
    
    @DeleteMapping()
    public ResponseEntity<?> deletePersona(@RequestBody Optional<Persona> persona){
        logger.info(">delete" +  persona.toString());
        try{
            persona=personaService.findPersona(persona.get().getIdPersona());
            if(persona.isPresent())
                personaService.deletePersona(persona.get().getIdPersona());
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found for delete").build());  
            
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        return ResponseEntity.ok(persona.get());  
    }
    
    
}
