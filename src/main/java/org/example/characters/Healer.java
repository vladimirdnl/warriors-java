package org.example.characters;

public class Healer extends Warrior{
    private static final int HEAL_POWER = 2;
    @Override
    protected void processCommand(Command command, Warrior sender) {
        if (command instanceof OurChampHasHit) {
            heal(sender);
        }
        super.processCommand(command, sender);
    }

    @Override
    public int attack(Warrior war) {
        return 0;
    }

    public int getHealPower(){
        return HEAL_POWER;
    }

    private void heal(Warrior warrior){
        warrior.healBy(getHealPower());
    }
}
