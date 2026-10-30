package br.faesa.cursos.reports;
import br.faesa.cursos.conexion.Db;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
public final class Relatorios {
    private Relatorios() { }
    public static void inscricoesPorCurso() throws SQLException {
        executar("relatorio_inscricoes_por_curso.sql", "INSCRICOES E RECEITA
                POR CURSO");
    }
    public static void detalheInscricoes() throws SQLException {
        executar("relatorio_detalhe_inscricoes.sql", "DETALHE DAS
                INSCRICOES");
    }
    private static void executar(String arquivo, String titulo) throws
            SQLException {
        System.out.println();
        System.out.println("--- RELATORIO: " + titulo + " ---");
        try (InputStream in = Relatorios.class.getResourceAsStream("/sql/" +
                arquivo)) {
            if (in == null) throw new SQLException("Arquivo SQL não
                    encontrado: " + arquivo);
            String sql = new String(in.readAllBytes(),
                    StandardCharsets.UTF_8).trim();
            if (sql.endsWith(";")) sql = sql.substring(0, sql.length() - 1);
// JDBC não aceita ';'
            Db.imprimir(sql);
        } catch (IOException e) {
            throw new SQLException("Erro ao ler o relatório", e);
        }
    }
}