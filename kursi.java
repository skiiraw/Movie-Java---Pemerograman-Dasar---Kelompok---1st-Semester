
public class kursi {
    private String nomorKursi;
    private String baris;
    private boolean statusKursi; 

    public kursi(String nomorKursi, String baris, boolean statusKursi) {
        this.nomorKursi = nomorKursi;
        this.baris = baris;
        this.statusKursi = statusKursi;
    }

    public String getNomorKursi() {
        return nomorKursi;
    }

    public void setNomorKursi(String nomorKursi) {
        this.nomorKursi = nomorKursi;
    }

    public String getBaris() {
        return baris;
    }

    public void setBaris(String baris) {
        this.baris = baris;
    }

    public boolean isStatusKursi() {
        return statusKursi;
    }

    public void setStatusKursi(boolean statusKursi) {
        this.statusKursi = statusKursi;
    }

    public boolean cekKetersediaan() {
        return statusKursi;
    }

    public void pilihKursi() {
        if (statusKursi) {
            statusKursi = false;
            System.out.println("Kursi " + nomorKursi + " (Baris " + baris + ") berhasil dipilih.");
        } else {
            System.out.println("Kursi " + nomorKursi + " (Baris " + baris + ") sudah terisi.");
        }
    }
}
