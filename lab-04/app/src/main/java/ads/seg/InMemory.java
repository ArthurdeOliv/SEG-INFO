package ads.seg;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementação de UserRepository que guarda os usuários em um Map em memória.
 */
public class InMemory implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public void update(User user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public User findByLogin(String login) {
        return users.get(login);
    }
}
