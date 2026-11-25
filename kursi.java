package BioskopJava;

public class Kursi {
    private String nomorKursi;
    private String baris;
    private boolean tersedia;

    public Kursi(String nomorKursi, String baris, boolean tersedia) {
        this.nomorKursi = nomorKursi;
        this.baris = baris;
        this.tersedia = tersedia;
    }

    public String getNomorKursi() {
        return nomorKursi;
    }

    public String getBaris() {
        return baris;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public boolean pilihKursi() {
        if (tersedia) {
            tersedia = false;
            System.out.println("Kursi " + nomorKursi + " (Baris " + baris + ") berhasil dipilih.");
            return true;
        }
        System.out.println("Kursi " + nomorKursi + " (Baris " + baris + ") sudah terisi.");
        return false;
    }
}
