package com.example.studentcompetition;

import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backButton;
    private static final String USER_DATA_FILE = "user_data.txt";

    public RegisterFrame() {
        createUI();
    }

    private void createUI() {
        setTitle("注册账号");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // 创建表单面板
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(30, 50, getWidth(), getHeight());

        // 账号输入
        JLabel usernameLabel = new JLabel("账号:");
        usernameLabel.setBounds(100, 20, 80, 30);
        formPanel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(180, 20, 250, 25);
        formPanel.add(usernameField);

        // 密码输入
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(100, 60, 80, 30);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(180, 60, 250, 25);
        formPanel.add(passwordField);

        // 确认密码
        JLabel confirmPasswordLabel = new JLabel("确认密码:");
        confirmPasswordLabel.setBounds(100, 100, 80, 30);
        formPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setBounds(180, 100, 250, 25);
        formPanel.add(confirmPasswordField);

        // 注册按钮
        registerButton = new JButton("注册");
        registerButton.setBounds(180, 140, 100, 30);
        formPanel.add(registerButton);

        // 返回按钮
        backButton = new JButton("返回登录");
        backButton.setBounds(300, 140, 100, 30);
        formPanel.add(backButton);

        // 添加事件监听
        registerButton.addActionListener(this::register);
        backButton.addActionListener(this::backToLogin);

        add(formPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void register(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // 验证输入
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "账号和密码不能为空");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "两次输入的密码不一致");
            return;
        }

        // 保存用户数据
        try (FileWriter writer = new FileWriter(USER_DATA_FILE, true)) {
            writer.write(username + ":" + password + "\n");
            JOptionPane.showMessageDialog(this, "注册成功");
            backToLogin(null);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "注册失败，无法写入文件");
        }
    }

    private void backToLogin(ActionEvent e) {
        this.dispose();
        new M1();
    }
}