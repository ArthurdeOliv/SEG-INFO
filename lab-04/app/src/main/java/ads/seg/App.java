package ads.seg;

public class App {
    static void main(){
        UserService userService = new UserService(new InMemory());
    }


}
