package com.example.studentcompetition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {
    public AdminFrame() {
        createUI();
    }

    private void createUI() {
        setTitle("管理员中心");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建顶部面板
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setPreferredSize(new Dimension(900, 60));
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel welcomeLabel = new JLabel("管理员欢迎您！");
        welcomeLabel.setFont(new Font("宋体", Font.PLAIN, 16));
        topPanel.add(welcomeLabel);

        JButton logoutButton = new JButton("退出登录");
        logoutButton.addActionListener(this::logout);
        topPanel.add(logoutButton);

        add(topPanel, BorderLayout.NORTH);

        // 创建左侧导航面板
        JPanel navPanel = new JPanel();
        navPanel.setPreferredSize(new Dimension(180, 540));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        JButton userManageButton = new JButton("用户管理");
        userManageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        userManageButton.setPreferredSize(new Dimension(140, 40));
        userManageButton.setMargin(new Insets(10, 20, 10, 20));
        navPanel.add(Box.createVerticalStrut(20));
        navPanel.add(userManageButton);

        JButton competitionManageButton = new JButton("竞赛管理");
        competitionManageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        competitionManageButton.setPreferredSize(new Dimension(140, 40));
        competitionManageButton.setMargin(new Insets(10, 20, 10, 20));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(competitionManageButton);

        JButton resultManageButton = new JButton("成绩管理");
        resultManageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultManageButton.setPreferredSize(new Dimension(140, 40));
        resultManageButton.setMargin(new Insets(10, 20, 10, 20));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(resultManageButton);

        JButton statisticsButton = new JButton("数据统计");
        statisticsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        statisticsButton.setPreferredSize(new Dimension(140, 40));
        statisticsButton.setMargin(new Insets(10, 20, 10, 20));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(statisticsButton);

        add(navPanel, BorderLayout.WEST);

        // 创建主内容面板
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("管理员控制台");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // 创建功能卡片面板
        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new GridLayout(2, 2, 20, 20));

        // 用户管理卡片
        JPanel userCard = new JPanel();
        userCard.setBorder(BorderFactory.createTitledBorder("用户管理"));
        userCard.setLayout(new GridLayout(3, 1, 10, 10));
        userCard.add(new JLabel("• 用户注册审核"));
        userCard.add(new JLabel("• 用户信息管理"));
        userCard.add(new JLabel("• 用户权限设置"));
        functionPanel.add(userCard);

        // 竞赛管理卡片
        JPanel competitionCard = new JPanel();
        competitionCard.setBorder(BorderFactory.createTitledBorder("竞赛管理"));
        competitionCard.setLayout(new GridLayout(3, 1, 10, 10));
        competitionCard.add(new JLabel("• 竞赛发布"));
        competitionCard.add(new JLabel("• 竞赛信息修改"));
        competitionCard.add(new JLabel("• 竞赛状态管理"));
        functionPanel.add(competitionCard);

        // 成绩管理卡片
        JPanel resultCard = new JPanel();
        resultCard.setBorder(BorderFactory.createTitledBorder("成绩管理"));
        resultCard.setLayout(new GridLayout(3, 1, 10, 10));
        resultCard.add(new JLabel("• 成绩录入"));
        resultCard.add(new JLabel("• 成绩查询"));
        resultCard.add(new JLabel("• 成绩统计"));
        functionPanel.add(resultCard);

        // 数据统计卡片
        JPanel statisticsCard = new JPanel();
        statisticsCard.setBorder(BorderFactory.createTitledBorder("数据统计"));
        statisticsCard.setLayout(new GridLayout(3, 1, 10, 10));
        statisticsCard.add(new JLabel("• 用户数据统计"));
        statisticsCard.add(new JLabel("• 竞赛参与统计"));
        statisticsCard.add(new JLabel("• 成绩分析"));
        functionPanel.add(statisticsCard);

        contentPanel.add(functionPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void logout(ActionEvent e) {
        this.dispose();
        new M1();
    }
}