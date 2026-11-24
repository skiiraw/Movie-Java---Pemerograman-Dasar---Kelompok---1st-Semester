import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Studio[] daftarStudio = {
            new Studio(1, "Studio 1", 30),
            new Studio(2, "Studio 2", 40),
            new Studio(3, "Studio 3", 50),
            new Studio(4, "Studio 4", 60),
            new Studio(5, "Studio 5", 70)
        };

        Film[] daftarFilm = {
            new Film("Interstellar", "Sci-Fi", 169, "SU"),
            new Film("Inside Out 2", "Animasi", 96, "SU"),
            new Film("The Conjuring", "Horor", 112, "17+"),
            new Film("Avengers: Endgame", "Action", 181, "13+"),
            new Film("Laskar Pelangi", "Drama", 125, "SU")
        };

        String[] jamTayang = {"10:00", "13:00", "16:00", "19:00"};
        double[] hargaTayang = {35000, 45000, 45000, 50000};
        boolean[][][] kursiTerisi = new boolean[daftarStudio.length][jamTayang.length][10];
        int nomorTiket = 1;

        while (true) {
            System.out.println("\n========== BIOSKOP UB==========");
            System.out.println("1. Pesan Tiket");
            System.out.println("2. Lihat Daftar Studio");
            System.out.println("0. Keluar");
            int menu = bacaAngka("Pilih menu: ", 0, 2);
            if (menu == 0) {
                System.out.println("Terima kasih sudah menggunakan Bioskop UB!");
                break;
            }
            if (menu == 2) {
                for (Studio studio : daftarStudio) studio.showStudio();
                continue;
            }

            System.out.println("\n--- PILIH STUDIO ---");
            for (int i = 0; i < daftarStudio.length; i++) {
                System.out.print((i + 1) + ". ");
                daftarStudio[i].showStudio();
            }
            int pilihanStudio = bacaAngka("Pilih studio: ", 1, daftarStudio.length) - 1;
            Studio studio = daftarStudio[pilihanStudio];
            studio.cekKapasitas(1);

            System.out.println("\n--- PILIH FILM ---");
            for (int i = 0; i < daftarFilm.length; i++) {
                System.out.print((i + 1) + ". ");
                daftarFilm[i].cetakInfo();
            }
            int pilihanFilm = bacaAngka("Pilih film: ", 1, daftarFilm.length) - 1;
            Film film = daftarFilm[pilihanFilm];

            String tanggal = LocalDate.now(ZoneId.of("Asia/Jakarta")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            System.out.println("\n--- PILIH JADWAL ---");
            System.out.println("Tanggal tayang: " + tanggal);
            for (int i = 0; i < jamTayang.length; i++) {
                System.out.printf("%d. %s WIB - Rp %,.0f%n", i + 1, jamTayang[i], hargaTayang[i]);
            }
            int pilihanJam = bacaAngka("Pilih jam: ", 1, jamTayang.length) - 1;
            jadwalFilm jadwal = new jadwalFilm("JD" + (pilihanJam + 1), tanggal,
                    jamTayang[pilihanJam], hargaTayang[pilihanJam]);
            jadwal.tampilkanJadwal();

            System.out.println("\n--- PILIH KURSI ---");
            System.out.println("O = tersedia, X = terisi");
            for (int i = 0; i < 10; i++) {
                System.out.printf("%s%d [%s]   ", "A", i + 1,
                    kursiTerisi[pilihanStudio][pilihanJam][i] ? "X" : "O");
                if (i == 4) System.out.println();
            }
            System.out.println();
            int pilihanKursi = bacaAngka("Pilih nomor kursi (1-10): ", 1, 10) - 1;
            if (kursiTerisi[pilihanStudio][pilihanJam][pilihanKursi]) {
                System.out.println("Kursi sudah terisi, silakan pesan lagi.");
                continue;
            }
            String nomorKursi = "A" + (pilihanKursi + 1);
            kursi tempatDuduk = new kursi(nomorKursi, "A", true);
            if (!tempatDuduk.cekKetersediaan()) continue;
            tempatDuduk.pilihKursi();

            tiket tiketPesanan = new tiket("TKT" + String.format("%03d", nomorTiket),
                    nomorKursi, jadwal.getHarga(), "Tersedia");
            tiketPesanan.pesanTiket();

            System.out.println("\n--- METODE PEMBAYARAN ---");
            System.out.println("1. Tunai");
            System.out.println("2. Transfer Bank");
            System.out.println("3. QRIS");
            int metode = bacaAngka("Pilih metode: ", 1, 3);
            String[] daftarMetode = {"Tunai", "Transfer Bank", "QRIS"};
            Pembayaran pembayaran = new Pembayaran(daftarMetode[metode - 1], tiketPesanan.getHarga());
            StatusPembayaran status = new StatusPembayaran();
            pembayaran.tampilkanPembayaran();
            System.out.println("1. Konfirmasi sudah bayar (simulasi)");
            System.out.println("2. Batalkan pemesanan");
            int konfirmasi = bacaAngka("Pilihan: ", 1, 2);
            if (konfirmasi == 2) {
                tiketPesanan.batalkanTiket();
                System.out.println("Pemesanan dibatalkan.");
                continue;
            }

            status.konfirmasiBayar();
            kursiTerisi[pilihanStudio][pilihanJam][pilihanKursi] = true;
            nomorTiket++;
            System.out.println("\n========== STRUK PEMESANAN ==========");
            System.out.println("Film             : " + film.getJudul());
            System.out.println("Studio           : " + studio.getNameStudio());
            System.out.println("Tanggal & Jam    : " + jadwal.getTanggal() + " / " + jadwal.getJamMulai() + " WIB");
            tiketPesanan.cetakTiket();
            pembayaran.tampilkanPembayaran();
            status.tampilkanStatus();
            System.out.println("=====================================");
        }
        input.close();
    }

    private static int bacaAngka(String pesan, int minimum, int maksimum) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();
            try {
                int angka = Integer.parseInt(teks);
                if (angka >= minimum && angka <= maksimum) return angka;
            } catch (NumberFormatException e) {
            }
            System.out.println("Masukkan angka " + minimum + " sampai " + maksimum + ".");
        }
    }
}
