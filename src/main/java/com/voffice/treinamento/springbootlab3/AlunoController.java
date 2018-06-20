package com.voffice.treinamento.springbootlab3;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Aluno", tags = "Aluno")
@RequestMapping("/aluno")
public class AlunoController {

    private AlunoResourceAssembler assembler = new AlunoResourceAssembler();

    private List<AlunoResource> alunos = new ArrayList<>();

    public AlunoController() {
        super();
        alunos.add(assembler.toResource(new Aluno(1L, "João Silva", 11444, "joao.silva@gmail.com")));
        alunos.add(assembler.toResource(new Aluno(2L, "Maria Silva", 11445, "maria.silva@gmail.com")));
        alunos.add(assembler.toResource(new Aluno(3L, "José Silva", 11446, "jose.silva@gmail.com")));
    }



    @ApiOperation(value = "View a list of available Alunos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AlunoResource>> listAlunos() {

        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AlunoResource>> createAluno(@RequestBody Aluno aluno) {

        this.alunos.add(assembler.toResource(aluno));
        return new ResponseEntity<>(alunos, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AlunoResource>> updateAluno(@PathVariable("id") Long id,
                                                   @RequestBody Aluno aluno) {

        alunos.stream().map(AlunoResource::getContent).filter(aluno1 -> aluno1.getId() == id).findFirst().ifPresent(aluno1 -> {
            alunos.remove(assembler.toResource(aluno1));
            alunos.add(assembler.toResource(aluno));
        });

        return new ResponseEntity<>(alunos, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AlunoResource>> deleteAluno(@PathVariable("id") Long id) {

        alunos.stream().map(AlunoResource::getContent).filter(aluno1 -> aluno1.getId() == id).findFirst().ifPresent(aluno1 -> {
            alunos.remove(assembler.toResource(aluno1));
        });

        return new ResponseEntity<>(alunos, HttpStatus.CREATED);
    }

}
