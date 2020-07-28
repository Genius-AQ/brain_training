package info.androidhive.firebase;

/**
 * Created by GeniusAQ on 23/01/2017.
 */

public class Puzzle {
    int id;
    String content;
    String result;

    public Puzzle(String content, String result) {
        super();
        this.content = content;
        this.result = result;
    }

    public Puzzle(int id, String content, String result) {
        super();
        this.id = id;
        this.content = content;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
