package com.enp.bibliotheque.benp.dto.initalized;


import com.enp.bibliotheque.benp.users.entities.Role;
import com.enp.bibliotheque.benp.users.entities.User;
import com.enp.bibliotheque.benp.users.enums.Gender;
import com.enp.bibliotheque.benp.users.enums.Profile;
import com.enp.bibliotheque.benp.users.repository.RoleRepository;
import com.enp.bibliotheque.benp.users.repository.UserRepository;
import com.sun.el.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor =@__(@Autowired))
public class DataInitialzed {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @PostConstruct
    public void DataInitialyzed() {

        log.info("****************************** creation des roles ******************************");
        roleRepository.save(Role.builder().id(null).role(Profile.SUPER_ADMIN.name()).build());
        roleRepository.save(Role.builder().id(null).role(Profile.ADMIN.name()).build());
        roleRepository.save(Role.builder().id(null).role(Profile.USER.name()).build());

        log.info("****************************** creation du super-admin ******************************");

        if(userRepository.count() > 0 ) {
                return;
        }

        userRepository.saveAndFlush(User.builder()
                            .id(null)
                            .firstname("Al Ousseynou")
                            .lastname("Fall")
                            .username("ousseynou")
                            .password(passwordEncoder.encode("Fall@86SQ"))
                            .gender(Gender.MALE)
                            .phoneNumber("779909898")
                            .email("fallNX@enp.sn")
                            .active(true)
                            .creationDate(ZonedDateTime.now())
                            .lastConnectionDate(ZonedDateTime.now())
                            .authorities(List.of(roleRepository.findRoleByRole(Profile.SUPER_ADMIN.name())))
                            .build());

        log.info("****************************** creation des admins ******************************");

        userRepository.saveAndFlush(User.builder()
                            .id(null)
                            .firstname("Macoumba")
                            .lastname("Diong")
                            .username("macoumba")
                            .password(passwordEncoder.encode("Diong@78PJ"))
                            .gender(Gender.MALE)
                            .phoneNumber("781101010")
                            .email("diongMK@enp.sn")
                            .active(true)
                            .creationDate(ZonedDateTime.now())
                            .lastConnectionDate(ZonedDateTime.now())
                            .authorities(List.of(roleRepository.findRoleByRole(Profile.USER.name())))
                            .build());

        userRepository.saveAndFlush(User.builder()
                            .id(null)
                            .firstname("Mouhamed")
                            .lastname("Diack")
                            .username("mouhamed")
                            .password(passwordEncoder.encode("Diack57@RT"))
                            .gender(Gender.MALE)
                            .phoneNumber("781575757")
                            .email("diackZZ@enp.sn")
                            .active(true)
                            .creationDate(ZonedDateTime.now())
                            .lastConnectionDate(ZonedDateTime.now())
                            .authorities(List.of(roleRepository.findRoleByRole(Profile.ADMIN.name())))
                            .build());

        userRepository.saveAndFlush(User.builder()
                            .id(null)
                            .firstname("Makhtar")
                            .lastname("Saré")
                            .username("makhtar")
                            .password(passwordEncoder.encode("sare98@"))
                            .gender(Gender.MALE)
                            .phoneNumber("781886654")
                            .email("sare011@enp.sn")
                            .active(true)
                            .creationDate(ZonedDateTime.now())
                            .lastConnectionDate(ZonedDateTime.now())
                            .authorities(List.of(roleRepository.findRoleByRole(Profile.ADMIN.name())))
                            .build());

        userRepository.saveAndFlush(User.builder()
                            .id(null)
                            .firstname("Rama")
                            .lastname("Diouf")
                            .username("rama")
                            .password(passwordEncoder.encode("rama@30E"))
                            .gender(Gender.FEMALE)
                            .phoneNumber("781812321")
                            .email("rama15@enp.sn")
                            .active(true)
                            .creationDate(ZonedDateTime.now())
                            .lastConnectionDate(ZonedDateTime.now())
                            .authorities(List.of(roleRepository.findRoleByRole(Profile.USER.name())))
                            .build());

    }

    @PreDestroy
    public void DataDestroyed() {
        userRepository.deleteAll();
    }
}
