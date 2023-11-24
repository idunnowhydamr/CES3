package co.edu.poli.ces3.universitas.model;

import co.edu.poli.ces3.universitas.dto.DtoUser;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUD {
    UserServlet create(DtoUser user) throws SQLException;

    public ArrayList<UserServlet> all();

    public UserServlet findById(int id) throws SQLException;

    UserServlet update(UserServlet user) throws SQLException;

    void delete(int userId) throws SQLException;
}