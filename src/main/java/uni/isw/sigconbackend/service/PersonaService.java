package uni.isw.sigconbackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;
    
    public List<Persona> listPersonas(){
        return personaRepository.findAll();
    }
    public Optional<Persona> findPersona(Long id){
        return personaRepository.findById(id);
    }
    
    public Persona insertPersona(Persona persona){
        return personaRepository.save(persona);
    } 
    
    public Persona updatePersona(Persona persona){
        return personaRepository.save(persona);
    }   
    
    public void deletePersona(Long id){
        personaRepository.deleteById(id);
    }
    
    
}
