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

public class UserFrame extends JFrame {
    private String username;
    private CardLayout cardLayout;
    private JPanel mainContentPanel;
    
    // 竞赛数据
    private List<CompetitionData> competitions;
    private List<ParticipationData> participations;

    public UserFrame(String username) {
        this.username = username;
        initializeData();
        createUI();
    }
    
    private void initializeData() {
        // 初始化竞赛数据
        competitions = new ArrayList<>();
        competitions.add(new CompetitionData(1L, "程序设计竞赛", "国家级", "2024-12-30 14:00", "广州大学", 100, 20));
        competitions.add(new CompetitionData(2L, "数学建模竞赛", "省级", "2024-11-20 09:00", "华南理工大学", 80, 15));
        competitions.add(new CompetitionData(3L, "英语演讲比赛", "校级", "2024-10-15 10:00", "广东工业大学", 50, 10));
        competitions.add(new CompetitionData(4L, "机器人竞赛", "国家级", "2024-11-25 08:30", "中山大学", 120, 25));
        competitions.add(new CompetitionData(5L, "创业计划竞赛", "省级", "2024-12-05 13:30", "深圳大学", 60, 12));
        competitions.add(new CompetitionData(6L, "电子设计竞赛", "国家级", "2024-11-10 09:00", "哈尔滨工业大学", 90, 18));
        
        // 初始化参赛数据
        participations = new ArrayList<>();
        participations.add(new ParticipationData(1L, "程序设计竞赛", "国家级", "2024-12-30 14:00", "已报名", "广州大学"));
        participations.add(new ParticipationData(2L, "数学建模竞赛", "省级", "2024-11-20 09:00", "已完成", "华南理工大学"));
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
        competitionListButton.addActionListener(e -> cardLayout.show(mainContentPanel, "competitionList"));
        navPanel.add(Box.createVerticalStrut(20));
        navPanel.add(competitionListButton);

        JButton myParticipationButton = new JButton("我的参赛");
        myParticipationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        myParticipationButton.setPreferredSize(new Dimension(120, 40));
        myParticipationButton.setMargin(new Insets(10, 20, 10, 20));
        myParticipationButton.addActionListener(e -> cardLayout.show(mainContentPanel, "myParticipation"));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(myParticipationButton);

        JButton personalInfoButton = new JButton("个人信息");
        personalInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        personalInfoButton.setPreferredSize(new Dimension(120, 40));
        personalInfoButton.setMargin(new Insets(10, 20, 10, 20));
        personalInfoButton.addActionListener(e -> cardLayout.show(mainContentPanel, "personalInfo"));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(personalInfoButton);

        JButton addCompetitionButton = new JButton("添加竞赛");
        addCompetitionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCompetitionButton.setPreferredSize(new Dimension(120, 40));
        addCompetitionButton.setMargin(new Insets(10, 20, 10, 20));
        addCompetitionButton.addActionListener(e -> cardLayout.show(mainContentPanel, "addCompetition"));
        navPanel.add(Box.createVerticalStrut(10));
        navPanel.add(addCompetitionButton);

        add(navPanel, BorderLayout.WEST);

        // 创建主内容面板，使用卡片布局
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        
        // 添加各个页面
        mainContentPanel.add(createCompetitionListPanel(), "competitionList");
        mainContentPanel.add(createMyParticipationPanel(), "myParticipation");
        mainContentPanel.add(createPersonalInfoPanel(), "personalInfo");
        mainContentPanel.add(createAddCompetitionPanel(), "addCompetition");
        
        add(mainContentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // 创建竞赛列表页面
    private JPanel createCompetitionListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("竞赛列表");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // 创建竞赛列表区域
        JPanel competitionPanel = new JPanel();
        competitionPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // 添加竞赛卡片
        for (CompetitionData comp : competitions) {
            JPanel card = new JPanel();
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            card.setLayout(new BorderLayout());
            card.setPreferredSize(new Dimension(200, 180));

            JLabel compTitle = new JLabel(comp.getName(), SwingConstants.CENTER);
            compTitle.setFont(new Font("宋体", Font.BOLD, 16));
            card.add(compTitle, BorderLayout.NORTH);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new GridLayout(4, 1, 5, 5));
            infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            infoPanel.add(new JLabel("级别: " + comp.getLevel()));
            infoPanel.add(new JLabel("时间: " + comp.getTime()));
            infoPanel.add(new JLabel("地点: " + comp.getLocation()));
            infoPanel.add(new JLabel("参赛人数: " + comp.getParticipantCount()));
            card.add(infoPanel, BorderLayout.CENTER);

            JButton joinButton = new JButton("报名参赛");
            CompetitionData currentComp = comp;
            joinButton.addActionListener(e -> {
                // 检查是否已经报名
                boolean alreadyJoined = false;
                for (ParticipationData part : participations) {
                    if (part.getCompetitionName().equals(currentComp.getName())) {
                        alreadyJoined = true;
                        break;
                    }
                }
                
                if (alreadyJoined) {
                    JOptionPane.showMessageDialog(this, "您已经报名过该竞赛！");
                    return;
                }
                
                // 添加到参赛列表
                ParticipationData newPart = new ParticipationData(
                    (long) (participations.size() + 1),
                    currentComp.getName(),
                    currentComp.getLevel(),
                    currentComp.getTime(),
                    "已报名",
                    currentComp.getLocation()
                );
                participations.add(newPart);
                
                JOptionPane.showMessageDialog(this, "报名成功！");
                // 这里可以添加刷新参赛列表的逻辑
            });
            card.add(joinButton, BorderLayout.SOUTH);

            competitionPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(competitionPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // 创建我的参赛页面
    private JPanel createMyParticipationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("我的参赛");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // 创建参赛列表表格
        String[] columnNames = {"竞赛名称", "级别", "时间", "状态", "地点"};
        Object[][] data = new Object[participations.size()][5];
        
        for (int i = 0; i < participations.size(); i++) {
            ParticipationData part = participations.get(i);
            data[i][0] = part.getCompetitionName();
            data[i][1] = part.getLevel();
            data[i][2] = part.getTime();
            data[i][3] = part.getStatus();
            data[i][4] = part.getLocation();
        }
        
        JTable table = new JTable(data, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // 创建个人信息页面
    private JPanel createPersonalInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("个人信息");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // 创建信息表单
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 2, 20, 20));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        infoPanel.add(new JLabel("用户名: ", SwingConstants.RIGHT));
        infoPanel.add(new JLabel(username));
        
        infoPanel.add(new JLabel("学生ID: ", SwingConstants.RIGHT));
        infoPanel.add(new JLabel("202100001"));
        
        infoPanel.add(new JLabel("学院: ", SwingConstants.RIGHT));
        infoPanel.add(new JLabel("计算机学院"));
        
        infoPanel.add(new JLabel("专业: ", SwingConstants.RIGHT));
        infoPanel.add(new JLabel("计算机科学与技术"));
        
        infoPanel.add(new JLabel("邮箱: ", SwingConstants.RIGHT));
        infoPanel.add(new JLabel(username + "@example.com"));
        
        panel.add(infoPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    // 创建添加竞赛页面
    private JPanel createAddCompetitionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("添加竞赛");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        JButton addButton = new JButton("创建新竞赛");
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.addActionListener(e -> showAddCompetitionDialog());
        buttonPanel.add(addButton);
        
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        return panel;
    }

    private void logout(ActionEvent e) {
        this.dispose();
        new M1();
    }
    
    // 显示添加竞赛对话框
    private void showAddCompetitionDialog() {
        JDialog dialog = new JDialog(this, "创建新竞赛", true);
        dialog.setSize(500, 450);
        dialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 竞赛名称
        formPanel.add(new JLabel("竞赛名称: ", SwingConstants.RIGHT));
        final JTextField nameField = new JTextField();
        formPanel.add(nameField);
        
        // 竞赛级别
        formPanel.add(new JLabel("竞赛级别: ", SwingConstants.RIGHT));
        String[] levels = {"校级", "省级", "国家级"};
        final JComboBox<String> levelCombo = new JComboBox<>(levels);
        formPanel.add(levelCombo);
        
        // 竞赛时间
        formPanel.add(new JLabel("竞赛时间: ", SwingConstants.RIGHT));
        final JTextField timeField = new JTextField("2024-12-31 09:00");
        formPanel.add(timeField);
        
        // 竞赛地点
        formPanel.add(new JLabel("竞赛地点: ", SwingConstants.RIGHT));
        final JTextField locationField = new JTextField();
        formPanel.add(locationField);
        
        // 参赛人数限制
        formPanel.add(new JLabel("参赛人数限制: ", SwingConstants.RIGHT));
        final JTextField participantCountField = new JTextField("50");
        formPanel.add(participantCountField);
        
        // 获奖人数
        formPanel.add(new JLabel("获奖人数: ", SwingConstants.RIGHT));
        final JTextField winnerCountField = new JTextField("10");
        formPanel.add(winnerCountField);
        
        // 竞赛描述
        formPanel.add(new JLabel("竞赛描述: ", SwingConstants.RIGHT));
        final JTextField descriptionField = new JTextField();
        formPanel.add(descriptionField);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton confirmButton = new JButton("创建竞赛");
        confirmButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String level = (String) levelCombo.getSelectedItem();
            String time = timeField.getText().trim();
            String location = locationField.getText().trim();
            String participantCountStr = participantCountField.getText().trim();
            String winnerCountStr = winnerCountField.getText().trim();
            String description = descriptionField.getText().trim();
            
            // 验证输入
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "竞赛名称不能为空");
                return;
            }
            
            if (time.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "竞赛时间不能为空");
                return;
            }
            
            if (location.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "竞赛地点不能为空");
                return;
            }
            
            int participantCount;
            int winnerCount;
            
            try {
                participantCount = Integer.parseInt(participantCountStr);
                if (participantCount <= 0) {
                    JOptionPane.showMessageDialog(dialog, "参赛人数限制必须是正整数");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "参赛人数限制必须是数字");
                return;
            }
            
            try {
                winnerCount = Integer.parseInt(winnerCountStr);
                if (winnerCount <= 0 || winnerCount > participantCount) {
                    JOptionPane.showMessageDialog(dialog, "获奖人数必须是正整数且不超过参赛人数");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "获奖人数必须是数字");
                return;
            }
            
            // 检查竞赛名称是否重复
            for (CompetitionData existingComp : competitions) {
                if (existingComp.getName().equals(name)) {
                    JOptionPane.showMessageDialog(dialog, "竞赛名称已存在，请使用其他名称");
                    return;
                }
            }
            
            // 创建新竞赛
            long newId = competitions.size() + 1;
            CompetitionData newCompetition = new CompetitionData(
                newId, name, level, time, location, participantCount, winnerCount
            );
            competitions.add(newCompetition);
            
            JOptionPane.showMessageDialog(dialog, "竞赛创建成功！");
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
    
    // 内部类：竞赛数据
    class CompetitionData {
        private Long id;
        private String name;
        private String level;
        private String time;
        private String location;
        private int participantCount;
        private int winnerCount;
        
        public CompetitionData(Long id, String name, String level, String time, String location, int participantCount, int winnerCount) {
            this.id = id;
            this.name = name;
            this.level = level;
            this.time = time;
            this.location = location;
            this.participantCount = participantCount;
            this.winnerCount = winnerCount;
        }
        
        public String getName() { return name; }
        public String getLevel() { return level; }
        public String getTime() { return time; }
        public String getLocation() { return location; }
        public int getParticipantCount() { return participantCount; }
    }
    
    // 内部类：参赛数据
    class ParticipationData {
        private Long id;
        private String competitionName;
        private String level;
        private String time;
        private String status;
        private String location;
        
        public ParticipationData(Long id, String competitionName, String level, String time, String status, String location) {
            this.id = id;
            this.competitionName = competitionName;
            this.level = level;
            this.time = time;
            this.status = status;
            this.location = location;
        }
        
        public String getCompetitionName() { return competitionName; }
        public String getLevel() { return level; }
        public String getTime() { return time; }
        public String getStatus() { return status; }
        public String getLocation() { return location; }
    }
}