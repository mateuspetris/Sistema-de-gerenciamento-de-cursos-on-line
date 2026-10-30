SELECT i.id_inscricao AS id,
       a.nome AS aluno,
       c.nome AS curso,
       TO_CHAR(i.data_inscricao, 'DD/MM/YYYY') AS data_inscricao,
       i.progresso || '%' AS progresso
FROM inscricoes i
         JOIN alunos a ON a.id_aluno = i.id_aluno
         JOIN cursos c ON c.id_curso = i.id_curso
ORDER BY a.nome, c.nome