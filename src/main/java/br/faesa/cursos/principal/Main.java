
package br.faesa.cursos.principal;

import br.faesa.cursos.controller.ControllerAluno;
import br.faesa.cursos.controller.ControllerCurso;
import br.faesa.cursos.controller.ControllerInscricao;
import br.faesa.cursos.model.Aluno;
import br.faesa.cursos.model.Curso;
import br.faesa.cursos.model.Inscricao;
import br.faesa.cursos.reports.Relatorios;
import br.faesa.cursos.utils.Config;
import br.faesa.cursos.utils.Entrada;
import br.faesa.cursos.utils.Menu;
import br.faesa.cursos.utils.SplashScreen;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    private static final ControllerCurso cursos = new ControllerCurso();
    private static final ControllerAluno alunos = new ControllerAluno();
    private static final ControllerInscricao inscricoes = new ControllerInscricao();

    public static void main(String[] args) {
        try {
            SplashScreen.exibir();
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar ao banco: " + e.getMessage());
            System.out.println("Verifique DB_URL, DB_USER, DB_PASS e se as tabelas foram criadas.");
            return;
        }

        int op;
        do {
            op = Menu.exibir("MENU PRINCIPAL", Config.MENU_PRINCIPAL);
            try {
                switch (op) {
                    case 1 -> relatorios();
                    case 2 -> inserir();
                    case 3 -> remover();
                    case 4 -> atualizar();
                    default -> {
                        System.out.println();
                        System.out.println("Encerrando o sistema. Até logo!");
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erro de banco de dados: " + e.getMessage());
            }
        } while (op != 5);
    }

    // ------------------------------------------------------------ RELATÓRIOS
    private static void relatorios() throws SQLException {
        int op;
        do {
            op = Menu.exibir("RELATÓRIOS", Config.MENU_RELATORIOS);
            if (op == 1) Relatorios.inscricoesPorCurso();
            else if (op == 2) Relatorios.detalheInscricoes();
        } while (op != 3);
    }

    // ------------------------------------------------------------ INSERIR
    private static void inserir() throws SQLException {
        do {
            int ent = Menu.exibir("INSERIR - ESCOLHA A ENTIDADE", Config.MENU_ENTIDADES);
            if (ent == 4) return;

            switch (ent) {
                case 1 -> inserirCurso();
                case 2 -> inserirAluno();
                case 3 -> inserirInscricao();
            }
        } while (Entrada.confirmar("Deseja inserir mais algum registro?"));
    }

    private static void inserirCurso() throws SQLException {
        Curso c = new Curso();
        c.setNome(Entrada.texto("Nome do curso: "));
        c.setCategoria(Entrada.texto("Categoria: "));
        c.setCargaHoraria(Entrada.inteiro("Carga horária (horas): "));
        c.setValor(Entrada.decimal("Valor (R$): "));

        if (c.getCargaHoraria() <= 0 || c.getValor().signum() < 0) {
            System.out.println("Carga horária deve ser maior que 0 e valor não pode ser negativo.");
            return;
        }

        cursos.inserir(c);
        System.out.println("Curso inserido com sucesso: " + c);
    }

    private static void inserirAluno() throws SQLException {
        Aluno a = new Aluno();
        a.setNome(Entrada.texto("Nome do aluno: "));
        a.setEmail(Entrada.texto("E-mail: "));

        if (alunos.emailExiste(a.getEmail(), 0)) {
            System.out.println("Já existe um aluno com este e-mail.");
            return;
        }

        a.setDataNascimento(Entrada.data("Data de nascimento (DD/MM/AAAA): "));
        alunos.inserir(a);
        System.out.println("Aluno inserido com sucesso: " + a);
    }

    private static void inserirInscricao() throws SQLException {
        alunos.listar();
        Aluno a = alunos.buscar(Entrada.inteiro("ID do aluno: "));
        cursos.listar();
        Curso c = cursos.buscar(Entrada.inteiro("ID do curso: "));

        if (a == null || c == null) {
            System.out.println("Aluno ou curso inexistente.");
            return;
        }

        if (inscricoes.existe(a.getIdAluno(), c.getIdCurso())) {
            System.out.println("Este aluno já está inscrito neste curso.");
            return;
        }

        Inscricao i = new Inscricao();
        i.setAluno(a);
        i.setCurso(c);
        i.setDataInscricao(LocalDate.now());
        i.setProgresso(0);
        inscricoes.inserir(i);
        System.out.println("Inscrição realizada com sucesso: " + i);
    }

    // ------------------------------------------------------------ REMOVER
    private static void remover() throws SQLException {
        do {
            int ent = Menu.exibir("REMOVER - ESCOLHA A ENTIDADE", Config.MENU_ENTIDADES);
            if (ent == 4) return;

            boolean continuar = switch (ent) {
                case 1 -> removerCurso();
                case 2 -> removerAluno();
                default -> removerInscricao();
            };

            if (!continuar) return; // usuário não confirmou: volta ao menu principal
        } while (Entrada.confirmar("Deseja excluir mais algum registro?"));
    }

    private static boolean removerCurso() throws SQLException {
        cursos.listar();
        int id = Entrada.inteiro("ID do curso a remover: ");

        if (!cursos.existe(id)) {
            System.out.println("Curso não encontrado.");
            return true;
        }

        if (!Entrada.confirmar("Confirma a remoção do curso " + id + "?"))
            return false;

        boolean comFilhas = false;
        int filhas = cursos.totalInscricoes(id);

        if (filhas > 0) {
            System.out.println("Este curso é referenciado por " + filhas + " inscrição(ões).");

            if (!Entrada.confirmar("Remover também essas inscrições?")) {
                System.out.println("Remoção cancelada: o curso possui inscrições.");
                return true;
            }

            comFilhas = true;
        }

        cursos.excluir(id, comFilhas);
        System.out.println("Curso removido com sucesso.");
        return true;
    }

    private static boolean removerAluno() throws SQLException {
        alunos.listar();
        int id = Entrada.inteiro("ID do aluno a remover: ");

        if (!alunos.existe(id)) {
            System.out.println("Aluno não encontrado.");
            return true;
        }

        if (!Entrada.confirmar("Confirma a remoção do aluno " + id + "?"))
            return false;

        boolean comFilhas = false;
        int filhas = alunos.totalInscricoes(id);

        if (filhas > 0) {
            System.out.println("Este aluno possui " + filhas + " inscrição(ões).");

            if (!Entrada.confirmar("Remover também essas inscrições?")) {
                System.out.println("Remoção cancelada: o aluno possui inscrições.");
                return true;
            }

            comFilhas = true;
        }

        alunos.excluir(id, comFilhas);
        System.out.println("Aluno removido com sucesso.");
        return true;
    }

    private static boolean removerInscricao() throws SQLException {
        inscricoes.listar();
        int id = Entrada.inteiro("ID da inscrição a remover: ");

        if (!inscricoes.existe(id)) {
            System.out.println("Inscrição não encontrada.");
            return true;
        }

        if (!Entrada.confirmar("Confirma a remoção da inscrição " + id + "?"))
            return false;

        inscricoes.excluir(id);
        System.out.println("Inscrição removida com sucesso.");
        return true;
    }

    // ------------------------------------------------------------ ATUALIZAR
    private static void atualizar() throws SQLException {
        do {
            int ent = Menu.exibir("ATUALIZAR - ESCOLHA A ENTIDADE", Config.MENU_ENTIDADES);
            if (ent == 4) return;

            switch (ent) {
                case 1 -> atualizarCurso();
                case 2 -> atualizarAluno();
                case 3 -> atualizarInscricao();
            }
        } while (Entrada.confirmar("Deseja atualizar mais algum registro?"));
    }

    private static void atualizarCurso() throws SQLException {
        cursos.listar();
        int id = Entrada.inteiro("ID do curso a atualizar: ");
        Curso c = cursos.buscar(id);

        if (c == null) {
            System.out.println("Curso não encontrado.");
            return;
        }

        System.out.println("Registro atual: " + c);
        System.out.println("Informe os novos dados:");
        c.setNome(Entrada.texto("Nome do curso: "));
        c.setCategoria(Entrada.texto("Categoria: "));
        c.setCargaHoraria(Entrada.inteiro("Carga horária (horas): "));
        c.setValor(Entrada.decimal("Valor (R$): "));

        if (c.getCargaHoraria() <= 0 || c.getValor().signum() < 0) {
            System.out.println("Carga horária deve ser maior que 0 e valor não pode ser negativo.");
            return;
        }

        cursos.atualizar(c);
        System.out.println("Registro atualizado:");
        cursos.exibir(id);
    }

    private static void atualizarAluno() throws SQLException {
        alunos.listar();
        int id = Entrada.inteiro("ID do aluno a atualizar: ");
        Aluno a = alunos.buscar(id);

        if (a == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.println("Registro atual: " + a);
        System.out.println("Informe os novos dados:");
        a.setNome(Entrada.texto("Nome do aluno: "));
        a.setEmail(Entrada.texto("E-mail: "));

        if (alunos.emailExiste(a.getEmail(), id)) {
            System.out.println("Este e-mail já pertence a outro aluno.");
            return;
        }

        a.setDataNascimento(Entrada.data("Data de nascimento (DD/MM/AAAA): "));
        alunos.atualizar(a);
        System.out.println("Registro atualizado:");
        alunos.exibir(id);
    }

    private static void atualizarInscricao() throws SQLException {
        inscricoes.listar();
        int id = Entrada.inteiro("ID da inscrição a atualizar: ");

        if (!inscricoes.existe(id)) {
            System.out.println("Inscrição não encontrada.");
            return;
        }

        System.out.println("Informe os novos dados:");

        Inscricao i = new Inscricao();
        i.setIdInscricao(id);
        i.setDataInscricao(Entrada.data("Data da inscrição (DD/MM/AAAA): "));

        int progresso = Entrada.inteiro("Progresso (0 a 100): ");

        if (progresso < 0 || progresso > 100) {
            System.out.println("O progresso deve estar entre 0 e 100.");
            return;
        }

        i.setProgresso(progresso);
        inscricoes.atualizar(i);
        System.out.println("Registro atualizado:");
        inscricoes.exibir(id);
    }
}
