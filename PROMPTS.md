## Prompt 1

- Usuário: João Vitor de Jesus Rocha
- IA: ChatGPT
- Prompt: "Preciso escolher um tema para um trabalho de Banco de Dados que exige no mínimo 2 tabelas com relacionamento, CRUD em console e dois relatórios (um com GROUP BY e um com JOIN). Estou pensando em um Sistema de Gerenciamento de Cursos Online com as entidades Curso, Aluno e Inscrição. Isso atende aos requisitos? Quais seriam os atributos de cada entidade e os relacionamentos entre elas?"
- Resposta: A IA confirmou que o tema atende, explicou que a Inscrição resolve o N:N entre Aluno e Curso, e sugeriu atributos para as três entidades. Indicou que Inscrição teria duas FKs (id_aluno e id_curso).
- Como foi aplicado: Usamos a sugestão de entidades e atributos. Ajustamos os nomes dos campos para o padrão do projeto e adicionamos a constraint CHECK (progresso BETWEEN 0 AND 100), que a IA não tinha sugerido.

---

## Prompt 2

- Usuário: Lucas de Freitas Pereira
- IA: Claude
- Prompt: "Estou modelando um banco relacional para um Sistema de Cursos Online com as tabelas cursos, alunos e inscricoes. A tabela inscricoes tem id_aluno e id_curso como FKs. Me ajude a desenhar o diagrama relacional no formato Mermaid (erDiagram), incluindo cardinalidades, chaves primárias e estrangeiras, e indique quais campos devem ser NOT NULL e UNIQUE."
- Resposta: A IA gerou um código Mermaid com erDiagram, mostrando ALUNOS 1:N INSCRICOES e CURSOS 1:N INSCRICOES, e sugeriu email como UNIQUE e os demais campos como NOT NULL. Também sugeriu CHECK para carga horária e valor.
- Como foi aplicado: Usamos o código Mermaid como base do arquivo docs/modelo.mmd. Conferimos as cardinalidades, exportamos o diagrama para PDF e adicionamos as constraints ck_curso_carga, ck_curso_valor e ck_insc_prog.

---

## Prompt 3

- Usuário: Beatriz de Souza Oliveira
- IA: Gemini
- Prompt: "Gere o script SQL para Oracle Database criando as tabelas cursos, alunos e inscricoes do meu projeto. Preciso de SEQUENCE para cada tabela (seq_curso, seq_aluno, seq_inscricao), chaves primárias nomeadas, chave estrangeira de inscricoes para alunos e cursos, constraint UNIQUE no e-mail do aluno, DEFAULT SYSDATE na data de inscrição, DEFAULT 0 no progresso e CHECK para progresso entre 0 e 100. Use VARCHAR2 e NUMBER."
- Resposta: A IA gerou o script completo com CREATE SEQUENCE, CREATE TABLE e todas as constraints nomeadas (pk_cursos, pk_alunos, pk_inscricoes, fk_insc_aluno, fk_insc_curso, uq_aluno_email, ck_curso_carga, ck_curso_valor, ck_insc_prog).
- Como foi aplicado: Salvamos em sql/01_criar_tabelas.sql e executamos no Oracle da VM. Testamos cada constraint inserindo valores inválidos para ver o banco rejeitar. Não precisamos mudar a estrutura.

---

## Prompt 4

- Usuário: Thainara Santos de Souza
- IA: DeepSeek
- Prompt: "Crie um script SQL para Oracle com dados de exemplo para as tabelas cursos, alunos e inscricoes. Preciso de 4 cursos, 4 alunos e 6 inscrições, usando as sequences seq_curso, seq_aluno e seq_inscricao para os IDs, TO_DATE para as datas e COMMIT no final. Inclua cursos com categorias variadas e inscrições com progressos diferentes, incluindo uma com progresso 0 e uma com 100."
- Resposta: A IA gerou os INSERTs com os IDs via seq_*.NEXTVAL, datas com TO_DATE e 6 inscrições com progressos variados.
- Como foi aplicado: Salvamos em sql/02_dados_exemplo.sql. Trocamos os nomes de cursos e e-mails por dados fictícios nossos e validamos que as 6 inscrições respeitavam as FKs.

---

## Prompt 5

- Usuário: Mateus de Souza Petris
- IA: ChatGPT
- Prompt: "Preciso de uma classe Java chamada Db que concentre a conexão JDBC com Oracle. Requisitos: ler DB_URL, DB_USER e DB_PASS de variáveis de ambiente com getOrDefault; método conectar() que devolve Connection; método transacao(String... comandos) que executa vários comandos com setAutoCommit(false), commit e rollback em caso de erro; método executar() para um comando único; método contar() que executa um SELECT COUNT e devolve int; método proximoId() que usa sequence.NEXTVAL FROM dual; e métodos str(), num() e data() para formatar valores concatenados no SQL. Use try-with-resources."
- Resposta: A IA gerou a classe Db com todos esses métodos, usando try-with-resources, rollback no catch, escape de aspas no str(), toPlainString() no num() e TO_DATE no data().
- Como foi aplicado: Usamos como base do Db.java. Adaptamos a URL padrão para o ambiente da VM (FREEPDB1), trocamos o usuário padrão para curso e adicionamos o método imprimir(), que a IA não tinha gerado, para formatar os resultados em tabela no console.

---

## Prompt 6

- Usuário: Letícia Ventura Brandão
- IA: Claude
- Prompt: "Estou fazendo um projeto Java de CRUD em console. Tenho as entidades Curso, Aluno e Inscricao. O edital exige que, quando um objeto tem associação com outro, a classe tenha como atributo uma instância do outro objeto, não apenas o ID. Como ficaria a classe Inscricao guardando objetos Aluno e Curso? Preciso de atributos privados, getters, setters e toString(). A classe Curso usa BigDecimal para valor e a Aluno usa LocalDate para data de nascimento."
- Resposta: A IA gerou as três classes com atributos privados, construtores, getters/setters e toString(). Na Inscricao usou private Aluno aluno e private Curso curso, chamando aluno.getNome() e curso.getNome() no toString().
- Como foi aplicado: Salvamos em model/. Mantivemos a associação por instância e ajustamos o toString() para o formato usado no console. Também adicionamos o construtor vazio em todas as classes para permitir instanciação no fluxo de inserção.

---

## Prompt 7

- Usuário: João Vitor de Jesus Rocha
- IA: Gemini
- Prompt: "Gere uma classe ControllerCurso em Java para um CRUD de cursos no Oracle. Métodos: existe(int id), inserir(Curso c) usando Db.proximoId('seq_curso') e Db.executar, atualizar(Curso c), totalInscricoes(int id) que conta as inscrições filhas, excluir(int id, boolean comInscricoes) que apaga as filhas numa transação se comInscricoes for true ou só o curso caso contrário, buscar(int id) que devolve Curso, listar() com id e nome, e exibir(int id). Use concatenação de strings (sem PreparedStatement) e Db.str/Db.num."
- Resposta: A IA gerou a classe com todos os métodos, incluindo excluir(id, comInscricoes) que usa Db.transacao(delFilhas, delCurso) quando comInscricoes = true e Db.executar(delCurso) caso contrário.
- Como foi aplicado: Usamos como base do ControllerCurso.java. Ajustamos o buscar() para ler os campos corretamente do ResultSet e testamos a exclusão com e sem filhas no Oracle. O listar() foi ajustado para exibir id_curso AS id, nome, categoria.

---

## Prompt 8

- Usuário: Lucas de Freitas Pereira
- IA: DeepSeek
- Prompt: "Preciso de uma classe ControllerAluno em Java para Oracle com os mesmos métodos do ControllerCurso, mas com uma validação extra: um método emailExiste(String email, int idIgnorar) que verifica se o e-mail já está cadastrado, ignorando um ID específico (para o caso de atualização não conflitar com o próprio registro). Use LOWER() para comparar sem diferenciar maiúsculas/minúsculas."
- Resposta: A IA gerou a classe com emailExiste usando LOWER(email) = LOWER('...') AND id_aluno <> idIgnorar, além dos outros métodos.
- Como foi aplicado: Salvamos em ControllerAluno.java. Testamos o emailExiste na inserção (idIgnorar = 0) e na atualização (com o próprio ID), confirmando que o aluno não conflita consigo mesmo. O exibir() foi ajustado para formatar a data com TO_CHAR.

---

## Prompt 9

- Usuário: Beatriz de Souza Oliveira
- IA: ChatGPT
- Prompt: "Gere uma classe ControllerInscricao em Java para Oracle. A tabela inscricoes tem id_inscricao (PK), id_aluno e id_curso (FKs), data_inscricao e progresso. Preciso de: existe(int id), existe(int idAluno, int idCurso) para evitar duplicidade, inserir(Inscricao i) que usa i.getAluno().getIdAluno() e i.getCurso().getIdCurso(), atualizar(Inscricao i) que só altera data e progresso, excluir(int id), listar() e exibir(int id). Para listar e exibir, use JOIN com alunos e cursos e uma constante JUNCAO para evitar repetição."
- Resposta: A IA gerou a classe com a constante JUNCAO = "FROM inscricoes i JOIN alunos a ON a.id_aluno = i.id_aluno JOIN cursos c ON c.id_curso = i.id_curso " e usou nos métodos listar() e exibir().
- Como foi aplicado: Usamos como base do ControllerInscricao.java. Corrigimos um erro de digitação da resposta (impirimir em vez de imprimir) e ajustamos o exibir() para formatar a data com TO_CHAR e o progresso com || '%'. O atualizar() foi mantido alterando apenas data e progresso, porque aluno e curso definem a identidade da inscrição.

---

## Prompt 10

- Usuário: Thainara Santos de Souza
- IA: Claude
- Prompt: "Preciso de um relatório SQL para Oracle que atenda ao requisito de sumarização (GROUP BY + função agregada) do meu projeto de cursos. Quero, por curso: id, nome, quantidade de inscrições, média de progresso arredondada para 1 casa decimal (0 se não houver inscrições) e receita potencial (quantidade de inscrições × valor do curso). Use LEFT JOIN para incluir cursos sem inscrições, GROUP BY e ORDER BY por quantidade de inscrições decrescente."
- Resposta: A IA gerou o SELECT com COUNT(i.id_inscricao), NVL(ROUND(AVG(i.progresso), 1), 0), COUNT(i.id_inscricao) * c.valor, LEFT JOIN, GROUP BY e ORDER BY.
- Como foi aplicado: Salvamos em src/main/resources/sql/relatorio_inscricoes_por_curso.sql e executamos no Oracle. Testamos com um curso sem inscrições para confirmar que o LEFT JOIN + NVL retornava 0 e ajustamos o ORDER BY para usar o alias inscricoes.

---

## Prompt 11

- Usuário: Letícia Ventura Brandão
- IA: Gemini
- Prompt: "Preciso de um relatório SQL para Oracle que atenda ao requisito de junção de tabelas (JOIN) do meu projeto de cursos. Quero listar cada inscrição com: id da inscrição, nome do aluno, nome do curso, data de inscrição formatada como DD/MM/YYYY e progresso com símbolo de porcentagem. Use JOIN entre inscricoes, alunos e cursos, e ordene por nome do aluno e nome do curso."
- Resposta: A IA gerou o SELECT com JOIN alunos, JOIN cursos, TO_CHAR(i.data_inscricao, 'DD/MM/YYYY') e i.progresso || '%', com ORDER BY a.nome, c.nome.
- Como foi aplicado: Salvamos em src/main/resources/sql/relatorio_detalhe_inscricoes.sql. Testamos no Oracle e confirmamos que a concatenação || '%' funciona para o progresso. O ORDER BY foi mantido.

---

## Prompt 12

- Usuário: Mateus de Souza Petris
- IA: DeepSeek
- Prompt: "Gere uma classe Java chamada Relatorios que leia arquivos .sql da pasta resources/sql/ e execute com Db.imprimir(). Preciso de dois métodos públicos: inscricoesPorCurso() e detalheInscricoes(), cada um chamando um método privado executor(arquivo, titulo) que lê o arquivo com getResourceAsStream, converte para UTF-8, remove o ponto e vírgula final (JDBC não aceita) e chama Db.imprimir(sql). Trate IOException como SQLException."
- Resposta: A IA gerou a classe com executor() usando getResourceAsStream, readAllBytes, StandardCharsets.UTF_8, remoção do ; final e try/catch de IOException.
- Como foi aplicado: Salvamos em reports/Relatorios.java. Testamos os dois relatórios no console e confirmamos que a leitura do arquivo e a remoção do ; funcionam.

---

## Prompt 13

- Usuário: João Vitor de Jesus Rocha
- IA: ChatGPT
- Prompt: "Gere uma classe Java SplashScreen para console que exiba uma caixa com # contendo: nome do sistema 'PLATAFORMA DE CURSOS ONLINE', total de registros de cursos, alunos e inscrições (usando SELECT COUNT(1) total_... FROM ...), nomes dos integrantes lidos de Config.INTEGRANTES, disciplina 'BANCO DE DADOS', semestre '2026/2' e professor 'HOWARD ROATTI'. Use printf com largura fixa para alinhar as linhas."
- Resposta: A IA gerou a classe com três chamadas Db.contar(), uma constante L = 46 para a largura, métodos linha() e centro() para alinhamento e "#".repeat(L + 4) para as bordas.
- Como foi aplicado: Salvamos em utils/SplashScreen.java. Ajustamos o nome do sistema e a lista de integrantes no Config.java e testamos a exibição no console da VM, confirmando que as contagens aparecem corretamente após rodar o script de dados de exemplo.

---

## Prompt 14

- Usuário: Beatriz de Souza Oliveira
- IA: Claude
- Prompt: "Preciso de três classes utilitárias em Java para um CRUD em console: Config (com arrays estáticos de integrantes e menus), Menu (método exibir(titulo, opcoes) que mostra menu numerado e só retorna opção válida) e Entrada (métodos texto, inteiro, decimal, data e confirmar, todos com validação e sem quebrar o programa se o usuário digitar errado). Para data, use DateTimeFormatter com ResolverStyle.STRICT no formato dd/MM/uuuu."
- Resposta: A IA gerou as três classes. Entrada.data() usou DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT). Entrada.confirmar() leu S/N e retornou boolean. Menu.exibir() validou a opção com while(true).
- Como foi aplicado: Salvamos em utils/. Testamos entradas inválidas (letra onde se espera número, data 31/02, opção de menu inexistente) e confirmamos que o programa não quebra. O ResolverStyle.STRICT foi mantido porque rejeita datas inválidas.

---

## Prompt 15

- Usuário: Lucas de Freitas Pereira
- IA: Gemini
- Prompt: "Gere a classe Main em Java para um CRUD em console com menu principal (Relatórios, Inserir, Remover, Atualizar, Sair). Ao iniciar, chame SplashScreen.exibir() e trate SQLException mostrando mensagem e encerrando. Para inserir, remover e atualizar, exiba o menu de entidades (Cursos, Alunos, Inscrições, Voltar), execute a operação e pergunte se deseja continuar (voltando ao menu de entidades ou ao principal). Na remoção, antes de excluir curso ou aluno, conte as inscrições filhas e pergunte se quer removê-las também; se sim, use transação. Na atualização, busque o registro, exiba o atual, peça os novos dados, atualize e exiba o atualizado."
- Resposta: A IA gerou a classe com do/while para os menus, switch para as opções, try/catch de SQLException em cada operação, e os métodos inserir, remover e atualizar para cada entidade.
- Como foi aplicado: Salvamos em principal/Main.java. Revisamos cada fluxo contra o item 6 do edital e testamos todos os caminhos: inserir com e sem continuar, remover com e sem filhas, atualizar com e sem continuar. O tratamento de FK na remoção foi mantido conforme o edital (item 6.c.5.i).

---

## Prompt 16

- Usuário: Thainara Santos de Souza
- IA: ChatGPT
- Prompt: "Gere um README.md para um projeto Java + Oracle (Maven) que roda em Linux. Deve conter: nome do projeto, disciplina, professor, integrantes, descrição do sistema, requisitos (JDK 17+, Maven, Oracle), como clonar, como executar os scripts SQL, como configurar as variáveis de ambiente DB_URL, DB_USER e DB_PASS, como executar com mvn exec:java, e um espaço para o link do vídeo do YouTube."
- Resposta: A IA gerou o README com todas as seções, incluindo os comandos export DB_URL=... e mvn -q compile exec:java.
- Como foi aplicado: Salvamos na raiz do repositório. Adaptamos os nomes dos integrantes, o nome do sistema e o link do vídeo, e testamos os comandos na VM Linux da disciplina para confirmar que funcionam do zero.

---

## Prompt 17

- Usuário: Mateus de Souza Petris
- IA: DeepSeek
- Prompt: "Preciso explicar para o professor cada parte do meu projeto Java + Oracle: a classe Db (conexão, transação, formatação, impressão), as classes de modelo (associação por instância), os controllers (CRUD com tratamento de FK), os relatórios (GROUP BY e JOIN) e a Main (fluxos de menu). Me ajude a montar uma explicação clara, com foco no que cada método faz e por que ele existe, para eu conseguir responder perguntas."
- Resposta: A IA gerou uma explicação detalhada de cada classe e método, incluindo justificativas (por que BigDecimal, por que try-with-resources, por que transação, por que LEFT JOIN no relatório de sumarização).
- Como foi aplicado: Usamos a explicação como roteiro de estudo para a apresentação. Cada integrante revisou a parte que lhe cabia e testou o funcionamento no console para confirmar o que a IA explicou. Nenhum código foi alterado a partir deste prompt, foi usado apenas para estudo.

---

## Prompt 18

- Usuário: João Vitor de Jesus Rocha
- IA: Claude
- Prompt: "O edital do meu trabalho exige um arquivo PROMPTS.md registrando prompt, fonte e o que o grupo fez com a resposta de cada uso de IA. Me ajude a estruturar esse arquivo de forma organizada, com uma seção por prompt, incluindo ferramenta, autor, prompt, resumo da resposta e como foi utilizada. O grupo tem 6 integrantes e usou ChatGPT, Claude, Gemini e DeepSeek."
- Resposta: A IA gerou a estrutura deste arquivo, com cabeçalho, seção de introdução e uma seção por prompt.
- Como foi aplicado: Este arquivo foi criado a partir dessa estrutura. Preenchemos os prompts reais e as adaptações feitas e revisamos cada entrada para garantir que refletia o uso real. O arquivo foi então versionado no repositório.
