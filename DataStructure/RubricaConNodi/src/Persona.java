public class Persona {
    private String name = "";
    private String surname = "";
    private String email = "";
    private String phone = "";

    public Persona(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public String getFullName() {
        return (surname + " " + name);
    }

    @Override
    public String toString() {
        return "\"" + surname + " " + name + '\"';
    }

    public boolean isLowerThan(Persona p) {
        return !(this.getFullName().compareTo(p.getFullName()) > 0);
    }

    public String toCSV() {
        return name + ", " +  surname + ", " + email + ", " + phone + "\n";
    }
}
