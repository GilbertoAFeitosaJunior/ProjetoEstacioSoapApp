package br.com.projetoestacioapp.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

import br.com.projetoestacioapp.R;
import br.com.projetoestacioapp.adapter.AlunoAdapter;
import br.com.projetoestacioapp.bean.Aluno;
import br.com.projetoestacioapp.bo.AlunoBo;

import static br.com.projetoestacioapp.R.id.lista;

public class AlunoFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private AlunoBo mAlunoBo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mAlunoBo = new AlunoBo(getActivity());

        new ListAlunosTask().execute();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aluno, container, false);

        mListView = view.findViewById(lista);
        mListView.setOnItemClickListener(this);
        mListView.setEmptyView(view.findViewById(R.id.mensagem_lista_vazia));
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }

    private class ListAlunosTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String SOAP_ACTION = getString(R.string.SOAP_ACTION_ALUNO); //nome da açao
            String METHOD_NAME = getString(R.string.METHOD_NAME_ALUNO);// nome do método a ser envocado
            String NAMESPACE = getString(R.string.NAMESPACE_ALUNO);//NOME DO WEBSERVICE
            String URL = getString(R.string.URL_ALUNO); /// URL DO METODO

            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
                Request.addProperty("page", 0);
                Request.addProperty("query", "");

                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = false;
                soapEnvelope.setOutputSoapObject(Request);
                HttpTransportSE transport = new HttpTransportSE(URL);

                transport.call(SOAP_ACTION, soapEnvelope);

                Vector<SoapObject> vector = (Vector<SoapObject>) soapEnvelope.getResponse();
                mAlunoBo.clean();
                for (int i = 0; i < vector.size(); i++) {
                    SoapObject object = vector.get(i);
                    System.out.println(object.getProperty("nome"));

                    Aluno aluno = new Aluno();
                    aluno.setNome(object.getPropertyAsString("nome"));
                    aluno.setId(Long.parseLong(object.getPropertyAsString("id")));
                    aluno.setEmail(object.getPropertyAsString("email"));
                    aluno.setDdd(Integer.parseInt(object.getPropertyAsString("ddd")));
                    aluno.setCelular(Integer.parseInt(object.getPropertyAsString("celular")));
                    aluno.setCpf(object.getPropertyAsString("cpfMask"));

                    mAlunoBo.insert(aluno);

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("ALUNO ERRO", "ERROR: " + ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mListView.setAdapter(new AlunoAdapter(getActivity(), mAlunoBo.list()));
            super.onPostExecute(aVoid);
        }
    }
}