package io.quarkus.workshop.superheroes.load;

public class BookStoreLoad {

    public static void main(String[] args) {
        Thread inventoryScenario = new Thread(new ScenarioInventory());
        inventoryScenario.start();
        Thread bookScenario = new Thread(new ScenarioBook());
        bookScenario.start();
        Thread numberScenario = new Thread(new ScenarioNumber());
        numberScenario.start();
    }
}
