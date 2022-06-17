package com.unemploymenthouse.unemploymenthouse;

import com.unemploymenthouse.unemploymenthouse.domain.Specialty;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.repository.UnemployedRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UnemployedRepositoryTest {
    @Autowired
    private UnemployedRepository unemployedRepository;

    @Autowired
    private TestEntityManager entityManager;

   @Test
    public void testCreateSpecialty() {
        Specialty specialty1 = new Specialty();
        Specialty specialty2 = new Specialty();

        entityManager.persist(specialty1);
        entityManager.persist(specialty2);
    }

    @Test
    public void testCrateNewUnemployedWithOneSpecialty(){
        Specialty specialty1 = entityManager.find(Specialty.class, 2);
        Unemployed unemployed3 = new Unemployed();
        unemployed3.addSpecialty(specialty1);

        unemployedRepository.save(unemployed3);
    }

    @Test
    public void testCrateNewUnemployedWithTwoSpecialties(){
        Specialty specialty2 = entityManager.find(Specialty.class, 1);
        Specialty specialty1 = entityManager.find(Specialty.class, 2);
        Unemployed unemployed2 = entityManager.find(Unemployed.class, 6);
        unemployed2.addSpecialty(specialty1);
        unemployed2.addSpecialty(specialty2);
    }

    @Test
    public void testAssigningSpecialtyToExistingUser(){
       Unemployed unemployed5 = unemployedRepository.findById(5).get();
       Specialty specialty2 = entityManager.find(Specialty.class, 1);
       Specialty specialty1 = entityManager.find(Specialty.class, 2);
       unemployed5.addSpecialty(specialty1);
       unemployed5.addSpecialty(specialty2);
    }

    @Test
    public void testRemoveSpecialtyFromExistingUser(){
        Unemployed unemployed6 = unemployedRepository.findById(6).get();
        Specialty specialty2 = new Specialty(2);
        unemployed6.removeSpecialty(specialty2);
    }

    @Test
    public void testCreateNewUserWithNewSpecialty(){
        Specialty specialty3 = new Specialty();
        Unemployed unemployed8 = new Unemployed();
        unemployed8.addSpecialty(specialty3);

        unemployedRepository.save(unemployed8);
    }

    @Test
    public void testGetUnemployed(){
        Unemployed unemployed5 = unemployedRepository.findById(5).get();
        System.out.println(unemployed5.getIdUnemployed());
        //System.out.println(unemployed5.getSpecialties());
    }

    @Test
    public void testRemoveUnemployed(){
        unemployedRepository.deleteById(8);
    }
}
