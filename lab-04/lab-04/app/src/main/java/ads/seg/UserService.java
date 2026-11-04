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

import java.security.SecureRandom;
import java.util.Base64;

public class UserService {

    private UserRepository repository = new InMemory();
    private String hashAlg;

    public UserService(UserRepository db, String hashAlg) {
        this.repository = db;
        this.hashAlg = "BCrypt";
        if (hashAlg.equalsIgnoreCase("PBKDF2")) {
            this.hashAlg = hashAlg;
        }
    }

    public UserService(UserRepository repository) {
        this.repository = repository;
        this.hashAlg = "BCrypt";
    }


    public boolean register(String login, String password) throws UserAlreadyExistsException {
        if (repository.findByLogin(login) != null) {
            throw new UserAlreadyExistsException("Usuario em uso!!!");
        }
        return repository.save(new User(login, hash(password)));
    }

    public boolean updatePassword(String login, String currentP, String newP) throws UserNotFoundException, InvalidPasswordException {

        if (repository.findByLogin(login) == null) {
            throw new UserNotFoundException("Usuario nao encontrado");
        }

        if (!verify(currentP, repository.findByLogin(login))) {
            throw new InvalidPasswordException("Senha incorreta");
        }

        return repository.update(new User(login, hash(newP)));
    }

    public boolean authenticate(String login, String password) throws InvalidLoginException {

        if (repository.findByLogin(login) == null || !verify(password, repository.findByLogin(login))) {
            throw new InvalidLoginException("Login ou senha invalidos");
        }

        return true;
    }

    private String hash(String password) {
        char[] senha = password.toCharArray();

        if (hashAlg.equalsIgnoreCase("PBKDF2")) {
            try {
                byte[] salt = new byte[16];
                new SecureRandom().nextBytes(salt);

                byte[] hash = PasswordHashing.hashPasswordWithPBKDF2(senha, salt, "PBKDF2WithHmacSHA512", 210000, 256);

                String saltBase64 = Base64.getEncoder().encodeToString(salt);
                String hashBase64 = Base64.getEncoder().encodeToString(hash);
                return saltBase64 + "$" + hashBase64;
            } catch (Exception x) {
            }
        }

        byte[] hashBCrypt = PasswordHashing.hashPasswordWithBCrypt(senha);
        return new String(hashBCrypt);
    }

    private boolean verify(String password, User user) {
        char[] senha = password.toCharArray();

        if (hashAlg.equalsIgnoreCase("PBKDF2")) {
            try {
                String[] v = user.getPassword().split("\\$");
                byte[] salt = Base64.getDecoder().decode(v[0]);
                byte[] hash = Base64.getDecoder().decode(v[1]);
                return PasswordHashing.verifyPasswordWithPBKDF2(senha, salt, "PBKDF2WithHmacSHA512", 210000, 256, hash);
            } catch (Exception x) {
                return false;
            }
        }

        return PasswordHashing.verifyPasswordWithBCrypt(senha, user.getPassword().getBytes());
    }
}
