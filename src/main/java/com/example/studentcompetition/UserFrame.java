package com.example.studentcompetition;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
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

        add(navPanel, BorderLayout.WEST);

        // 创建主内容面板，使用卡片布局
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        
        // 添加各个页面
        mainContentPanel.add(createCompetitionListPanel(), "competitionList");
        mainContentPanel.add(createMyParticipationPanel(), "myParticipation");
        mainContentPanel.add(createPersonalInfoPanel(), "personalInfo");
        
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
            joinButton.addActionListener(e -> showJoinCompetitionDialog(currentComp));
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
        panel.setName("myParticipation");

        JLabel titleLabel = new JLabel("我的参赛");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // 创建参赛列表面板
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        
        if (participations.isEmpty()) {
            JLabel emptyLabel = new JLabel("暂无参赛记录");
            emptyLabel.setFont(new Font("宋体", Font.PLAIN, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(Box.createVerticalGlue());
            listPanel.add(emptyLabel);
            listPanel.add(Box.createVerticalGlue());
        } else {
            for (ParticipationData part : participations) {
                JPanel participationCard = createParticipationCard(part);
                listPanel.add(participationCard);
                listPanel.add(Box.createVerticalStrut(10));
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // 创建参赛卡片
    private JPanel createParticipationCard(ParticipationData part) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setPreferredSize(new Dimension(500, 150));
        
        // 左侧信息区域
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 2, 10, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        
        infoPanel.add(new JLabel("竞赛名称: "));
        infoPanel.add(new JLabel(part.getCompetitionName()));
        infoPanel.add(new JLabel("级别: "));
        infoPanel.add(new JLabel(part.getLevel()));
        infoPanel.add(new JLabel("时间: "));
        infoPanel.add(new JLabel(part.getTime()));
        
        card.add(infoPanel, BorderLayout.WEST);
        
        // 右侧状态和管理区域
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        
        // 状态显示
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel statusLabel = new JLabel("状态: " + part.getStatus());
        statusLabel.setFont(new Font("宋体", Font.BOLD, 14));
        
        // 根据状态设置不同颜色
        if ("已报名".equals(part.getStatus())) {
            statusLabel.setForeground(new Color(0, 128, 0)); // 绿色
        } else if ("进行中".equals(part.getStatus())) {
            statusLabel.setForeground(new Color(255, 140, 0)); // 橙色
        } else if ("已完成".equals(part.getStatus())) {
            statusLabel.setForeground(new Color(0, 0, 139)); // 深蓝色
        }
        statusPanel.add(statusLabel);
        rightPanel.add(statusPanel, BorderLayout.NORTH);
        
        // 管理按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton detailButton = new JButton("查看详情");
        detailButton.addActionListener(e -> showParticipationDetail(part));
        buttonPanel.add(detailButton);
        
        if ("已报名".equals(part.getStatus())) {
            JButton cancelButton = new JButton("取消报名");
            cancelButton.setForeground(Color.RED);
            cancelButton.addActionListener(e -> cancelParticipation(part));
            buttonPanel.add(cancelButton);
        }
        
        rightPanel.add(buttonPanel, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);
        
        return card;
    }
    
    // 刷新"我的参赛"页面
    private void refreshMyParticipationPanel() {
        // 查找并移除旧的"我的参赛"页面
        Component[] components = mainContentPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel && "myParticipation".equals(((JPanel) comp).getName())) {
                mainContentPanel.remove(comp);
                break;
            }
        }
        
        // 重新创建并添加页面
        JPanel newPanel = createMyParticipationPanel();
        mainContentPanel.add(newPanel, "myParticipation");
        
        // 重新布局
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
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
    
    // 显示报名参赛对话框
    private void showJoinCompetitionDialog(CompetitionData competition) {
        JDialog dialog = new JDialog(this, "报名参赛 - " + competition.getName(), true);
        dialog.setSize(500, 450);
        dialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        
        // 竞赛信息（只读显示）
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("竞赛名称: "), gbc);
        gbc.gridx = 1;
        formPanel.add(new JLabel(competition.getName()), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("竞赛级别: "), gbc);
        gbc.gridx = 1;
        formPanel.add(new JLabel(competition.getLevel()), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("竞赛时间: "), gbc);
        gbc.gridx = 1;
        formPanel.add(new JLabel(competition.getTime()), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("竞赛地点: "), gbc);
        gbc.gridx = 1;
        formPanel.add(new JLabel(competition.getLocation()), gbc);
        
        // 学生信息
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("学生ID: "), gbc);
        gbc.gridx = 1;
        final JTextField studentIdField = new JTextField("202100001");
        formPanel.add(studentIdField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("学生姓名: "), gbc);
        gbc.gridx = 1;
        final JTextField studentNameField = new JTextField(username);
        formPanel.add(studentNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("所属学院: "), gbc);
        gbc.gridx = 1;
        final JTextField collegeField = new JTextField("计算机学院");
        formPanel.add(collegeField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("联系电话: "), gbc);
        gbc.gridx = 1;
        final JTextField phoneField = new JTextField();
        formPanel.add(phoneField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 8;
        formPanel.add(new JLabel("电子邮箱: "), gbc);
        gbc.gridx = 1;
        final JTextField emailField = new JTextField(username + "@example.com");
        formPanel.add(emailField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 9;
        formPanel.add(new JLabel("参赛理由: "), gbc);
        gbc.gridx = 1;
        final JTextArea reasonArea = new JTextArea(3, 20);
        reasonArea.setLineWrap(true);
        reasonArea.setWrapStyleWord(true);
        JScrollPane reasonScrollPane = new JScrollPane(reasonArea);
        formPanel.add(reasonScrollPane, gbc);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton confirmButton = new JButton("确认报名");
        confirmButton.addActionListener(e -> {
            String studentId = studentIdField.getText().trim();
            String studentName = studentNameField.getText().trim();
            String college = collegeField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String reason = reasonArea.getText().trim();
            
            // 验证输入
            if (studentId.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "学生ID不能为空");
                return;
            }
            
            if (studentName.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "学生姓名不能为空");
                return;
            }
            
            if (college.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "所属学院不能为空");
                return;
            }
            
            if (phone.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "联系电话不能为空");
                return;
            }
            
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "电子邮箱不能为空");
                return;
            }
            
            if (reason.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "参赛理由不能为空");
                return;
            }
            
            // 检查是否已经报名
            boolean alreadyJoined = false;
            for (ParticipationData part : participations) {
                if (part.getCompetitionName().equals(competition.getName())) {
                    alreadyJoined = true;
                    break;
                }
            }
            
            if (alreadyJoined) {
                JOptionPane.showMessageDialog(dialog, "您已经报名过该竞赛！");
                return;
            }
            
            // 添加到参赛列表
            String currentTime = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String studentInfo = studentName + " (ID: " + studentId + ")";
            String contactInfo = phone + " | " + email;
            
            ParticipationData newPart = new ParticipationData(
                (long) (participations.size() + 1),
                competition.getName(),
                competition.getLevel(),
                competition.getTime(),
                "已报名",
                competition.getLocation(),
                currentTime,
                studentInfo,
                contactInfo,
                reason
            );
            participations.add(newPart);
            
            JOptionPane.showMessageDialog(dialog, "报名成功！");
            dialog.dispose();
            
            // 刷新"我的参赛"页面
            refreshMyParticipationPanel();
        });
        
        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void logout(ActionEvent e) {
        this.dispose();
        new M1();
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
        private String registerTime;
        private String studentInfo;
        private String contactInfo;
        private String reason;
        
        public ParticipationData(Long id, String competitionName, String level, String time, String status, String location) {
            this.id = id;
            this.competitionName = competitionName;
            this.level = level;
            this.time = time;
            this.status = status;
            this.location = location;
            this.registerTime = "已报名";
            this.studentInfo = "";
            this.contactInfo = "";
            this.reason = "";
        }
        
        public ParticipationData(Long id, String competitionName, String level, String time, String status, 
                               String location, String registerTime, String studentInfo, String contactInfo, String reason) {
            this.id = id;
            this.competitionName = competitionName;
            this.level = level;
            this.time = time;
            this.status = status;
            this.location = location;
            this.registerTime = registerTime;
            this.studentInfo = studentInfo;
            this.contactInfo = contactInfo;
            this.reason = reason;
        }
        
        public String getCompetitionName() { return competitionName; }
        public String getLevel() { return level; }
        public String getTime() { return time; }
        public String getStatus() { return status; }
        public String getLocation() { return location; }
        public String getRegisterTime() { return registerTime; }
        public String getStudentInfo() { return studentInfo; }
        public String getContactInfo() { return contactInfo; }
        public String getReason() { return reason; }
        public Long getId() { return id; }
        
        public void setStatus(String status) { this.status = status; }
    }
    
    // 查看参赛详情
    private void showParticipationDetail(ParticipationData part) {
        JDialog dialog = new JDialog(this, "参赛详情", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 创建详情内容面板
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // 竞赛信息
        gbc.gridx = 0; gbc.gridy = 0;
        detailPanel.add(new JLabel("竞赛名称:"), gbc);
        gbc.gridx = 1;
        detailPanel.add(new JLabel(part.getCompetitionName()), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        detailPanel.add(new JLabel("竞赛级别:"), gbc);
        gbc.gridx = 1;
        detailPanel.add(new JLabel(part.getLevel()), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        detailPanel.add(new JLabel("竞赛时间:"), gbc);
        gbc.gridx = 1;
        detailPanel.add(new JLabel(part.getTime()), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        detailPanel.add(new JLabel("竞赛地点:"), gbc);
        gbc.gridx = 1;
        detailPanel.add(new JLabel(part.getLocation()), gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        detailPanel.add(new JLabel("参赛状态:"), gbc);
        gbc.gridx = 1;
        JLabel statusLabel = new JLabel(part.getStatus());
        if ("已报名".equals(part.getStatus())) {
            statusLabel.setForeground(new Color(0, 128, 0));
        } else if ("进行中".equals(part.getStatus())) {
            statusLabel.setForeground(new Color(255, 140, 0));
        } else if ("已完成".equals(part.getStatus())) {
            statusLabel.setForeground(new Color(0, 0, 139));
        }
        detailPanel.add(statusLabel, gbc);
        
        // 添加分隔线
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(new JSeparator(), gbc);
        
        // 报名信息
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        detailPanel.add(new JLabel("报名时间:"), gbc);
        gbc.gridx = 1;
        detailPanel.add(new JLabel(part.getRegisterTime()), gbc);
        
        contentPanel.add(detailPanel, BorderLayout.CENTER);
        
        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeButton);
        
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(contentPanel);
        
        dialog.setVisible(true);
    }
    
    // 取消报名
    private void cancelParticipation(ParticipationData part) {
        int result = JOptionPane.showConfirmDialog(
            this,
            "确定要取消报名 '" + part.getCompetitionName() + "' 吗？",
            "确认取消报名",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            // 从参赛列表中移除
            participations.remove(part);
            
            JOptionPane.showMessageDialog(this, "已取消报名！");
            
            // 刷新页面
            refreshMyParticipationPanel();
        }
    }
}