package com.example.studentcompetition;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class AdminFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContentPanel;
    
    // 竞赛数据
    private List<AdminCompetitionData> competitions;
    private DefaultTableModel competitionTableModel;

    public AdminFrame() {
        initializeData();
        createUI();
    }
    
    private void initializeData() {
        // 初始化竞赛数据
        competitions = new ArrayList<>();
        competitions.add(new AdminCompetitionData(1L, "程序设计竞赛", "国家级", "2024-12-30 14:00", "广州大学", 100, 20, "进行中"));
        competitions.add(new AdminCompetitionData(2L, "数学建模竞赛", "省级", "2024-11-20 09:00", "华南理工大学", 80, 15, "已结束"));
        competitions.add(new AdminCompetitionData(3L, "英语演讲比赛", "校级", "2024-10-15 10:00", "广东工业大学", 50, 10, "已结束"));
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
        userManageButton.addActionListener(e -> cardLayout.show(mainContentPanel, "userManage"));
        navPanel.add(Box.createVerticalStrut(20));
        navPanel.add(userManageButton);

        JButton competitionManageButton = new JButton("竞赛管理");
        competitionManageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        competitionManageButton.setPreferredSize(new Dimension(140, 40));
        competitionManageButton.setMargin(new Insets(10, 20, 10, 20));
        competitionManageButton.addActionListener(e -> cardLayout.show(mainContentPanel, "competitionManage"));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(competitionManageButton);

        JButton resultManageButton = new JButton("成绩管理");
        resultManageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultManageButton.setPreferredSize(new Dimension(140, 40));
        resultManageButton.setMargin(new Insets(10, 20, 10, 20));
        resultManageButton.addActionListener(e -> cardLayout.show(mainContentPanel, "resultManage"));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(resultManageButton);

        JButton statisticsButton = new JButton("数据统计");
        statisticsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        statisticsButton.setPreferredSize(new Dimension(140, 40));
        statisticsButton.setMargin(new Insets(10, 20, 10, 20));
        statisticsButton.addActionListener(e -> cardLayout.show(mainContentPanel, "statistics"));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(statisticsButton);

        add(navPanel, BorderLayout.WEST);

        // 创建主内容面板，使用卡片布局
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        
        // 添加各个页面
        mainContentPanel.add(createWelcomePanel(), "welcome");
        mainContentPanel.add(createUserManagePanel(), "userManage");
        mainContentPanel.add(createCompetitionManagePanel(), "competitionManage");
        mainContentPanel.add(createResultManagePanel(), "resultManage");
        mainContentPanel.add(createStatisticsPanel(), "statistics");
        
        add(mainContentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // 创建欢迎页面
    private JPanel createWelcomePanel() {
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
        
        return contentPanel;
    }
    
    // 创建用户管理页面
    private JPanel createUserManagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("用户管理");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // 创建用户表格
        String[] columnNames = {"用户ID", "用户名", "学生ID", "学院", "专业", "状态"};
        Object[][] data = {
            {1, "user1", "202100001", "计算机学院", "计算机科学与技术", "正常"},
            {2, "user2", "202100002", "电子学院", "电子工程", "正常"},
            {3, "user3", "202100003", "机械学院", "机械工程", "待审核"}
        };
        
        JTable table = new JTable(data, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // 底部按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(new JButton("添加用户"));
        buttonPanel.add(new JButton("修改用户"));
        buttonPanel.add(new JButton("删除用户"));
        buttonPanel.add(new JButton("审核用户"));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // 创建竞赛管理页面
    private JPanel createCompetitionManagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("竞赛管理");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // 创建竞赛表格
        String[] columnNames = {"竞赛ID", "名称", "级别", "时间", "地点", "参赛人数", "状态"};
        competitionTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // 填充表格数据
        for (AdminCompetitionData comp : competitions) {
            competitionTableModel.addRow(new Object[]{
                comp.getId(),
                comp.getName(),
                comp.getLevel(),
                comp.getTime(),
                comp.getLocation(),
                comp.getParticipantCount(),
                comp.getStatus()
            });
        }
        
        JTable table = new JTable(competitionTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // 底部按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton addButton = new JButton("添加竞赛");
        addButton.addActionListener(e -> showAddCompetitionDialog());
        buttonPanel.add(addButton);
        
        JButton editButton = new JButton("修改竞赛");
        buttonPanel.add(editButton);
        
        JButton deleteButton = new JButton("删除竞赛");
        buttonPanel.add(deleteButton);
        
        JButton statusButton = new JButton("修改状态");
        buttonPanel.add(statusButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // 创建添加竞赛对话框
    private void showAddCompetitionDialog() {
        JDialog dialog = new JDialog(this, "添加竞赛", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());
        
        // 表单面板
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 竞赛名称
        formPanel.add(new JLabel("竞赛名称: ", SwingConstants.RIGHT));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);
        
        // 竞赛级别
        formPanel.add(new JLabel("竞赛级别: ", SwingConstants.RIGHT));
        String[] levels = {"国家级", "省级", "校级", "院级"};
        JComboBox<String> levelCombo = new JComboBox<>(levels);
        formPanel.add(levelCombo);
        
        // 竞赛时间
        formPanel.add(new JLabel("竞赛时间: ", SwingConstants.RIGHT));
        JTextField timeField = new JTextField();
        timeField.setToolTipText("格式: 2024-12-30 14:00");
        formPanel.add(timeField);
        
        // 竞赛地点
        formPanel.add(new JLabel("竞赛地点: ", SwingConstants.RIGHT));
        JTextField locationField = new JTextField();
        formPanel.add(locationField);
        
        // 参赛人数上限
        formPanel.add(new JLabel("参赛人数上限: ", SwingConstants.RIGHT));
        JTextField participantCountField = new JTextField();
        formPanel.add(participantCountField);
        
        // 获奖人数
        formPanel.add(new JLabel("获奖人数: ", SwingConstants.RIGHT));
        JTextField winnerCountField = new JTextField();
        formPanel.add(winnerCountField);
        
        // 竞赛状态
        formPanel.add(new JLabel("竞赛状态: ", SwingConstants.RIGHT));
        String[] statuses = {"未开始", "进行中", "已结束"};
        JComboBox<String> statusCombo = new JComboBox<>(statuses);
        formPanel.add(statusCombo);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        
        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton confirmButton = new JButton("确定添加");
        confirmButton.addActionListener(e -> {
            // 收集表单数据
            String name = nameField.getText();
            String level = (String) levelCombo.getSelectedItem();
            String time = timeField.getText();
            String location = locationField.getText();
            int participantCount = Integer.parseInt(participantCountField.getText());
            int winnerCount = Integer.parseInt(winnerCountField.getText());
            String status = (String) statusCombo.getSelectedItem();
            
            // 生成新ID
            long newId = competitions.size() + 1;
            
            // 添加到列表
            AdminCompetitionData newComp = new AdminCompetitionData(newId, name, level, time, location, participantCount, winnerCount, status);
            competitions.add(newComp);
            
            // 更新表格
            competitionTableModel.addRow(new Object[]{
                newId, name, level, time, location, participantCount, status
            });
            
            JOptionPane.showMessageDialog(dialog, "竞赛添加成功！");
            dialog.dispose();
        });
        
        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    // 创建成绩管理页面
    private JPanel createResultManagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("成绩管理");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // 成绩表格
        String[] columnNames = {"ID", "竞赛名称", "学生ID", "学生姓名", "学院", "成绩", "排名"};
        Object[][] data = {
            {1, "数学建模竞赛", "202100001", "张三", "计算机学院", "95", "1"},
            {2, "数学建模竞赛", "202100002", "李四", "电子学院", "88", "2"},
            {3, "数学建模竞赛", "202100003", "王五", "机械学院", "82", "3"}
        };
        
        JTable table = new JTable(data, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // 底部按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(new JButton("录入成绩"));
        buttonPanel.add(new JButton("修改成绩"));
        buttonPanel.add(new JButton("导出成绩"));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // 创建数据统计页面
    private JPanel createStatisticsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("数据统计");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // 统计卡片面板
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2, 2, 20, 20));
        
        // 用户统计
        JPanel userStats = new JPanel();
        userStats.setBorder(BorderFactory.createTitledBorder("用户统计"));
        userStats.setLayout(new GridLayout(4, 1, 10, 10));
        userStats.add(new JLabel("• 总用户数: 150"));
        userStats.add(new JLabel("• 活跃用户: 120"));
        userStats.add(new JLabel("• 待审核用户: 5"));
        userStats.add(new JLabel("• 禁用用户: 2"));
        statsPanel.add(userStats);
        
        // 竞赛统计
        JPanel compStats = new JPanel();
        compStats.setBorder(BorderFactory.createTitledBorder("竞赛统计"));
        compStats.setLayout(new GridLayout(4, 1, 10, 10));
        compStats.add(new JLabel("• 总竞赛数: 25"));
        compStats.add(new JLabel("• 进行中竞赛: 8"));
        compStats.add(new JLabel("• 已结束竞赛: 17"));
        compStats.add(new JLabel("• 总参赛人次: 850"));
        statsPanel.add(compStats);
        
        // 成绩统计
        JPanel resultStats = new JPanel();
        resultStats.setBorder(BorderFactory.createTitledBorder("成绩统计"));
        resultStats.setLayout(new GridLayout(4, 1, 10, 10));
        resultStats.add(new JLabel("• 已录入成绩: 620"));
        resultStats.add(new JLabel("• 优秀率(90+): 15%"));
        resultStats.add(new JLabel("• 及格率: 88%"));
        resultStats.add(new JLabel("• 平均成绩: 75.5"));
        statsPanel.add(resultStats);
        
        // 学院统计
        JPanel collegeStats = new JPanel();
        collegeStats.setBorder(BorderFactory.createTitledBorder("学院统计"));
        collegeStats.setLayout(new GridLayout(4, 1, 10, 10));
        collegeStats.add(new JLabel("• 计算机学院: 320人次"));
        collegeStats.add(new JLabel("• 电子学院: 210人次"));
        collegeStats.add(new JLabel("• 机械学院: 180人次"));
        collegeStats.add(new JLabel("• 其他学院: 140人次"));
        statsPanel.add(collegeStats);
        
        panel.add(statsPanel, BorderLayout.CENTER);
        
        return panel;
    }

    private void logout(ActionEvent e) {
        this.dispose();
        new M1();
    }
    
    // 内部类：管理员竞赛数据
    class AdminCompetitionData {
        private Long id;
        private String name;
        private String level;
        private String time;
        private String location;
        private int participantCount;
        private int winnerCount;
        private String status;
        
        public AdminCompetitionData(Long id, String name, String level, String time, String location, int participantCount, int winnerCount, String status) {
            this.id = id;
            this.name = name;
            this.level = level;
            this.time = time;
            this.location = location;
            this.participantCount = participantCount;
            this.winnerCount = winnerCount;
            this.status = status;
        }
        
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getLevel() { return level; }
        public String getTime() { return time; }
        public String getLocation() { return location; }
        public int getParticipantCount() { return participantCount; }
        public int getWinnerCount() { return winnerCount; }
        public String getStatus() { return status; }
    }
}