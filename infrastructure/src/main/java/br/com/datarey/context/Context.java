package br.com.datarey.context;

import java.lang.annotation.Annotation;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import br.com.datarey.dao.JpaUtil;

public class Context {
    private static Weld weld;
    private static WeldContainer container;

  
    public static void init(){
        weld = new Weld();
        container = weld.initialize();
        //inicia conexão da jpa
        getBean(JpaUtil.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> clazz) {
        return (T) container.instance().select(clazz).get();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> clazz, Annotation... annotations) {
        return (T) container.instance().select(clazz, annotations).get();
    }
    
    public static void shutdown(){
        weld.shutdown();
        System.exit(0);
    }
}
