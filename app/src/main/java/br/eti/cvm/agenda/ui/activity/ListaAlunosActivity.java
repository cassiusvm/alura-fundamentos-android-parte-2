package br.eti.cvm.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.eti.cvm.agenda.R;
import br.eti.cvm.agenda.dao.AlunoDAO;
import br.eti.cvm.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Lista de alunos";

    private AlunoDAO dao;

    private List<Aluno> alunos;

    private Intent chamaFormularioAlunoActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APPBAR);

        executaBotaoNovoAluno();

        dao = new AlunoDAO();

        dao.salva(new Aluno("Aluno 01", "123", "aluno01@escola.br"));
        dao.salva(new Aluno("Aluno 02", "456", "aluno02@escola.br"));
        alunos = dao.getAlunos();

        chamaFormularioAlunoActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
    }

    private void executaBotaoNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaFormularioAlunoActivity.putExtra("posicao", -1);
                startActivity(chamaFormularioAlunoActivity);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        alunos = dao.getAlunos();

        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        exibeListaAlunos(listaDeAlunos);

        exibeMenuContexto(listaDeAlunos);
    }

    private void exibeListaAlunos(ListView listView) {
        listView.setAdapter(new ArrayAdapter<Aluno>(this,
            android.R.layout.simple_list_item_1, alunos));
    }

    private void exibeMenuContexto(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = alunos.get(posicao);
                chamaFormularioAlunoActivity.putExtra("posicao", posicao);
                startActivity(chamaFormularioAlunoActivity);
            }
        });
    }

}
