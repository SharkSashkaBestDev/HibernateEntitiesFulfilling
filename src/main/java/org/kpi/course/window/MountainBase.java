package org.kpi.course.window;

import org.kpi.course.window.Tables.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MountainBase extends JFrame{
    private JButton startBtn;
    private JPanel rootPanel;
    private JButton infoBtn;
    private JButton exitBtn;
    private JButton shwProd;
    private JButton shwRadio;
    private JButton shwFood;
    private JButton shwJourney;
    private JButton shwClientService;
    private JButton chgProd;
    private JButton shwClient;
    private JButton shwPlaces;
    private JTable table1;

    // JDBC URL, username and password of MySQL server
//    private static final String url = "jdbc:mysql://localhost:3306/dev";
    private static final String url = "jdbc:h2:test";
//    private static final String user = "root";
    private static final String user = "sa";
    private static final String password = "";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    int count;

    DefaultTableModel tableModel = new DefaultTableModel();

    public MountainBase() {

        table1.setModel(tableModel);

        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        //работа с окном
        add(rootPanel);

        setTitle("Альпинистская База");
        setSize(800,600);

        //закрытие по нажатию на крестик
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Центрирование окна
        setLocationRelativeTo(null);

        //действие для кнопки
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            ZvitBooch zvitBooch = new ZvitBooch();
            zvitBooch.setVisible(true);
            ZvitNach zvitNach = new ZvitNach();
            zvitNach.setVisible(true);
            setVisible(false);
            }
        });

        infoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Info info = new Info();
                info.setVisible(true);
            }
        });


        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        shwProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showProducts();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        chgProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    changeProducts();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        shwPlaces.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Places places = new Places();
                places.setVisible(true);
            }
        });

        shwRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Radio radio = new Radio();
                radio.setVisible(true);
            }
        });
        shwFood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Food food = new Food();
                food.setVisible(true);
            }
        });
        shwJourney.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Journey journey = new Journey();
                journey.setVisible(true);
            }
        });
        shwClientService.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ClientService clientService = new ClientService();
                clientService.setVisible(true);
            }
        });
        shwClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Client client = new Client();
                client.setVisible(true);
            }
        });
    }

    public void showProducts() throws SQLException {

        while(tableModel.getRowCount() > 0)
        {
            tableModel.removeRow(0);
        }

        tableModel.addColumn("id");
        tableModel.addColumn("price");


        ////////////////////////////////////////////////////////////////////////////////

        String sql = ("SELECT * FROM Products;");

        String[] s1 = new String[1000];
        String[] s2 = new String[1000];
        int c = 0;

        try{
            Class.forName("org.h2.Driver").newInstance();

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                s1[c] = String.valueOf(rs.getInt(1));
                s2[c] = String.valueOf(rs.getInt(2));
                c++;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();} catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
//            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        ////////////////////////////////////////////////////////////////////////////////


        //Добавим строку data в таблицу

        for (int i = 0; i < s1.length; i++) {
            String[] data= { s1[i], s2[i] };
            tableModel.addRow(data);
        }
    }

    public void changeProducts() throws SQLException {

        ////////////////////////////////////////////////////////////////////////////////
        setVisible(false);
        Products products = new Products();
        products.setVisible(true);
        ////////////////////////////////////////////////////////////////////////////////

    }

    public int Connection() {

        String query = "select count(*) from Client";

        try{
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();} finally {
//            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return count;
    }
}


//открыть окно с сообщением
//JOptionPane.showMessageDialog(rootPanel,"Hello World!");

//калькулятор

//                Util util = new Util();
//
//                int a = 0;
//                int b = 0;
//                try {
//                    a = Integer.parseInt(txtA.getText());
//                    b = Integer.parseInt(txtB.getText());
//                } catch (Exception exception) {
//                    JOptionPane.showMessageDialog(rootPanel,"Enter a number");
//                }
//
//                int res = util.getSquare(a, b);
//                txtC.setText(String.valueOf(res));