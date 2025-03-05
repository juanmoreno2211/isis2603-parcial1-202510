package co.edu.uniandes.dse.parcialprueba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinicaEntity, Long> {

    List<HistoriaClinicaEntity> findByDiagnostico(String diagnostico);
    List<HistoriaClinicaEntity> findByTratamiento(String tratamiento);
    List<HistoriaClinicaEntity> findByFechaDeCreación(String fechaDeCreación);
    


    
}
