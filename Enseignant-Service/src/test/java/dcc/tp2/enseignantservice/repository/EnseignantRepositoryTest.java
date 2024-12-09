package dcc.tp2.enseignantservice.repository;

import dcc.tp2.enseignantservice.entities.Enseignant;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EnseignantRepositoryTest {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @BeforeEach
    void setUp() {
        enseignantRepository.save(new Enseignant(null,"Ali","Ahmadi","LA11111","ali@mail.com","123","infor","Enseignant"));
        enseignantRepository.save(new Enseignant(null,"mohamed","mrini","KB1234","mrini@mail.com","123","infor","Enseignant"));
    }

    @Test
    void findEnseignantByEmail() {
        String email="ali@mail.com";
        Enseignant enseignant = new Enseignant(null,"Ali","Ahmadi","LA11111","ali@mail.com","123","infor","Enseignant");

        Enseignant result = enseignantRepository.findEnseignantByEmail(email);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(enseignant);

    }

    @Test
    void Not_findEnseignantByEmail() {
        String email="abc@mail.com";
        Enseignant result = enseignantRepository.findEnseignantByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNull();

    }



}