# Documentação de Testes – Sistema de Cursos e Inscrições

**Disciplina:** Banco de Dados – 2026/2  
**Professor:** Howard Roatti  
**Data dos testes:** 30/10/2026  
**Ambiente:** aplicação de console (menu interativo) conectada ao banco de dados

## 1. Objetivo

Registrar os testes funcionais realizados sobre a aplicação, verificando que as operações de consulta (relatórios), inserção, remoção e atualização funcionam corretamente no banco de dados.

## 2. Resumo dos testes

| ID | Teste | Resultado |
|----|-------|-----------|
| T01 | Menu principal e navegação até Relatórios | Aprovado |
| T02 | Relatório 1 – Inscrições e receita por curso (agrupamento) | Aprovado |
| T03 | Relatório 2 – Detalhe das inscrições (junção de tabelas) | Aprovado |
| T04 | Inserir registro (Curso) | Aprovado |
| T05 | Remover registro (Curso) | Aprovado |
| T06 | Atualizar registro (Curso) | Aprovado |

## 3. Detalhamento

### T01 – Menu principal e navegação até Relatórios

**Objetivo:** Verificar a exibição do menu principal e a navegação para o submenu de relatórios.

**Passos:**

1. Executar a aplicação.
2. No menu principal, escolher a opção 1 (Relatórios).
3. No submenu, escolher a opção 1 (Inscrições e receita por curso).

**Resultado esperado:** Menu principal com 5 opções (Relatórios, Inserir, Remover, Atualizar, Sair) e submenu de relatórios exibidos corretamente.

**Resultado obtido:** Menus exibidos conforme esperado e navegação funcionando. O programa retorna ao submenu após cada relatório.

**Evidência:**

![T01](evidencias/01_menu_principal_relatorio_agrupamento.jpeg)

**Status:** Aprovado

### T02 – Relatório 1 – Inscrições e receita por curso (agrupamento)

**Objetivo:** Validar a consulta com agrupamento (GROUP BY) que totaliza inscrições, progresso médio e receita por curso.

**Passos:**

1. Menu Relatórios > opção 1.

**Resultado esperado:** Uma linha por curso, incluindo cursos sem inscrição (junção externa), com valores agregados coerentes.

**Resultado obtido:** Relatório exibido com os 4 cursos cadastrados. Os valores foram conferidos manualmente (tabela abaixo).

| Curso | Inscrições | Progresso médio | Receita total | Conferência |
|---|---|---|---|---|
| Java do Zero | 3 | 71,7 | 599,7 | (100+35+80)/3 = 71,67 ; 3 x 199,90 = 599,70 |
| SQL e Oracle | 2 | 30 | 298 | (60+0)/2 = 30 ; 2 x 149,00 = 298 |
| UX Design | 1 | 10 | 129,5 | 1 inscrição, progresso 10 ; 129,50 |
| Python para Dados | 0 | 0 | 0 | Sem inscrições (aparece com zeros) |

**Evidência:**

![T02](evidencias/02_relatorio_agrupamento.jpeg)

**Status:** Aprovado

### T03 – Relatório 2 – Detalhe das inscrições (junção de tabelas)

**Objetivo:** Validar a consulta com junção (JOIN) entre inscrições, alunos e cursos.

**Passos:**

1. Menu Relatórios > opção 2.

**Resultado esperado:** Lista de todas as inscrições com aluno, curso, data e progresso.

**Resultado obtido:** 6 inscrições exibidas com nomes de aluno e curso resolvidos corretamente. Os dados são consistentes com o Relatório 1: Java do Zero (3: Ana, Bruno, Carla), SQL e Oracle (2: Ana, Diego) e UX Design (1: Carla).

**Evidência:**

![T03](evidencias/03_relatorio_juncao.jpeg)

**Status:** Aprovado

### T04 – Inserir registro (Curso)

**Objetivo:** Validar a inclusão de um novo curso pelo menu Inserir.

**Passos:**

1. Menu principal > opção 2 (Inserir registros) > opção 1 (Cursos).
2. Informar: nome = Html e css; categoria = front; carga horária = 80; valor = 1200.
3. Responder N à pergunta de inserir mais registros.

**Resultado esperado:** Curso gravado no banco com novo ID e mensagem de sucesso.

**Resultado obtido:** Curso inserido com sucesso, recebendo id=5 (Html e css, front, 80h, R$ 1200).

**Evidência:**

![T04](evidencias/04_inserir_curso.jpeg)

**Status:** Aprovado

### T05 – Remover registro (Curso)

**Objetivo:** Validar a remoção de um curso pelo menu Remover, com confirmação.

**Passos:**

1. Menu principal > opção 3 (Remover registros) > opção 1 (Cursos).
2. Informar o ID 5 (curso criado no T04) e confirmar com S.

**Resultado esperado:** Curso removido do banco e mensagem de sucesso.

**Resultado obtido:** A listagem exibiu os 5 cursos, a confirmação foi solicitada e o sistema retornou "Curso removido com sucesso".

**Evidência:**

![T05](evidencias/05_remover_curso.jpeg)

**Status:** Aprovado

### T06 – Atualizar registro (Curso)

**Objetivo:** Validar a alteração de dados de um curso existente pelo menu Atualizar.

**Passos:**

1. Menu principal > opção 4 (Atualizar registros) > opção 1 (Cursos).
2. Informar o ID 1 (Java do Zero) e alterar a carga horária de 40 para 45 horas, mantendo os demais campos.
3. Responder N à pergunta de atualizar mais registros.

**Resultado esperado:** Registro atualizado no banco e exibido com os novos valores.

**Resultado obtido:** O registro atual foi exibido (40h) e, após a atualização, o curso 1 aparece com 45 horas; nome, categoria e valor (199,9) permaneceram inalterados.

**Evidência:**

![T06](evidencias/06_atualizar_curso.jpeg)

**Status:** Aprovado

## 4. Conclusão

Todos os 6 testes foram aprovados. As funcionalidades de relatórios (com agrupamento e junção), inserção, remoção e atualização operam corretamente, e os dados exibidos nos relatórios são consistentes entre si e com os cálculos conferidos manualmente.
