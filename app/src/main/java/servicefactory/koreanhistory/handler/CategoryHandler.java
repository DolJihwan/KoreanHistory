package servicefactory.koreanhistory.handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import servicefactory.koreanhistory.persistence.DatabaseAssetHelper;


/**
 * Created by JihwanHome on 2016-08-06.
 */
public class CategoryHandler {
    private DatabaseAssetHelper openHelper;
    private SQLiteDatabase database;
    private static CategoryHandler categoryInstance;

    // database init.
    private CategoryHandler(Context context) {
        this.openHelper = new DatabaseAssetHelper(context);
        database = openHelper.getWritableDatabase();
    }

    // database singleton instance
    public static CategoryHandler getInstance(Context context) {
        if (categoryInstance == null) {
            categoryInstance = new CategoryHandler(context);
        }
        return categoryInstance;
    }

    // database open.
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    // database close.
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public Cursor getTotalCategory(){
        String query = "select category_major, category_minor from quiz group by category_minor order by _id";

        return database.rawQuery(query, null);
    }

    public Cursor getTotalCategoryList(){
        String query = "select category_major, category_minor from quiz group by category_minor order by _id";

        return database.rawQuery(query, null);
    }

}
