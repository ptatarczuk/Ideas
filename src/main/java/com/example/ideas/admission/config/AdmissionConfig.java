//package com.example.ideas.admission.config;
//
//import com.example.ideas.admission.model.Admission;
//import com.example.ideas.admission.repository.AdmissionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class AdmissionConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerAdmission(AdmissionRepository repository) {
//        return args -> {
//            Admission firstAdmission = new Admission("This is awesome! I hope this idea gets a lot of supportive votes!");
//            Admission secondAdmission = new Admission("Mate, you high? The Board will never agree to this...");
//            repository.saveAll(List.of(firstAdmission, secondAdmission));
//        };
//    }
//}
