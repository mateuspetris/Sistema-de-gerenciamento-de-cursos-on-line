package br.faesa.cursos.controller;

import br.faesa.cursos.conexion.Db;
import br.faesa.cursos.model.Curso;
import java.sql.*;

public class ControllerCurso {

    public boolean existe(int id) throws SQLException {
        return Db.contar("SELECT COUNT(1) total FROM cursos WHERE id_curso = " + id) > 0;
    }

    public void inserir(Curso c) throws SQLException {
        c.setIdCurso(Db.proximoId("seq_curso"));
        String sql = "INSERT INTO cursos (id_curso, nome, categoria, carga_horaria, valor) VALUES ("
                + c.getIdCurso() + ", " + Db.str(c.getNome()) + ", " + Db.str(c.getCategoria()) + ", "
                + c.getCargaHoraria() + ", " + Db.num(c.getValor()) + ")";
        Db.executar(sql);
    }

    public void atualizar(Curso c) throws SQLException {
        String sql = "UPDATE cursos SET nome = " + Db.str(c.getNome())
                + ", categoria = " + Db.str(c.getCategoria())
                + ", carga_horaria = " + c.getCargaHoraria()
                + ", valor = " + Db.num(c.getValor())
                + " WHERE id_curso = " + c.getIdCurso();
        Db.executar(sql);
    }

    /** Quantas inscrições (tabela filha) referenciam este curso. */
    public int totalInscricoes(int id) throws SQLException {
        return Db.contar("SELECT COUNT(1) total FROM inscricoes WHERE id_curso = " + id);
    }

    public void excluir(int id, boolean comInscricoes) throws SQLException {
        String delFilhas = "DELETE FROM inscricoes WHERE id_curso = " + id;
        String delCurso  = "DELETE FROM cursos WHERE id_curso = " + id;
        if (comInscricoes) {
            Db.transacao(delFilhas, delCurso);
        } else {
            Db.executar(delCurso);
        }
    }

    public Curso buscar(int id) throws SQLException {
        String sql = "SELECT nome, categoria, carga_horaria, valor FROM cursos WHERE id_curso = " + id;
        try (Connection con = Db.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (!rs.next()) return null;
            return new Curso(id, rs.getString("nome"), rs.getString("categoria"),
                    rs.getInt("carga_horaria"), rs.getBigDecimal("valor"));
        }
    }

    public void listar() throws SQLException {
        Db.imprimir("SELECT id_curso AS id, nome, categoria FROM cursos ORDER BY id_curso");
    }

    public void exibir(int id) throws SQLException {
        Db.imprimir("SELECT id_curso AS id, nome, categoria, carga_horaria AS horas, valor FROM cursos WHERE id_curso = " + id);
    }
}