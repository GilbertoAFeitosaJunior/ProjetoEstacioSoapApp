package br.com.projetoestacioapp.bo;

import android.content.Context;

import br.com.projetoestacioapp.bean.Aluno;
import br.com.projetoestacioapp.bean.Usuario;
import br.com.projetoestacioapp.dao.AlunoDao;
import br.com.projetoestacioapp.dao.UsuarioDao;
import mobi.stos.podataka_lib.interfaces.IOperations;
import mobi.stos.podataka_lib.service.AbstractService;


/**
 * Created by Gilberto on 06/05/2016.
 */
public class AlunoBo extends AbstractService<Aluno> {

    private AlunoDao dao;

    public AlunoBo(Context context) {
        this.dao = new AlunoDao(context);
    }

    @Override
    protected IOperations<Aluno> getDao() {
        return dao;
    }

}
