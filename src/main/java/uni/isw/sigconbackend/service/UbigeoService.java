package uni.isw.sigconbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigconbackend.model.Ubigeo;
import uni.isw.sigconbackend.repository.UbigeoRepository;

@Service
public class UbigeoService {
    @Autowired
    UbigeoRepository ubigeoRepository;
    public List<Ubigeo> getUbigeos(){
        return (List<Ubigeo>)ubigeoRepository.findAll();
    }
}
