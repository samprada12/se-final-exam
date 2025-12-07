package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;

public class Snake extends Pet implements PetImpl {

    private Breed breed;
    private int lengthInFeet;

    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed) {
        this(animalType, skinType, gender, breed, new BigDecimal("0.00"), 0);
    }

    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed,
                 BigDecimal cost, int petStoreId) {
        super(PetType.SNAKE, cost, gender, petStoreId);
        super.skinType = skinType;
        super.animalType = animalType;
        this.breed = breed;
        this.lengthInFeet = 3; // Default size
    }

    @Override
    public Breed getBreed() {
        return this.breed;
    }

    public int getLengthInFeet() {
        return lengthInFeet;
    }

    public void setLengthInFeet(int lengthInFeet) {
        this.lengthInFeet = lengthInFeet;
    }

    public String snakeHypoallergenic() {
        return super.petHypoallergenic(this.skinType).replaceAll("pet", "snake");
    }

    public String speak() {
        switch (animalType) {
            case DOMESTIC:
                return "The snake goes hissssss!";
            case WILD:
                return "The snake goes SSSSS-GRRR!";
            default:
                return "The snake goes " + super.getPetType().speak + "! " +
                        super.getPetType().speak + "!";
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "The snake is " + this.animalType + "!\n" +
                "The snake breed is " + this.breed + "!\n" +
                this.snakeHypoallergenic() + "!\n" +
                this.speak() + "\n" +
                "Snake length: " + this.lengthInFeet + " feet.\n";
    }
}
