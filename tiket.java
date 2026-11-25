package BioskopJava;

public class Tiket {
    private String idTiket;
    private String status;

    private Film film;
    private Studio studio;
    private JadwalFilm jadwal;
    private Kursi kursi;
    private Pembayaran pembayaran;
    private StatusPembayaran statusPembayaran;

    public Tiket(String idTiket, Film film, Studio studio, JadwalFilm jadwal, Kursi kursi) {
        this.idTiket = idTiket;
        this.film = film;
        this.studio = studio;
        this.jadwal = jadwal;
        this.kursi = kursi;
        this.status = "Tersedia";
    }

    public String getIdTiket() {
        return idTiket;
    }

    public String getStatus() {
        return status;
    }

    public Film getFilm() {
        return film;
    }

    public Studio getStudio() {
        return studio;
    }

    public JadwalFilm getJadwal() {
        return jadwal;
    }

    public Kursi getKursi() {
        return kursi;
    }

    public void setDetailPembayaran(Pembayaran pembayaran, StatusPembayaran statusPembayaran) {
        this.pembayaran = pembayaran;
        this.statusPembayaran = statusPembayaran;
    }

    public boolean pesanTiket() {
        if (status.equalsIgnoreCase("Tersedia") || status.equalsIgnoreCase("Dibatalkan")) {
            status = "Dipesan";
            System.out.println("Tiket " + idTiket + " untuk kursi " + kursi.getNomorKursi() + " berhasil dipesan.");
            return true;
        }
        System.out.println("Tiket " + idTiket + " tidak dapat dipesan (status saat ini: " + status + ").");
        return false;
    }

    public boolean batalkanTiket() {
        if (status.equalsIgnoreCase("Dipesan")) {
            status = "Dibatalkan";
            kursi.setTersedia(true);
            System.out.println("Tiket " + idTiket + " berhasil dibatalkan.");
            return true;
        }
        System.out.println("Tiket " + idTiket + " tidak sedang dipesan (status saat ini: " + status + ").");
        return false;
    }

    public void cetakTiket() {
        System.out.println("\n========== STRUK PEMESANAN ==========");
        System.out.println("ID Tiket          : " + idTiket);
        System.out.println("Film              : " + film.getJudul() + " (" + film.getRating() + ")");
        System.out.println("Studio            : " + studio.getNamaStudio());
        System.out.println("Tanggal & Jam     : " + jadwal.getTanggal() + " / " + jadwal.getJamMulai() + " WIB");
        System.out.println("Nomor Kursi       : " + kursi.getNomorKursi());
        System.out.println("Status Tiket      : " + status);
        if (pembayaran != null) {
            pembayaran.tampilkanPembayaran();
        }
        if (statusPembayaran != null) {
            statusPembayaran.tampilkanStatus();
        }
        System.out.println("=====================================");
    }
}
