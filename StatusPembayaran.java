package BioskopJava;

public class StatusPembayaran {
    private String statusBayar;
    private String waktuPembayaran;

    public StatusPembayaran() {
        this.statusBayar = "Belum Dibayar";
        this.waktuPembayaran = "-";
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public boolean isLunas() {
        return statusBayar.equalsIgnoreCase("Lunas");
    }

    public void konfirmasiBayar(String tanggal, String jam) {
        this.statusBayar = "Lunas";
        this.waktuPembayaran = tanggal + " / " + jam + " WIB";
    }

    public void tampilkanStatus() {
        System.out.println("Status Pembayaran : " + statusBayar);
        if (isLunas()) {
            System.out.println("Waktu Konfirmasi  : " + waktuPembayaran);
        } else {
            System.out.println("Waktu Konfirmasi  : Belum ada pembayaran");
        }
    }
}
