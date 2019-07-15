package br.eti.cvm.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.eti.cvm.agenda.R;
import br.eti.cvm.agenda.dao.AlunoDAO;
import br.eti.cvm.agenda.model.Aluno;

import static br.eti.cvm.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";

    private AlunoDAO dao;

    private Aluno aluno;

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_aluno);

        setTitle(TITULO_APPBAR_NOVO_ALUNO);

        dao = new AlunoDAO();

        InicializaCamposDoFormulario();

        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_ALUNO)) {

            int id = (int) dados.getSerializableExtra(CHAVE_ALUNO);

            aluno = dao.buscaPeloId(id);

            setTitle(TITULO_APPBAR_EDITA_ALUNO);

            GravaCamposNoFormulario();
        } else {
            aluno = new Aluno();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar) {
            atualizaOuSalvaAluno();
        }

        return super.onOptionsItemSelected(item);
    }

    private void InicializaCamposDoFormulario() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void GravaCamposNoFormulario() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void atualizaOuSalvaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());

        if(aluno.temIdValido())
            dao.atualiza(aluno);
        else
            dao.salva(aluno);

        finish();
    }
}
