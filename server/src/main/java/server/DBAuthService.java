package server;

public class DBAuthService implements AuthService{
    @Override
    public String getNicknameByLoginAndPassword(String login, String password){
        return DBHandler.getNicknameByLoginAndPassword(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return DBHandler.registration(login, password, nickname);
    }

    @Override
    public boolean changeName(String nickname) {
        return DBHandler.changeName(nickname);
    }
}
