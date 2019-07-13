package br.eti.cvm.agenda.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.eti.cvm.agenda.R;
import br.eti.cvm.agenda.dao.AlunoDAO;
import br.eti.cvm.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";

    private AlunoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_aluno);

        setTitle(TITULO_APPBAR);

        dao = new AlunoDAO();

        salvaAluno();
    }

    private void salvaAluno() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.salva(criaAluno());

                finish();
            }
        });
    }

    private Aluno criaAluno() {
        EditText campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        EditText campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        EditText campoEmail = findViewById(R.id.activity_formulario_aluno_email);

        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}
