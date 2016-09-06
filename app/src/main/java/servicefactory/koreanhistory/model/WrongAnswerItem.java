package servicefactory.koreanhistory.model;

/**
 * Created by JihwanHome on 2016-08-24.
 */
public class WrongAnswerItem {
    private String datetime;
    private String categoryList;
    private String totalQuizCount;
    private String wrongQuizCount;

    public WrongAnswerItem() {
    }

    public WrongAnswerItem(String datetime, String categoryList, String totalQuizCount, String wrongQuizCount) {
        this.datetime = datetime;
        this.categoryList = categoryList;
        this.totalQuizCount = totalQuizCount;
        this.wrongQuizCount = wrongQuizCount;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(String categoryList) {
        this.categoryList = categoryList;
    }

    public String getTotalQuizCount() {
        return totalQuizCount;
    }

    public void setTotalQuizCount(String totalQuizCount) {
        this.totalQuizCount = totalQuizCount;
    }

    public String getWrongQuizCount() {
        return wrongQuizCount;
    }

    public void setWrongQuizCount(String wrongQuizCount) {
        this.wrongQuizCount = wrongQuizCount;
    }

    @Override
    public String toString() {
        return "WrongAnswerItem{" +
                "datetime='" + datetime + '\'' +
                ", categoryList='" + categoryList + '\'' +
                ", totalQuizCount=" + totalQuizCount +
                ", wrongQuizCount=" + wrongQuizCount +
                '}';
    }
}