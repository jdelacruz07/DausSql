package com.game.repository;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.game.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DausSqlApplication {

//    private static final Logger log = LoggerFactory.getLogger(DausSqlApplication.class);

//    public static void main(String args[]) {
//        SpringApplication.run(DausSqlApplication.class, args);
//    }

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Override
//    public void run(String... strings) throws Exception {

  //      log.info("Creating tables");

 //       jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
//        jdbcTemplate.execute("CREATE TABLE customers(" +
//                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
//        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
//                .map(name -> name.split(" "))
//                .collect(Collectors.toList());
//
//        // Use a Java 8 stream to print out each tuple of the list
//        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
//
//        // Uses JdbcTemplate's batchUpdate operation to bulk load data
//        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
//
//        log.info("Querying for customer records where first_name = 'Josh':");
//        jdbcTemplate.query(
//                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
//                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//        ).forEach(customer -> log.info(customer.toString()));
//    	

//		int total = jdbcTemplate.queryForObject(
//				"SELECT count(*) FROM customers", Integer.class);
//        
//		System.out.println("Numero de registros" + total);
//        int id = 30;
//        int idupdate = 32;
//        String firstName = "Teresita";
//        String lastName = "Jimenez";
//        //////////////////   CRUD ////////////////////////////
//        insert(firstName, lastName);
//        selectAll();
//        update(idupdate,firstName,lastName);
//        delete(id);
//        
        private void insert(String firstName, String lastName) {
        	jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES (?,?)", firstName, lastName);
        }
        
        public void selectAll() {   
        	jdbcTemplate.query(
        			"SELECT * FROM customers",  new Object[] {  },
        			(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        			). forEach(customer -> System.out.println(customer));
        }
        
        public void update(Integer idupdate,String firstName, String lastName) {
        	jdbcTemplate.update(
        			"update customers set first_Name = ?, last_Name = ? where id = ?",  firstName, lastName, idupdate );
        }
        
        public void delete(Integer id) {
        	jdbcTemplate.update(
        			"DELETE FROM customers where id= ?",  new Object[] { id });
        }
    }
    
    		
//}