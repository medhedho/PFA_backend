package insat.pfa.expat.Model;

public enum TypePublication {
    TEXT("text"),COMPLAINT("complaint"),SUGGESTION("event");
    private String type;

    TypePublication(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
