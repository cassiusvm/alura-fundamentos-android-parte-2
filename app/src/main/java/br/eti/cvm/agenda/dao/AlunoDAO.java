package br.eti.cvm.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.eti.cvm.agenda.model.Aluno;

public class AlunoDAO {

    private static int contadorDeIds = 1;

    private static final List<Aluno> alunos = new ArrayList<>();

    @Nullable
    public Aluno buscaPeloId(int id) {
        Aluno aluno = null;

        for (Aluno a : alunos) {
            if (a.getId() == id) {
                aluno = a;
                break;
            }
        }

        return aluno;
    }

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds++);
        alunos.add(aluno);
    }

    public void atualiza(Aluno aluno) {
        Aluno alunoEncontrado = buscaPeloId(aluno.getId());

        if (alunoEncontrado != null) {
            alunoEncontrado.setNome(aluno.getNome());
            alunoEncontrado.setTelefone(aluno.getTelefone());
            alunoEncontrado.setEmail(aluno.getEmail());
        }
    }

    public void remove(Aluno aluno) {
        Aluno alunoEncontrado = buscaPeloId(aluno.getId());

        if (alunoEncontrado != null) {
            alunos.remove(aluno);
            contadorDeIds--;
        }
    }

    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos);
    }

    public void limpaLista() {
        alunos.clear();
    }
}

