package run.makemy.services.currentuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import run.makemy.domains.user.CurrentUser;
import run.makemy.domains.user.User;
import run.makemy.services.user.UserService;

import java.util.function.Supplier;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email)
                .orElseThrow(new Supplier<UsernameNotFoundException>() {
                    @Override
                    public UsernameNotFoundException get() {
                        return new UsernameNotFoundException(String.format("User with email=%s could not be found", email));
                    }
                });

        return new CurrentUser(user);
    }

}
