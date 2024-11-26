package dcc.tp2.enseignantservice.client;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Chercheur-service", url = "http://localhost:8082")
public interface ChercheurRestFeign {

    @GetMapping("/Chercheurs/Enseignant/{id}")
    @CircuitBreaker(name = "chercheur-count",fallbackMethod = "Chercheur_fallbackMethod")
    @Retry(name = "chercheur-count",fallbackMethod = "Chercheur_fallbackMethod")
    Long nb_chercheur_Enseignant(@PathVariable Long id);

    default Long Chercheur_fallbackMethod(Long id,Exception e){
        return null;
    }

}
