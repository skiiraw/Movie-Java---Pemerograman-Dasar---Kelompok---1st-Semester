
public class Film {
    private String judul;
    private String genre;
    private int durasi;      
    

    public Film(String judul, String genre, int durasi, String rating) {
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
    }

    public String getJudul() {
        return judul;
    }

    public void cetakInfo() {
        System.out.println(judul + " | " + genre + " | " + durasi + " menit" );
    }
}
