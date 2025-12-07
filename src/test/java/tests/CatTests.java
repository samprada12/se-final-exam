package tests;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Cat;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CatTests {

    private static Cat actualCat;

    @BeforeAll
    public static void createAnimals() {
        actualCat = new Cat(AnimalType.DOMESTIC, Skin.FUR, Gender.UNKNOWN, Breed.UNKNOWN);
    }

    @Test
    @Order(1)
    @DisplayName("Cat Animal Type Test")
    public void animalTypeTests() {
        assertEquals(AnimalType.DOMESTIC, actualCat.getAnimalType());
    }

    @Test
    @Order(1)
    @DisplayName("Cat Speak Tests - Domestic")
    public void catGoesPrrTest() {
        assertEquals("The cat goes prr! prr!", actualCat.speak());
    }

    @Test
    @Order(1)
    @DisplayName("Cat Hypoallergenic Test")
    public void catHypoallergenicTests() {
        assertTrue(actualCat.catHypoallergenic().contains("cat"));
    }

    @Test
    @Order(1)
    @DisplayName("Cat Number of Legs Test")
    public void legTests() {
        assertNotNull(actualCat.getNumberOfLegs());
    }

    @Test
    @Order(2)
    @DisplayName("Cat Gender Test")
    public void genderTestFemale() {
        actualCat = new Cat(AnimalType.WILD, Skin.UNKNOWN, Gender.FEMALE, Breed.UNKNOWN);
        assertEquals(Gender.FEMALE, actualCat.getGender());
    }

    @Test
    @Order(2)
    @DisplayName("Cat Breed Test")
    public void catBreedTest() {
        actualCat = new Cat(AnimalType.WILD, Skin.UNKNOWN, Gender.FEMALE, Breed.BURMESE);
        assertEquals(Breed.BURMESE, actualCat.getBreed());
    }

    @Test
    @Order(2)
    @DisplayName("Cat Speak Tests - Wild")
    public void catGoesHissTest() {
        actualCat = new Cat(AnimalType.WILD, Skin.UNKNOWN, Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The cat goes hiss! hiss!", actualCat.speak());
    }

    @Test
    @Order(2)
    @DisplayName("Cat Speak Tests - Default Branch")
    public void catDefaultSpeakTest() {
        actualCat = new Cat(AnimalType.UNKNOWN, Skin.UNKNOWN, Gender.UNKNOWN, Breed.UNKNOWN);
        String speak = actualCat.speak();
        assertTrue(speak.contains("The cat goes "));
    }
}
