package br.faesa.cursos.utils;
public final class Menu {
    private Menu() { }
    public static int exibir(String titulo, String[] opcoes) {
        System.out.println();
        System.out.println("=== " + titulo + " ===");
        for (int i = 0; i < opcoes.length; i++) {
            System.out.printf(" %d - %s%n", i + 1, opcoes[i]);
        }
        while (true) {
            int op = Entrada.inteiro("Escolha uma opção: ");
            if (op >= 1 && op <= opcoes.length) return op;
            System.out.println(" Opção inválida.");
        }
    }
}
