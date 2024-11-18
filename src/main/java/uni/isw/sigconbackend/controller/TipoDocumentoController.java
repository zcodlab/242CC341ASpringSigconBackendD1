package uni.isw.sigconbackend.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigconbackend.model.TipoDocumento;
import uni.isw.sigconbackend.service.TipoDocumentoService;
@RestController
@RequestMapping(path="api/v1/tipodocumento")
public class TipoDocumentoController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    TipoDocumentoService tipoDocumentoService;        
    
    @GetMapping()
    public ResponseEntity<List<TipoDocumento>> getTipoDocumentos(){
        List<TipoDocumento> listaTipoDocumentos=null;
        try{
            listaTipoDocumentos=tipoDocumentoService.getTipoDocumentos();
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(listaTipoDocumentos,HttpStatus.OK);
    }
}
