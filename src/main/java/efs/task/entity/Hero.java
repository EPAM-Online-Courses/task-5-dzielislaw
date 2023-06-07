package efs.task.collections.entity;

public class Hero {
    private String name;
    private String heroClass;

    public Hero(String name, String heroClass) {
        this.name = name;
        this.heroClass = heroClass;
    }

    public String getName() {
        return name;
    }

    public String getHeroClass() {
        return heroClass;
    }

    //TODO implementacja metody equal porównująca obiekty Hero na podstawie pól name i heroClass.
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
        Hero other = (Hero) o;
        return other.getName().equals(this.getName()) && other.getHeroClass().equals(this.getHeroClass());
    }

    //TODO implementacja metody equal biorąca pod uwagę name i heroClass.
    @Override
    public int hashCode() {return 1;}

    @Override
    public String toString() {
        return "My name is " + name + "and I am " + heroClass;
    }
}
