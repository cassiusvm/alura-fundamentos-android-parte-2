package br.eti.cvm.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.eti.cvm.agenda.R;
import br.eti.cvm.agenda.dao.AlunoDAO;
import br.eti.cvm.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";

    private AlunoDAO dao;

    private List<Aluno> alunos;

    private Aluno aluno;

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_aluno);

        setTitle(TITULO_APPBAR);

        dao = new AlunoDAO();
        alunos = dao.getAlunos();

        InicializaCamposDoFormulario();

        Intent dados = getIntent();

        posicao = (int) dados.getSerializableExtra("posicao");

        if(posicao != -1) {
            aluno = alunos.get(posicao);

            GravaCamposNoFormulario(aluno.getNome(), aluno.getTelefone(), aluno.getEmail());
        }
        else {
            aluno = new Aluno();
        }

        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            atualizaAluno();

            if(posicao == -1)
                dao.salva(aluno);

            finish();
            }
        });
    }

    private void InicializaCamposDoFormulario() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void GravaCamposNoFormulario(String nome, String telefone, String email) {
        campoNome.setText(nome);
        campoTelefone.setText(telefone);
        campoEmail.setText(email);
    }

    private void atualizaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
    }
}
