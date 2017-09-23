package br.com.projetoestacioapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.SimpleDateFormat;

import br.com.projetoestacioapp.util.Constants;
import br.com.projetoestacioapp.util.Util;
import br.com.projetoestacioapp.bean.Usuario;
import br.com.projetoestacioapp.bo.UsuarioBo;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private ProgressDialog progressDialog;
    private Usuario usuario;
    private Button btnFazerLogin;
    private String login, password;
    private String TAG = "Response";
    private boolean autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        btnFazerLogin = (Button) findViewById(R.id.btnFazerLogin);

        btnFazerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(editEmail.getText().toString().equals("") && editSenha.getText().toString().equals(""))) {
                    login = editEmail.getText().toString();
                    password = editSenha.getText().toString();
                    LoginTask loginTask = new LoginTask();
                    loginTask.execute();
                }else{
                    Toast.makeText(LoginActivity.this, getString(R.string.erro_campos_em_branco), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private class LoginTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage(getText(R.string.carregando));
            progressDialog.setIndeterminate(true);
            progressDialog.show();


        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.i(TAG, "doInBackground");
            fazerLogin();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if(autenticacao){
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                LoginActivity.this.finish();
            }

        }
    }


    private void fazerLogin() {
        String SOAP_ACTION = getString(R.string.SOAP_ACTION_LOGIN); //nome da açao
        String METHOD_NAME = getString(R.string.METHOD_NAME_LOGIN) ;// nome do método a ser envocado
        String NAMESPACE = getString(R.string.NAMESPACE_LOGIN) ;//NOME DO WEBSERVICE
        String URL = getString(R.string.URL_LOGIN); /// URL DO METODO

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("login", login);
            Request.addProperty("senha", Util.md5(password));

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = false;
            soapEnvelope.setOutputSoapObject(Request);
            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject response = (SoapObject) soapEnvelope.getResponse();


            usuario = new Usuario();
            UsuarioBo usuarioBo = new UsuarioBo(LoginActivity.this);

            usuario.setId(response.getPropertyAsString("id"));
            usuario.setLogin(response.getPropertyAsString("login"));
            usuario.setNome(response.getPropertyAsString("nome"));
            usuario.setSenha(response.getPropertyAsString("senha"));

            usuario.setUltimoAcesso(new SimpleDateFormat("yyyy-MM-dd").parse(response.getPropertyAsString("ultimoAcesso")));


            usuarioBo.clean();
            usuarioBo.insert(usuario);
            SharedPreferences.Editor editor = getSharedPreferences(Constants.APP, Context.MODE_PRIVATE).edit();
            editor.putString(Constants.USUARIO_LOGADO, "LOGADO");
            editor.apply();
            autenticacao = true;

        } catch (Exception ex) {
            Log.e(TAG, "ERROR: " + ex.getMessage());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    erroLogin();
                }
            });
        }

    }

    public void erroLogin() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.title_login_erro));
        alertDialog.setMessage(getString(R.string.msg_erro_login));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                autenticacao = false;
            }
        });
        alertDialog.show();
    }
}
