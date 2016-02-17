package com.springmvcsampler.registry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Created by atheedom on 15/01/2016.
 */
@Component
public class ServiceRegistrationEventListener {

    private static final String ANNOTATED_CLASSES = "annotatedClasses";

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    ApplicationContext applicationContext;

    @EventListener
    void contextRefreshedEvent(ContextRefreshedEvent event) throws NoSuchFieldException, IllegalAccessException {
        ApplicationContext applicationContext = event.getApplicationContext();
        AnnotationConfigWebApplicationContext annotationContext = ((AnnotationConfigWebApplicationContext) applicationContext);
        for (Field field : annotationContext.getClass().getDeclaredFields()) {
            if (ANNOTATED_CLASSES.equals(field.getName())) {
                field.setAccessible(true);
                Object aContext = field.get(annotationContext);
                if (aContext instanceof Set) {
                    Set<Class<?>> classes = (Set<Class<?>>) aContext;
                    for (Class clazz : classes) {
                        for (Annotation annotation : clazz.getDeclaredAnnotations()) {
                            if (annotation.annotationType().isAssignableFrom(ServiceRegistration.class)) {

                                RegistrationConfiguration rc = new RegistrationConfiguration(((ServiceRegistration) annotation).serviceName());

                                applicationEventPublisher.publishEvent(new ServiceRegistrationEvent(rc));
                            }
                        }
                    }
                }
            }
        }
    }


}


//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//
//        String[] annotations = applicationContext.getBeanNamesForAnnotation(ServiceRegistration.class);
//
//        if (annotations != null && annotations.length > 0) {
//            System.out.println("Annotation found");
//        } else {
//            System.out.println("Annotation not found");
//        }
//
//
//    }


//    System.out.println("Enabled: " + enabled);
//
//
//    String[] annotations = applicationContext.getBeanNamesForAnnotation(ServiceRegistration.class);
//    if (annotations != null && annotations.length > 0) {
//        System.out.println("Annotation found");
//    } else {
//        System.out.println("Annotation not found");
//    }