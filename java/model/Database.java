package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Database {
    private static final String ALL_USER_PATH = "info";
    private static final String ALL_USER_FILE = ALL_USER_PATH + "/allUser.json";
    private Vector<User> allUsers;

    public Database() {
        allUsers = new Vector<>();
    }

    private static String fileToString() throws FileNotFoundException {
        File file = new File(Database.ALL_USER_FILE);
        Scanner scanner = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine())
            builder.append(scanner.nextLine()).append('\n');
        return builder.toString();
    }

    private static void saveObjectToFile(Object object) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        try {
            FileWriter fileWriter = new FileWriter(Database.ALL_USER_FILE);
            fileWriter.write(gson.toJson(object));
            fileWriter.flush();
        } catch (IOException ignored) {

        }
    }

    public void loadDataFromFile() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type allUsersType = new TypeToken<Vector<User>>() {
        }.getType();

        try {
            allUsers = gson.fromJson(fileToString(), allUsersType);
        } catch (FileNotFoundException ignored) {
            allUsers = new Vector<>();
        }
    }

    public void saveDataIntoFile() {
        checkForSavingDirectory();
        saveObjectToFile(allUsers);
    }

    private void checkForSavingDirectory() {
        File directory = new File(ALL_USER_PATH);
        if (!directory.exists())
            directory.mkdirs();
    }

    public User getUserByUsername(String username) {
        for (User user : allUsers)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    public void addUser(String username, String password) {
        User newUser = new User(username, password);
        allUsers.add(newUser);
    }

    public void removeUser(User user) {
        allUsers.remove(user);
        saveDataIntoFile();
    }

    public ArrayList<User> sort(int level) {
        ArrayList<User> sorted = new ArrayList<>();
        for (int i = 0; i < Math.min(allUsers.size(), 10); i++) {
            int MaxScore = 0;
            User rankedUser = null;
            int minTime = 120;
            for (User user : allUsers) {
                if ((user.getHighScore(level) > MaxScore || (user.getHighScore(level) == MaxScore &&
                        user.getHighScoreTime(level) <= minTime)) && !sorted.contains(user)) {
                    minTime = user.getHighScoreTime(level);
                    MaxScore = user.getHighScore(level);
                    rankedUser = user;
                }
            }
            sorted.add(rankedUser);
        }
        return sorted;
    }
}
