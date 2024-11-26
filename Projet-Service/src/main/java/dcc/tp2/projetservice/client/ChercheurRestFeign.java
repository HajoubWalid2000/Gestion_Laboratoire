package dcc.tp2.projetservice.client;

import dcc.tp2.projetservice.module.Chercheur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Chercheur-service",url = "http://localhost:8082")
public interface ChercheurRestFeign {

    @GetMapping("/Chercheurs/{id}")
    Chercheur GetChercheurByID(@PathVariable Long id);

}
