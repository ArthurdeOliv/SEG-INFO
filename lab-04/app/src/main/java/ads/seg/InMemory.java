package ads.seg;

import java.util.HashMap;
import java.util.Map;

public class InMemory implements UserRepository {

    private final Map<String, User> dados = new HashMap<>();

    @Override
    public boolean save(User user) {

        dados.put(user.getLogin(), user);
        return true;
    }

    @Override
    public boolean update(User user) {
        dados.put(user.getLogin(), user);
        return true;
        
    }

    @Override
    public User findByLogin(String login) {
        return dados.get(login);
    }
}
