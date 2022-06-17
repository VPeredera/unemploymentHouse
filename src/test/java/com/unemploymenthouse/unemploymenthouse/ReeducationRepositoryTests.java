package com.unemploymenthouse.unemploymenthouse;

import com.unemploymenthouse.unemploymenthouse.domain.Reeducation;
import com.unemploymenthouse.unemploymenthouse.domain.Unemployed;
import com.unemploymenthouse.unemploymenthouse.repository.ReeducationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ReeducationRepositoryTests {
    @Autowired
    private ReeducationRepository reeducationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUnemployed() {
        Unemployed unemployed1 = new Unemployed();
        Unemployed unemployed2 = new Unemployed();

        entityManager.persist(unemployed1);
        entityManager.persist(unemployed2);
    }

    @Test
    public void testCrateNewReeducation(){
        Unemployed unemployed1 = entityManager.find(Unemployed.class, 5);
        Reeducation reeducation1 = new Reeducation();
        reeducation1.addUnemployed(unemployed1);

        reeducationRepository.save(reeducation1);
    }
}
