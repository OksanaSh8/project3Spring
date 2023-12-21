package oksana.internship.lesson3.db;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import oksana.internship.lesson3.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;


    public class ConnectionManagerTest extends SpringConfig {
        private static int containerPort = 3306;
        private static int localPort = 3306;

        public static MySQLContainer<?> container = new MySQLContainer<>("mysql:5.6")
                .withDatabaseName("rest_api_template")
                .withUsername("root")
                .withPassword("")
                .withExposedPorts(containerPort)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(localPort),
                                new ExposedPort(containerPort)))
                ))
                .withInitScript("db.sql");

        public ConnectionManagerTest(ApplicationContext applicationContext, Environment env) {
            super(applicationContext, env);
        }


        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setUrl(container.getJdbcUrl());
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUsername(container.getUsername());
            dataSource.setPassword(container.getPassword());
            return dataSource;
        }

    }