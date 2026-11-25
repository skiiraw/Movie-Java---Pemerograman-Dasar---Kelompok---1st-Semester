package BioskopJava;

public class JadwalFilm {
    private String idJadwal;
    private String tanggal;
    private String jamMulai;
    private double harga;

    public JadwalFilm() {
        this("JD0", "-", "00:00", 0);
    }

    public JadwalFilm(String idJadwal, String tanggal, String jamMulai, double harga) {
        this.idJadwal = idJadwal;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        setHarga(harga);
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        if (tanggal != null && !tanggal.isBlank()) {
            this.tanggal = tanggal;
        }
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        if (jamMulai != null && !jamMulai.isBlank()) {
            this.jamMulai = jamMulai;
        }
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga >= 0) {
            this.harga = harga;
        } else {
            System.out.println("Harga tidak boleh negatif.");
        }
    }

    public boolean cekJadwal(String tanggalCari, String jamCari) {
        return tanggalCari != null && jamCari != null
                && tanggalCari.equalsIgnoreCase(this.tanggal)
                && jamCari.equalsIgnoreCase(this.jamMulai);
    }

    public void tampilkanJadwal() {
        System.out.println("ID Jadwal    : " + idJadwal);
        System.out.println("Tanggal      : " + tanggal);
        System.out.println("Jam Mulai    : " + jamMulai + " WIB");
        System.out.printf("Harga Tiket  : Rp %,.0f%n", harga);
        System.out.println("----------------------------------------");
    }
}
