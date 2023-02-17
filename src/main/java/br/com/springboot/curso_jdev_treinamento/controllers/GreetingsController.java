package br.com.springboot.curso_jdev_treinamento.controllers;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

    @Autowired// Essa notação é a injeção de depedência
    private UsuarioRepository usuarioRepository;//Crio uma variavel da classe UsuarioRepository

    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API:  " + name + "!";
    }


    //Criando um mapeamento
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome){


        Usuario usuario = new Usuario();
        usuario.setNome(nome);

        usuarioRepository.save(usuario);//esse comando grava no banco de dados

        return "Ola mundo " + nome;
    }


//------------------------------------------------------------------------------------------------------------------------------------------

    //Criando Metodo GET, para bucar todos os dados do banco.
    @GetMapping(value =  "listatodos")//Mapeamento para buscar pela URL
    @ResponseBody //Retorna os dados para o corpo da respopsta.(Essa notação retorna um JSON)
    public ResponseEntity<List<Usuario>> listaUsuario(){

        List<Usuario> usuarios = usuarioRepository.findAll(); //Executa a consulta no banco de dados

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //Retorna a lista em Json de todos os dados do banco

    }

 //------------------------------------------------------------------------------------------------------------------------------------------

    //Criando Metodo POST, para salvar no banco de dados
    @PostMapping(value = "salvar")//Notação para salvar (mapeia a URL)
    @ResponseBody //Notação para o retorno da requisição, depois que salvar. (Descrição da resposta)
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){// @RequestBody - Recebe os dados e injeta na vlasse usuario para salvar

        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

    }

//------------------------------------------------------------------------------------------------------------------------------------------

    //Criando Metodo DELETE, para deletar conforme Id
    @DeleteMapping(value = "delete")// (mapeia a URL)
    @ResponseBody //Notação para o retorno da requisição, depois que deletar. (Descrição da resposta)
    public ResponseEntity<String> delete(@RequestParam Long iduser) {// @RequestParam  - Recebe uma String, nesse caso será o Id desejado

        usuarioRepository.deleteById(iduser);

        return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);

    }

//------------------------------------------------------------------------------------------------------------------------------------------

    //Criando Metodo GET, para buscar conforme Id
    @GetMapping(value = "buscaruserid")// (mapeia a URL)
    @ResponseBody //Notação para o retorno da requisição.
    public ResponseEntity<Usuario> buscaruserid(@RequestParam("iduser") Long iduser) {// @RequestParam  - Recebe um Usuario, a busca será realizada pelo Id

        Usuario usuario = usuarioRepository.findById(iduser).get();

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

    }

//------------------------------------------------------------------------------------------------------------------------------------------

    //Criando Metodo PUT, para atualizar
    @PutMapping(value = "atualizar")//Notação para salvar (mapeia a URL)
    @ResponseBody //Notação para o retorno da requisição.
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){// @RequestBody - Recebe os dados e injeta na vlasse usuario para salvar
        // Nessa caso a passagem de parâmetro foi retornado um interrogação (public ResponseEntity<?>), porque eu posso retorno uma String ou um objeto.

        if(usuario.getId() == null){//Esse if foi criado para quando houver atualização, o Id tem que ser informado obrigatoriamente.
            return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);

    }

//------------------------------------------------------------------------------------------------------------------------------------------

    //Criando Metodo GET, para buscar por nome
    @GetMapping(value = "buscarPorNome")// (mapeia a URL)
    @ResponseBody //Notação para o retorno da requisição.
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam("name") String name) {// @RequestParam  - Recebe uma lista de Usuarios, a busca será realizada pela partes de um nome

        //buscarPorNome(name.trim().toUpperCase()); - Nesse comando eu consigo fazer a pesquisa ignorando os espaços e asletras maisculas/minusculas
        List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());// .trim() - retira todos os espaços

        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

    }



}
