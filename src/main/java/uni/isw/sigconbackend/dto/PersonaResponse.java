package uni.isw.sigconbackend.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.model.TipoDocumento;
import uni.isw.sigconbackend.model.Ubigeo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaResponse {
    private Long idPersona;    
    private String apellidoPaterno;    
    private String apellidoMaterno;    
    private String nombres;    
    private Date fechaNacimiento;        
    private String nDocumento;    
    private String direccion;    
    private TipoDocumento tipoDocumento;    
    private Ubigeo ubigeo;    
    
    public static PersonaResponse fromEntity(Persona persona) {
        return PersonaResponse.builder()
                .idPersona(persona.getIdPersona())
                .apellidoPaterno(persona.getApellidoPaterno())
                .apellidoMaterno(persona.getApellidoMaterno())
                .nombres(persona.getNombres())
                .fechaNacimiento(persona.getFechaNacimiento())                
                .nDocumento(persona.getNDocumento())
                .direccion(persona.getDireccion())            
                .tipoDocumento(persona.getTipoDocumento())
                .ubigeo(persona.getUbigeo())                //.provincia(persona.getUbigeo()==null ? "": persona.getUbigeo().getProvincia())
                .build();
    }
    
    public static List<PersonaResponse> fromEntities(List<Persona> persona) {
        return persona.stream()
                .map(PersonaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
