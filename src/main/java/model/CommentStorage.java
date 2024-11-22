package main.java.model;
import java.util.ArrayList;
import java.util.List;

public class CommentStorage {
    private static List<String> comments = new ArrayList<>();
    private static List<String> userNames = new ArrayList<>();

    public static void saveComment(String userName, String comment) {
        comments.add(comment);
        userNames.add(userName);
    }

    public static List<String> getComments() {
        return comments;
    }

    public static List<String> getUserNames() {
        return userNames;
    }

    public static String getLastComment() {
        if (comments.isEmpty()) return "";
        return comments.get(comments.size() - 1);
    }

    public static String getLastUserName() {
        if (userNames.isEmpty()) return "";
        return userNames.get(userNames.size() - 1);
    }
}
