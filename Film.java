package BioskopJava;

public class Film {
    private String judul;
    private String genre;
    private int durasi;
    private String rating;

    public Film(String judul, String genre, int durasi, String rating) {
        this.judul = (judul != null && !judul.isBlank()) ? judul : "Tanpa Judul";
        this.genre = (genre != null && !genre.isBlank()) ? genre : "Umum";
        this.durasi = (durasi > 0) ? durasi : 0;
        this.rating = (rating != null && !rating.isBlank()) ? rating : "SU";
    }

    public String getJudul() {
        return judul;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public String getRating() {
        return rating;
    }

    public void cetakInfo() {
        System.out.println(judul + " | " + genre + " | " + durasi + " menit | Rating: " + rating);
    }
}
