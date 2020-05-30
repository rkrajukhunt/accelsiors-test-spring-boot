package com.accelsiors.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Start Spring Boot Web Application
 * @author ZoltanS
 * @param args	Command line parameters
 *
 */

@SpringBootApplication
public class StartWebApplication 
{
    public static void main( String[] args ) {
    	SpringApplication.run(StartWebApplication.class, args);
    }
}
