package efs.task.collections.data;

import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.*;

public class DataProvider {

    public static final String DATA_SEPARATOR = ",";

    // TODO Utwórz listę miast na podstawie tablicy Data.baseTownsArray.
    //  Tablica zawiera String zawierający nazwę miasta oraz dwie klasy bohatera związane z tym miastem oddzielone przecinkami.
    //  Korzystając z funkcji split() oraz stałej DATA_SEPARATOR utwórz listę obiektów klasy efs.task.collections.entities.Town.
    //  Funkcja zwraca listę obiektów typu Town ze wszystkimi dziewięcioma podstawowymi miastami.
    public List<Town> getTownsList() {
        List<Town> townList = new LinkedList<Town>();
        for (String i : Data.baseTownsArray){
            String[] splitedLine = i.split(DATA_SEPARATOR);
            townList.add(new Town(splitedLine[0], Arrays.asList(splitedLine[1].trim(), splitedLine[2].trim())));
        }
        return townList;
    }

    //TODO Analogicznie do getTownsList utwórz listę miast na podstawie tablicy Data.DLCTownsArray
    public List<Town> getDLCTownsList() {
        List<Town> townList = new LinkedList<Town>();
        String townName;
        List<String> startingHeroesClass = new LinkedList<>();
        for (String i : Data.dlcTownsArray){
            townName = i.split(DATA_SEPARATOR)[0];
            startingHeroesClass.add(0, i.split(DATA_SEPARATOR)[1]);
            startingHeroesClass.add(1, i.split(DATA_SEPARATOR)[2]);
            townList.add(new Town(townName, startingHeroesClass));
        }
        return townList;
    }

    //TODO Na podstawie tablicy Data.baseCharactersArray utworzyć listę bohaterów dostępnych w grze.
    // Tablica Data.baseCharactersArray zawiera oddzielone przecinkiem imie bohatera, klasę bohatera.
    // Korzystając z funkcji split() oraz DATA_SEPARATOR utwórz listę unikalnych obiektów efs.task.collections.entities.Hero.
    // UWAGA w Data.baseCharactersArray niektórzy bohaterowie powtarzają się, do porównania bohaterów używamy zarówno imie jak i jego klasę;
    public Set<Hero> getHeroesSet() {
        Set<String> uniqueBaseCharactersSet = new HashSet<>();
        uniqueBaseCharactersSet.addAll(Arrays.stream(Data.baseCharactersArray).toList());
        Set<Hero> heroesSet = new HashSet<>();
        String heroName = null;
        String heroClass = null;
        for (String i : uniqueBaseCharactersSet){
            heroName = i.split(DATA_SEPARATOR)[0].trim();
            heroClass = i.split(DATA_SEPARATOR)[1].trim();
            heroesSet.add(new Hero(heroName, heroClass));
        }
        return heroesSet;
    }

    //TODO Analogicznie do getHeroesSet utwórz listę bohaterów na podstawie tablicy Data.DLCCharactersArray
    public Set<Hero> getDLCHeroesSet() {
        Set<String> uniqueDlcCharactersSet = new HashSet<>();
        uniqueDlcCharactersSet.addAll(Arrays.stream(Data.dlcCharactersArray).toList());
        Set<Hero> heroesSet = new HashSet<>();
        String heroName;
        String heroClass;
        for (String i : uniqueDlcCharactersSet){
            heroName = i.split(DATA_SEPARATOR)[0];
            heroClass = i.split(DATA_SEPARATOR)[1];
            heroesSet.add(new Hero(heroName, heroClass));
        }
        return heroesSet;
    }
}
