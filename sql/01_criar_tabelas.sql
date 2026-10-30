CREATE SEQUENCE seq_curso START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE seq_aluno START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE seq_inscricao START WITH 1 INCREMENT BY 1 NOCACHE;


CREATE TABLE cursos (
    id_curso NUMBER(10) CONSTRAINT pk_cursos PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    categoria VARCHAR2(50) NOT NULL,
    carga_horaria NUMBER(4) NOT NULL CONSTRAINT ck_curso_carga CHECK (carga_horaria > 0),
    valor NUMBER(10,2) NOT NULL CONSTRAINT ck_curso_valor CHECK (valor >= 0)
);


CREATE TABLE alunos (
     id_aluno NUMBER(10) CONSTRAINT pk_alunos PRIMARY KEY,
     nome VARCHAR2(100) NOT NULL,
     email VARCHAR2(120) NOT NULL CONSTRAINT uq_aluno_email UNIQUE,
     data_nascimento DATE NOT NULL
);


CREATE TABLE inscricoes (
     id_inscricao NUMBER(10) CONSTRAINT pk_inscricoes PRIMARY KEY,
     id_aluno NUMBER(10) NOT NULL,
     id_curso NUMBER(10) NOT NULL,
     data_inscricao DATE DEFAULT SYSDATE NOT NULL,
     progresso NUMBER(3) DEFAULT 0 NOT NULL CONSTRAINT ck_insc_prog CHECK (progresso BETWEEN 0 AND 100),
     CONSTRAINT fk_insc_aluno FOREIGN KEY (id_aluno) REFERENCES alunos(id_aluno),
     CONSTRAINT fk_insc_curso FOREIGN KEY (id_curso) REFERENCES cursos(id_curso),
     CONSTRAINT uq_aluno_curso UNIQUE (id_aluno, id_curso)
);