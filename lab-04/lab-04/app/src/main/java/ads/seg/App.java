package ads.seg;

public class App {

    static UserService userService = new UserService(new InMemory());
    // static UserService userService = new UserService(new InMemory(), "PBKDF2");

    public static void main(String[] args) {

        String opcao = "";
        while (!opcao.equals("4")) {
            IO.println("\n1 - Cadastrar usuario");
            IO.println("2 - trocar senha");
            IO.println("3 - verificar conta");
            IO.println("4 - sair");
            opcao = IO.readln("opcao: ");

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> trocarSenha();
                case "3" -> verificar();
            }
        }
    }

    static void cadastrar() {
        String login = IO.readln("login: ");
        String senha = IO.readln("senha: ");
        try {
            userService.register(login, senha);
            IO.println("usuario cadastrado");
        } catch (UserAlreadyExistsException e) {
            IO.println(e.getMessage());
        }
    }

    static void trocarSenha() {
        String login = IO.readln("login: ");
        String atual = IO.readln("senha atual: ");
        String nova = IO.readln("nova senha: ");
        try {
            userService.updatePassword(login, atual, nova);
            IO.println("senha alterada");
        } catch (UserNotFoundException | InvalidPasswordException e) {
            IO.println(e.getMessage());
        }
    }

    static void verificar() {
        String login = IO.readln("login: ");
        String senha = IO.readln("senha: ");
        try {
            userService.authenticate(login, senha);
            IO.println("conta autenticada");
        } catch (InvalidLoginException e) {
            IO.println(e.getMessage());
        }
    }
}