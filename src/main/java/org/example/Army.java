package org.example;

import org.example.characters.HasWarriorBehind;
import org.example.characters.Warrior;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.Supplier;

public class Army {
    private Queue<Warrior> troops = new LinkedList<Warrior>();

    public Iterator<Warrior> firstAlive() {
        return new FirstAliveIterator();
    }

//    static class UnitInArmy extends Warrior implements HasWarriorBehind{
//        Warrior warrior;
//        Warrior behind;
//
//        public UnitInArmy(Warrior warrior){
//            this.warrior = warrior;
//        }
//
//        public Warrior getWarriorBehind(){
//            return behind;
//        }
//    }
    private class FirstAliveIterator implements Iterator<Warrior>{

        @Override
        public boolean hasNext() {
            while (peekFirst()!=null && !peekFirst().isAlive()){
                removeFirst();
            }
            return peekFirst() != null;
        }

        @Override
        public Warrior next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            return peekFirst();
        }
    }

    public Army addUnit (Warrior warrior){
        troops.add(warrior);
        return this;
    }

    public Army addUnits (Supplier<Warrior> factory, int count){
        for (int i = 0; i < count; i++) {
            troops.add(factory.get());
        }
        return this;
    }

    private Warrior peekFirst(){
        return troops.peek();
    }

    private void removeFirst(){
        troops.poll();
    }
}
