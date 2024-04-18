package ma.emsi.hopital3;

import ma.emsi.hopital3.entities.Patient;
import ma.emsi.hopital3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Hopital3Application implements CommandLineRunner {
    //utiliser interface patienrepo avec injection des dependance en utilisant auto
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Hopital3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Merouan",new Date(),false,34));
        patientRepository.save(new Patient(null,"hanaa",new Date(),false,4321));
        patientRepository.save(new Patient(null,"ziad",new Date(),true,344));
    }
}
