package model;

import java.net.URL;

public class User {
    private final int[] highScore;
    private final int[] highScoreTime;
    private String username;
    private String password;
    private URL avatarUrl;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        highScore = new int[]{0, 0, 0};
        highScoreTime = new int[]{0, 0, 0};
        avatarUrl = User.class.getResource("/image/avatar" + (int) (Math.random() * 3) + ".png");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHighScore(int type) {
        return highScore[type];
    }

    public int getHighScoreTime(int type) {
        return highScoreTime[type];
    }

    public URL getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(URL avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void checkRecord(int record, int type, int time) {
        if (record > highScore[type] || (record == highScore[type] && time < highScoreTime[type])) {
            highScore[type] = record;
            highScoreTime[type] = time;
        }
    }
}
