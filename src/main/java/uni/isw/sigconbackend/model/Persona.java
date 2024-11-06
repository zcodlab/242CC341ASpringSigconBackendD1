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
    private Long IdPersona;
    @Column(name="apellido_paterno",nullable=false)
    private String ApellidoPaterno;
    @Column(name="apellido_materno",nullable=false)
    private String ApellidoMaterno;
    @Column(name="nombres",nullable=false)
    private String Nombres;
    @Column(name="fecha_nacimiento",nullable=false)
    private Date FechaNacimiento;
    @Column(name="id_tipo_documento",nullable=false)
    private Integer IdTipoDocumento;
    @Column(name="ndocumento",nullable=false)
    private String Ndocumento;
    @Column(name="direccion",nullable=false)
    private String Direccion;
    @Column(name="idubigeo",nullable=false)
    private String IdUbigeo;
    
    @ManyToOne
    @JoinColumn(name="id_tipo_documento", referencedColumnName="id_tipo_documento", 
            insertable=false,updatable=false)
    private TipoDocumento tipo_documento;
}
