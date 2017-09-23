package br.com.projetoestacioapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.projetoestacioapp.R;
import br.com.projetoestacioapp.util.Util;
import br.com.projetoestacioapp.bean.Aluno;


/**
 * Created by feito on 21/09/2017.
 */

public class AlunoAdapter extends ArrayAdapter<Aluno> {

    public AlunoAdapter(Context context, List<Aluno> arquivos) {
        super(context, R.layout.adapter_aluno, arquivos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_aluno, parent, false);

            holder = new ViewHolder();
            holder.tvNome = (TextView) convertView.findViewById(R.id.tvNome);
            holder.tvCpf = (TextView) convertView.findViewById(R.id.tvCpf);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Aluno aluno = getItem(position);

        holder.tvNome.setText(aluno.getNome());
        holder.tvCpf.setText(aluno.getCpf());


        return convertView;
    }

    private static class ViewHolder {
        TextView tvNome;
        TextView tvCpf;

    }
}
