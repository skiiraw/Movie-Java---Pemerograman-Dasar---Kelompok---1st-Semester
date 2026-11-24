import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StatusPembayaran {
    private String statusBayar;
    private LocalDateTime waktuPembayaran;

    public StatusPembayaran() {
        statusBayar = "Belum Dibayar";
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void konfirmasiBayar() {
        statusBayar = "Lunas";
        waktuPembayaran = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
    }

    public void tampilkanStatus() {
        System.out.println("Status Pembayaran : " + statusBayar);
        if (waktuPembayaran != null) {
            Locale indonesia = Locale.forLanguageTag("id-ID");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm:ss", indonesia);
            System.out.println("Hari / Tanggal    : " + waktuPembayaran.format(format));
        } else {
            System.out.println("Hari / Tanggal    : Belum ada pembayaran");
        }
    }
}
