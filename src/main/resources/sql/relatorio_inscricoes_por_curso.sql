SELECT c.id_curso AS id,
       c.nome AS curso,
       COUNT(i.id_inscricao) AS inscricoes,
       NVL(ROUND(AVG(i.progresso), 1), 0) AS progresso_medio,
       COUNT(i.id_inscricao) * c.valor AS receita_total
FROM cursos c
         LEFT JOIN inscricoes i ON i.id_curso = c.id_curso
GROUP BY c.id_curso, c.nome, c.valor
ORDER BY inscricoes DESC, c.nome