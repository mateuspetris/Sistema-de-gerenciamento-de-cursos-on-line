package br.faesa.cursos.conexion;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Db {
    private Db() {

    }
    public static Connection conectar() throws SQLException {
        String url = System.getenv().getOrDefault("DB_URL", "jdbc:oracle:thin:@//localhost:1521/FREEPDB1");
        String user = System.getenv().getOrDefault("DB_USER", "curso");
        String pass = System.getenv().getOrDefault("DB_PASS", "oracle");
        return DriverManager.getConnection(url, user, pass);
    }

    public static void transacao(String... comandos) throws SQLException {
    try (Connection c = conectar()) {
        c.setAutoCommit(false);
        try (Statement st = c.createStatement()) {
            for (String sql : comandos) {
                st.executeUpdate(sql);
            }
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }
    }
}
    public static void executar(String sql) throws SQLException {
        transacao(sql);
    }

    public static int contar(String sql) throws SQLException {
        try (Connection c = conectar(); Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
    public static int proximoId(String sequence) throws SQLException {
        return contar("SELECT " + sequence + ".NEXTVAL FROM dual");
    }

    public static String str(String s) { return "'" + s.replace("'",
            "''") + "'"; }
    public static String num(BigDecimal n) { return n.toPlainString(); }

    public static String data(LocalDate d) { return "TO_DATE('" + d + "', 'YYYY-MM-DD')"; }

        public static void imprimir(String sql) throws SQLException {
            try (Connection c = conectar(); Statement st = c.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                ResultSetMetaData md = rs.getMetaData();
                int n = md.getColumnCount();
                String[] cab = new String[n];
                int[] larg = new int[n];
                for (int i = 0; i < n; i++) {
                    cab[i] = md.getColumnLabel(i + 1).toUpperCase();
                    larg[i] = cab[i].length();
                }
                List<String[]> linhas = new ArrayList<>();
                while (rs.next()) {
                    String[] l = new String[n];
                    for (int i = 0; i < n; i++) {
                        String v = rs.getString(i + 1);
                        l[i] = (v == null) ? "-" : v;
                        larg[i] = Math.max(larg[i], l[i].length());
                    }
                    linhas.add(l);
                }
                if (linhas.isEmpty()) {
                    System.out.println(" (nenhum registro encontrado)");
                    return;
                }
                int total = 0;
                for (int w : larg) total += w + 3;
                imprimirLinha(cab, larg);
                System.out.println(" " + "-".repeat(total));
                for (String[] l : linhas) imprimirLinha(l, larg);
            }
        }

        private static void imprimirLinha(String[] v, int[] larg) {
            StringBuilder sb = new StringBuilder(" ");
            for (int i = 0; i < v.length; i++) {
                sb.append(String.format("%-" + larg[i] + "s", v[i]));
                if (i < v.length - 1) sb.append(" | ");
            }
            System.out.println(sb);
        }
    }
