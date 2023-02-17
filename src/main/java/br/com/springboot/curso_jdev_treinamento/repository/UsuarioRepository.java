package br.com.springboot.curso_jdev_treinamento.repository;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//interface para persistencia de dados(Grava dados no banco de dados)
//Tenho que criar uma variável na classe "GreetingsController"
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//-----------------------------------------------------------------------------------------------------------------------------------------------
    //Para criar um método de buscar por partes de um nome, tenho que fazer uma declaração de um método, e criar uma query de pesquisa
    //nesse caso é "?1"(interrogação e um) porque é apenas uma passagem de parâmetro, que no caso é "name"
    //tenho que envolver u.nome em um trim, para remover os espaços  = trim(u.nome)
    //tenho que envolver trim(u.nome) em um upper = upper(trim(u.nome)) para ignorar letras maisculas/minusculas para pesquisa
    @Query(value = "select  u from Usuario u where upper(trim(u.nome)) like %?1%")
    List<Usuario> buscarPorNome(String name);


//-----------------------------------------------------------------------------------------------------------------------------------------------




}
