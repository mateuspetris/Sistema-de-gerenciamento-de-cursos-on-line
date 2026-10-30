package br.faesa.cursos.model;
import java.math.BigDecimal;
public class Curso {
    private int idCurso;
    private String nome;
    private String categoria;
    private int cargaHoraria;
    private BigDecimal valor;
    public Curso() { }
    public Curso(int idCurso, String nome, String categoria, int
            cargaHoraria, BigDecimal valor) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.categoria = categoria;
        this.cargaHoraria = cargaHoraria;
        this.valor = valor;
    }
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria;
    }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria =
            cargaHoraria; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    Guia do Projeto: Sistema de Gerenciamento de Cursos Online (Java + Oracle)
    @Override
    public String toString() {
        return "Curso [id=" + idCurso + ", nome=" + nome + ", categoria=" +
                categoria
                + ", cargaHoraria=" + cargaHoraria + "h, valor=R$ " + valor +
                "]";
    }
}
