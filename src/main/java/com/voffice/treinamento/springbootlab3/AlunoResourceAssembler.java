package com.voffice.treinamento.springbootlab3;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class AlunoResourceAssembler extends ResourceAssemblerSupport<Aluno, AlunoResource> {

    public AlunoResourceAssembler() {
        super(Aluno.class, AlunoResource.class);
    }

    @Override
    public AlunoResource toResource(Aluno aluno) {
        return new AlunoResource(aluno, linkTo(AlunoController.class).slash(aluno.getId()).withSelfRel());
    }
}
