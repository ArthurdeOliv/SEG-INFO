//RA1 O sistema deve ter uma classe User com os atributos login e password.

// • O atributo password deve armazenar o hash da senha do usuário, e não a senha em claro.

// • O atributo login deve guardar uma String.


package ads.seg;

public class User {

    private String login;
    private String senha;

    public User(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
