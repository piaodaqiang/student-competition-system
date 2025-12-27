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
import java.awt.event.MouseAdapter;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AdminFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContentPanel;
    
    // 竞赛数据
    private List<AdminCompetitionData> competitions;
    private DefaultTableModel competitionTableModel;
    
    // 当前编辑的竞赛信息
    private Long currentEditingCompetitionId;
    private AdminCompetitionData currentEditingCompetition;
    
    // 参赛人员数据
    private List<ParticipantData> participants;
    private DefaultTableModel participantTableModel;

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
        
        // 初始化参赛人员数据
        participants = new ArrayList<>();
        participants.add(new ParticipantData(1L, 1L, "202100001", "张三", "计算机学院", "计算机科学与技术", "已确认"));
        participants.add(new ParticipantData(2L, 1L, "202100002", "李四", "电子学院", "电子工程", "已确认"));
        participants.add(new ParticipantData(3L, 2L, "202100003", "王五", "机械学院", "机械工程", "已确认"));
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
        mainContentPanel.add(createCompetitionEditPanel(), "competitionEdit");
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
        
        // 创建竞赛表格，添加操作列
        String[] columnNames = {"竞赛ID", "名称", "级别", "时间", "地点", "参赛人数", "状态", "操作"};
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
                comp.getStatus(),
                "编辑"
            });
        }
        
        final JTable table = new JTable(competitionTableModel);
        
        // 设置操作列宽度
        table.getColumnModel().getColumn(7).setPreferredWidth(80);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        
        // 设置操作列居中对齐
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        
        // 添加表格行点击事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int column = table.getColumnModel().getColumnIndexAtX(evt.getX());
                int row = evt.getY() / table.getRowHeight();
                
                if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                    if (column == 7) { // 编辑按钮点击
                        // 获取当前编辑的竞赛信息
                        long compId = (long) competitionTableModel.getValueAt(row, 0);
                        currentEditingCompetitionId = compId;
                        
                        // 查找竞赛对象
                        for (AdminCompetitionData comp : competitions) {
                            if (comp.getId().equals(compId)) {
                                currentEditingCompetition = comp;
                                break;
                            }
                        }
                        
                        // 切换到竞赛编辑页面
                        cardLayout.show(mainContentPanel, "competitionEdit");
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // 底部按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton addButton = new JButton("添加竞赛");
        addButton.addActionListener(e -> showAddCompetitionDialog());
        buttonPanel.add(addButton);
        
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
        final JTextField nameField = new JTextField();
        formPanel.add(nameField);
        
        // 竞赛级别
        formPanel.add(new JLabel("竞赛级别: ", SwingConstants.RIGHT));
        String[] levels = {"国家级", "省级", "校级", "院级"};
        final JComboBox<String> levelCombo = new JComboBox<>(levels);
        formPanel.add(levelCombo);
        
        // 竞赛时间
        formPanel.add(new JLabel("竞赛时间: ", SwingConstants.RIGHT));
        final JTextField timeField = new JTextField();
        timeField.setToolTipText("格式: 2024-12-30 14:00");
        formPanel.add(timeField);
        
        // 竞赛地点
        formPanel.add(new JLabel("竞赛地点: ", SwingConstants.RIGHT));
        final JTextField locationField = new JTextField();
        formPanel.add(locationField);
        
        // 参赛人数上限
        formPanel.add(new JLabel("参赛人数上限: ", SwingConstants.RIGHT));
        final JTextField participantCountField = new JTextField();
        formPanel.add(participantCountField);
        
        // 获奖人数
        formPanel.add(new JLabel("获奖人数: ", SwingConstants.RIGHT));
        final JTextField winnerCountField = new JTextField();
        formPanel.add(winnerCountField);
        
        // 竞赛状态
        formPanel.add(new JLabel("竞赛状态: ", SwingConstants.RIGHT));
        String[] statuses = {"未开始", "进行中", "已结束"};
        final JComboBox<String> statusCombo = new JComboBox<>(statuses);
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
                newId, name, level, time, location, participantCount, status, "编辑"
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
    
    // 创建修改竞赛对话框
    private void showEditCompetitionDialog(int selectedRow) {
        JDialog dialog = new JDialog(this, "修改竞赛", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());
        
        // 获取选中的竞赛数据
        long id = (long) competitionTableModel.getValueAt(selectedRow, 0);
        String currentName = (String) competitionTableModel.getValueAt(selectedRow, 1);
        String currentLevel = (String) competitionTableModel.getValueAt(selectedRow, 2);
        String currentTime = (String) competitionTableModel.getValueAt(selectedRow, 3);
        String currentLocation = (String) competitionTableModel.getValueAt(selectedRow, 4);
        int currentParticipantCount = (int) competitionTableModel.getValueAt(selectedRow, 5);
        String currentStatus = (String) competitionTableModel.getValueAt(selectedRow, 6);
        
        // 找到对应的竞赛对象
        final AdminCompetitionData[] selectedCompArray = new AdminCompetitionData[1];
        for (AdminCompetitionData comp : competitions) {
            if (comp.getId() == id) {
                selectedCompArray[0] = comp;
                break;
            }
        }
        
        // 表单面板
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 竞赛名称
        formPanel.add(new JLabel("竞赛名称: ", SwingConstants.RIGHT));
        final JTextField nameField = new JTextField(currentName);
        formPanel.add(nameField);
        
        // 竞赛级别
        formPanel.add(new JLabel("竞赛级别: ", SwingConstants.RIGHT));
        String[] levels = {"国家级", "省级", "校级", "院级"};
        final JComboBox<String> levelCombo = new JComboBox<>(levels);
        levelCombo.setSelectedItem(currentLevel);
        formPanel.add(levelCombo);
        
        // 竞赛时间
        formPanel.add(new JLabel("竞赛时间: ", SwingConstants.RIGHT));
        final JTextField timeField = new JTextField(currentTime);
        timeField.setToolTipText("格式: 2024-12-30 14:00");
        formPanel.add(timeField);
        
        // 竞赛地点
        formPanel.add(new JLabel("竞赛地点: ", SwingConstants.RIGHT));
        final JTextField locationField = new JTextField(currentLocation);
        formPanel.add(locationField);
        
        // 参赛人数上限
        formPanel.add(new JLabel("参赛人数上限: ", SwingConstants.RIGHT));
        final JTextField participantCountField = new JTextField(String.valueOf(currentParticipantCount));
        formPanel.add(participantCountField);
        
        // 获奖人数
        int currentWinnerCount = selectedCompArray[0] != null ? selectedCompArray[0].getWinnerCount() : 0;
        formPanel.add(new JLabel("获奖人数: ", SwingConstants.RIGHT));
        final JTextField winnerCountField = new JTextField(String.valueOf(currentWinnerCount));
        formPanel.add(winnerCountField);
        
        // 竞赛状态
        formPanel.add(new JLabel("竞赛状态: ", SwingConstants.RIGHT));
        String[] statuses = {"未开始", "进行中", "已结束"};
        final JComboBox<String> statusCombo = new JComboBox<>(statuses);
        statusCombo.setSelectedItem(currentStatus);
        formPanel.add(statusCombo);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        
        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton confirmButton = new JButton("确定修改");
        confirmButton.addActionListener(e -> {
            // 收集表单数据
            String name = nameField.getText();
            String level = (String) levelCombo.getSelectedItem();
            String time = timeField.getText();
            String location = locationField.getText();
            int participantCount = Integer.parseInt(participantCountField.getText());
            int winnerCount = Integer.parseInt(winnerCountField.getText());
            String status = (String) statusCombo.getSelectedItem();
            
            // 更新竞赛对象
            AdminCompetitionData selectedComp = selectedCompArray[0];
            if (selectedComp != null) {
                selectedComp.setName(name);
                selectedComp.setLevel(level);
                selectedComp.setTime(time);
                selectedComp.setLocation(location);
                selectedComp.setParticipantCount(participantCount);
                selectedComp.setWinnerCount(winnerCount);
                selectedComp.setStatus(status);
            }
            
            // 更新表格
            competitionTableModel.setValueAt(name, selectedRow, 1);
            competitionTableModel.setValueAt(level, selectedRow, 2);
            competitionTableModel.setValueAt(time, selectedRow, 3);
            competitionTableModel.setValueAt(location, selectedRow, 4);
            competitionTableModel.setValueAt(participantCount, selectedRow, 5);
            competitionTableModel.setValueAt(status, selectedRow, 6);
            
            JOptionPane.showMessageDialog(dialog, "竞赛修改成功！");
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
    
    // 创建竞赛编辑页面
    private JPanel createCompetitionEditPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("竞赛信息编辑");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // 竞赛信息表单面板
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
        
        // 参赛人员管理面板
        JPanel participantPanel = new JPanel();
        participantPanel.setLayout(new BorderLayout());
        participantPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel participantTitle = new JLabel("参赛人员管理");
        participantTitle.setFont(new Font("宋体", Font.BOLD, 18));
        participantTitle.setHorizontalAlignment(SwingConstants.CENTER);
        participantPanel.add(participantTitle, BorderLayout.NORTH);
        
        // 参赛人员表格
        String[] participantColumnNames = {"学生ID", "姓名", "学院", "专业", "状态", "操作"};
        participantTableModel = new DefaultTableModel(participantColumnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5;
            }
        };
        
        JTable participantTable = new JTable(participantTableModel);
        participantTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // 简化状态列设置，不使用复杂的编辑器
        participantTable.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return c;
            }
        });
        
        JScrollPane participantScrollPane = new JScrollPane(participantTable);
        participantPanel.add(participantScrollPane, BorderLayout.CENTER);
        
        // 参赛人员操作按钮
        JPanel participantButtonPanel = new JPanel();
        participantButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton addParticipantButton = new JButton("添加参赛人员");
        addParticipantButton.addActionListener(e -> {
            // 这里可以添加添加参赛人员的逻辑
            JOptionPane.showMessageDialog(panel, "添加参赛人员功能待实现");
        });
        
        JButton deleteParticipantButton = new JButton("删除选中参赛人员");
        deleteParticipantButton.addActionListener(e -> {
            int selectedRow = participantTable.getSelectedRow();
            if (selectedRow >= 0) {
                participantTableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(panel, "请先选择要删除的参赛人员");
            }
        });
        
        participantButtonPanel.add(addParticipantButton);
        participantButtonPanel.add(deleteParticipantButton);
        participantPanel.add(participantButtonPanel, BorderLayout.SOUTH);
        
        // 底部按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton saveButton = new JButton("保存修改");
        saveButton.addActionListener(e -> {
            // 收集表单数据
            String name = nameField.getText();
            String level = (String) levelCombo.getSelectedItem();
            String time = timeField.getText();
            String location = locationField.getText();
            int participantCount = Integer.parseInt(participantCountField.getText());
            int winnerCount = Integer.parseInt(winnerCountField.getText());
            String status = (String) statusCombo.getSelectedItem();
            
            // 更新竞赛对象
            if (currentEditingCompetition != null) {
                currentEditingCompetition.setName(name);
                currentEditingCompetition.setLevel(level);
                currentEditingCompetition.setTime(time);
                currentEditingCompetition.setLocation(location);
                currentEditingCompetition.setParticipantCount(participantCount);
                currentEditingCompetition.setWinnerCount(winnerCount);
                currentEditingCompetition.setStatus(status);
                
                // 更新竞赛列表
                for (int i = 0; i < competitions.size(); i++) {
                    if (competitions.get(i).getId().equals(currentEditingCompetitionId)) {
                        competitions.set(i, currentEditingCompetition);
                        break;
                    }
                }
                
                // 刷新竞赛管理页面
                refreshCompetitionManagePanel();
                
                JOptionPane.showMessageDialog(panel, "竞赛修改成功！");
                
                // 返回竞赛管理页面
                cardLayout.show(mainContentPanel, "competitionManage");
            }
        });
        
        JButton backButton = new JButton("返回");
        backButton.addActionListener(e -> {
            cardLayout.show(mainContentPanel, "competitionManage");
        });
        
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        
        // 将表单和参赛人员面板垂直排列
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(formPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(participantPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonPanel);
        
        panel.add(centerPanel, BorderLayout.CENTER);
        
        // 当页面显示时，加载当前编辑的竞赛信息
        new Thread(() -> {
            SwingUtilities.invokeLater(() -> {
                if (currentEditingCompetition != null) {
                    nameField.setText(currentEditingCompetition.getName());
                    levelCombo.setSelectedItem(currentEditingCompetition.getLevel());
                    timeField.setText(currentEditingCompetition.getTime());
                    locationField.setText(currentEditingCompetition.getLocation());
                    participantCountField.setText(String.valueOf(currentEditingCompetition.getParticipantCount()));
                    winnerCountField.setText(String.valueOf(currentEditingCompetition.getWinnerCount()));
                    statusCombo.setSelectedItem(currentEditingCompetition.getStatus());
                    
                    // 加载参赛人员数据
                    loadParticipantsData(currentEditingCompetitionId);
                }
            });
        }).start();
        
        return panel;
    }
    
    // 加载参赛人员数据
    private void loadParticipantsData(Long competitionId) {
        // 清空表格
        participantTableModel.setRowCount(0);
        
        // 加载对应竞赛的参赛人员
        for (ParticipantData participant : participants) {
            if (participant.getCompetitionId().equals(competitionId)) {
                participantTableModel.addRow(new Object[]{
                    participant.getStudentId(),
                    participant.getStudentName(),
                    participant.getCollege(),
                    participant.getMajor(),
                    participant.getStatus(),
                    "删除"
                });
            }
        }
    }
    
    // 刷新竞赛管理页面
    private void refreshCompetitionManagePanel() {
        // 清空表格
        competitionTableModel.setRowCount(0);
        
        // 重新填充数据
        for (AdminCompetitionData comp : competitions) {
            competitionTableModel.addRow(new Object[]{
                comp.getId(),
                comp.getName(),
                comp.getLevel(),
                comp.getTime(),
                comp.getLocation(),
                comp.getParticipantCount(),
                comp.getStatus(),
                "编辑"
            });
        }
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
        
        // Getters
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getLevel() { return level; }
        public String getTime() { return time; }
        public String getLocation() { return location; }
        public int getParticipantCount() { return participantCount; }
        public int getWinnerCount() { return winnerCount; }
        public String getStatus() { return status; }
        
        // Setters
        public void setName(String name) { this.name = name; }
        public void setLevel(String level) { this.level = level; }
        public void setTime(String time) { this.time = time; }
        public void setLocation(String location) { this.location = location; }
        public void setParticipantCount(int participantCount) { this.participantCount = participantCount; }
        public void setWinnerCount(int winnerCount) { this.winnerCount = winnerCount; }
        public void setStatus(String status) { this.status = status; }
    }
    
    // 内部类：参赛人员数据
    class ParticipantData {
        private Long id;
        private Long competitionId;
        private String studentId;
        private String studentName;
        private String college;
        private String major;
        private String status;
        
        public ParticipantData(Long id, Long competitionId, String studentId, String studentName, String college, String major, String status) {
            this.id = id;
            this.competitionId = competitionId;
            this.studentId = studentId;
            this.studentName = studentName;
            this.college = college;
            this.major = major;
            this.status = status;
        }
        
        // Getters
        public Long getId() { return id; }
        public Long getCompetitionId() { return competitionId; }
        public String getStudentId() { return studentId; }
        public String getStudentName() { return studentName; }
        public String getCollege() { return college; }
        public String getMajor() { return major; }
        public String getStatus() { return status; }
        
        // Setters
        public void setStatus(String status) { this.status = status; }
    }
}