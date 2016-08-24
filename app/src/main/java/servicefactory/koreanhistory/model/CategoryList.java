package servicefactory.koreanhistory.model;

/**
 * Created by leejonghyeog on 2016. 8. 3..
 */

import java.util.ArrayList;

public class CategoryList {

    public String groupName;
    public ArrayList<String> childNameList;
    public ArrayList<String> selection;

    public CategoryList() {
        childNameList = new ArrayList<String>();
        selection = new ArrayList<String>();
    }

    public CategoryList(String groupName) {
        this();
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "CategoryList{" +
                groupName + '\'' +
                ", " + childNameList +
                ", " + selection +
                '}';
    }
}

