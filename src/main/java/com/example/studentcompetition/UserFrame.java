package com.example.studentcompetition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {
    private String username;

    public UserFrame(String username) {
        this.username = username;
        createUI();
    }

    private void createUI() {
        setTitle("用户中心 - " + username);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建顶部面板
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setPreferredSize(new Dimension(800, 60));
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel welcomeLabel = new JLabel("欢迎，" + username + "!");
        welcomeLabel.setFont(new Font("宋体", Font.PLAIN, 16));
        topPanel.add(welcomeLabel);

        JButton logoutButton = new JButton("退出登录");
        logoutButton.addActionListener(this::logout);
        topPanel.add(logoutButton);

        add(topPanel, BorderLayout.NORTH);

        // 创建左侧导航面板
        JPanel navPanel = new JPanel();
        navPanel.setPreferredSize(new Dimension(150, 540));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        JButton competitionListButton = new JButton("竞赛列表");
        competitionListButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        competitionListButton.setPreferredSize(new Dimension(120, 40));
        competitionListButton.setMargin(new Insets(10, 20, 10, 20));
        navPanel.add(Box.createVerticalStrut(20));
        navPanel.add(competitionListButton);

        JButton myParticipationButton = new JButton("我的参赛");
        myParticipationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        myParticipationButton.setPreferredSize(new Dimension(120, 40));
        myParticipationButton.setMargin(new Insets(10, 20, 10, 20));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(myParticipationButton);

        JButton personalInfoButton = new JButton("个人信息");
        personalInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        personalInfoButton.setPreferredSize(new Dimension(120, 40));
        personalInfoButton.setMargin(new Insets(10, 20, 10, 20));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(personalInfoButton);

        add(navPanel, BorderLayout.WEST);

        // 创建主内容面板
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("竞赛列表");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // 创建竞赛列表区域
        JPanel competitionPanel = new JPanel();
        competitionPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // 添加模拟竞赛卡片
        for (int i = 1; i <= 6; i++) {
            JPanel card = new JPanel();
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            card.setLayout(new BorderLayout());
            card.setPreferredSize(new Dimension(200, 150));

            JLabel compTitle = new JLabel("竞赛名称 " + i, SwingConstants.CENTER);
            compTitle.setFont(new Font("宋体", Font.BOLD, 16));
            card.add(compTitle, BorderLayout.NORTH);

            JLabel compDesc = new JLabel("这是竞赛描述 " + i, SwingConstants.CENTER);
            card.add(compDesc, BorderLayout.CENTER);

            JButton joinButton = new JButton("报名参赛");
            joinButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "报名成功！"));
            card.add(joinButton, BorderLayout.SOUTH);

            competitionPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(competitionPanel);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void logout(ActionEvent e) {
        this.dispose();
        new M1();
    }
}