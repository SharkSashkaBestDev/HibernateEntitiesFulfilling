package org.kpi.course.window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Info extends JFrame{
    private JButton backB;
    private JPanel infoRoot;
    private JTextArea textArea1;

    public Info() {

        add(infoRoot);

        setTitle("Инфорамция об авторе");
        setSize(800,600);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        backB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MountainBase mountainBase = new MountainBase();
                mountainBase.setVisible(true);
            }
        });
    }
}
