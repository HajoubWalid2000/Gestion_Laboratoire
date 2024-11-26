package dcc.tp2.enseignantservice.client;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Projet-service", url = "http://localhost:8083")
public interface ProjetRestFeign {



    @GetMapping("/Projets/Enseignant/{id}")
    @CircuitBreaker(name = "projet-count",fallbackMethod = "Projet_fallbackMethod")
    @Retry(name = "projet-count",fallbackMethod = "Chercheur_fallbackMethod")
    Long nb_Projet_Enseignant(@PathVariable Long id);

    default Long Projet_fallbackMethod(Long id,Exception e){
        return null;
    }

}
