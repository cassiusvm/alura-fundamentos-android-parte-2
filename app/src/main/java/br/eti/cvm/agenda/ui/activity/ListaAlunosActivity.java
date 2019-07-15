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

import br.eti.cvm.agenda.R;
import br.eti.cvm.agenda.dao.AlunoDAO;
import br.eti.cvm.agenda.model.Aluno;

import static br.eti.cvm.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Lista de alunos";

    private AlunoDAO dao;
    private ArrayAdapter<Aluno> adapterListaAlunos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APPBAR);

        dao = new AlunoDAO();

        dao.salva(new Aluno("Aluno 01", "123", "aluno01@escola.br"));
        dao.salva(new Aluno("Aluno 02", "456", "aluno02@escola.br"));

        configuraBotaoNovoAluno();

        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        configuraAdapter(listaDeAlunos);

        configuraNovoOuEditaAluno(listaDeAlunos);

        configuraExclusaoAluno(listaDeAlunos);
    }

    private void configuraBotaoNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(abreFormularioModoInsereAluno());
            }
        });
    }

    private Intent abreFormularioModoInsereAluno() {
        return new Intent(this, FormularioAlunoActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapterListaAlunos.clear();
        adapterListaAlunos.addAll(dao.getAlunos());
    }

    private void configuraExclusaoAluno(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                dao.remove(alunoEscolhido);
                adapterListaAlunos.remove(alunoEscolhido);
                return true;
            }
        });
    }

    private void configuraAdapter(ListView listView) {
        adapterListaAlunos = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        listView.setAdapter(adapterListaAlunos);
    }

    private void configuraNovoOuEditaAluno(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaAluno(aluno);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        intent.putExtra(CHAVE_ALUNO, aluno.getId());
        startActivity(intent);
    }

}
