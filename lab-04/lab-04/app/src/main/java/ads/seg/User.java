//RA1 O sistema deve ter uma classe User com os atributos login e password.

// • O atributo password deve armazenar o hash da senha do usuário, e não a senha em claro.

// • O atributo login deve guardar uma String.


package ads.seg;

public class User {

    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
