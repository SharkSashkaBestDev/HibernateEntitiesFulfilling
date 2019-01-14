package org.kpi.course.window.Tables;

import org.kpi.course.window.MountainBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Radio extends JFrame{
    private JPanel sRoot;
    private JPanel root;
    private JButton addBtn;
    private JButton rfrBtn;
    private JButton rmvBtn;
    private JTextField txtId;
    private JTextField txtPrice;
    private JButton backBtn;
    private JButton shwBtn;
    private JTable table1;
    private JTextField txtPrice1;
    private JTextField txtStart;
    private JTextField txtEnd;

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/dev";
    private static final String user = "dev";
    private static final String password = "dev123";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    DefaultTableModel tableModel = new DefaultTableModel();

    public Radio(){

        table1.setModel(tableModel);
        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(root);

        setTitle("Таблица радио");
        setSize(800,600);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MountainBase mountainBase = new MountainBase();
                mountainBase.setVisible(true);
            }
        });


        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addData();
            }
        });

        shwBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });

        rfrBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshData();
            }
        });

        rmvBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeData();
            }
        });

    }

    public void addData() {
        String id = txtId.getText();
        String price = txtPrice.getText();
        String price1 = txtPrice1.getText();
        String date = txtStart.getText();
        String date1 = txtEnd.getText();

        //"INSERT INTO `dev`.`Places` (`idPlaces`, `tent`, `house`, `RentBegin`, `RentEnd`) VALUES ('"+Integer.parseInt(id)+"', '"+Integer.parseInt(price)+"', '"+Integer.parseInt(price1)+"', '', '5');";
        String sql = ("INSERT INTO `dev`.`Products` (`idProducts`, `ProductFromStorage`) VALUES ('"+Integer.parseInt(id)+"', '"+Integer.parseInt(price)+"');");

        try{
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(root,"Wrong data added!");
            sqlEx.printStackTrace();} finally {
//            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        JOptionPane.showMessageDialog(root,"Added success!");
    }

    public void showData() {

        while(tableModel.getRowCount() > 0)
        {
            tableModel.removeRow(0);
        }

        tableModel.addColumn("id");
        tableModel.addColumn("Take radio date");
        tableModel.addColumn("Back radio date");

        ////////////////////////////////////////////////////////////////////////////////

        String sql = ("SELECT * FROM Radio;");

        String[] s1 = new String[1000];
        String[] s4 = new String[1000];
        String[] s5 = new String[1000];
        int c = 0;

        try{
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                s1[c] = String.valueOf(rs.getInt(1));
                s4[c] = String.valueOf(rs.getDate(2));
                s5[c] = String.valueOf(rs.getDate(3));
                c++;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();} finally {
//            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        ////////////////////////////////////////////////////////////////////////////////

        //Добавим строку data в таблицу

        for (int i = 0; i < s1.length; i++) {
            String[] data= { s1[i], s4[i], s5[i] };
            tableModel.addRow(data);
        }
    }

    public void refreshData() {

        while(tableModel.getRowCount() > 0)
        {
            tableModel.removeRow(0);
        }

        ////////////////////////////////////////////////////////////////////////////////

        String sql = ("SELECT * FROM Radio;");

        String[] s1 = new String[1000];
        String[] s2 = new String[1000];
        String[] s3 = new String[1000];
        String[] s4 = new String[1000];
        String[] s5 = new String[1000];
        int c = 0;

        try{
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                s1[c] = String.valueOf(rs.getInt(1));
                s2[c] = String.valueOf(rs.getInt(2));
                s3[c] = String.valueOf(rs.getInt(3));
                s4[c] = String.valueOf(rs.getDate(4));
                s5[c] = String.valueOf(rs.getDate(5));
                c++;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();} finally {
//            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        ////////////////////////////////////////////////////////////////////////////////

        //Добавим строку data в таблицу

        for (int i = 0; i < s1.length; i++) {
            String[] data= { s1[i], s2[i], s3[i], s4[i], s5[i] };
            tableModel.addRow(data);
        }
    }

    public void removeData() {

        ////////////////////////////////////////////////////////////////////////////////
        String id = txtId.getText();

        String sql = ("DELETE FROM `dev`.`Products` WHERE (`idProducts` = '"+Integer.parseInt(id)+"');");

        try{
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(root,"Wrong data try to delete!");
            sqlEx.printStackTrace();} finally {
//            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        JOptionPane.showMessageDialog(root,"Removing success!");
        ////////////////////////////////////////////////////////////////////////////////
    }
}
