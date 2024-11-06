package uni.isw.sigconbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tipo_documento")
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_tipo_documento",nullable=false)
    private Integer IdTipoDocumento;
    @Column(name="descripcion",nullable=false)
    private String descripcion;    
    
}
