package com.example.studentcompetition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class M1 extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton clientButton;
    private JButton adminButton;
    private JButton registerButton;
    private static final String adminUsername = "DGUT12306";
    private static final String adminPassword = "DGUT12306";
    private static final String USER_DATA_FILE = "user_data.txt";

    public M1() {
        createUI();
    }

    private void createUI() {
        setTitle("学生竞赛系统");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // 添加图片到窗口的上半部分
        addImageToWindow();

        // 创建一个 JPanel 来放置账号和密码输入框
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(30, 200, getWidth(), getHeight() / 2);

        // 添加组件到 formPanel
        JLabel usernameLabel = new JLabel("账号:");
        usernameLabel.setBounds(100, 10, 80, 30);
        formPanel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 10, 250, 25);
        formPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(100, 40, 80, 30);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 40, 250, 25);
        formPanel.add(passwordField);

        clientButton = new JButton("客户端登录");
        clientButton.setBounds(150, 80, 100, 30);
        formPanel.add(clientButton);

        adminButton = new JButton("管理端登录");
        adminButton.setBounds(300, 80, 100, 30);
        formPanel.add(adminButton);

        registerButton = new JButton("注册账号");
        registerButton.setBounds(200, 120, 150, 30);
        formPanel.add(registerButton);

        // 为按钮添加事件监听
        clientButton.addActionListener(e -> clientLogin(e));
        adminButton.addActionListener(e -> adminLogin(e));
        registerButton.addActionListener(e -> openRegisterPage());

        add(formPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addImageToWindow() {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(null);
        imagePanel.setBounds(0, 0, getWidth(), getHeight() / 2);

        // 创建一个 ImageIcon 来显示图片
        ImageIcon originalIcon = new ImageIcon("src/main/resources/static/logo.jpg");
        if (originalIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
            // 如果图片加载失败，使用默认文本
            JLabel errorLabel = new JLabel("学生竞赛系统", SwingConstants.CENTER);
            errorLabel.setFont(new Font("宋体", Font.BOLD, 24));
            errorLabel.setBounds(0, 0, getWidth(), getHeight() / 2);
            imagePanel.add(errorLabel);
        } else {
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(230, getHeight() / 2, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage), SwingConstants.CENTER);
            imageLabel.setBounds(0, 0, getWidth(), getHeight() / 2);
            imagePanel.add(imageLabel);
        }

        add(imagePanel);
    }

    private void clientLogin(ActionEvent e) {
        login("客户端");
    }

    private void adminLogin(ActionEvent e) {
        login("管理端");
    }

    private void login(String userType) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userType.equals("客户端") && adminUsername.equals(username) && adminPassword.equals(password)) {
            JOptionPane.showMessageDialog(this, "登录失败，无法使用管理员账号登录客户端");
            return;
        }

        if (isValidUser(username, password)) {
            if ("管理端".equals(userType)) {
                if (adminUsername.equals(username) && adminPassword.equals(password)) {
                    JOptionPane.showMessageDialog(this, "管理员登录成功");
                    openAdminPage();
                } else {
                    JOptionPane.showMessageDialog(this, "登录失败，管理员账号和密码不匹配");
                }
            } else {
                JOptionPane.showMessageDialog(this, "用户登录成功");
                openUserPage();
            }
        } else {
            JOptionPane.showMessageDialog(this, "登录失败，用户名或密码错误");
        }
    }

    private boolean isValidUser(String username, String password) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            return true;
        } else {
            try (Scanner scanner = new Scanner(new File(USER_DATA_FILE))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(":");
                    if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                        return true;
                    }
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "用户文件未找到");
            }
            return false;
        }
    }

    private void openUserPage() {
        UserFrame userframe = new UserFrame(usernameField.getText());
        userframe.setVisible(true);
        this.setVisible(false);
    }

    private void openRegisterPage() {
        RegisterFrame registerFrame = new RegisterFrame();
        registerFrame.setVisible(true);
        this.setVisible(false);
    }

    private void openAdminPage() {
        AdminFrame adminFrame = new AdminFrame();
        adminFrame.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(M1::new);
    }
}