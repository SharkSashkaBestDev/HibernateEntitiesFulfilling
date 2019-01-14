package org.kpi.course;

import org.hibernate.Session;
import org.kpi.course.entity.*;
import org.kpi.course.util.EntityFulfiller;
import org.kpi.course.window.MountainBase;

import javax.swing.*;
import java.util.List;

public class Main {

    private static final int MAX_ENTITIES = 300;

    public static void main(String[] args) throws Exception {
        List<Client> clients = EntityFulfiller.getClients(MAX_ENTITIES);
        List<Place> places = EntityFulfiller.getPlaces(MAX_ENTITIES);
        List<Food> feed = EntityFulfiller.getFeed(MAX_ENTITIES);
        List<Travel> travels = EntityFulfiller.getTravel(MAX_ENTITIES);
        List<RadioRent> rents = EntityFulfiller.getRadioRent(MAX_ENTITIES);
        List<Product> products = EntityFulfiller.getProducts(MAX_ENTITIES);
        List<ClientService> clientServices = EntityFulfiller.getClientServices(MAX_ENTITIES);

        System.out.println("Clients count: " + clients.size());
        System.out.println("Places count: " + places.size());
        System.out.println("Feed count: " + feed.size());
        System.out.println("Travels count: " + travels.size());
        System.out.println("Rents count: " + rents.size());
        System.out.println("Products count: " + products.size());
        System.out.println("Client services count: " + clientServices.size());

        Session session = EntityFulfiller.sessionFactory.getCurrentSession();

        session.beginTransaction();

        clients.stream().map(session::merge).forEach(System.out::println);
        places.stream().map(session::merge).forEach(System.out::println);
        feed.stream().map(session::merge).forEach(System.out::println);
        travels.stream().map(session::merge).forEach(System.out::println);
        rents.stream().map(session::merge).forEach(System.out::println);
        products.stream().map(session::merge).forEach(System.out::println);
        clientServices.stream().map(session::merge).forEach(System.out::println);

        session.getTransaction().commit();

        EntityFulfiller.closeAll();
    }

    static void wf() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                MountainBase mountainBase = new MountainBase();
                mountainBase.setVisible(true);
                mountainBase.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }
}
