package co.edu.poli.ces3.universitas.controller;

import co.edu.poli.ces3.universitas.dto.DtoUser;
import co.edu.poli.ces3.universitas.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class CtrUser {

    private User modelUser;

    public CtrUser(){
        modelUser = new User();
    }

    public DtoUser addUser(DtoUser student){
        try {
            User newUser = modelUser.create(student);
            return new DtoUser(newUser.getId(), newUser.getMail(), newUser.getName(), newUser.getPass());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<DtoUser> getAllUsers() {
        try {
            ArrayList<User> users = modelUser.all(); // Llama al método 'all' del modelo
            ArrayList<DtoUser> dtoUsers = new ArrayList<>();

            for (User user : users) {
                DtoUser dtoUser = new DtoUser(
                        user.getId(),
                        user.getMail(),
                        user.getName(),
                        user.getPass()
                );
                dtoUsers.add(dtoUser);
            }

            return dtoUsers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DtoUser getUserById(int userId) {
        try {
            User user = modelUser.findById(userId);
            if (user != null) {
                return new DtoUser(user.getId(), user.getMail(), user.getName(), user.getPass());
            } else {
                throw new RuntimeException("NO ESTÁ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DtoUser updateUser(int userId, DtoUser updateUser) {
        try {
            User user = new User(
                    userId,
                    updateUser.getMail(),
                    updateUser.getName(),
                    updateUser.getPass()

            );

            User updated = modelUser.update(user);
            return new DtoUser(updated.getId(), updated.getMail(), updated.getName(),updated.getPass());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int userId) {
        try {
            modelUser.delete(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
