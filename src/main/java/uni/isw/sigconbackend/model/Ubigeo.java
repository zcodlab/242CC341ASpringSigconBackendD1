package uni.isw.sigconbackend.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ubigeo")
public class Ubigeo {
    @Id    
    @Column(name="idubigeo",nullable=false)
    private String IdUbigeo;
    @Column(name="departamento",nullable=false)
    private String Departamento;
    @Column(name="provincia",nullable=false)
    private String Provincia;
    @Column(name="distrito",nullable=false)
    private String Distrito;            
}
