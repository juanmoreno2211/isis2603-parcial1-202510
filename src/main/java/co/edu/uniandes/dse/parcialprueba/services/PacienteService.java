package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;



    @Transactional
    public PacienteEntity crearPaciente(PacienteEntity paciente) throws IllegalOperationException {
        log.info("Proceso de crear un paciente");

        if (paciente.getTelefono().length() !=11){
            throw new IllegalOperationException("11 dígitos necesario");}

        if (! paciente.getTelefono().startsWith("311") || !paciente.getTelefono().startsWith("601")){
            throw new IllegalOperationException("El número de teléfono debe comenzar con 3");}

        return pacienteRepository.save(paciente);

    }

    @Transactional
    public void asociarAcudiente(Long idPaciente, Long idAcudiente) {

        PacienteEntity paciente = pacienteRepository.findById(idPaciente).orElseThrow(() -> new IllegalArgumentException("El paciente no existe"));

        PacienteEntity acudiente = pacienteRepository.findById(idAcudiente).orElseThrow(() -> new IllegalArgumentException("El acudiente no existe"));

        if (paciente.getAcudiente() != null) {
            throw new IllegalArgumentException("El paciente ya tiene un acudiente asignado");
        }

        if (acudiente.getAcudiente() != null) {
            throw new IllegalArgumentException("El acudiente ya tiene un acudiente y no puede ser asignado");
        }

        if (acudiente.getHistoriasClinicas().isEmpty()) {
            throw new IllegalArgumentException("El acudiente debe tener al menos una historia clínica");
        }

        paciente.setAcudiente(acudiente);
        pacienteRepository.save(paciente);
    }


    @Transactional
    public HistoriaClinicaEntity crearHistoriaClinica(Long idPaciente, String diagnostico, String tratamiento) {
        PacienteEntity paciente = pacienteRepository.findById(idPaciente).orElseThrow(() -> new IllegalArgumentException("El paciente no existe"));

        if (paciente.getAcudiente() != null) {
            diagnostico = "HistoriaCompartida-" + diagnostico;
        }

        HistoriaClinicaEntity historiaClinica = new HistoriaClinicaEntity();
        historiaClinica.setDiagnostico(diagnostico);
        historiaClinica.setTratamiento(tratamiento);
        historiaClinica.setFechaDeCreación("05-03-2025");
        historiaClinica.setPaciente(paciente);

        return historiaClinicaRepository.save(historiaClinica);
    }

    
}