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


// removed import org.springframework.boot.SpringApplication;
// removed import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * Main Application class
 * @author Tanmaya Mahapatra
 *
 */
// REMOVED @SpringBootApplication 
//@ComponentScan
//@ImportResource("classpath:hadoop-context.xml")
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
// REMOVED @EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class})
//@PropertySource("classpath:application.properties")
public class Application {
	
	//@Inject HbaseTemplate hbaseTemplate;
	public static void main(String[] args) {
		// removed SpringApplication.run(Application.class,args);
		// com.querydsl.mongodb.AbstractMongodbQuery a;
	}
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
    	
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
        		registry.addMapping("/api/**");
            }
        };
    }	
    
    /* removed
    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
        return new TomcatEmbeddedServletContainerFactory();
    }
*/
}


