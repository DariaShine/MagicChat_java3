package server;

public interface AuthService {
    /**
     * Получение никнейма по логину и паролю
     * возвращает никнейм если учетка есть
     * null если пары логин пароль не нашлось
     * */
    String getNicknameByLoginAndPassword(String login, String password);
    /**
     * Регистрация нового пользователя
     * при успешной регистрации ( логин и никнейм не заняты ) вернет true
     * иначе вернет false
     *
     * @return*/
    boolean registration(String login, String password, String nickname);

    boolean changeName(String name);
}
