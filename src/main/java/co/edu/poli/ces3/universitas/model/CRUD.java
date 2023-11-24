package co.edu.poli.ces3.universitas.model;

import co.edu.poli.ces3.universitas.dto.DtoUser;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUD {
    User create(DtoUser user) throws SQLException;

    public ArrayList<User> all();

    public User findById(int id) throws SQLException;

    User update(User user) throws SQLException;

    void delete(int userId) throws SQLException;
}