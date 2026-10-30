package br.faesa.cursos.model;
import java.time.LocalDate;
public class Aluno {
    private int idAluno;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    public Aluno() { }
    public Aluno(int idAluno, String nome, String email, LocalDate
            dataNascimento) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }
    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento; }
    @Override
    public String toString() {return "Aluno [id=" + idAluno + ", nome=" + nome + ", email=" + email
            + ", nascimento=" + dataNascimento + "]";
    }
}

