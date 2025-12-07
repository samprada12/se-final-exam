package tests;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Snake;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SnakeTests {

    private static Snake snake;

    @BeforeAll
    public static void init() {
        snake = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.UNKNOWN, Breed.UNKNOWN);
    }

    @Test
    @Order(1)
    public void testDomesticSpeak() {
        assertEquals("The snake goes hissssss!", snake.speak());
    }

    @Test
    @Order(1)
    public void testHypoallergenic() {
        assertTrue(snake.snakeHypoallergenic().toLowerCase().contains("snake"));
    }

    @Test
    @Order(1)
    public void testLengthGetterSetter() {
        snake.setLengthInFeet(7);
        assertEquals(7, snake.getLengthInFeet());
    }

    @Test
    @Order(2)
    public void testWildSpeak() {
        snake = new Snake(AnimalType.WILD, Skin.SCALES, Gender.MALE, Breed.UNKNOWN);
        assertEquals("The snake goes SSSSS-GRRR!", snake.speak());
    }

    @Test
    @Order(2)
    public void testDefaultSpeak() {
        snake = new Snake(AnimalType.UNKNOWN, Skin.SCALES, Gender.UNKNOWN, Breed.UNKNOWN);
        assertTrue(snake.speak().contains("The snake goes"));
    }
}
