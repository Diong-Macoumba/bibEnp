package com.enp.bibliotheque.benp.dto.initalized;


import com.enp.bibliotheque.benp.users.entities.User;
import com.enp.bibliotheque.benp.users.enums.Gender;
import com.enp.bibliotheque.benp.users.enums.Profile;
import com.enp.bibliotheque.benp.users.repository.UserRepository;
import com.enp.bibliotheque.benp.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.ZonedDateTime;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor =@__(@Autowired))
public class DataInitialzed {
    private final UserRepository userRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    @PostConstruct
    public void DataInitialyzed() {

        log.info("****************************** creation du super-admin ******************************");
        final User fall = User.builder()
                .id(null).firstname("Al Ousseynou").lastname("Fall").username("ousseynou")
                .password(passwordEncoder.encode("Fall@86")).gender(Gender.MALE)
                .phoneNumber("779909898").email("fall@enp.sn").active(true).creationDate(ZonedDateTime.now())
                .build();
        userService.create(fall);

        userService.addRoleToUser("Ousseynou", Profile.SUPER_ADMIN.name());

        log.info("****************************** creation des admins ******************************");

        userService.create(User.builder()
                .id(null).firstname("Macoumba").lastname("Diong").username("macoumba")
                .password(passwordEncoder.encode("Diong@78")).gender(Gender.MALE)
                .phoneNumber("781101010").email("diong@enp.sn").active(true).creationDate(ZonedDateTime.now())
                .build());

        userService.addRoleToUser("macoumba", Profile.ADMIN.name());

        userService.create(User.builder()
                .id(null).firstname("Mouhamed").lastname("Diack").username("mouhamed")
                .password(passwordEncoder.encode("Diack57@")).gender(Gender.MALE)
                .phoneNumber("781575757").email("diack@enp.sn").active(true).creationDate(ZonedDateTime.now())
                .build());

        userService.addRoleToUser("mouhamed", Profile.ADMIN.name());

        userService.create(User.builder()
                .id(null).firstname("Makhtar").lastname("Saré").username("makhtar")
                .password(passwordEncoder.encode("sare98@")).gender(Gender.MALE)
                .phoneNumber("781886654").email("sare098@enp.sn").active(true).creationDate(ZonedDateTime.now())
                .build());

        userService.addRoleToUser("makhtar", Profile.ADMIN.name());

    }

    @PreDestroy
    public void DataDestroyed() {
        userRepository.deleteAll();
    }
}
