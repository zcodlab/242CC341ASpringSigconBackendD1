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
import uni.isw.sigconbackend.model.Ubigeo;
import uni.isw.sigconbackend.service.UbigeoService;

@RestController
@RequestMapping(path="api/v1/ubigeo")
public class UbigeoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UbigeoService ubigeoService;
      
    @GetMapping()
    public ResponseEntity<List<Ubigeo>> getUbigeos() {
        logger.info(">listar");

        List<Ubigeo> listaUbigeo = null;
        try {
                listaUbigeo = ubigeoService.getUbigeos();
        } catch (Exception e) {
                logger.error("Unexpected Exception caught.", e);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(">listar");
        return new ResponseEntity<>(listaUbigeo, HttpStatus.OK);
    }
}
