package efs.task.collections.entity;

import java.util.List;

public class Town implements Comparable {
    private String townName;
    private List<String> startingHeroClasses;

    public Town(String townName, List<String> startingHeroesClass) {
        this.townName = townName;
        startingHeroClasses = startingHeroesClass;
    }

    public String getTownName() {
        return townName;
    }

    public List<String> getStartingHeroClasses() {
        return startingHeroClasses;
    }

    //TODO implementacja metody equal porównująca obiekty Town na podstawie tylko townName.
    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(this == null){
            if(o == null) {
                return true;
            }
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        Town other = (Town) o;
        return other.getTownName().equals(this.getTownName());
    }

    //TODO implementacja metody equal biorąca pod uwagę tylko townName.
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Miasto :" + townName;
    }

    @Override
    public int compareTo(Object o) {
        Town other = (Town) o;
        return this.getTownName().compareTo(other.getTownName());
    }
}