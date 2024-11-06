package uni.isw.sigconbackend.controller;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.service.PersonaService;

@RestController
@RequestMapping(path="api/v1/persona")
public class PersonaController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonaService personaService;    
    
    @RequestMapping(value="/list", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> getPersonas(){
        List<Persona> listaPersonas=null;
        try{
            listaPersonas=personaService.listPersonas();
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(listaPersonas,HttpStatus.OK);
    }
    @RequestMapping(value="/find", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> searchPersona(@RequestBody Optional<Persona> persona){
        logger.info(">find" +  persona.toString());
        try{
            persona=personaService.findPersona(persona.get().getIdPersona());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(persona.get(),HttpStatus.OK);
    }
    @RequestMapping(value="/insert", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> insertPersona(@RequestBody Persona persona){
        logger.info(">insert" +  persona.toString());
        Persona newpersona;
        try{            
            newpersona=personaService.insertPersona(persona);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newpersona,HttpStatus.OK);
    }
    
    @RequestMapping(value="/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> updatePersona(@RequestBody Persona persona){
        logger.info(">update" +  persona.toString());
        Persona newpersona;
        try{
            newpersona=personaService.updatePersona(persona);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(persona,HttpStatus.OK);
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> deletePersona(@RequestBody Optional<Persona> persona){
        logger.info(">delete" +  persona.toString());
        try{
            persona=personaService.findPersona(persona.get().getIdPersona());
            if(persona.isPresent())
                personaService.deletePersona(persona.get().getIdPersona());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(persona.get(),HttpStatus.OK);
    }
    
    
}
