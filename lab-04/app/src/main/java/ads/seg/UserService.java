//RA2 O sistema deve ser implementado com o padrão de projeto Service3, que separa a lógica de negócio
//da lógica de apresentação (interface com o usuário).

// • A classe UserService deve ter os seguintes métodos:
//      – register: para cadastrar um novo usuário. Caso o login já exista, retornar uma exceção
//personalizada Ex: UserAlreadyExistsException);
//      – updatePassword: para atualizar a senha de um usuário. Caso o usuário não exista, retornar uma exceção personalizada Ex: UserNotFoundException). Caso a senha informada
//não corresponda a atual senha do usuário, deve retornar uma exceção personalizada Ex:InvalidPasswordException);
//      – authenticate: para simular a autenticação de um usuário. Caso o login ou a senha informa-
//dos estejam incorretos, deve retornar uma exceção personalizada Ex: InvalidLoginExcep-
//tion).

// • O algoritmo de hash de senha (e.g. PBKDF2 ou BCrypt) deve ser configurável, ou seja, deve
//ser possível alterar o algoritmo de hash sem alterar o código da aplicação. Assim, ao instanciar
//a classe UserService, deve-se indicar qual será o algoritmo de hash a ser utilizado. Se não for
//informado um algoritmo de hash, então deve-se usar o algoritmo BCrypt por padrão.


package ads.seg;

public class UserService {

    private UserRepository userService = new InMemory();

}
