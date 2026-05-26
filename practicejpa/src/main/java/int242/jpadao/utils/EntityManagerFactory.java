package int242.jpadao.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EntityManagerFactory {
    private static String PERSISTENCE_UNIT_NAME = "default";
    private static jakarta.persistence.EntityManagerFactory emf =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    static {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/application.env")){
            prop.load(input);
            PERSISTENCE_UNIT_NAME = prop.getProperty("PERSISTENCE_UNIT_NAME");
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(PERSISTENCE_UNIT_NAME);
    }
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
