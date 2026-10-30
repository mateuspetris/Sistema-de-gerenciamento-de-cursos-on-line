package br.faesa.cursos.utils;
public final class Config {
    private Config() { }
    public static final String[] INTEGRANTES = {
            "João Vitor de Jesus Rocha", "Lucas de Freitas Pereira", "Beatriz de Souza Oliveira", "Thainara Santos de Souza",
            "Letícia Ventura Brandão", "Mateus de Souza Petris"
    };
    public static final String[] MENU_PRINCIPAL = {
            "Relatórios", "Inserir registros", "Remover registros", "Atualizar registros","Sair"
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
