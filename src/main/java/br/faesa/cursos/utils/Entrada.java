package br.faesa.cursos.utils;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;
public final class Entrada {
    private static final Scanner IN = new Scanner(System.in);
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STR
                    ICT);
    private Entrada() { }
    public static String texto(String msg) {
        while (true) {
            System.out.print(msg);
            String s = IN.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println(" Campo obrigatório.");
        }
    }
    public static int inteiro(String msg) {
        while (true) {
            try {
                return Integer.parseInt(texto(msg));
            } catch (NumberFormatException e) {
                System.out.println(" Digite um número inteiro.");
            }
        }
    }
    public static BigDecimal decimal(String msg) {
        while (true) {
            try {
                return new BigDecimal(texto(msg).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println(" Digite um valor numérico (ex.:
                        199,90).");
            }
        }
    }
    public static LocalDate data(String msg) {
        while (true) {
            try {
                return LocalDate.parse(texto(msg), FMT);
            } catch (DateTimeParseException e) {
                System.out.println(" Data inválida. Use DD/MM/AAAA.");
            }
        }
    }
    public static boolean confirmar(String msg) {
        while (true) {
            String r = texto(msg + " (S/N): ").toUpperCase();
            if (r.equals("S") || r.equals("SIM")) return true;
            if (r.equals("N") || r.equals("NAO") || r.equals("NÃO")) return
                    false;
            System.out.println(" Responda S ou N.");
        }
    }
}