package com.example.secondhandclothes.clothes.domain;

import com.example.secondhandclothes.user.domain.User;
import com.example.secondhandclothes.user.domain.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@DataJpaTest
@RunWith(SpringRunner.class)
public class GarmentRepositoryTest {
    @Autowired
    private GarmentRepository garmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void searchWithValidParametersReturnsListTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("password");
        user.setRoles("ROLE_USER");
        User savedUser = userRepository.save(user);

        Garment garment1 = new Garment();
        garment1.setType("Shirt");
        garment1.setDescription("Cotton Shirt");
        garment1.setSize("M");
        garment1.setPrice(19.99);
        garment1.setPublisher(savedUser);

        Garment garment2 = new Garment();
        garment2.setType("Pants");
        garment2.setDescription("Blue Jeans");
        garment2.setSize("L");
        garment2.setPrice(29.99);
        garment2.setPublisher(savedUser);

        garmentRepository.save(garment1);
        garmentRepository.save(garment2);

        List<Garment> foundGarments = garmentRepository.search("Shirt", "Cotton", "M", 19.99, 1L);

        assertNotNull(foundGarments);
        assertEquals(1, foundGarments.size());
        assertEquals("Shirt", foundGarments.get(0).getType());
        assertTrue(foundGarments.get(0).getDescription().contains("Cotton"));
        assertEquals("M", foundGarments.get(0).getSize());
        assertEquals(19.99, foundGarments.get(0).getPrice());
    }

    @Test
    public void searchWithNonMatchingParametersReturnsEmptyListTest() {
        List<Garment> foundGarments = garmentRepository.search("Jacket", "Leather", "S", 50.0, 1L);

        assertNotNull(foundGarments);
        assertTrue(foundGarments.isEmpty());
    }

    @Test
    public void searchWithoutParametersReturnsFullListTest() {
        User user1 = new User();
        user1.setId(2L);
        user1.setUsername("test");
        user1.setPassword("password");
        user1.setRoles("ROLE_USER");

        Garment garment1 = new Garment();
        garment1.setType("Shirt");
        garment1.setDescription("Blue Cotton Shirt");
        garment1.setSize("M");
        garment1.setPrice(15.99);

        Garment garment2 = new Garment();
        garment2.setType("Sweater");
        garment2.setDescription("Wool Sweater");
        garment2.setSize("L");
        garment2.setPrice(22.99);

        User saved = userRepository.save(user1);
        garment1.setPublisher(saved);
        garment2.setPublisher(saved);

        garmentRepository.save(garment1);
        garmentRepository.save(garment2);

        List<Garment> foundGarments = garmentRepository.search(null, null, null, null, null);

        assertNotNull(foundGarments);
        assertEquals(2, foundGarments.size());
    }
}
