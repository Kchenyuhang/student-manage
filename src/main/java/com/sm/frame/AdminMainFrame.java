package com.sm.frame;

import com.sm.entity.Admin;
import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.thread.TimeThread;
import com.sm.ui.ImgPanel;
import com.sm.utils.AliOSSUtil;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

public class AdminMainFrame extends JFrame {
    private String uploadFileUrl;
    private File file;
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
    private JTextField depNameField;
    private JButton 新增Button;
    private JPanel leftPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JLabel timeLabel;
    private JButton 选择Logo图Buttun;
    private JLabel logoLabel;
    private JTextField textField1;
    private JTextField classNameFiled;
    private JButton 新增班级Button;
    private JPanel treePanel;
    private JPanel classContentPanel;
    private int departmentId = 0;
    private JComboBox<Department> depcomboBox;
    private JPanel stuTopPanel;
    private JComboBox<Department> comboBox1;
    private JComboBox<CClass> comboBox2;
    private JTextField studentNameField;
    private JButton 搜索Button;
    private JButton 新增学生Button;
    private JButton 批量导入Button;
    private ImgPanel infoPanel;
    private JPanel tablePanel;
    private JLabel stuAvatarLabel;
    private JLabel stuClassLabel;
    private JLabel stuDepLabel;
    private JLabel stuGenderLabel;
    private JLabel stuNameLabel;
    private JLabel stuIdLabel;
    private JTextField stuAddressField;
    private JTextField stuPhoneField;
    private JLabel stuBirthdayLabel;
    private JButton 初始化数据Button;

    public AdminMainFrame(Admin admin) {
        int width = 200;
        int height = 200;
        //设置需要的背景图
        rootPanel.setFileName("bg.jpg");
        infoPanel.setFileName("bg01.jpg");
        //组件重绘
        rootPanel.repaint();
        infoPanel.repaint();
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
                cardLayout.show(centerPanel, "Card1");
            }
        });
        班级管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card2");
                showClassPanel();
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
                cardLayout.show(centerPanel, "Card4");
            }
        });
        新增院系Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = leftPanel.isVisible();
                if (flag == false) {
                    leftPanel.setVisible(true);
                } else {
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
        //
        depNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                depNameField.setText("");
            }
        });
        选择Logo图Buttun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:\\Users\\陈宇航\\Desktop\\自制壁纸资源"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //通过文件创建icon对象
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
                    //通过标签显示图片
                    logoLabel.setIcon(icon);
                    //设置标签可见
                    logoLabel.setVisible(true);
                    //将按钮隐藏
                    选择Logo图Buttun.setVisible(false);
                }
            }
        });
        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //上传文件到OSS并返回外链
                uploadFileUrl = AliOSSUtil.ossUpload(file);
                //创建Department对象，并设置相应属性
                Department department = new Department();
                department.setDepartmentName(depNameField.getText().trim());
                department.setLogo(uploadFileUrl);
//                department.setDescription(descriptionArea.getText().trim());
                //调用service实现新增功能
                int n = ServiceFactory.getDepartmentServiceInstance().addDepartment(department);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系成功");
                    //新增成功后，将侧边栏隐藏
                    leftPanel.setVisible(false);
                    //刷新界面数据
                    showDepartments();
                    //将图片预览标签隐藏
                    logoLabel.setVisible(false);
                    //将选择图片的按钮可见
                    选择Logo图Buttun.setVisible(true);
                    //清空文本框
                    depNameField.setText("");
//                    descriptionArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系失败");
                }
            }
        });
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:\\Users\\陈宇航\\Desktop\\自制壁纸资源"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //通过文件创建icon对象
                    Icon icon = new ImageIcon(file.getAbsolutePath());
                    //通过标签显示图片
                    logoLabel.setIcon(icon);
                    //设置标签可见
                    logoLabel.setVisible(true);
                    //将按钮隐藏
                    选择Logo图Buttun.setVisible(false);
                }
            }
        });
        depcomboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //得到选中项的索引
                int index = depcomboBox.getSelectedIndex();
                //按照索引取出项，就是一个Department对象，然后取出其id备用
                departmentId = depcomboBox.getItemAt(index).getId();
            }
        });
        新增班级Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CClass cClass = new CClass();
                cClass.setDepartmentId(departmentId);
                cClass.setClassName(classNameFiled.getText().trim());
                int n = ServiceFactory.getCClassServiceInstance().insertClass(cClass);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系成功");
                    //刷新界面数据
                    showClassPanel();
                    //清空文本框
                    classNameFiled.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系失败");
                }
            }
        });
        学生管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "card3");
                //调用显示学生数据的方法
                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
                showStudentTable(studentList);

                //两个下拉框初始化提示数据，因为里面元素都是对象，所以这样进行了处理
                Department tip1 = new Department();
                tip1.setDepartmentName("请选择院系");
                comboBox1.addItem(tip1);
                CClass tip2 = new CClass();
                tip2.setClassName("请选择班级");
                comboBox2.addItem(tip2);

                //初始化院系下拉框数据
                List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
                for (Department department : departmentList) {
                    comboBox1.addItem(department);
                }

                //初始化班级下拉框数据
                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectAll();
                for (CClass cClass : cClassList) {
                    comboBox2.addItem(cClass);
                }

                //院系下拉框监听，选中哪项，表格中显示该院系所有学生，并级联查出该院系的所有班级，更新到班级下拉框
                comboBox1.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            int index = comboBox1.getSelectedIndex();
                            //排除第一项，那是提示信息，不能作为查询依据
                            if (index != 0) {
                                departmentId = comboBox1.getItemAt(index).getId();
                                //获取该院系的学生并显示在表格中
                                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByDepartmentId(departmentId);
                                showStudentTable(studentList);
                                //二级联动—班级下拉框的内容随着选择院系的不同而变化
                                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(departmentId);
                                //一定要先移除之前的数据，否则下拉框内容会叠加
                                comboBox2.removeAllItems();
                                CClass tip = new CClass();
                                tip.setClassName("请选择班级");
                                comboBox2.addItem(tip);
                                for (CClass cClass : cClassList) {
                                    comboBox2.addItem(cClass);
                                }
                            }
                        }
                    }

        });
                //班级下拉框监听，可以根据选中的班级显示所有学生
                comboBox2.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            int index = comboBox2.getSelectedIndex();
                            if (index != 0) {
                                int classId = comboBox2.getItemAt(index).getId();
                                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByClassId(classId);
                                showStudentTable(studentList);
                            }
                        }
                    }
                });
                初始化数据Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //还原表格数据
                        List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
                        showStudentTable(studentList);
                        //院系下拉框还原
                        comboBox1.setSelectedIndex(0);
                        //班级下拉框还原
                        comboBox2.setSelectedIndex(0);
                        CClass tip2 = new CClass();
                        comboBox2.addItem(tip2);
                        List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectAll();
                        for (CClass cClass: cClassList) {
                            comboBox2.addItem(cClass);
                        }
                        //右侧个人信息显示区域数据还原
                        stuAvatarLabel.setText("<html><img src='https://student-manage99.oss-cn-hangzhou.aliyuncs.com/avatar/rentou.png'/></html>");
                        stuIdLabel.setText("未选择");
                        stuDepLabel.setText("未选择");
                        stuClassLabel.setText("未选择");
                        stuNameLabel.setText("未选择");
                        stuGenderLabel.setText("未选择");
                        stuBirthdayLabel.setText("未选择");
                        stuAddressField.setText("");
                        stuPhoneField.setText("");
                        //搜索框清空
                        studentNameField.setText("");
                        //编辑按钮隐藏

                    }
                });
            }

            private void showStudentTable(List<StudentVO> studentList) {
                tablePanel.removeAll();
//                tablePanel.setBackground(new Color(231, 215, 200));
                //创建表格
                JTable table = new JTable();
                //表格数据模型
                DefaultTableModel model = new DefaultTableModel();
                table.setModel(model);
                //表头内容
                model.setColumnIdentifiers(new String[]{"学号", "院系", "班级", "姓名", "性别", "地址", "手机号", "出生日期", "头像"});
                //遍历List,转成Object数组
                for (StudentVO student : studentList) {
                    Object[] object = new Object[]{student.getId(), student.getDepartmentName(), student.getClassName(), student.getStudentName(), student.getGender(), student.getAddress(), student.getPhone(), student.getBirthday(), student.getAvatar()};
                    model.addRow(object);
                }
                //将最后一列隐藏头像地址不显示在表格中
                TableColumn tc = table.getColumnModel().getColumn(8);
                tc.setMinWidth(0);
                tc.setMaxWidth(0);
                //获得表头
                JTableHeader head = table.getTableHeader();
                //表头居中
                DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
                hr.setHorizontalAlignment(JLabel.CENTER);
                head.setDefaultRenderer(hr);
                //设置表头大小
                head.setPreferredSize(new Dimension(head.getWidth(), 40));
                //设置表头字体
                head.setFont(new Font("楷体", Font.PLAIN, 22));
                //设置表格行高
                table.setRowHeight(35);
                //表格背景色
                table.setBackground(new Color(231, 215, 200));
                //表格内容居中
                DefaultTableCellRenderer r = new DefaultTableCellRenderer();
                r.setHorizontalAlignment(JLabel.CENTER);
                table.setDefaultRenderer(Object.class, r);
                //表格加入滚动面板,水平垂直方向带滚动条
                JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                tablePanel.add(scrollPane);
                tablePanel.revalidate();

                //表格内容选择监听，点击一行，在右边显示这个学生信息
                table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int row = table.getSelectedRow();
                        stuIdLabel.setText(table.getValueAt(row, 0).toString());
                        stuDepLabel.setText(table.getValueAt(row, 1).toString());
                        stuClassLabel.setText(table.getValueAt(row, 2).toString());
                        stuNameLabel.setText(table.getValueAt(row, 3).toString());
                        stuGenderLabel.setText(table.getValueAt(row, 4).toString());
                        stuAddressField.setText(table.getValueAt(row, 5).toString());
                        stuPhoneField.setText(table.getValueAt(row, 6).toString());
                        stuBirthdayLabel.setText(table.getValueAt(row, 7).toString());
                        stuAvatarLabel.setText("<html><img src='" + table.getValueAt(row, 8).toString() + "'/></html>");
                    }
                });
                搜索Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String keywords = studentNameField.getText().trim();
                        List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByKeywords(keywords);
                        if (studentList != null) {
                            showStudentTable(studentList);
                        }
                    }
                });
            }
        });



    }

    private void showClassPanel() {
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        showCombobox(departmentList);
        showTree(departmentList);
        showClasses(departmentList);
    }

    private void showCombobox(List<Department> departmentList) {
        for (Department department : departmentList
        ) {
            depcomboBox.addItem(department);
        }
    }

    private void showTree(List<Department> departmentList) {
        treePanel.removeAll();
        //左侧树形结构根节点
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("南工院");
        for (Department department : departmentList) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            top.add(group);
            List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(department.getId());
            for (CClass cClass : cClassList) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(cClass.getClassName());
                group.add(node);
            }
        }
        final JTree tree = new JTree(top);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        treePanel.add(tree);
        treePanel.revalidate();
    }

    private void showClasses(List<Department> departmentList) {
        classContentPanel.removeAll();
        //右侧流式布局显示
        Font titlefont = new Font("微软雅黑", Font.PLAIN, 22);
        for (Department department : departmentList) {
            ImgPanel depPanel = new ImgPanel();
            depPanel.setFileName("bg1.jpg");
            depPanel.repaint();
            depPanel.setPreferredSize(new Dimension(350, 500));
            depPanel.setLayout(null);
            JLabel depNameLabel = new JLabel(department.getDepartmentName());
            depNameLabel.setFont(titlefont);
            depNameLabel.setBounds(130, 15, 200, 30);
            //获得这个院系的所有班级
            List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(department.getId());
            //数据模型
            DefaultListModel listModel = new DefaultListModel();
            //遍历班级集合，依次加入数据模型
            for (CClass cClass : cClassList) {
                listModel.addElement(cClass);
            }
            //使用
            JList<CClass> jList = new JList<>(listModel);
            //JList加入滚动面板
            JScrollPane listScrollPanel = new JScrollPane(jList);
            listScrollPanel.setBounds(90, 120, 200, 260);
            listScrollPanel.setBackground(new Color(225, 225, 224));
            depPanel.add(depNameLabel);
            depPanel.add(listScrollPanel);
            classContentPanel.add(depPanel);
            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem item1 = new JMenuItem("修改");
            JMenuItem item2 = new JMenuItem("删除");
            jPopupMenu.add(item1);
            jPopupMenu.add(item2);
            jList.add(jPopupMenu);

            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //选中项的下标
                    int index = jList.getSelectedIndex();
                    //点击鼠标右键
                    if (e.getButton() == 3) {
                        //在鼠标位置弹出菜单
                        jPopupMenu.show(jList, e.getX(), e.getY());
                        //取出选中项数据
                        CClass cClass = jList.getModel().getElementAt(index);
                        //对“删除”菜单项添加监听
                        item2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int choice = JOptionPane.showConfirmDialog(depPanel, "确定删除吗？");
                                if (choice == 0) {
                                    ServiceFactory.getCClassServiceInstance().deleteClassById(cClass.getId());
                                    listModel.remove(index);
                                    showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                                }
                            }
                        });
                    }
                }
            });
        }
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
                    int n = JOptionPane.showConfirmDialog(null, "确认删除？", "警告", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        contentPanel.remove(depPanel);
                        contentPanel.repaint();
                        ServiceFactory.getDepartmentServiceInstance().deleteDepartmentById(department.getId());
                    }
                }
            });
            delBtn.setPreferredSize(new Dimension(80, 35));
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

    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AdminMainFrame(DAOFactory.getAdminDAOInstance().getAdminByAccount("admin"));
    }
}