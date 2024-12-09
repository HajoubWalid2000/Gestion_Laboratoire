package dcc.tp2.enseignantservice.service;

import dcc.tp2.enseignantservice.client.ChercheurRestFeign;
import dcc.tp2.enseignantservice.client.ProjetRestFeign;
import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.repository.EnseignantRepository;
import org.assertj.core.api.AssertionsForClassTypes;
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

    @Mock
    private EnseignantRepository enseignantRepository;
    @Mock
    private ChercheurRestFeign chercheurRestFeign;
    @Mock
    private ProjetRestFeign projetRestFeign;
    @InjectMocks
    private EnseignantService enseignantService;

    @Test
    void create_Enseignant() {
        Enseignant enseignant= new Enseignant(null,"ali","ahmadi","LA11233","ali@mail.com","123","info","Enseignant");
        Enseignant enseignantSaved= new Enseignant(1L,"ali","ahmadi","LA11233","ali@mail.com","123","info","Enseignant");
        //action
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(enseignantSaved);
        // tester
        Enseignant result = enseignantService.Create_Enseignant(enseignant);
        //véfication
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantSaved);

    }

    @Test
    void getAll_Enseignant() {

        List<Enseignant> enseignantList=  List.of(
                new Enseignant(1L,"ali","ahmadi","LA11233","ali@mail.com","123","info","Enseignant"),
                new Enseignant(2L,"mohamed","mrini","KB11233","mohamed@mail.com","123","info","Enseignant")
        );

        Mockito.when(enseignantRepository.findAll()).thenReturn(enseignantList);
        List<Enseignant> result = enseignantService.GetAll_Enseignant();
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantList);


    }

    @Test
    void get_EnseignantByID() {

        Long id = 1L;
        Enseignant enseignant = new Enseignant(1L,"ali","ahmadi","LA11233","ali@mail.com","123","info","Enseignant");
        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));

        Enseignant result = enseignantService.Get_EnseignantByID(id);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);
    }

    @Test
    void NOt_get_EnseignantByID() {

        Long id = 100L;
        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.empty());
        Enseignant result = enseignantService.Get_EnseignantByID(id);
        AssertionsForClassTypes.assertThat(result).isNull();

    }

    @Test
    void findByEmail() {
        String email = "ali@mail.com";
        Enseignant enseignant = new Enseignant(1L,"ali","ahmadi","LA11233","ali@mail.com","123","info","Enseignant");

        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(enseignant);

        Enseignant result = enseignantService.FindByEmail(email);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);

    }

    @Test
    void NotfindByEmail() {
        String email = "abc@mail.com";
        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(null);
        Enseignant result = enseignantService.FindByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNull();

    }

    @Test
    void update_Enseignant() {
        Long id =1L;
        Enseignant enseignant = new Enseignant(1L,"ali","ahmadi","LA11233","ali@mail.com","123","info","Enseignant");
        Enseignant enseignant_modify = new Enseignant(1L,"ali","AL-ahmadi","LA11233","ali@mail.com","123","info","Enseignant");

        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));

        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(enseignant_modify);

        Enseignant result = enseignantService.Update_Enseignant(enseignant_modify,id);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant_modify);



    }

    @Test
    void delete_Enseignant() {
        Long id =1L;
        enseignantService.Delete_Enseignant(id);
    }

    @Test
    void statistique() {
        Long id=1L;

        Map<String, Long> Statistiques = new HashMap<>();
        Statistiques.put("nombre de projet",2L);
        Statistiques.put("nombre de chercheur",2L);

        Mockito.when(chercheurRestFeign.nb_chercheur_Enseignant(id)).thenReturn(2L);
        Mockito.when(projetRestFeign.nb_Projet_Enseignant(id)).thenReturn(2L);

        Map<String, Long> result = enseignantService.statistique(id);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(Statistiques);


    }
}