package dcc.tp2.enseignantservice.service;

import dcc.tp2.enseignantservice.client.ChercheurRestFeign;
import dcc.tp2.enseignantservice.client.ProjetRestFeign;
import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.repository.EnseignantRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EnseignantServiceTest {

    @InjectMocks
    private EnseignantService enseignantService;
    @Mock
    private EnseignantRepository enseignantRepository;
    @Mock
    private ChercheurRestFeign chercheurRestFeign;
    @Mock
    private ProjetRestFeign projetRestFeign;


    @Test
    void create_Enseignant() {
        Enseignant enseignant = new Enseignant(null, "Ali", "Ahmadi", "LA11111", "ali@mail.com", "123", "infor", "Enseignant");
        Enseignant Enseignantsaved = new Enseignant(1L, "Ali", "Ahmadi", "LA11111", "ali@mail.com", "123", "infor", "Enseignant");
        // action
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(Enseignantsaved);
        // tester
        Enseignant resultat = enseignantService.Create_Enseignant(enseignant);
        // vérifier
        AssertionsForClassTypes.assertThat(resultat).usingRecursiveComparison().isEqualTo(Enseignantsaved);
    }

    @Test
    void getAll_Enseignant() {
        List<Enseignant> enseignantList = List.of(
                new Enseignant(null, "Ali", "Ahmadi", "LA11111", "ali@mail.com", "123", "infor", "Enseignant"),
                new Enseignant(null, "mohamed", "mrini", "KB1234", "mrini@mail.com", "123", "infor", "Enseignant")
        );
        // action
        Mockito.when(enseignantRepository.findAll()).thenReturn(enseignantList);
        List<Enseignant> result = enseignantService.GetAll_Enseignant();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantList);
    }

    @Test
    void get_EnseignantByID() {
        Long id = 1L;
        Enseignant enseignant = new Enseignant(1L, "Ali", "Ahmadi", "LA11111", "ali@mail.com", "123", "infor", "Enseignant");
        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));
        Enseignant result = enseignantService.Get_EnseignantByID(id);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);
    }

    @Test
    void Not_get_EnseignantByID() {
        Long id = 9L;
        Enseignant enseignant = new Enseignant(1L, "Ali", "Ahmadi", "LA11111", "ali@mail.com", "123", "infor", "Enseignant");
        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.empty());
        Enseignant result = enseignantService.Get_EnseignantByID(id);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isNotEqualTo(enseignant);

    }

    @Test
    void findByEmail() {
        String email = "ali@mail.com";
        Enseignant enseignant = new Enseignant(1L, "Ali", "Ahmadi", "LA11111", "ali@mail.com", "123", "infor", "Enseignant");
        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(enseignant);
        Enseignant result = enseignantService.FindByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);
    }

    @Test
    void Not_findByEmail() {
        String email = "abc@mail.com";
        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(null);
        Enseignant result = enseignantService.FindByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNull();
    }

    @Test
    void update_Enseignant() {
        Long id = 1L;
        Enseignant enseignant = new Enseignant(1L, "Ali", "Ahmadi", "LA11111", "ali@mail.com", "123", "infor", "Enseignant");
        Enseignant enseignant_modify = new Enseignant(1L, "Ali", "AL-Ahmadi", "LA11111", "ali@mail.com", "123", "infor", "Enseignant");

        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(enseignant_modify);

        Enseignant result = enseignantService.Update_Enseignant(enseignant_modify, id);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant_modify);

    }

    @Test
    void delete_Enseignant() {
        Long id = 1L;
        enseignantService.Delete_Enseignant(id);
    }
    @Test
    void statistique() {
        Long id = 1L;
        //action
        Mockito.when(chercheurRestFeign.nb_chercheur_Enseignant(id)).thenReturn(2L);
        Mockito.when(projetRestFeign.nb_Projet_Enseignant(id)).thenReturn(2L);

        Map<String, Long> Statistiques = new HashMap<>();
        Statistiques.put("nombre de projet",2L);
        Statistiques.put("nombre de chercheur",2L);

        Map<String, Long> result = enseignantService.statistique(id);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(Statistiques);

    }
}