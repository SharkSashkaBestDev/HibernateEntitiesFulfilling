package org.kpi.course.window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZvitBooch extends JFrame{

    public JPanel pureRoot;
    private JButton backBtn;

    public ZvitBooch() {

        add(pureRoot);

        setTitle("Отчёты для Бухгалтерии");
        setSize(800,600);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MountainBase mountainBase = new MountainBase();
                mountainBase.setVisible(true);
            }
        });
    }
}
