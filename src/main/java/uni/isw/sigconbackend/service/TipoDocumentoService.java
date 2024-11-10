package uni.isw.sigconbackend.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigconbackend.model.TipoDocumento;
import uni.isw.sigconbackend.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;
    
    public List<TipoDocumento> getTipoDocumentos(){
        return tipoDocumentoRepository.findAll();
    }
    public Optional<TipoDocumento> getTipoDocumento(Integer id){
        return tipoDocumentoRepository.findById(id);
    }
    
    public void insertTipoDocumento(TipoDocumento tipoDocumento){
        tipoDocumentoRepository.save(tipoDocumento);
    }   
    
    public void updateTipoDocumento(TipoDocumento tipoDocumento){
        tipoDocumentoRepository.save(tipoDocumento);
    }   
    
    public void deleteTipoDocumento(Integer id){
        tipoDocumentoRepository.deleteById(id);
    }
}
