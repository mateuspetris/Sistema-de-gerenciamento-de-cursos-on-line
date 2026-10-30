package br.faesa.cursos.utils;
import br.faesa.cursos.conexion.Db;
import java.sql.SQLException;
public final class SplashScreen {
    private static final int L = 46;
    private SplashScreen() { }
    public static void exibir() throws SQLException {
        int cursos = Db.contar("SELECT COUNT(1) total_cursos FROM cursos");
        int alunos = Db.contar("SELECT COUNT(1) total_alunos FROM alunos");
        int inscricoes = Db.contar("SELECT COUNT(1) total_inscricoes FROM inscricoes");
                System.out.println("#".repeat(L + 4));
        linha("");
        linha(centro("PLATAFORMA DE CURSOS ONLINE"));
        linha("");
        linha("TOTAL DE REGISTROS EXISTENTES");
        linha(String.format(" 1 - CURSOS: %6d", cursos));
        linha(String.format(" 2 - ALUNOS: %6d", alunos));
        linha(String.format(" 3 - INSCRICOES: %6d", inscricoes));
        linha("");
        linha("CRIADO POR:");
        for (String nome : Config.INTEGRANTES) {
            linha(" " + nome);
        }
        linha("");
        linha("DISCIPLINA: BANCO DE DADOS");
        linha(" 2026/2");
        linha("PROFESSOR: HOWARD ROATTI");
        System.out.println("#".repeat(L + 4));
    }
    private static void linha(String texto) {
        System.out.printf("# %-" + L + "s #%n", texto);
    }
    private static String centro(String texto) {
        return " ".repeat(Math.max(0, (L - texto.length()) / 2)) + texto;
    }
}
