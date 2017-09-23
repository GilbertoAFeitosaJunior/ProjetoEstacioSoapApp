package br.com.projetoestacioapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.text.SimpleDateFormat;

import br.com.projetoestacioapp.R;
import br.com.projetoestacioapp.bean.Usuario;
import br.com.projetoestacioapp.bo.UsuarioBo;


public class MeuPerfilFragment extends Fragment {


    private TextView nome_usuario, email_usuario, data_ultimo_acesso;
    private Usuario usuario;
    private UsuarioBo usuarioBO;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meu_perfil, container, false);

        context = container.getContext();


        nome_usuario = view.findViewById(R.id.nome_usuario);
        email_usuario = view.findViewById(R.id.email_usuario);
        data_ultimo_acesso = view.findViewById(R.id.data_ultimo_acesso);


        usuarioBO = new UsuarioBo(context);
        usuario = usuarioBO.get(null, null);

        nome_usuario.setText(usuario.getNome());

        String emailApresentacao = "<b>" + getString(R.string.email) + "</b>" + " " + usuario.getLogin();
        email_usuario.setText(Html.fromHtml(emailApresentacao));

        String ultimoAcessoApresentacao = "<b>" + getString(R.string.ultimo_acesso) + "</b>" + " " + new SimpleDateFormat("dd/MM/yyyy").format(usuario.getUltimoAcesso());
        data_ultimo_acesso.setText(Html.fromHtml(ultimoAcessoApresentacao));

        return view;
    }
}