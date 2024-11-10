package uni.isw.sigconbackend.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.isw.sigconbackend.model.Persona;

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
    private String tipoDocumento;    
    private String nDocumento;    
    private String direccion;    
    private String departamento;
    private String provincia;
    private String distrito;
    
    public static PersonaResponse fromEntity(Persona persona) {
        return PersonaResponse.builder()
                .idPersona(persona.getIdPersona())
                .apellidoPaterno(persona.getApellidoPaterno())
                .apellidoMaterno(persona.getApellidoMaterno())
                .nombres(persona.getNombres())
                .fechaNacimiento(persona.getFechaNacimiento())
                .tipoDocumento(persona.getTipoDocumento()==null ? "":persona.getTipoDocumento().getDescripcion())
                .nDocumento(persona.getNDocumento())
                .direccion(persona.getDireccion())                
                .departamento(persona.getUbigeo()==null ? "": persona.getUbigeo().getDepartamento())
                .provincia(persona.getUbigeo()==null ? "": persona.getUbigeo().getProvincia())
                .distrito(persona.getUbigeo()==null ? "": persona.getUbigeo().getDistrito())                
                .build();
    }
    
    public static List<PersonaResponse> fromEntities(List<Persona> persona) {
        return persona.stream()
                .map(PersonaResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
