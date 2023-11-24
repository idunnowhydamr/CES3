package co.edu.poli.ces3.universitas.model;

import co.edu.poli.ces3.universitas.dto.DtoUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServlet extends Conexion implements CRUD{
    public int id;

    protected String mail;

    private String name;



    protected String pass;

    public UserServlet(int id, String mail, String name, String pass){
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.pass = pass;
    }

    public UserServlet(String mail){
        this.mail = mail;
    }

    public UserServlet() {
    }

    public int getId(){
        return this.id;
    }


    private void setId(int id){
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    @Override
    public String toString() {
        return "El user se llama: " + this.name +
                " su mail es: " + this.mail;
    }

    @Override
    public UserServlet create(DtoUser user) throws SQLException {
        Connection cnn = this.getConexion();
        if(cnn != null) {
            String sql = "INSERT INTO user(mail, name, pass) VALUES('"+user.getMail()+"', '"+user.getName()+"','"+user.getPass()+"')";
            this.mail = user.getMail();
            this.name = user.getName();
            this.pass = user.getPass();
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                this.id = rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                cnn.close();
            }
            return this;
        }
        return null;
    }

    @Override
    public ArrayList<UserServlet> all() {
        Connection cnn = this.getConexion();
        ArrayList<UserServlet> users = new ArrayList<>();

        if (cnn != null) {
            String sql = "SELECT id,mail,name,pass FROM user";
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String mail = rs.getString("mail");
                    String pass = rs.getString("pass");
                    UserServlet user = new UserServlet(id, mail, name,pass);
                    users.add(user);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (cnn != null) {
                        cnn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return users;
        }
        return null;
    }



@Override
    public UserServlet findById(int userId) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "SELECT id,mail,name,pass FROM user WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String mail = rs.getString("mail");
                        String name = rs.getString("name");
                        String pass = rs.getString("pass");
                        return new UserServlet(id, mail, name,pass);
                    } else {
                        return null;
                    }
                }
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
        return null;
    }

    @Override
    public UserServlet update(UserServlet user) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "UPDATE user SET mail = ?, name = ?, pass = ? WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setString(1, user.getMail());
                stmt.setString(2, user.getName());
                stmt.setString(3, user.getPass());
                stmt.setInt(4, user.getId());
                stmt.executeUpdate();
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
        return user;
    }

    @Override
    public void delete(int userId) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "DELETE FROM user WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
    }


}
