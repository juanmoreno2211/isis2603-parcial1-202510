package co.edu.uniandes.dse.parcialprueba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;

public interface PacienteRepository extends  JpaRepository<PacienteEntity, Long> {

    List<PacienteEntity> findByName(String name);
    List<PacienteEntity> findByCorreo(String correo);
    List<PacienteEntity> findByTelefono(String telefono);
    PacienteEntity findAcudiente(Long id);
    


}
