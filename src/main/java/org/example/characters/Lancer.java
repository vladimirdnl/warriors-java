package org.example.characters;

public class Lancer extends Warrior{
    static final int PIERCING_POWER = 50;
    @Override
    public int attack(Warrior war) {
        int damageDealt = super.attack(war);
        int dmg_return;
        if (war instanceof HasWarriorBehind unitInArmy) {
            int damageForTheNext = damageDealt * PIERCING_POWER / 100;
            Warrior next = unitInArmy.getWarriorBehind();
            dmg_return = next.receiveDamage(damageForTheNext) + damageDealt;
        }
        else{
            dmg_return = damageDealt;
        }
        return dmg_return;
    }
}
