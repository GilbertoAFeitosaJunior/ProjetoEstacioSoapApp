package br.com.projetoestacioapp.bo;

import android.content.Context;

import br.com.projetoestacioapp.bean.Usuario;
import br.com.projetoestacioapp.dao.UsuarioDao;
import mobi.stos.podataka_lib.interfaces.IOperations;
import mobi.stos.podataka_lib.service.AbstractService;


/**
 * Created by Gilberto on 06/05/2016.
 */
public class UsuarioBo extends AbstractService<Usuario> {

    private UsuarioDao dao;

    public UsuarioBo(Context context) {
        this.dao = new UsuarioDao(context);
    }

    @Override
    protected IOperations<Usuario> getDao() {
        return dao;
    }

}
