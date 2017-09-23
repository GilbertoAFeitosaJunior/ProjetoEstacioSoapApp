package br.com.projetoestacioapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.projetoestacioapp.bean.Usuario;
import br.com.projetoestacioapp.bo.UsuarioBo;
import br.com.projetoestacioapp.fragment.AlunoFragment;
import br.com.projetoestacioapp.fragment.MeuPerfilFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.app.ActionBar actionBar;
    private UsuarioBo usuarioBo;
    private View headerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        exibirViewPerfil();
        navigationView.setItemIconTintList(null);


        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AlunoFragment()).commit();
        actionBar.setTitle(getString(R.string.alunos));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        switch (id) {
            case R.id.nav_perfil:
                fragment = new MeuPerfilFragment();
                actionBar.setTitle(getString(R.string.perfil));
                break;
            case R.id.nav_aluno:
                fragment = new AlunoFragment();
                actionBar.setTitle(getString(R.string.alunos));
                break;
            case R.id.nav_share:
                Toast.makeText(this, getString(R.string.em_desenvolvimento), Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, getString(R.string.em_desenvolvimento), Toast.LENGTH_LONG).show();
                break;
        }


        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void exibirViewPerfil() {
        usuarioBo = new UsuarioBo(this);
        Usuario usuario = usuarioBo.get(null, null);
        TextView nomePerfil = (TextView) headerLayout.findViewById(R.id.textViewPerfil);
        TextView email = (TextView) headerLayout.findViewById(R.id.textViewEmail);
        nomePerfil.setText(usuario.getNome());
        email.setText(usuario.getLogin());

    }
}
