//RA3 O sistema deve ser implementado com o padrão de projeto Repository4, que separa a lógica de acesso
//a dados da lógica de negócio.

//• A interface UserRepository deve ter os seguintes métodos:
//      – save: recebe um objeto do tipo User e o armazena na camada de persistência;
//      – update: recebe um objeto do tipo User e atualiza os dados do usuário na camada de persistência;
//      – findByLogin: recebe um login e retornar o objeto do tipo User correspondente ao login in-
//formado, ou null caso não exista um usuário com o login informado.

//• A interface UserRepository consiste em uma abstração da camada de persistência, ou seja, in-
//dependente da tecnologia utilizada para armazenar os dados Ex: em memória, em arquivos de
//texto ou em um banco de dados relacional).
//      – Você deverá prover uma implementação para armazenar os dados dos usuários em uma
//coleção em memória Ex: List<User> ou Map<String, User>). Mas se desejar, você pode
//prover outras implementações para armazenar os dados dos usuários em arquivos de texto
//ou em um banco de dados relacional (e.g. H2, SQLite, MySQL, PostgreSQL, etc.).

package ads.seg;

public interface UserRepository {

    void save(User user);

    void update(User user);

    User findBylogin(String login);

}
