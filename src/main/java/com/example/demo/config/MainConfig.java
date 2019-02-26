package com.example.demo.config;
import com.example.demo.domain.Knight;
import com.example.demo.domain.Quest;
import com.example.demo.domain.repository.DBKnightRepository;
import com.example.demo.domain.repository.InMemoryRepository;
import com.example.demo.domain.repository.KnightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class MainConfig {
    @Bean(name = "inMemoryRepository")
    @Profile("dev")
    public KnightRepository createInMemoryRepo(){
        KnightRepository repo = new InMemoryRepository();
        return repo;
    }

    @Bean(name = "DBRepository")
    @Profile("prod")
    public KnightRepository createDBRepo(){
        KnightRepository repo = new DBKnightRepository();
        return repo;
    }
    @Autowired
    public void securityUsers(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user1");
    }
}
