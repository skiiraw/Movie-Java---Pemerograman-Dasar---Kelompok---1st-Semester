
public class tiket {
    private String idTiket;
    private String nomorKursi;
    private double harga;
    private String status;

    public tiket(String idTiket, String nomorKursi, double harga, String status) {
        this.idTiket = idTiket;
        this.nomorKursi = nomorKursi;
        this.harga = harga;
        this.status = status;
    }

    public String getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(String idTiket) {
        this.idTiket = idTiket;
    }

    public String getNomorKursi() {
        return nomorKursi;
    }

    public void setNomorKursi(String nomorKursi) {
        this.nomorKursi = nomorKursi;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void pesanTiket() {
        if (status.equals("Tersedia") || status.equalsIgnoreCase("Dibatalkan")) {
            status = "Dipesan";
            System.out.println("Tiket " + idTiket + " untuk kursi " + nomorKursi + " berhasil dipesan.");
        } else {
            System.out.println("Tiket " + idTiket + " tidak dapat dipesan (status saat ini: " + status + ").");
        }
    }

    public void batalkanTiket() {
        if (status.equals("Dipesan")) {
            status = "Dibatalkan";
            System.out.println("Tiket " + idTiket + " berhasil dibatalkan.");
        } else {
            System.out.println("Tiket " + idTiket + " tidak sedang dipesan (status saat ini: " + status + ").");
        }
    }

    public void cetakTiket() {
        System.out.println("=== DETAIL TIKET ===");
        System.out.println("ID Tiket    : " + idTiket);
        System.out.println("Nomor Kursi : " + nomorKursi);
        System.out.println("Harga       : Rp" + harga);
        System.out.println("Status      : " + status);
        System.out.println("====================");
    }
}
