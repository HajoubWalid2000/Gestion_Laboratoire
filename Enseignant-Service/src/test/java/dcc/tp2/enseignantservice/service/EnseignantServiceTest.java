package dcc.tp2.enseignantservice.service;

import dcc.tp2.enseignantservice.client.ChercheurRestFeign;
import dcc.tp2.enseignantservice.client.ProjetRestFeign;
import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.repository.EnseignantRepository;
import dcc.tp2.enseignantservice.service.EnseignantService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
    private EnseignantService service_test;



    @Test
    void create_Enseignant() {
        Enseignant enseignant = new Enseignant(null,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        Enseignant enseignantSaved = new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        //action
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(enseignantSaved);

        Enseignant result = service_test.Create_Enseignant(enseignant);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantSaved);
    }

    @Test
    void Not_create_Enseignant() {
        Enseignant enseignant = new Enseignant(null,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        Enseignant enseignantSaved = new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        //action
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(null);
        Enseignant result = service_test.Create_Enseignant(enseignant);
        AssertionsForClassTypes.assertThat(result).isNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isNotEqualTo(enseignantSaved);
    }

    @Test
    void getAll_Enseignant() {
        List<Enseignant> enseignantList = List.of(
                new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant"),
                new Enseignant(2L,"nadir","ehmimed","la1223","nadir@mail.com","nadir123","informatique","Enseignant")
        );
        Mockito.when(enseignantRepository.findAll()).thenReturn(enseignantList);
        List<Enseignant> result = service_test.GetAll_Enseignant();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantList);
    }

    @Test
    void get_EnseignantByID() {
        Long id = 1L;
        Enseignant enseignant = new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));

        Enseignant result = service_test.Get_EnseignantByID(id);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);

    }

    @Test
    void Not_get_EnseignantByID() {
        Long id = 8L;
        Enseignant enseignant = new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.empty());
        Enseignant result = service_test.Get_EnseignantByID(id);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isNotEqualTo(enseignant);

    }

    @Test
    void findByEmail() {
        String email = "driss@mail.com";
        Enseignant enseignant = new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(enseignant);

        Enseignant result = service_test.FindByEmail(email);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);

    }

    @Test
    void NotfindByEmail() {
        String email = "driss@mail.com";
        Enseignant enseignant = new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(null);

        Enseignant result = service_test.FindByEmail(email);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isNotEqualTo(enseignant);

    }

    @Test
    void update_Enseignant() {
        Long id = 1L;
        Enseignant enseignant = new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        Enseignant enseignant_UP = new Enseignant(1L,"driss","el-mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(enseignant_UP);

        Enseignant result = service_test.Update_Enseignant(enseignant_UP,id);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);
    }

    @Test
    void delete_Enseignant() {
        Long id = 1L;
        Enseignant enseignant = new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");


    }

    @Test
    void statistique() {
        Long id = 1L;
        Enseignant enseignant = new Enseignant(1L,"driss","mrabet","la1223","driss@mail.com","driss123","informatique","Enseignant");
    }
}