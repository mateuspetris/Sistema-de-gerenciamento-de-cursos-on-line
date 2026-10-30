package br.faesa.cursos.model;
import java.time.LocalDate;
public class Inscricao {
    private int idInscricao;
    private Aluno aluno;
    private Curso curso;
    private LocalDate dataInscricao;
    private int progresso;
    public Inscricao() { }
    public int getIdInscricao() { return idInscricao; }
    public void setIdInscricao(int idInscricao) { this.idInscricao =
            idInscricao; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public LocalDate getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao; }
    public int getProgresso() { return progresso; }
    public void setProgresso(int progresso) { this.progresso = progresso; }
    @Override
    public String toString() {
        return "Inscricao [id=" + idInscricao + ", aluno=" + aluno.getNome()
                + ", curso=" + curso.getNome()
                + ", data=" + dataInscricao + ", progresso=" + progresso +
                "%]";
    }
}
