package efs.task.collections.game;

import efs.task.collections.data.DataProvider;
import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.*;

public class GameLobby{

    public static final String HERO_NOT_FOUND = "Nie ma takiego bohatera ";
    public static final String NO_SUCH_TOWN = "Nie ma takiego miasta ";

    private final DataProvider dataProvider;
    private Map<Town, List<Hero>> playableTownsWithHeroesList;

    public GameLobby() {
        this.dataProvider = new DataProvider();
        this.playableTownsWithHeroesList =
                mapHeroesToStartingTowns(dataProvider.getTownsList(), dataProvider.getHeroesSet());
    }

    public Map<Town, List<Hero>> getPlayableTownsWithHeroesList() {
        return playableTownsWithHeroesList;
    }

    //TODO Dodać miasta i odpowiadających im bohaterów z DLC gry do mapy dostępnych
    // miast - playableTownsWithHeroesList, tylko jeżeli jeszcze się na niej nie znajdują.
    public void enableDLC() {
        for (Town town : dataProvider.getDLCTownsList()) {
            List<Hero> heroesInTown = new LinkedList<>();
            List<String> heroesClassesInTown = new LinkedList<>(town.getStartingHeroClasses());
            for (Hero hero : dataProvider.getDLCHeroesSet()) {
                if(heroesClassesInTown.contains(hero.getHeroClass())){
                    heroesInTown.add(hero);
                }
            }
            this.playableTownsWithHeroesList.put(town, heroesInTown);
        }
    }

    //TODO Usunąć miasta i odpowiadających im bohaterów z DLC gry z mapy dostępnych
    // miast - playableTownsWithHeroesList.
    public void disableDLC() {
        for(Town dlcTown : dataProvider.getDLCTownsList()){
            playableTownsWithHeroesList.remove(dlcTown);
        }
        for (Hero dlcHero : dataProvider.getDLCHeroesSet()){
            if(playableTownsWithHeroesList.containsValue(dlcHero)){
               for (Town town : playableTownsWithHeroesList.keySet()){
                   playableTownsWithHeroesList.remove(town, dlcHero);
               }
            }
        }
    }

    // TODO Sprawdza czy mapa playableCharactersByTown zawiera dane miasto.
    //  Jeśli tak zwróć listę bohaterów z tego miasta.
    //  Jeśli nie rzuć wyjątek NoSuchElementException z wiadomością NO_SUCH_TOWN + town.getName()
    public List<Hero> getHeroesFromTown(Town town) {
        if(playableTownsWithHeroesList.keySet().contains(town)){
            return playableTownsWithHeroesList.get(town);
        }
        throw new NoSuchElementException(NO_SUCH_TOWN + town.getTownName());
    }

    // TODO Metoda powinna zwracać mapę miast w kolejności alfabetycznej z odpowiadającymi im bohaterami.
    //  Każde z miast charakteryzuje się dwoma klasami bohaterów dostępnymi dla tego miasta - Town.startingHeroClass.
    //  Mapa ma zawierać pare klucz-wartość gdzie klucz: miasto, wartość: lista bohaterów;
    public Map<Town, List<Hero>> mapHeroesToStartingTowns(List<Town> availableTowns, Set<Hero> availableHeroes) {
        Map<Town, List<Hero>> heroesToStartingTowns = new TreeMap<>();
        for(Town town : availableTowns){
            List<Hero> heroesInTown = new LinkedList<>();
            List<String> heroesClassesInTown = town.getStartingHeroClasses();
            for(Hero hero : availableHeroes){
                for (String heroClass:heroesClassesInTown){
                    if(heroClass.equals(hero.getHeroClass())){
                        heroesInTown.add(hero);
                    }
                }
            }
            heroesToStartingTowns.put(town, heroesInTown);
        }
        return heroesToStartingTowns;
    }

    //TODO metoda zwraca wybranego bohatera na podstawie miasta z którego pochodzi i imienia.
    // Jeżeli istnieje usuwa go z listy dostępnych bohaterów w danym mieście i zwraca bohatera.
    // Jeżeli nie ma go na liście dostępnych bohaterów rzuca NoSuchElementException z wiadomością HERO_NOT_FOUND + name
    public Hero selectHeroByName(Town heroTown, String name) {
        for(Hero hero:playableTownsWithHeroesList.get(heroTown)){
            if (hero.getName().equals(name)){
                playableTownsWithHeroesList.get(heroTown).remove(hero);
                return hero;
            }
        }
        throw new NoSuchElementException(HERO_NOT_FOUND + name);
    }
}