package com.example.studentcompetition.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.studentcompetition.model.User;

@Service
public class UserService {

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        // 添加一个示例用户用于测试
        users.add(new User(1L, "admin", "password123", "20200000", "admin@example.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User register(User user) {
        // 检查用户名是否已存在
        if (findByUsername(user.getUsername()) != null) {
            return null; // 用户名已存在
        }
        
        // 检查学号是否已存在
        if (findByStudentId(user.getStudentId()) != null) {
            return null; // 学号已存在
        }
        
        // 设置用户ID并添加到列表
        user.setId((long) (users.size() + 1));
        users.add(user);
        return user;
    }

    public User login(String username, String password) {
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
        return optionalUser.orElse(null);
    }

    public User findByUsername(String username) {
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
        return optionalUser.orElse(null);
    }

    public User findByStudentId(String studentId) {
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getStudentId().equals(studentId))
                .findFirst();
        return optionalUser.orElse(null);
    }

    public User findById(Long id) {
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
        return optionalUser.orElse(null);
    }

    public User addUser(User user) {
        if (findByUsername(user.getUsername()) != null) {
            return null;
        }
        if (findByStudentId(user.getStudentId()) != null) {
            return null;
        }
        user.setId((long) (users.size() + 1));
        users.add(user);
        return user;
    }
}