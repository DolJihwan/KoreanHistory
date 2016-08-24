package servicefactory.koreanhistory.contorller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import servicefactory.koreanhistory.handler.CategoryHandler;
import servicefactory.koreanhistory.model.Category;
import servicefactory.koreanhistory.model.CategoryList;

/**
 * Created by JihwanHome on 2016-08-11.
 */
public class CategoryController {
    private Context context;
    private CategoryHandler categoryHandler;

    public CategoryController(Context context) {
        this.context = context;

        // db connect(categoryHandler).
        categoryHandler = CategoryHandler.getInstance(this.context);
        categoryHandler.open();
    }



    public ArrayList<Category> getTotalCategory() {
        ArrayList<Category> totalCategory = new ArrayList<>();
        Category category = null;

        Cursor cursor = categoryHandler.getTotalCategory();

        while(cursor.moveToNext()){
            String category_major = cursor.getString(0);
            String category_minor = cursor.getString(1);

            category = new Category(category_major, category_minor);
            totalCategory.add(category);
        }

        Log.i("CategoryController", "getTotalCategory: " + totalCategory.toString());

        return totalCategory;
    }

    public ArrayList<CategoryList> getTotalCategoryList() {
        ArrayList<CategoryList> categoryArrList = new ArrayList<>();
        CategoryList categoryList = null;

        Cursor cursor = categoryHandler.getTotalCategoryList();

        while(cursor.moveToNext()){
            String groupNm = cursor.getString(0);
            String childNm = cursor.getString(1);

            if(categoryList == null){
                categoryList = new CategoryList(groupNm);
            }

            if(!categoryList.groupName.equals(groupNm)){
                categoryArrList.add(categoryList);
                categoryList = new CategoryList(groupNm);
                categoryList.childNameList.add(childNm);
            }else{
                categoryList.childNameList.add(childNm);
            }
        }
        categoryArrList.add(categoryList);

        Log.i("CategoryController", "getTotalCategoryList: " + categoryArrList.toString());

        return categoryArrList;
    }
}
