package domain;

public class Document {
    private String name;
    private String keywords;
    private String content;

    public Document(String name, String keywords, String content) {
        this.name = name;
        this.keywords = keywords;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", keywords='" + keywords + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
