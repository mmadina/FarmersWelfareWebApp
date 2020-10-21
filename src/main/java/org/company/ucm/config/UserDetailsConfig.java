package org.company.ucm.config;

import org.company.ucm.client.FarmersWelfareServicesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsConfig implements UserDetailsService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private FarmersWelfareServicesClient farmersWelfareServicesClient;

    @Autowired
    public UserDetailsConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
                             FarmersWelfareServicesClient farmersWelfareServicesClient) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.farmersWelfareServicesClient = farmersWelfareServicesClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<org.company.ucm.model.User> optionalUser = farmersWelfareServicesClient.findUserByUsername(username);
        if(optionalUser.isPresent()) {
            org.company.ucm.model.User user = optionalUser.get();

            String[] roles = new String[1];
            if(user.getAdminUser()) {
                roles[0] = "ADMIN_USER";
            } else {
                roles[0] = "NON_ADMIN_USER";
            }
            return User.builder()
                    .username(user.getFirstName())
                    .password( bCryptPasswordEncoder.encode(user.getPassword()) )
                    .roles(roles)
                    .build();
        } else {
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }
}
