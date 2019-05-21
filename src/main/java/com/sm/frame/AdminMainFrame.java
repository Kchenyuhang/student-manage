package com.sm.frame;

import com.sm.entity.Admin;
import com.sm.entity.Department;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.thread.TimeThread;
import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AdminMainFrame extends JFrame{
    private Admin admin;
    private JLabel adminNameLabel;
    private JButton 院系管理Button;
    private JButton 奖惩管理Button;
    private JButton 班级管理Button;
    private JButton 学生管理Button;
    private JPanel centerPanel;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel rewardPunishPanel;
    private ImgPanel rootPanel;
    private JButton 新增院系Button;
    private JButton 刷新院系Button;
    private JTextField textField1;
    private JButton 新增Button;
    private JButton 选择Logo图Button;
    private JPanel leftPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JLabel timeLabel;

    public AdminMainFrame(Admin admin) {
        //设置需要的背景图
        rootPanel.setFileName("bg.jpg");
        //组件重绘
        rootPanel.repaint();
        this.admin = admin;
        adminNameLabel.setText("当前管理员为：" + admin.getAdminName());
        setTitle("管理员主界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //调用显示所有院系的方法
        showDepartments();
        //获取
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();

            院系管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card1");
            }
        });
        班级管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card2");
            }
        });
        学生管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card3");
            }
        });
        奖惩管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card4");
            }
        });
        新增院系Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = leftPanel.isVisible();
                if (flag == false){
                    leftPanel.setVisible(true);
                }else {
                    leftPanel.setVisible(false);
                }
            }
        });
        刷新院系Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDepartments();
            }
        });
        //连接时间线程
        Font font1 = new Font("楷体", Font.BOLD, 30);
        timeLabel.setFont(font1);
        TimeThread timeThread = new TimeThread();
        timeThread.setTimeLabel(timeLabel);
        timeThread.start();
    }



    private void showDepartments() {
        //移除原有数据
        contentPanel.removeAll();
        //从service层获取到所有院系列表
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        int len = departmentList.size();
        int row = len % 4 == 0 ? len / 4 : len / 4 + 1;
        GridLayout gridLayout = new GridLayout(row, 4, 15, 15);
        contentPanel.setLayout(gridLayout);
        for (Department department : departmentList) {
            //给每个院系对象创建一个面板
            JPanel depPanel = new JPanel();
            JButton delBtn = new JButton("删除");
            depPanel.setPreferredSize(new Dimension(200, 200));
            //删除按钮
            delBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int n = JOptionPane.showConfirmDialog(null,"确认删除？","警告",JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        contentPanel.remove(depPanel);
                        contentPanel.repaint();
                        ServiceFactory.getDepartmentServiceInstance().deleteDepartmentById(department.getId());
                    }
                }
            });
            delBtn.setPreferredSize(new Dimension(80,35));
            //将院系名称设置给面板标题
            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            //新建一个Label用来放置院系logo，并指定大小
            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() + "' width=200 height=180/></html>");
            //图标标签加入院系面板
            depPanel.add(logoLabel);
            depPanel.add(delBtn);
            //院系面板加入主体内容面板
            contentPanel.add(depPanel);
            //刷新主体内容面板
            contentPanel.revalidate();
        }
    }

    public static void main(String[] args) throws Exception{
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AdminMainFrame(DAOFactory.getAdminDAOInstance().getAdminByAccount("admin"));
    }
}
