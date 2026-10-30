package br.faesa.cursos.utils;
public final class Config {
    private Config() { }
    public static final String[] INTEGRANTES = {
            "INTEGRANTE 1", "INTEGRANTE 2", "INTEGRANTE 3", "INTEGRANTE 4",
            "INTEGRANTE 5"
    };
    public static final String[] MENU_PRINCIPAL = {
            "Relatórios", "Inserir registros", "Remover registros", "Atualizar
            registros", "Sair"
    };
    public static final String[] MENU_RELATORIOS = {
            "Inscrições e receita por curso (agrupamento)",
            "Detalhe das inscrições (junção de tabelas)",
            "Voltar ao menu principal"
    };
    public static final String[] MENU_ENTIDADES = {
            "Cursos", "Alunos", "Inscrições", "Voltar ao menu principal"
    };
}
