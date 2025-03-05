package co.edu.uniandes.dse.parcialprueba.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

class PacienteServiceTest {

    @Autowired

    private PacienteService pacienteService;

    @Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<PacienteEntity> pacienteList = new ArrayList<>();

    @BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from PacienteEntity");
        entityManager.getEntityManager().createQuery("delete from HistoriaClinicaEntity");
	}

	private void insertData() {
		
		editorialEntity = factory.manufacturePojo(EditorialEntity.class);
		entityManager.persist(editorialEntity);

		for (int i = 0; i < 3; i++) {
			PacienteEntity pacienteEntity = factory.manufacturePojo(PacienteEntity.class);
			pacienteEntity.setEditorial(editorialEntity);
			entityManager.persist(pacienteEntity);
			pacienteList.add(pacienteEntity);
		}

		AuthorEntity authorEntity = factory.manufacturePojo(AuthorEntity.class);
		entityManager.persist(authorEntity);
		authorEntity.getBooks().add(bookList.get(0));
		bookList.get(0).getAuthors().add(authorEntity);
	}



    
}
