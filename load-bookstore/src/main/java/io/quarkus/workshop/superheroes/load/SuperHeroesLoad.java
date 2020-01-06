package io.quarkus.workshop.superheroes.load;

public class SuperHeroesLoad {

    public static void main(String[] args) {
        Thread heroScenario = new Thread(new ScenarioInventory());
        heroScenario.start();
        Thread villainScenario = new Thread(new ScenarioBook());
        villainScenario.start();
        Thread fightScenario = new Thread(new ScenarioNumber());
        fightScenario.start();
    }
}
