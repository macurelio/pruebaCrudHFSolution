package com.prueba.pruebaCrudHFSolution;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements  PersonService{

    private final PersonRepository repository;

    private final List<Person> buffer = new CopyOnWriteArrayList<>();

@Value("${app.buffer.flush-interval-ms:10000}")
  private long flushIntervalMs;
@Value("${app.buffer.max-buffer-size:100}")
private int maxBufferSize;

private ExecutorService executor;

@PostConstruct
public void init()
{
    executor = Executors.newCachedThreadPool();
}


    @Override
    public PersonDTO save(PersonDTO dto) {
        Callable<PersonDTO> task = () -> {
            try {
                Person p = Person.builder()
                        .rut(dto.getRut())
                        .nombre(dto.getNombre())
                        .apellido(dto.getApellido())
                        .fechaNacimiento(dto.getFechaNacimiento())
                        .direccion(Direccion.builder()
                                .calle(dto.getCalle())
                                .comuna(dto.getComuna())
                                .region(dto.getRegion()).build())
                        .build();
                Person saved = repository.save(p);
                return PersonDTO.fromEntity(saved);
            } catch (DataAccessException dae) {
                buffer.add(Person.builder()
                        .rut(dto.getRut())
                        .nombre(dto.getNombre())
                        .apellido(dto.getApellido())
                        .fechaNacimiento(dto.getFechaNacimiento())
                        .direccion(Direccion.builder()
                                .calle(dto.getCalle())
                                .comuna(dto.getComuna())
                                .region(dto.getRegion()).build()).build());

                return PersonDTO.fromEntity(Person.builder()
                        .rut(dto.getRut())
                        .nombre(dto.getNombre())
                        .apellido(dto.getApellido())
                        .fechaNacimiento(dto.getFechaNacimiento())
                        .direccion(Direccion.builder()
                                .calle(dto.getCalle())
                                .comuna(dto.getComuna())
                                .region(dto.getRegion())
                                .build())
                        .build());
            }
        };
        Future<PersonDTO> future = executor.submit(task);
        try {
            // Timeout de 3s configurable si quieres
            return future.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException te) {
            future.cancel(true);
            throw new RuntimeException("La operación tardó más de 3 segundos");
        } catch (ExecutionException ee) {
            throw new RuntimeException("Error en ejecución: " + ee.getMessage(), ee);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Operación interrumpida", ie);
        }
    }

    @Scheduled(fixedDelayString = "${app.buffer.flush-interval-ms:10000}")
    public void flushBuffer() {
        if (buffer.isEmpty()) return;
        List<Person> toFlush = new ArrayList<>(buffer);
        try {
            repository.saveAll(toFlush);
            buffer.removeAll(toFlush);
        } catch (Exception e) {
            // Si falla, se mantiene en buffer y se intenta después
        }
    }

    @Override
    public Optional<Person> findByRut(String rut) {
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        return List.of();
    }

    @Override
    public Person update(String rut, Person dto) {
        return null;
    }

    @Override
    public void delete(String rut) {

    }
}
