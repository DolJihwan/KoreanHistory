package servicefactory.koreanhistory.model;

import java.io.Serializable;

/**
 * Created by leejonghyeog on 2016. 8. 30..
 */
public class Study implements Serializable {
    public static final String TAG = "Study";

    private String categoryQuiz;
    private String contents;


    public Study(String categoryQuiz, String contents) {
        this.categoryQuiz = categoryQuiz;
        this.contents = contents;
    }

    public String getCategoryQuiz() {
        return categoryQuiz;
    }

    public void setCategoryQuiz(String categoryQuiz) {
        this.categoryQuiz = categoryQuiz;
    }
    public String getContents(){
        return contents;
    }
    public void setContents(String contents){
        this.contents=contents;
    }
}
