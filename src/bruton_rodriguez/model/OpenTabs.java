package bruton_rodriguez.model;

import java.util.ArrayList;

/**
 * Created by dylonrodriguez on 2/22/17.
 */
public class OpenTabs extends ArrayList<Tab> {
    public void saveTabs() {
        for (Tab tab : this) {
            System.out.println(tab.getTitle());
        }
    }

}
