package insat.pfa.expat.Model;

public enum TypeUser {
    EXPAT("expat"),NON_EXPAT("nonExpat"),ADMIN("admin");
    private String type;

    TypeUser(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
