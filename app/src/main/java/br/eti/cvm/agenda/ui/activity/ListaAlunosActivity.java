package br.eti.cvm.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.eti.cvm.agenda.R;
import br.eti.cvm.agenda.dao.AlunoDAO;
import br.eti.cvm.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Lista de alunos";

    private AlunoDAO dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APPBAR);

        iniciaFormularioNovoAluno();

        dao = new AlunoDAO();
    }

    private void iniciaFormularioNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        exibeListaAlunos();
    }

    private void exibeListaAlunos() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaDeAlunos.setAdapter(new ArrayAdapter<Aluno>(this,
                android.R.layout.simple_list_item_1, dao.todos()));
    }
}
