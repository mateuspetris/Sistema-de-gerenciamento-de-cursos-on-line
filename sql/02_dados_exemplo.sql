INSERT INTO cursos (id_curso, nome, categoria, carga_horaria, valor) VALUES (seq_curso.NEXTVAL, 'Java do Zero', 'Programação', 40, 199.90);
INSERT INTO cursos (id_curso, nome, categoria, carga_horaria, valor) VALUES (seq_curso.NEXTVAL, 'SQL e Oracle', 'Banco de Dados', 30, 149.00);
INSERT INTO cursos (id_curso, nome, categoria, carga_horaria, valor) VALUES (seq_curso.NEXTVAL, 'UX Design', 'Design', 24, 129.50);
INSERT INTO cursos (id_curso, nome, categoria, carga_horaria, valor) VALUES (seq_curso.NEXTVAL, 'Python para Dados', 'Dados', 36, 179.00);

INSERT INTO alunos (id_aluno, nome, email, data_nascimento) VALUES (seq_aluno.NEXTVAL, 'Ana Souza', 'ana@email.com', TO_DATE('2001-03-15', 'YYYY-MM-DD'));
INSERT INTO alunos (id_aluno, nome, email, data_nascimento) VALUES (seq_aluno.NEXTVAL, 'Bruno Lima', 'bruno@email.com', TO_DATE('1999-07-22', 'YYYY-MM-DD'));
INSERT INTO alunos (id_aluno, nome, email, data_nascimento) VALUES (seq_aluno.NEXTVAL, 'Carla Dias', 'carla@email.com', TO_DATE('2003-11-02', 'YYYY-MM-DD'));
INSERT INTO alunos (id_aluno, nome, email, data_nascimento) VALUES (seq_aluno.NEXTVAL, 'Diego Alves', 'diego@email.com', TO_DATE('2000-01-30', 'YYYY-MM-DD'));

INSERT INTO inscricoes (id_inscricao, id_aluno, id_curso, data_inscricao, progresso) VALUES (seq_inscricao.NEXTVAL, 1, 1, TO_DATE('2026-08-01', 'YYYY-MM-DD'), 100);
INSERT INTO inscricoes (id_inscricao, id_aluno, id_curso, data_inscricao, progresso) VALUES (seq_inscricao.NEXTVAL, 1, 2, TO_DATE('2026-08-10', 'YYYY-MM-DD'), 60);
INSERT INTO inscricoes (id_inscricao, id_aluno, id_curso, data_inscricao, progresso) VALUES (seq_inscricao.NEXTVAL, 2, 1, TO_DATE('2026-08-15', 'YYYY-MM-DD'), 35);
INSERT INTO inscricoes (id_inscricao, id_aluno, id_curso, data_inscricao, progresso) VALUES (seq_inscricao.NEXTVAL, 3, 3, TO_DATE('2026-09-01', 'YYYY-MM-DD'), 10);
INSERT INTO inscricoes (id_inscricao, id_aluno, id_curso, data_inscricao, progresso) VALUES (seq_inscricao.NEXTVAL, 3, 1, TO_DATE('2026-09-05', 'YYYY-MM-DD'), 80);
INSERT INTO inscricoes (id_inscricao, id_aluno, id_curso, data_inscricao, progresso) VALUES (seq_inscricao.NEXTVAL, 4, 2, TO_DATE('2026-09-12', 'YYYY-MM-DD'), 0);

COMMIT;