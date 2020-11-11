//package com.stealth.yash.FaceRecognition.bootstrao;
//
//import com.stealth.yash.FaceRecognition.model.LogUsers;
//import com.stealth.yash.FaceRecognition.repository.LogUsersRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//@Component
//public class Loader implements CommandLineRunner {
//
//    private final LogUsersRepository logUsersRepository;
//
//    public Loader(LogUsersRepository logUsersRepository) {
//        this.logUsersRepository = logUsersRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//
//        LogUsers user1 = new LogUsers();
//        user1.setUserFobId("001");
//        user1.setAccessDate(LocalDate.of(2020, 11, 1));
//        user1.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user1);
//
//        LogUsers user2 = new LogUsers();
//        user2.setUserFobId("002");
//        user2.setAccessDate(LocalDate.of(2020, 11, 11));
//        user2.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user2);
//
//        LogUsers user3 = new LogUsers();
//        user3.setUserFobId("003");
//        user3.setAccessDate(LocalDate.of(2020, 11, 11));
//        user3.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user3);
//
//        LogUsers user4 = new LogUsers();
//        user4.setUserFobId("005");
//        user4.setAccessDate(LocalDate.of(2020, 11, 25));
//        user4.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user4);
//
//        LogUsers user5 = new LogUsers();
//        user5.setUserFobId("004");
//        user5.setAccessDate(LocalDate.of(2020, 12, 10));
//        user5.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user5);
//
//        LogUsers user6 = new LogUsers();
//        user6.setUserFobId("002");
//        user6.setAccessDate(LocalDate.of(2020, 12, 19));
//        user6.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user6);
//
//        LogUsers user7 = new LogUsers();
//        user7.setUserFobId("002");
//        user7.setAccessDate(LocalDate.of(2020, 12, 28));
//        user7.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user7);
//
//        LogUsers user8 = new LogUsers();
//        user8.setUserFobId("004");
//        user8.setAccessDate(LocalDate.of(2020, 12, 31));
//        user8.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user8);
//
//
//        LogUsers user9 = new LogUsers();
//        user9.setUserFobId("002");
//        user9.setAccessDate(LocalDate.of(2021, 1, 2));
//        user9.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user9);
//
//        LogUsers user10 = new LogUsers();
//        user10.setUserFobId("002");
//        user10.setAccessDate(LocalDate.of(2021, 1, 15));
//        user10.setAccessTime(LocalTime.now());
//        logUsersRepository.save(user10);
//
//    }
//}
