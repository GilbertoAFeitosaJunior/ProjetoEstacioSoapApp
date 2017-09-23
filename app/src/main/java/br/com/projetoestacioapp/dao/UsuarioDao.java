package br.com.projetoestacioapp.dao;

import android.content.Context;

import br.com.projetoestacioapp.bean.Usuario;
import mobi.stos.podataka_lib.repository.AbstractRepository;

/**
 * Created by Gilberto on 06/05/2016.
 */
public class UsuarioDao extends AbstractRepository<Usuario> {

    public UsuarioDao(Context context) {
        super(context, Usuario.class);
    }
}
