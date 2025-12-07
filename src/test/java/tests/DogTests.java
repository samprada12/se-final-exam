package tests;

import org.junit.jupiter.api.*;
import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Dog;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DogTests {

    private static Dog actualDog;

    @BeforeAll
    public static void createAnimals() {
        actualDog = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.UNKNOWN, Breed.UNKNOWN);
    }

    @Test
    @Order(1)
    @DisplayName("Dog Animal Type Test")
    public void animalTypeTests() {
        assertEquals(AnimalType.DOMESTIC, actualDog.getAnimalType());
    }

    @Test
    @Order(1)
    @DisplayName("Dog Speak Tests - Domestic")
    public void dogGoesWoofTest() {
        assertEquals("The dog goes woof! woof!", actualDog.speak());
    }

    @Test
    @Order(1)
    @DisplayName("Dog Hypoallergenic Test")
    public void dogHypoallergenicTests() {
        assertTrue(actualDog.dogHypoallergenic().contains("dog"));
    }

    @Test
    @Order(1)
    @DisplayName("Dog Number of Legs Test")
    public void legTests() {
        assertNotNull(actualDog.getNumberOfLegs());
    }

    @Test
    @Order(2)
    @DisplayName("Dog Gender Test")
    public void genderTestMale() {
        actualDog = new Dog(AnimalType.WILD, Skin.UNKNOWN, Gender.MALE, Breed.UNKNOWN);
        assertEquals(Gender.MALE, actualDog.getGender());
    }

    @Test
    @Order(2)
    @DisplayName("Dog Breed Test")
    public void dogBreedTest() {
        actualDog = new Dog(AnimalType.WILD, Skin.UNKNOWN, Gender.FEMALE, Breed.MALTESE);
        assertEquals(Breed.MALTESE, actualDog.getBreed());
    }

    @Test
    @Order(2)
    @DisplayName("Dog Speak Tests - Wild")
    public void dogGoesGrrTest() {
        actualDog = new Dog(AnimalType.WILD, Skin.UNKNOWN, Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The dog goes grr! grr!", actualDog.speak());
    }

    @Test
    @Order(2)
    @DisplayName("Dog Speak Tests - Default Branch")
    public void dogDefaultSpeakTest() {
        actualDog = new Dog(AnimalType.UNKNOWN, Skin.UNKNOWN, Gender.UNKNOWN, Breed.UNKNOWN);
        String speak = actualDog.speak();
        assertTrue(speak.contains("The dog goes "));
    }
}
