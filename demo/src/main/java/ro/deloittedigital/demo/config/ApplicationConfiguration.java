package ro.deloittedigital.demo.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.deloittedigital.demo.model.domain.User;
import ro.deloittedigital.demo.model.dto.UserBasicInfoDTO;
import ro.deloittedigital.demo.model.dto.UserDTO;


@Configuration
@EnableWebSecurity
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
    }
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        Converter<UserDTO, User> userConverter = context ->{
            UserDTO s = context.getSource();
            User d = new User();
            d.setId(s.getId());
            d.setFirst_name(s.getFirst_name());
            d.setLast_name(s.getLast_name());
            d.setEmail(s.getEmail());
            d.setPassword(s.getPassword());
            d.setDate_of_birth(s.getDate_of_birth());
            d.setGender(s.getGender());
            d.setUsername(s.getUsername());
            return d;
        };
        Converter<UserBasicInfoDTO, User> userBasicInfoConverter = context ->{
            UserBasicInfoDTO s = context.getSource();
            User d = new User();
            d.setFirst_name(s.getFirst_name());
            d.setLast_name(s.getLast_name());
            d.setEmail(s.getEmail());
            d.setDate_of_birth(s.getDate_of_birth());
            d.setGender(s.getGender());
            d.setUsername(s.getUsername());
            return d;
        };

        modelMapper.addConverter(userConverter);
        modelMapper.addConverter(userBasicInfoConverter);
        return modelMapper;
    }
}
