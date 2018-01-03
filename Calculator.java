package com.edu.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class Calculator {

    public static void main(String[] args) {
        MyFrame MyFrame=new MyFrame();

    }

}
class MyFrame extends JFrame{
    public static JTextField textField;
    public static String result;						//记录中间结果或者结果
    public static String lastcommand;					//记录最后一次运算符
    public static boolean startflag;					//是否是新运算开始
    public MyFrame(){

        setTitle("计算器");
        result="";
        lastcommand="=";
        startflag=true;

            setLocationRelativeTo(null);
            setLayout(new BorderLayout());				                    	         //设置四周性布局
            textField=new JTextField(10);
            add(textField,BorderLayout.NORTH);                                            //调整文本方向为上方
            textField.setFont(new Font("Roman", Font.PLAIN, 35));             //字体大小
            textField.setBackground(Color.gray);                                          //文本框颜色
            setBackground(Color.blue);

        btnpanel btnpanel=new btnpanel();
        add(btnpanel,BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO 自动生成的方法存根
            }
        });
        setVisible(true);//窗体可见
        pack();         //适应大小位置
    }
}
class btnpanel extends JPanel{//按钮面板
    public btnpanel(){
        setLayout(new GridLayout(5,5));
        Numaction numaction=new Numaction();//数字按钮事件
        CommandAction CommandAction=new CommandAction();//+-/*运算事件
        point point=new point();                        //小数点处理事件
        addButton("C",CommandAction);
        addButton("CE",CommandAction);
        addButton("sqrt",CommandAction);
        addButton("/",CommandAction);
        addButton("Back",CommandAction);
        addButton("%",CommandAction);
        addButton("7",numaction);
        addButton("8",numaction);
        addButton("9",numaction);
        addButton("*",CommandAction);
        addButton("m²",CommandAction);
        addButton("4",numaction);
        addButton("5",numaction);
        addButton("6",numaction);
        addButton("-",CommandAction);
        addButton("m³",CommandAction);
        addButton("1",numaction);
        addButton("2",numaction);
        addButton("3",numaction);
        addButton("+",CommandAction);
        addButton("M+",CommandAction);
        addButton("0",numaction);
        addButton("+/-",CommandAction);
        addButton(".",point);
        addButton("=",CommandAction);

    }

    public void addButton(String label,ActionListener listener){
        JButton btn=new JButton(label);
        btn.addActionListener(listener);//给按钮增加点击事件
        btn.setPreferredSize(new Dimension(75,45));                       //设置Jbutton格式
        btn.setFont(new java.awt.Font("Courier",20,20));               //设置Jbutton字体大小 以及风格
        btn.setBorder(BorderFactory.createRaisedBevelBorder());                          //Jbutton凸起
        if("1 2 3 4 5 6 7 8 9 0 . +/- M+ / * - + C CE sqrt ←".contains(label)){         //给所选数字按钮绑定
            btn.setBackground(Color.WHITE);                                               //设置按钮颜色 为白色
            btn.setForeground(Color.BLACK);                                               //设置字体颜色
        }
        if("= m² m³  M+ / * - + C CE sqrt Back %".contains(label)){
            btn.setBackground(Color.BLUE.cyan);
            btn.setForeground(Color.red);
        }

        add(btn);
    }
}


class Numaction implements ActionListener{//数据按钮时间

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MyFrame.startflag==true){
            MyFrame.textField.setText("");
            MyFrame.startflag=false;
        }
        MyFrame.textField.setText(
                MyFrame.textField.getText()+e.getActionCommand());
    }
}
class point implements ActionListener{//

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().equals(".")) && (MyFrame.textField.getText().indexOf(".") < 0)) {
            // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面
            MyFrame.textField.setText(MyFrame.textField.getText() + ".");
        } else if (!e.getActionCommand().equals(".")) {
            // 如果输入的不是小数点，则将数字附在结果文本框的后面
            MyFrame.textField.setText(MyFrame.textField.getText() + e.getActionCommand());
        }
        return;

    }
}


class CommandAction  implements ActionListener {//实现+-*/=运算事件

    @Override
    public void actionPerformed(ActionEvent e) {//实现+运算事件
        double rs = 0;
        // TODO 自动生成的方法存根
        if (e.getActionCommand().equals("=")) {
            double a1 = Double.parseDouble(MyFrame.result);
            double a2 = Double.parseDouble(MyFrame.textField.getText());
            if (MyFrame.lastcommand.equals("+")) { // 加法运算
                rs = a1 + a2;
                System.out.println(a1+"+"+a2+"="+(a1+a2));
            } else if (MyFrame.lastcommand.equals("-")) { // 减法运算
                rs = a1 - a2;
                System.out.println(a1+"-"+a2+"="+(a1-a2));
            } else if (MyFrame.lastcommand.equals("*")) { // 乘法运算
                rs = a1 * a2;
                System.out.println(a1+"*"+a2+"="+(a1*a2));
            } else if (MyFrame.lastcommand.equals("/")) { // 除法运算
                rs = a1 / a2;
                System.out.println(a1+"/"+a2+"="+(a1/a2));
            } else if (MyFrame.lastcommand.equals("%")) {// 百分号运算，除以100
                rs = a1 / 100;
            } else if (MyFrame.lastcommand.equals("sqrt")) { // 平方根运算
                rs = Math.sqrt(a1);
            } else if (MyFrame.lastcommand.equals("+/-")) {// 正数负数运算
                rs = a1 * (-1);
            } else if (MyFrame.lastcommand.equals("m²")) {// 平方运算
                rs = Math.pow(2, a1);
            } else if (MyFrame.lastcommand.equals("m³")) {// 立方运算
                rs = Math.pow(3, a1);
            }

            long t1;
            double t2;
            t1 = (long) rs;
            t2 = rs - t1;
            if (t2 == 0) {
                MyFrame.textField.setText(String.valueOf(t1));
            } else {
                MyFrame.textField.setText(String.valueOf(rs));
            }
            MyFrame.lastcommand = "=";
        } else if (e.getActionCommand().equals("C")) { //清空实现
            MyFrame.textField.setText(" ");
        } else if (e.getActionCommand().equals("CE")) {
            MyFrame.textField.setText("0");
        } else if (e.getActionCommand().equals("Back")) { //退格实现
            MyFrame.textField.setText(MyFrame.textField.getText().substring(0,
                    MyFrame.textField.getText().length() - 1));
        }



        else {//+-*/
            if (!MyFrame.lastcommand.equals("=")) {//连+-*/运算
                double a1 = Double.parseDouble(MyFrame.result);
                double a2 = Double.parseDouble(MyFrame.textField.getText());
                if (MyFrame.lastcommand.equals("+")) {//连+实现
                    rs = a1 + a2;
                }
               else if (MyFrame.lastcommand.equals("-")) {//连-实现
                    rs = a1 - a2;
                }
                else if (MyFrame.lastcommand.equals("*")) {//连*实现
                    rs = a1 * a2;
                }
                else if (MyFrame.lastcommand.equals("/")) {//连/实现
                    rs = a1 / a2;
                }
                    long t1;
                double t2;
                t1 = (long) rs;
                t2 = rs - t1;
                if (t2 == 0) {
                    MyFrame.textField.setText(String.valueOf(t1));
                } else {
                    MyFrame.textField.setText(String.valueOf(rs));
                }
            }
            MyFrame.result = MyFrame.textField.getText();//将界面值放入result
            MyFrame.lastcommand = e.getActionCommand();//记录运算符
        }
        MyFrame.startflag = true;
    }
}