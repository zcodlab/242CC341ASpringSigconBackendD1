package uni.isw.sigconbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="persona")
public class Persona {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_persona",nullable=false)
    private Long idPersona; //convecion camelCase
    @Column(name="apellido_paterno",nullable=false)
    private String apellidoPaterno;
    @Column(name="apellido_materno",nullable=false)
    private String apellidoMaterno;
    @Column(name="nombres",nullable=false)
    private String nombres;
    @Column(name="fecha_nacimiento",nullable=false)
    private Date fechaNacimiento;    
    @Column(name="ndocumento",nullable=false)
    private String nDocumento;
    @Column(name="direccion",nullable=false)
    private String direccion;    
    
    @ManyToOne(fetch = FetchType.EAGER)//LAZY
    @JoinColumn(name="id_tipo_documento", referencedColumnName="id_tipo_documento")
    private TipoDocumento tipoDocumento;    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idubigeo", referencedColumnName = "idubigeo")        
    private Ubigeo ubigeo;
}
