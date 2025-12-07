package tests;

import animals.petstore.pet.types.PetImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetImplTest {

    @Test
    public void petImplInterfaceLoadsCorrectly() {
        // Ensures the interface exists and loads without errors
        assertDoesNotThrow(() -> Class.forName("animals.petstore.pet.types.PetImpl"));
    }

    @Test
    public void petImplHasBreedField() throws Exception {
        // Validate the interface contains the 'breed' field
        assertNotNull(PetImpl.class.getDeclaredField("breed"));
    }
}
