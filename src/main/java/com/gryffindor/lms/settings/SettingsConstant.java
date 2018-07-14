package com.gryffindor.lms.settings;

import com.eclipsesource.json.JsonObject;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

public class SettingsConstant {
    public static double apiVersion=1.2;
    public static String userEndPoint = "user";
    public static String apiUrl = "api"+apiVersion;

    public static String getSuccessResponse(){
        JsonObject responseObject = new JsonObject();
        responseObject.add("success",true);
        return responseObject.toString();
    }
    public static String getFailedResponse(){
        JsonObject responseObject = new JsonObject();
        responseObject.add("success",false);
        return responseObject.toString();
    }
    public static SessionFactory getSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
}
