package se.lexicon.jpaworkshop;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.jpaworkshop.entity.AppUser;
import se.lexicon.jpaworkshop.entity.Details;
import se.lexicon.jpaworkshop.repository.AppUserRepository;
import se.lexicon.jpaworkshop.repository.DetailsRepository;

@Component
@Transactional
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    DetailsRepository detailsRepository;

    @Override
    public void run(String... args) throws Exception {

        AppUser appUser = new AppUser("Test", "password");
        Details details = new Details("TestUserName","test@test.se");
        detailsRepository.save(details);
        appUser.setDetails(details);
        appUserRepository.save(appUser);

        appUserRepository.findByUsername("Test").ifPresent(System.out::println);
        appUserRepository.findByDetails_Id(details.getId()).ifPresent(System.out::println);
    }
}
