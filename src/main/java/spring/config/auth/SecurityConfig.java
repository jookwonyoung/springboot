package spring.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import spring.domain.user.Role;


@RequiredArgsConstructor
@EnableWebSecurity      //Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                //h2화면 사용
                .csrf().disable().headers().frameOptions().disable()
                .and()
                //URL별 권한 관리 설정
                .authorizeRequests().antMatchers("/", "/css/**","images/**",
                        "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //나머 URL은      인증된 사용자만
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }


}
