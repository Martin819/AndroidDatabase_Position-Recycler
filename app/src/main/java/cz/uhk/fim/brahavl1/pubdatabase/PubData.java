package cz.uhk.fim.brahavl1.pubdatabase;

import java.util.ArrayList;

/**
 * Created by brahavl1 on 16.04.2018.
 */

public class PubData extends ArrayList<Pub> {
    public static PubData newTestInstance(){

        //veřejna metoda vracejici testovaci data
        PubData testPersonData = new PubData();
        testPersonData.add(new Pub("karel", 18, 12));
        testPersonData.add(new Pub("michal", 88, 12));
        testPersonData.add(new Pub("misa", 18, 12));
        return testPersonData;
    }

}
