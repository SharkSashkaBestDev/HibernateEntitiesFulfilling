package org.kpi.course.window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZvitNach extends JFrame{
    private JButton bckBtn;
    private JPanel pureRoot1;


    public ZvitNach() {

        add(pureRoot1);

        setTitle("Отчёты для Начальника");
        setSize(800,600);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        bckBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
