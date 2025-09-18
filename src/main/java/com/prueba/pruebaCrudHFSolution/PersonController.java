package com.prueba.pruebaCrudHFSolution;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

 @PostMapping
 public ResponseEntity<PersonDTO> create(@Validated @RequestBody PersonDTO dto) {
     PersonDTO saved = service.save(dto);
     return ResponseEntity.status(HttpStatus.CREATED).body(saved);
 }

 @GetMapping
 public ResponseEntity<List<PersonDTO>> findAll() {
     return ResponseEntity.ok(service.findAll());
 }

 @GetMapping("/{rut}")
    public ResponseEntity<PersonDTO> getByRut(@PathVariable String rut){
     return service.findByRut(rut)
             .map(ResponseEntity::ok)
             .orElseThrow(() -> new PersonNotFoundException(rut));
 }


 @PutMapping("/{rut}")
    public ResponseEntity<PersonDTO> update(@PathVariable String rut, @Validated @RequestBody PersonDTO dto){
     PersonDTO updated = service.update(rut, dto);
     return ResponseEntity.ok(updated);
 }

 @DeleteMapping("/{rut}")
  public ResponseEntity<void> delete(@PathVariable String rut){
     service.delete(rut);
     return ResponseEntity.ok().build();
 }

}
