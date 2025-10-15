public class Brano {
    private String titolo;
    private String artista;

    public Brano() {
    }

    public Brano(String titolo, String artista) {
        this.titolo = titolo;
        this.artista = artista;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Brano{" +
                "titolo='" + titolo + '\'' +
                ", artista='" + artista + '\'' +
                '}';
    }
}
