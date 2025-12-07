package tests;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Dog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Provide a hamcrest asserts example
 */
public class HamcrestExampleTest {

    private List<Pet> dListActual = Arrays.asList(
            new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.MALTESE,
                    new BigDecimal("750.00"), 1),
            new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                    new BigDecimal("750.00"), 2),
            new Dog(AnimalType.DOMESTIC, Skin.HAIR, Gender.FEMALE, Breed.GERMAN_SHEPARD,
                    new BigDecimal("750.00"), 2));

    private List<Pet> dListExpected = Arrays.asList(
            new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.MALTESE,
                    new BigDecimal("750.00"), 1),
            new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                    new BigDecimal("750.00"), 2),
            new Dog(AnimalType.DOMESTIC, Skin.HAIR, Gender.FEMALE, Breed.GERMAN_SHEPARD,
                    new BigDecimal("750.00"), 2)
    );

    @Test
    @DisplayName("ABC test")
    public void abcTest()
    {
        assertThat("abc", equalToIgnoringCase("abc"));
    }

    @Test
    @DisplayName("Empty String test")
    public void emptyStringTest()
    {
        assertThat("", is(""));
    }

    @Test
    @DisplayName("Collection Test not empty")
    public void dogCollectionNotEmptyTest()
    {
        assertThat(dListActual.isEmpty(), is(false));
    }

    @Test
    @DisplayName("Collection Test not null")
    public void dogCollectionNotEmptyNotNull()
    {
        assertThat(dListActual, is(notNullValue()));
    }

    @Test
    @DisplayName("Dog Collection Match Tests1")
    public void dogCollectionMatch()
    {
        // FIX: Compare string output because Dog does not override equals()
        assertThat("Lists should match exactly",
                dListActual.toString(), equalTo(dListExpected.toString()));
    }

    @Test
    @DisplayName("Dog Collection Match Tests2")
    public void dogCollectionSameListTest()
    {
        assertThat("Lists should match exactly", dListExpected, equalTo(dListExpected));
    }
}
