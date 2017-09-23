package br.com.projetoestacioapp.dao;

import android.content.Context;

import br.com.projetoestacioapp.bean.Aluno;
import mobi.stos.podataka_lib.repository.AbstractRepository;

/**
 * Created by Gilberto on 06/05/2016.
 */
public class AlunoDao extends AbstractRepository<Aluno> {

    public AlunoDao(Context context) {
        super(context, Aluno.class);
    }
}
