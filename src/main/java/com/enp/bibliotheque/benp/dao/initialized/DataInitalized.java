package com.enp.bibliotheque.benp.dao.initialized;


import com.enp.bibliotheque.benp.persons.entities.Agent;
import com.enp.bibliotheque.benp.persons.entities.Book;
import com.enp.bibliotheque.benp.persons.entities.Role;
import com.enp.bibliotheque.benp.persons.entities.User;
import com.enp.bibliotheque.benp.persons.enums.BookFormat;
import com.enp.bibliotheque.benp.persons.enums.Profile;
import com.enp.bibliotheque.benp.persons.repositories.AgentRepository;
import com.enp.bibliotheque.benp.persons.repositories.BookRepository;
import com.enp.bibliotheque.benp.persons.repositories.RoleRepository;
import com.enp.bibliotheque.benp.persons.repositories.UserRepository;
import com.enp.bibliotheque.benp.persons.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor =@__(@Autowired))
public class DataInitalized {

    private final UserRepository enpUserRepository;
    private final RoleRepository enpRoleRepository;
    private final UserService enpAccountService;
    private final AgentRepository agentRepository;
    private final BookRepository bookRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @PostConstruct
    public void DataInitialez() {
        log.info("****************************** creation des roles ******************************");
        enpRoleRepository.save(Role.builder().id(null).role("USER").build());
        enpRoleRepository.save(Role.builder().id(null).role("ADMIN").build());
        enpRoleRepository.save(Role.builder().id(null).role("SUPER_ADMIN").build());


        log.info("****************************** creation du super-admin ******************************");
        enpUserRepository.save(User.builder()
                        .id(null)
                        .firstname("Al Ouseynou")
                        .lastname("Fall")
                        .username("Ousseynou")
                        .password(passwordEncoder.encode("Fall123@"))
                        .gender("MALE")
                        .phoneNumber("781234567")
                        .email("fall@enp.sn")
                        .active(true)
                        .build());

        enpAccountService.addRoleToUser("Ousseynou", Profile.SUPER_ADMIN);

        log.info("****************************** creation des admins ******************************");

        enpUserRepository.save(User.builder()
                        .id(null)
                        .firstname("Macoumba")
                        .lastname("Diong")
                        .username("Macoumba")
                        .password(passwordEncoder.encode("Diong@78"))
                        .gender("MALE")
                        .phoneNumber("781101010")
                        .email("diong10@enp.sn")
                        .active(true)
                        .build());

        enpAccountService.addRoleToUser("Macoumba", Profile.ADMIN);

        enpUserRepository.save(User.builder()
                        .id(null)
                        .firstname("Mouhamed")
                        .lastname("Diack")
                        .username("Mouhamed")
                        .password(passwordEncoder.encode("Diack57@"))
                        .gender("MALE")
                        .phoneNumber("781575757")
                        .email("diack@enp.sn")
                        .active(true)
                        .build());

        enpAccountService.addRoleToUser("Mouhamed", Profile.ADMIN);

        enpUserRepository.save(User.builder()
                        .id(null)
                        .firstname("Makhtar")
                        .lastname("Saré")
                        .username("Makhtar")
                        .password(passwordEncoder.encode("sare98@"))
                        .gender("MALE")
                        .phoneNumber("781576657")
                        .email("sare098@enp.sn")
                        .active(true)
                        .build());

        enpAccountService.addRoleToUser("Makhtar", Profile.AGENT);

        log.info("****************************** creation des agents ******************************");

        agentRepository.save(Agent.builder()
                        .id(null)
                        .prenom("Michel")
                        .nom("Diouf")
                        .email("michel@gmail.com")
                        .ccap("7844259M4")
                        .grade("Col")
                        .telephone("781475869")
                        .build());

        agentRepository.save(Agent.builder()
                        .id(null)
                        .prenom("Andou")
                        .nom("Diop")
                        .email("abd@gmail.com")
                        .ccap("7845659M4")
                        .grade("Lte")
                        .telephone("781852869")
                        .build());

        agentRepository.save(Agent.builder()
                        .id(null)
                        .prenom("Amina")
                        .nom("Fall")
                        .email("afall@gmail.com")
                        .ccap("7248579M4")
                        .grade("Adj")
                        .telephone("771469869")
                        .build());


        bookRepository.save(Book.builder()
                        .id(null)
                        .name("Spring")
                        .author("mac")
                        .name("MacOs")
                        .format(BookFormat.NO_NUMERIC)
                        .stock(45)
                        .build());

        bookRepository.save(Book.builder()
                        .id(null)
                        .name("Angular")
                        .author("mat")
                        .name("Mmma")
                        .format(BookFormat.NO_NUMERIC)
                        .stock(4)
                        .build());

        bookRepository.save(Book.builder()
                        .id(null)
                        .name("Flutter")
                        .author("Asd")
                        .name("Madrzz")
                        .format(BookFormat.NUMERIC)
                        .stock(12)
                        .build());
    }

    @PreDestroy
    public void DataDestroyed() {
        enpRoleRepository.deleteAll();
        enpUserRepository.deleteAll();
    }
}
