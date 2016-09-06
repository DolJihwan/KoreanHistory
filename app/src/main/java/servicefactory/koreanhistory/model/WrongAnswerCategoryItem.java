package servicefactory.koreanhistory.model;

/**
 * Created by JihwanHome on 2016-09-06.
 */
public class WrongAnswerCategoryItem {
    private String categoryItem;
    private String totalQuizCount;

    public String getCategoryItem() {
        return categoryItem;
    }

    public void setCategoryItem(String categoryItem) {
        this.categoryItem = categoryItem;
    }

    public String getTotalQuizCount() {
        return totalQuizCount;
    }

    public void setTotalQuizCount(String totalQuizCount) {
        this.totalQuizCount = totalQuizCount;
    }

    @Override
    public String toString() {
        return "WrongAnswerCategoryItem{" +
                "" + categoryItem + '\'' +
                ", " + totalQuizCount + '\'' +
                '}';
    }
}
