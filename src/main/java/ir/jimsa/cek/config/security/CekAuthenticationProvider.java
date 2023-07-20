package ir.jimsa.cek.config.security;

import ir.jimsa.cek.model.entity.UserEntity;
import ir.jimsa.cek.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CekAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;

    public CekAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.request = request;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // ip location check
        // ...
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String ip1 = details.getRemoteAddress();
        String ip2 = request.getRemoteAddr();

        // Login from new location
        // ...

        // username and password check
        String username = authentication.getName();
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new BadCredentialsException("Can not find any user");
        }
        String rawPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(rawPassword, userEntity.getEncodedPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }

        // Authenticated:
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));
        return new UsernamePasswordAuthenticationToken(username, passwordEncoder, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
