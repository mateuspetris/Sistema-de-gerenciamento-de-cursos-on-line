package br.faesa.cursos.controller;

import br.faesa.cursos.conexion.Db;
import br.faesa.cursos.model.Aluno;
import java.sql.*;

public class ControllerAluno {

    public boolean existe(int id) throws SQLException {
        return Db.contar("SELECT COUNT(1) total FROM alunos WHERE id_aluno = " + id) > 0;
    }

    /** Verifica e-mail duplicado (passe idIgnorar = 0 ao inserir). */
    public boolean emailExiste(String email, int idIgnorar) throws SQLException {
        return Db.contar("SELECT COUNT(1) total FROM alunos WHERE LOWER(email) = LOWER(" + Db.str(email)
                + ") AND id_aluno <> " + idIgnorar) > 0;
    }

    public void inserir(Aluno a) throws SQLException {
        a.setIdAluno(Db.proximoId("seq_aluno"));
        String sql = "INSERT INTO alunos (id_aluno, nome, email, data_nascimento) VALUES ("
                + a.getIdAluno() + ", " + Db.str(a.getNome()) + ", " + Db.str(a.getEmail()) + ", "
                + Db.data(a.getDataNascimento()) + ")";
        Db.executar(sql);
    }

    public void atualizar(Aluno a) throws SQLException {
        String sql = "UPDATE alunos SET nome = " + Db.str(a.getNome())
                + ", email = " + Db.str(a.getEmail())
                + ", data_nascimento = " + Db.data(a.getDataNascimento())
                + " WHERE id_aluno = " + a.getIdAluno();
        Db.executar(sql);
    }

    public int totalInscricoes(int id) throws SQLException {
        return Db.contar("SELECT COUNT(1) total FROM inscricoes WHERE id_aluno = " + id);
    }

    public void excluir(int id, boolean comInscricoes) throws SQLException {
        String delFilhas = "DELETE FROM inscricoes WHERE id_aluno = " + id;
        String delAluno  = "DELETE FROM alunos WHERE id_aluno = " + id;
        if (comInscricoes) {
            Db.transacao(delFilhas, delAluno);
        } else {
            Db.executar(delAluno);
        }
    }

    public Aluno buscar(int id) throws SQLException {
        String sql = "SELECT nome, email, data_nascimento FROM alunos WHERE id_aluno = " + id;
        try (Connection con = Db.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (!rs.next()) return null;
            return new Aluno(id, rs.getString("nome"), rs.getString("email"),
                    rs.getDate("data_nascimento").toLocalDate());
        }
    }

    public void listar() throws SQLException {
        Db.imprimir("SELECT id_aluno AS id, nome, email FROM alunos ORDER BY id_aluno");
    }

    public void exibir(int id) throws SQLException {
        Db.imprimir("SELECT id_aluno AS id, nome, email, TO_CHAR(data_nascimento, 'DD/MM/YYYY') AS nascimento "
                + "FROM alunos WHERE id_aluno = " + id);
    }
}