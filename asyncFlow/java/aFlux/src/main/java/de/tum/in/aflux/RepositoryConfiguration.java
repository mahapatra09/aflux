/*
 *
 *  *
 *  * aFlux: JVM based IoT Mashup Tool
 *  * Copyright (C) 2018  Tanmaya Mahapatra
 *  *
 *  * This file is part of aFlux.
 *  *
 *  * aFlux is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * aFlux is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with aFlux.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
package de.tum.in.aflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import de.tum.in.aflux.model.FlowSetting;
import de.tum.in.aflux.model.NodeElementSetting;

/**
 * General configuration for spring repositories
 * @author Tanmaya Mahapatra
 *
 */
@Configuration
@PropertySource("classpath:/application.properties")
@EnableMongoRepositories(basePackages = "de.tum.in.aflux.dao")
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

	@Autowired
	Environment env;
	
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
/*	
	   @Bean
	   public static PropertySourcesPlaceholderConfigurer
	     propertySourcesPlaceholderConfigurer() {
	      return new PropertySourcesPlaceholderConfigurer();
	   }
	*/   
	   
    @Value("${mongodb.host}")
	public String mongodbHost; // =env.getProperty("mongodb.host");
     @Value("${mongodb.port}")
	public String mongodbPort="27017";
    @Value("${mongodb.database}")
	public String mongodbDatabase;
	
	
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(NodeElementSetting.class);
        config.exposeIdsFor(FlowSetting.class);
    }
    
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(mongodbHost,new Integer(mongodbPort));
    }
 
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), mongodbDatabase);
    }   
    
    
    
	// Bean name must be "multipartResolver", by default Spring uses method name as bean name.
    /*
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
    */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }    
    
    
    
	/*
	// if the method name is different, you must define the bean name manually like this :
	@Bean(name = "multipartResolver")
    public MultipartResolver createMultipartResolver() {
        return new StandardServletMultipartResolver();
    }*/
    @Bean
    public CommonsMultipartResolver viewResolver() {
    	CommonsMultipartResolver viewResolver=new CommonsMultipartResolver();
        return viewResolver;
    }    
    
    
    
    
}