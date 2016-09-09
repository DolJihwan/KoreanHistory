package servicefactory.koreanhistory.contorller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;

import servicefactory.koreanhistory.handler.StudyHandler;
import servicefactory.koreanhistory.model.Category;
import servicefactory.koreanhistory.model.Study;

/**
 * Created by leejonghyeog on 2016. 8. 17..
 */
public class StudyController {
    private StudyHandler studyHandler = null;
    private Context context = null;

    public StudyController(Context context) {
        this.context = context;
        studyHandler = StudyHandler.getInstance(this.context);
        studyHandler.open();
    }

    public ArrayList<Category> getMajorCategory() {

        Log.i("HelloWorld", "Test hello - controller");
        ArrayList<Category> categoryMajorList = new ArrayList<Category>();
        Category majorInstance = null;

        Cursor majorCategoryCursor = studyHandler.getMajorCategory();
        while (majorCategoryCursor.moveToNext()) {

            String category_major = majorCategoryCursor.getString(0);

            majorInstance = new Category(category_major);
            categoryMajorList.add(majorInstance);
        }
        return categoryMajorList;
    }

    public ArrayList<Category> getMinorCategory(String majorCategory) {
        ArrayList<Category> categoryMinorList = new ArrayList<Category>();
        Category minorInstance = null;

        Cursor minorCategoryCursor = studyHandler.getMinorCategory(majorCategory);
        while (minorCategoryCursor.moveToNext()) {
            String category_minor = minorCategoryCursor.getString(0);
            minorInstance = new Category(majorCategory, category_minor);
            categoryMinorList.add(minorInstance);
        }
        return categoryMinorList;
    }
    public Category getSelectedMajorCategory(String minorCategory){
        Category majorCategory = null;
        Cursor selectedMajorCategoryCursor = studyHandler.getSelectedMajorCategory(minorCategory);
        while (selectedMajorCategoryCursor.moveToNext()){
            String category_major = selectedMajorCategoryCursor.getString(0);
            majorCategory = new Category(category_major);
        }
        return majorCategory;
    }

    public ArrayList<ArrayList<Category>> getTotalCategoryList(){
        Log.i("start test","method");
        ArrayList<Category> tmpMajorCategoryList = getMajorCategory();
        ArrayList<Category> eachCategoryList=null;
        ArrayList<ArrayList<Category>> totalCagegoryList = new ArrayList<ArrayList<Category>>();
        Category tmpCategory = null;
        Cursor tmpCursor = null;
        for(int i=0; i<tmpMajorCategoryList.size(); i++){
            tmpCursor = studyHandler.getMinorCategory(tmpMajorCategoryList.get(i).getCategoryMajor());
            eachCategoryList = new ArrayList<Category>();
            while(tmpCursor.moveToNext()){
                String category_minor = tmpCursor.getString(0);
                Log.i("major : ",tmpMajorCategoryList.get(i).getCategoryMajor());
                Log.i("minor : ", category_minor);
                tmpCategory = new Category(tmpMajorCategoryList.get(i).getCategoryMajor(),category_minor);

                eachCategoryList.add(tmpCategory);
            }
            totalCagegoryList.add(eachCategoryList);
        }
        return totalCagegoryList;
    }
    public ArrayList<String> getSelectedCategoryTheme(String minorCategory){
        ArrayList<String> categoryThemeList = new ArrayList<String>();
        Cursor themeList = studyHandler.getSelectedCategoryTheme(minorCategory);
        while (themeList.moveToNext()) {
            String theme = themeList.getString(0);
            categoryThemeList.add(theme);
        }
        return categoryThemeList;
    }
    public ArrayList<String> getSelectedCategoryTitle(String categoryTheme){
        ArrayList<String> categoryTitleList = new ArrayList<String>();
        Cursor titleList = studyHandler.getSelectedCategoryTitle(categoryTheme);
        while (titleList.moveToNext()){
            String title = titleList.getString(0);
            categoryTitleList.add(title);
        }
        return categoryTitleList;
    }
    public ArrayList<String> getSelectedCategoryWord(String categoryTitle){
        ArrayList<String> categoryWordList = new ArrayList<String>();
        Cursor wordList = studyHandler.getSelectedCategoryWord(categoryTitle);
        while (wordList.moveToNext()){
            String word = wordList.getString(0);
            categoryWordList.add(word);
        }
        return categoryWordList;
    }
    public ArrayList<Study> getSelectedCategoryContents(String word){
        Study study;
        ArrayList<Study> studyList = new ArrayList<Study>();
        Cursor contentsList = studyHandler.getSelectedCategoryContents(word);
        while (contentsList.moveToNext()){
            String categoryQuiz=contentsList.getString(0);
            String categoryContents = contentsList.getString(1);
            study = new Study(categoryQuiz,categoryContents);
            studyList.add(study);
        }
        return studyList;
    }
}
