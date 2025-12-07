package tests;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Cat;
import animals.petstore.pet.types.Dog;
import animals.petstore.pet.types.Snake;
import animals.petstore.store.DuplicatePetStoreRecordException;
import animals.petstore.store.PetNotFoundSaleException;
import animals.petstore.store.PetStore;
import number.Numbers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class PetStoreTest {

    private static PetStore petStore;

    @BeforeEach
    public void loadThePetStoreInventory() {
        petStore = new PetStore();
        petStore.init();
    }

    // ---------------------------------------------------------
    // ORIGINAL PROVIDED TESTS
    // ---------------------------------------------------------

    @Test
    @DisplayName("Inventory Count Test")
    public void validateInventory() {
        assertEquals(5, petStore.getPetsForSale().size(), "Inventory counts are off!");
    }

    @Test
    @DisplayName("Print Inventory Test (No Assertion)")
    public void printInventoryTest() {
        petStore.printInventory();
    }

    @Test
    @DisplayName("Sale of Poodle Remove Item Test")
    public void poodleSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;
        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        petStore.soldPetItem(poodle);
        assertEquals(inventorySize, petStore.getPetsForSale().size(),
                "Expected inventory does not match actual");
    }

    @Test
    @DisplayName("Poodle Duplicate Record Exception Test")
    public void poodleDupRecordExceptionTest() {
        petStore.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1));

        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        String expectedMessage = "Duplicate Dog record store id [1]";
        Exception exception = assertThrows(DuplicatePetStoreRecordException.class, () -> {
            petStore.soldPetItem(poodle);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Sale of Sphynx Remove Item Test")
    public void sphynxSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"), 2);
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        assertEquals(inventorySize, petStore.getPetsForSale().size());
        assertEquals(sphynx.getPetStoreId(), removedItem.getPetStoreId());
    }

    @TestFactory
    @DisplayName("Sale of Sphynx Remove Item Test2")
    public Stream<DynamicNode> sphynxSoldTest2() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"), 2);
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        List<DynamicNode> tests = Arrays.asList(
                dynamicTest("Inventory Size Check", () ->
                        assertEquals(inventorySize, petStore.getPetsForSale().size())),
                dynamicTest("Cat IDs Match", () ->
                        assertEquals(sphynx.getPetStoreId(), removedItem.getPetStoreId()))
        );

        return Stream.of(dynamicContainer("Cat Item 2 Test", tests));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, -10, 128, Integer.MIN_VALUE})
    void isNumberEven(int number) {
        assertTrue(Numbers.isEven(number));
    }

    // ---------------------------------------------------------
    // NEW TESTS ADDED FOR COVERAGE
    // ---------------------------------------------------------

    @Test
    @DisplayName("Add New Pet Inventory Item Test")
    public void addNewPetInventoryItemTest() {
        int sizeBefore = petStore.getPetsForSale().size();

        Dog newDog = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.FEMALE, Breed.GERMAN_SHEPARD,
                new BigDecimal("500.00"), 99);

        petStore.addPetInventoryItem(newDog);

        assertEquals(sizeBefore + 1, petStore.getPetsForSale().size());
        assertTrue(petStore.getPetsForSale().contains(newDog));
    }

    @Test
    @DisplayName("Pet Not Found When Store ID = 0")
    public void sellPetWithZeroIdTest() {
        Dog dog = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("400.00"), 0);

        Exception exception = assertThrows(PetNotFoundSaleException.class, () -> {
            petStore.soldPetItem(dog);
        });

        assertTrue(exception.getMessage().contains("not part of the pet store"));
    }

    @Test
    @DisplayName("Duplicate Cat Record Exception Test")
    public void duplicateCatRecordTest() {
        petStore.addPetInventoryItem(
                new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                        new BigDecimal("100.00"), 2)
        );

        Cat target = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"), 2);

        Exception exception = assertThrows(DuplicatePetStoreRecordException.class, () -> {
            petStore.soldPetItem(target);
        });

        assertTrue(exception.getMessage().contains("Duplicate Cat record store id [2]"));
    }

    @Test
    @DisplayName("initAddDuplicateItem Adds Pet on Top of init() items")
    public void initAddDuplicateItemTest() {
        PetStore ps = new PetStore();
        Dog extraDog = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("100.00"), 99);

        ps.initAddDuplicateItem(extraDog);

        assertEquals(6, ps.getPetsForSale().size());
    }

    @Test
    @DisplayName("Snake Sale Test")
    public void snakeSaleTest() throws Exception {
        Snake snake = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.MALE, Breed.UNKNOWN,
                new BigDecimal("50.00"), 10);

        petStore.addPetInventoryItem(snake);

        Snake sold = (Snake) petStore.soldPetItem(snake);

        assertEquals(10, sold.getPetStoreId());
    }

    @Test
    @DisplayName("Selling Dog Does Not Remove Cats")
    public void sellingDogDoesNotRemoveCatsTest() throws Exception {

        long initialCatCount = petStore.getPetsForSale().stream()
                .filter(p -> p instanceof Cat)
                .count();

        Dog dogToSell = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        petStore.soldPetItem(dogToSell);

        long afterCatCount = petStore.getPetsForSale().stream()
                .filter(p -> p instanceof Cat)
                .count();

        assertEquals(initialCatCount, afterCatCount,
                "Selling a dog should NOT remove cats.");
    }

    @Test
    @DisplayName("Print Inventory Output Test (Captured Output)")
    public void printInventoryOutputTest() {
        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        try {
            petStore.printInventory();
        } finally {
            System.setOut(originalOut);
        }

        String output = out.toString();
        assertFalse(output.isEmpty());
        assertTrue(output.contains("Dog") || output.contains("Cat"));
    }
}
