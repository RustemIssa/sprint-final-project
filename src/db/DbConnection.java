package db;

import models.Comment;
import models.News;
import models.NewsCategory;
import models.User;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class DbConnection {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/NewsPortal",
                    "postgres",
                    "postgres"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUserByEmailAndPassword(String email, String pass) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email=? AND password=?");
            statement.setString(1, email);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setRoleId(resultSet.getInt("role_id"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<News> getNews() {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, c.name, n.title, n.content FROM news n " +
                            "inner join news_category c on n.category_id = c.id");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News n = new News();
                n.setId(resultSet.getInt("id"));
                n.setPostDate(resultSet.getString("post_date"));
                n.setTitle(resultSet.getString("title"));
                n.setText(resultSet.getString("content"));
                NewsCategory nc = new NewsCategory();
                nc.setId(resultSet.getInt("category_id"));
                nc.setName(resultSet.getString("name"));
                n.setCategoryId(nc);
                news.add(n);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static News getNewsById(int id) {
        News n = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, c.name, n.title, n.content FROM news n " +
                            "inner join news_category c on n.category_id = c.id WHERE n.id =?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                n = new News();
                n.setId(resultSet.getInt("id"));
                n.setTitle(resultSet.getString("title"));
                n.setPostDate(resultSet.getString("post_date"));
                n.setText(resultSet.getString("content"));
                NewsCategory nc = new NewsCategory();
                nc.setId(resultSet.getInt("category_id"));
                nc.setName(resultSet.getString("name"));
                n.setCategoryId(nc);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public static void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (email, password, name) values (?,?,?)"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUserById(int id) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setRoleId(resultSet.getInt("role_id"));
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setRoleId(resultSet.getInt("role_id"));
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void updateUser(String name, String email, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET name = ?, email = ? WHERE id = ?");

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUserAdmin(String name, String email, int role, String pass, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET name = ?, email = ?, role_id=?, password=?  WHERE id = ?");
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, role);
            statement.setString(4, pass);
            statement.setInt(5, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deleteUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE from users WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUserPass(String pass, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET password=? WHERE id=?");

            statement.setString(1, pass);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<NewsCategory> getCategory() {
        ArrayList<NewsCategory> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news_category");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                NewsCategory category = new NewsCategory();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static void addNews(String title, String text, String date, int categoryId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news (post_date, category_id, title, content) values (?,?,?,?)"
            );
            statement.setString(1, date);
            statement.setInt(2, categoryId);
            statement.setString(3, title);
            statement.setString(4, text);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateNews(int id, String title, String text, String date, int categoryId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE news SET post_date=?, category_id=?, title = ?, content = ? WHERE id=?");

            statement.setString(1, date);
            statement.setInt(2, categoryId);
            statement.setString(3, title);
            statement.setString(4, text);
            statement.setInt(5, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNewsById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE from news WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNewsComment(int newsId, int userId, String comment) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO comments (comment, user_id, news_id, post_date) values (?,?,?,now())"
            );
            statement.setString(1, comment);
            statement.setInt(2, userId);
            statement.setInt(3, newsId);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Comment> getCommentsByNewsId(int newsId) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.id, c.comment, c.post_date, c.user_id, u.name FROM comments c " +
                            "INNER JOIN users u on c.user_id = u.id WHERE c.news_id=?");

            statement.setInt(1, newsId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                User user = new User();
                comment.setId(resultSet.getInt("id"));
                LocalDateTime createdDate = resultSet.getTimestamp("post_date").toLocalDateTime();
                comment.setPostDate(createdDate);
                comment.setNewsId(newsId);
                comment.setComment(resultSet.getString("comment"));
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                comment.setUserId(user);
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setRoleId(resultSet.getInt("role_id"));
                users.add(user);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


}
