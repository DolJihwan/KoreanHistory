package servicefactory.koreanhistory.model;

/**
 * Created by JihwanHome on 2016-08-18.
 */
public class WrongAnswerList {
    private String waDatetime ;
    private String waCategory ;
    private String waTotalCount;
    private String waCount;

    public String getWaDatetime() {
        return waDatetime;
    }

    public void setWaDatetime(String waDatetime) {
        this.waDatetime = waDatetime;
    }

    public String getWaCategory() {
        return waCategory;
    }

    public void setWaCategory(String waCategory) {
        this.waCategory = waCategory;
    }

    public String getWaTotalCount() {
        return waTotalCount;
    }

    public void setWaTotalCount(String waTotalCount) {
        this.waTotalCount = waTotalCount;
    }

    public String getWaCount() {
        return waCount;
    }

    public void setWaCount(String waCount) {
        this.waCount = waCount;
    }
}
