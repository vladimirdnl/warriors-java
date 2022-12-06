package org.example.characters;

public class Warrior {
    private int health = 50;
    private final int initialHealth;
    static final int ATTACK = 5;
    private Warrior nextbehind;
    public Warrior(){
        this(50);
    }
    protected Warrior(int inithealth){
        setHealth(inithealth);
        initialHealth = inithealth;
    }
    protected void setHealth(int value){
        health = value;
    }
    protected void healBy(int value){
        setHealth(getHealth()+value);
    }
    public int getHealth(){
        return health;
    }
    private int getInitialHealth(){
        return initialHealth;
    }
    public int getAttack(){
        return ATTACK;
    }
    public int receiveDamage(int damage){
        int h1 = getHealth();
        int h2 = getHealth() - Math.max(damage, 0);
        setHealth(h2);
        return h1 - h2;
    }
    public int attack(Warrior war){
        return war.receiveDamage(getAttack());
//        int damageDealt = war.receiveDamage(getAttack());
//        processCommand(OurChampHasHit.INSTANCE, null);
//        return damageDealt;
    }
    public boolean isAlive(){
        return getHealth() > 0;
    }

    protected void processCommand(Command command, Warrior sender){
        var next = getWarriorBehind();
        if (next != null){
            next.processCommand(command, this);
        }
    }

    protected Warrior getWarriorBehind(){
        return nextbehind;
    }

    public void setWarriorBehind(Warrior warrior){
        nextbehind = warrior;
    }
}
