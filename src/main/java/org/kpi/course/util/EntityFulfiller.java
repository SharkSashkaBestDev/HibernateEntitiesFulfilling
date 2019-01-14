package org.kpi.course.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.kpi.course.entity.*;

import java.util.*;

public class EntityFulfiller {

    private static final int MIN_PLACE_1_PRICE = 50000,
            MAX_PLACE_1_PRICE = 300000,
            MIN_PLACE_2_PRICE = 1000,
            MAX_PLACE_2_PRICE = 40000,
            PLACE_DATE_START_MAX_DAYS = 300,
            PLACE_DATE_MAX_DURATION = 150,
            MIN_FOOD_PRICE = 10,
            MAX_FOOD_PRICE = 1000,
            TRAVEL_DATE_START_MAX_DAYS = 600,
            TRAVEL_DATE_MAX_DURATION = 300,
            RENT_DATE_START_MAX_DAYS = 100,
            RENT_DATE_MAX_DURATION = 50,
            MIN_PRODUCT_PRICE = 100,
            MAX_PRODUCT_PRICE = 300;

    private static List<String> femaleNames = Arrays.asList(
        "Мария","Анастасия","Екатерина","Виктория"
    );
    private static List<String> maleNames = Arrays.asList(
        "Андрей","Кирилл","Валентин","Никита","Игорь","Владладислав"
    );
    private static List<String> femaleMiddlenames = Arrays.asList(
        "Андреевна","Кирилловна","Валентиновна","Никитовна"
    );
    private static List<String> maleMiddlenames = Arrays.asList(
        "Андреевич","Кириллович","Валентинович","Никитович","Игоревич","Владиславич"
    );
    private static List<String> surnames = Arrays.asList(
        "Твердохлеб","Гончаров","Шаповал","Алексиков","Фомин","Патлай"
    );
    private static List<String> products = Arrays.asList(
        "тушенка","масло","повидло","консервы"
    );
    private static List<String> travels = Arrays.asList(
        "Гора Амроп","Гора Зыпро","Гора Вон","Гора Зилтак","Гора Покой"
    );
    private static List<String> places = Arrays.asList(
        "Дом","Палатка"
    );
    private static List<String> feed = Arrays.asList(
        "завтрак","обед","ужин"
    );

    public static SessionFactory sessionFactory = buildSessionFactory();

    public static List<Client> getClients(int count) {
        List result = new ArrayList();
        for (int i = 0; i < count; i++) {
            Client client = new Client();
            client.setId(i+1);
            client.setGender(getRandomGender());
            client.setName(getRandomName(client.getGender()));
            client.setSurname(getRandomSurname());
            client.setMiddlename(getRandomMiddlename(client.getGender()));
            client.setBirthdate(getRandomBirthdate());

            result.add(client);

            saveEntity(client);
        }
            return result;
    }

    public static List<Place> getPlaces(int count) {
        int clientsCount = getClientsCountInDatabase();

        List result = new ArrayList();
        for (int i = 0; i < count; i++) {
            Place place = new Place();
            place.setId(i+1);
            place.setName(getRandomPlaceName());
            place.setPrice(getRandomPlacePrice(place.getName()));
            place.setDateStart(getRandomCommonDateStart(PLACE_DATE_START_MAX_DAYS));
            place.setDateEnd(getRandomCommonDateEnd(place.getDateStart(), PLACE_DATE_MAX_DURATION));
            place.setClient(getRandomClient(clientsCount));

            result.add(place);

            saveEntity(place);
        }
        return result;
    }

    public static List<Food> getFeed(int count) {
        int clientsCount = getClientsCountInDatabase();

        List result = new ArrayList();
        for (int i = 0; i < count; i++) {
            Food food = new Food();
            food.setId(i+1);
            food.setName(getRandomFoodName());
            food.setPrice(getRandomFoodPrice());
            food.setClient(getRandomClient(clientsCount));

            result.add(food);

            saveEntity(food);
        }
        return result;
    }

    public static List<Travel> getTravel(int count) {
        List result = new ArrayList();
        for (int i = 0; i < count; i++) {
            Travel travel = new Travel();
            travel.setId(i+1);
            travel.setName(getRandomTravelName());
            travel.setDateStart(getRandomCommonDateStart(TRAVEL_DATE_START_MAX_DAYS));
            travel.setDateEnd(getRandomCommonDateEnd(travel.getDateStart(), TRAVEL_DATE_MAX_DURATION));

            result.add(travel);

            saveEntity(travel);
        }
        return result;
    }

    public static List<RadioRent> getRadioRent(int count) {
        List result = new ArrayList();
        for (int i = 0; i < count; i++) {
            RadioRent rent = new RadioRent();
            rent.setId(i+1);
            rent.setRentStart(getRandomCommonDateStart(RENT_DATE_START_MAX_DAYS));
            rent.setRentEnd(getRandomCommonDateEnd(rent.getRentStart(), RENT_DATE_MAX_DURATION));

            result.add(rent);

            saveEntity(rent);
        }
        return result;
    }

    public static List<Product> getProducts(int count) {
        List result = new ArrayList();
        for (int i = 0; i < count; i++) {
            Product product = new Product();
            product.setId(i+1);
            product.setName(getRandomProductName());
            product.setPrice(getRandomProductPrice());

            result.add(product);

            saveEntity(product);
        }
        return result;
    }

    public static List<ClientService> getClientServices(int count) {
        int clientsCount = getClientsCountInDatabase(),
            travelsCount = getTravelsCountInDatabase(),
            productsCount = getProductsCountInDatabase(),
            rentsCount = getRentsCountInDatabase();

        List result = new ArrayList();
        for (int i = 0; i < count; i++) {
            ClientService service = new ClientService();
            service.setId(i+1);
            service.setClient(getRandomClient(clientsCount));
            service.setTravel(getRandomTravel(travelsCount));
            service.setProduct(getRandomProduct(productsCount));
            service.setRadio(getRandomRent(rentsCount));

            result.add(service);

            saveEntity(service);
        }
        return result;
    }

    private static Gender getRandomGender() {
        return Gender.values()[new Random().nextInt(2)];
    }

    private static String getRandomName(Gender gender) {
        return gender.equals(Gender.мужчина) ?
                maleNames.get(getRandomNumberInRange(0, maleNames.size() - 1)) :
                femaleNames.get(getRandomNumberInRange(0, femaleNames.size() - 1));
    }

    private static String getRandomSurname() {
        return surnames.get(getRandomNumberInRange(0, surnames.size() - 1));
    }

    private static String getRandomMiddlename(Gender gender) {
        return gender.equals(Gender.мужчина) ?
                maleMiddlenames.get(getRandomNumberInRange(0, maleMiddlenames.size() - 1)) :
                femaleMiddlenames.get(getRandomNumberInRange(0, femaleMiddlenames.size() - 1));
    }

    private static Date getRandomBirthdate() {
        int randomYear = getRandomNumberInRange(1950, 2018);
        int randomMonth = getRandomNumberInRange(1, 12);
        int randomDay = getRandomNumberInRange(1, 28);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, randomDay);
        calendar.set(Calendar.MONTH, randomMonth-1);
        calendar.set(Calendar.YEAR, randomYear);

        return calendar.getTime();
    }

    private static String getRandomPlaceName() {
        return places.get(getRandomNumberInRange(0, places.size() - 1));
    }

    private static int getRandomPlacePrice(String name) {
        if (name.equals(places.get(0))) {
            return getRandomNumberInRange(MIN_PLACE_1_PRICE, MAX_PLACE_1_PRICE);
        } else {
            return getRandomNumberInRange(MIN_PLACE_2_PRICE, MAX_PLACE_2_PRICE);
        }
    }

    private static Client getRandomClient(int clientsCount) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Client client = session.get(Client.class, getRandomNumberInRange(1, clientsCount));
        session.getTransaction().commit();

        return client;
    }

    private static int getClientsCountInDatabase() {
        int count = getCommonCount(Client.class);

        return count;
    }

    private static String getRandomFoodName() {
        return feed.get(getRandomNumberInRange(0, feed.size() - 1));
    }

    private static int getRandomFoodPrice() {
        return getRandomNumberInRange(MIN_FOOD_PRICE, MAX_FOOD_PRICE);
    }

    private static String getRandomTravelName() {
        return travels.get(getRandomNumberInRange(0, travels.size() - 1));
    }

    private static Date getRandomCommonDateStart(int startMaxDays) {
        int year = 2019;
        int randomDays = getRandomNumberInRange(1, startMaxDays);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, year);
        calendar.add(Calendar.DATE, randomDays);

        return calendar.getTime();
    }

    private static Date getRandomCommonDateEnd(Date dateStart, int maxDuration) {
        int randomDays = getRandomNumberInRange(1, maxDuration);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStart);
        calendar.add(Calendar.DATE, randomDays);

        return calendar.getTime();
    }

    private static String getRandomProductName() {
        return products.get(getRandomNumberInRange(0, products.size() - 1));
    }

    private static int getRandomProductPrice() {
        return getRandomNumberInRange(MIN_PRODUCT_PRICE, MAX_PRODUCT_PRICE);
    }

    private static Travel getRandomTravel(int travelsCount) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Travel travel = session.get(Travel.class, getRandomNumberInRange(1, travelsCount));
        session.getTransaction().commit();

        return travel;
    }

    private static Product getRandomProduct(int productsCount) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Product product = session.get(Product.class, getRandomNumberInRange(1, productsCount));
        session.getTransaction().commit();

        return product;
    }

    private static RadioRent getRandomRent(int rentsCount) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        RadioRent rent = session.get(RadioRent.class, getRandomNumberInRange(1, rentsCount));
        session.getTransaction().commit();

        return rent;
    }

    private static int getTravelsCountInDatabase() {
        int count = getCommonCount(Travel.class);

        return count;
    }

    private static int getProductsCountInDatabase() {
        int count = getCommonCount(Product.class);

        return count;
    }

    private static int getRentsCountInDatabase() {
        int count = getCommonCount(RadioRent.class);

        return count;
    }

    private static int getCommonCount(Class clazz) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(clazz);
        criteria.setProjection(Projections.rowCount());

        List employees = criteria.list();

        session.getTransaction().commit();

        if (employees != null) {
            int rowCount = ((Long) employees.get(0)).intValue();
            System.out.println("Total Results:" + rowCount);
            return rowCount;
        } else {
            return 0;
        }
    }

    private static void saveEntity(Object object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration()
                .configure()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(ClientService.class)
                .addAnnotatedClass(Food.class)
                .addAnnotatedClass(Gender.class)
                .addAnnotatedClass(Place.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(RadioRent.class)
                .addAnnotatedClass(Travel.class)
                .buildSessionFactory();
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void closeAll() {
        sessionFactory.getCurrentSession().close();
        sessionFactory.close();
    }
}
