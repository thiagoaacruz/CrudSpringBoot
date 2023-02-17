package br.com.springboot.curso_jdev_treinamento.model;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity //Notação para deixar a classe como uma entidade, essa classe será a vinculado com o banco de dados
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)//Notação para deixar uma sequencia no código do usuario, nesse caso irá de um em um e irá começar do 1
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Notação para deixar id com primaryKey
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario") //Vinculando "@SequenceGenerator" na geração dos id
    private Long id;
    private String nome;
    private  int idade;





    //Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}


