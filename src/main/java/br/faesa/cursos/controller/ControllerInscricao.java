package br.faesa.cursos.controller;

import br.faesa.cursos.conexion.Db;
import br.faesa.cursos.model.Inscricao;
import java.sql.SQLException;

public class ControllerInscricao {

    private static final String JUNCAO =
            "FROM inscricoes i JOIN alunos a ON a.id_aluno = i.id_aluno JOIN cursos c ON c.id_curso = i.id_curso ";

    public boolean existe(int id) throws SQLException {
        return Db.contar("SELECT COUNT(1) total FROM inscricoes WHERE id_inscricao = " + id) > 0;
    }

    public boolean existe(int idAluno, int idCurso) throws SQLException {
        return Db.contar("SELECT COUNT(1) total FROM inscricoes WHERE id_aluno = " + idAluno
                + " AND id_curso = " + idCurso) > 0;
    }

    public void inserir(Inscricao i) throws SQLException {
        i.setIdInscricao(Db.proximoId("seq_inscricao"));
        String sql = "INSERT INTO inscricoes (id_inscricao, id_aluno, id_curso, data_inscricao, progresso) VALUES ("
                + i.getIdInscricao() + ", " + i.getAluno().getIdAluno() + ", " + i.getCurso().getIdCurso() + ", "
                + Db.data(i.getDataInscricao()) + ", " + i.getProgresso() + ")";
        Db.executar(sql);
    }

    /** Atualiza apenas data e progresso (aluno e curso definem a identidade da inscrição). */
    public void atualizar(Inscricao i) throws SQLException {
        String sql = "UPDATE inscricoes SET data_inscricao = " + Db.data(i.getDataInscricao())
                + ", progresso = " + i.getProgresso()
                + " WHERE id_inscricao = " + i.getIdInscricao();
        Db.executar(sql);
    }

    public void excluir(int id) throws SQLException {
        Db.executar("DELETE FROM inscricoes WHERE id_inscricao = " + id);
    }

    public void listar() throws SQLException {
        Db.imprimir("SELECT i.id_inscricao AS id, a.nome AS aluno, c.nome AS curso " + JUNCAO + "ORDER BY i.id_inscricao");
    }

    public void exibir(int id) throws SQLException {
        Db.imprimir("SELECT i.id_inscricao AS id, a.nome AS aluno, c.nome AS curso, "
                + "TO_CHAR(i.data_inscricao, 'DD/MM/YYYY') AS data_inscricao, i.progresso || '%' AS progresso "
                + JUNCAO + "WHERE i.id_inscricao = " + id);
    }
}