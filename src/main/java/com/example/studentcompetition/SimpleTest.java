package com.example.studentcompetition;

import javax.swing.*;
import java.awt.*;

public class SimpleTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("测试窗口");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            
            JLabel label = new JLabel("Hello Swing!", SwingConstants.CENTER);
            frame.add(label, BorderLayout.CENTER);
            
            System.out.println("窗口已创建，准备显示...");
            frame.setVisible(true);
            System.out.println("窗口已显示");
        });
    }
}