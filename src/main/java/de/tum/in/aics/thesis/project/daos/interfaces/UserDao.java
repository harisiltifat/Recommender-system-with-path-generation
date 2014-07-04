package de.tum.in.aics.thesis.project.daos.interfaces;

import java.util.List;

import de.tum.in.aics.thesis.project.models.User;

public interface UserDao {

	public void saveUser(User user);
    public List<User> getAllUser(User user);
    public User getUserById(Integer userId);
    public void deleteUser(User user);
    public List<User> findUser(long socialId);
}
