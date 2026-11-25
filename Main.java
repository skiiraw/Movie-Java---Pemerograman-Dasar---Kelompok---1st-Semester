package BioskopJava;

import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Studio[] daftarStudio = {
            new Studio(1, "Studio 1", 100),
            new Studio(2, "Studio 2", 100),
            new Studio(3, "Studio 3", 150),
            new Studio(4, "Studio 4", 150),
            new Studio(5, "Studio IMAX", 50)
        };

        Film[] daftarFilm = {
            new Film("Inception", "Sci-Fi", 168, "13+"),
            new Film("Inside Out 2", "Animasi", 96, "SU"),
            new Film("The Conjuring", "Horor", 112, "17+"),
            new Film("Avengers: Endgame", "Action", 181, "13+"),
            new Film("Laskar Pelangi", "Drama", 125, "SU")
        };

        String tanggalTayang = "26-11-2026";
        JadwalFilm[] daftarJadwal = {
            new JadwalFilm("JD1", tanggalTayang, "10:00", 35000),
            new JadwalFilm("JD2", tanggalTayang, "13:00", 45000),
            new JadwalFilm("JD3", tanggalTayang, "16:00", 45000),
            new JadwalFilm("JD4", tanggalTayang, "19:00", 50000)
        };

        Kursi[][] denahKursi = buatDenahKursi();

        Tiket[] riwayatTiket = new Tiket[100];
        int jumlahTiket = 0;
        int menu;

        do {
            tampilkanMenu();
            menu = bacaAngka("Pilih menu: ", 0, 2);

            switch (menu) {
                case 1:
                    Studio studioDipilih = pilihStudio(daftarStudio);
                    if (!studioDipilih.isKapasitasCukup(1)) {
                        System.out.println("Kapasitas studio tidak mencukupi.");
                        break;
                    }
                    System.out.println("Kapasitas studio masih mencukupi.");

                    Film filmDipilih = pilihFilm(daftarFilm);
                    JadwalFilm jadwalDipilih = pilihJadwal(daftarJadwal);
                    jadwalDipilih.tampilkanJadwal();

                    tampilkanDenahKursi(denahKursi, riwayatTiket, jumlahTiket, studioDipilih, jadwalDipilih);
                    int pilihanBaris = bacaAngka("Pilih baris (1 = A, 2 = B): ", 1, denahKursi.length) - 1;
                    int pilihanKolom = bacaAngka("Pilih nomor kolom (1-5): ", 1, denahKursi[0].length) - 1;

                    Kursi kursiTemplate = denahKursi[pilihanBaris][pilihanKolom];
                    if (isKursiTerisi(riwayatTiket, jumlahTiket, studioDipilih, jadwalDipilih, kursiTemplate.getNomorKursi())) {
                        System.out.println("Kursi " + kursiTemplate.getNomorKursi() + " sudah terisi, silakan pesan lagi.");
                        break;
                    }

                    Kursi kursiPesanan = new Kursi(kursiTemplate.getNomorKursi(), kursiTemplate.getBaris(), true);
                    if (!kursiPesanan.pilihKursi()) {
                        break;
                    }

                    String idTiket = "TKT-" + (jumlahTiket + 1);
                    Tiket tiketPesanan = new Tiket(idTiket, filmDipilih, studioDipilih, jadwalDipilih, kursiPesanan);
                    tiketPesanan.pesanTiket();

                    Pembayaran pembayaran = pilihMetodePembayaran(jadwalDipilih.getHarga());
                    StatusPembayaran statusBayar = new StatusPembayaran();
                    pembayaran.tampilkanPembayaran();

                    System.out.println("1. Konfirmasi sudah bayar (simulasi)");
                    System.out.println("2. Batalkan pemesanan");
                    int konfirmasi = bacaAngka("Pilihan: ", 1, 2);

                    if (konfirmasi == 2) {
                        tiketPesanan.batalkanTiket();
                        System.out.println("Pemesanan dibatalkan.");
                        break;
                    }

                    statusBayar.konfirmasiBayar(jadwalDipilih.getTanggal(), jadwalDipilih.getJamMulai());
                    tiketPesanan.setDetailPembayaran(pembayaran, statusBayar);

                    if (jumlahTiket < riwayatTiket.length) {
                        riwayatTiket[jumlahTiket] = tiketPesanan;
                        jumlahTiket++;
                    }

                    tiketPesanan.cetakTiket();
                    break;

                case 2:
                    tampilkanDaftarStudio(daftarStudio);
                    break;

                case 0:
                    System.out.println("Terima kasih sudah menggunakan Bioskop UB!");
                    break;

                default:
                    System.out.println("Menu tidak valid.");
            }
        } while (menu != 0);

        input.close();
    }


    private static void tampilkanMenu() {
        System.out.println("\n========== BIOSKOP UB ==========");
        System.out.println("1. Pesan Tiket");
        System.out.println("2. Lihat Daftar Studio");
        System.out.println("0. Keluar");
    }

    private static void tampilkanDaftarStudio(Studio[] daftarStudio) {
        System.out.println("\n--- DAFTAR STUDIO ---");
        for (Studio studio : daftarStudio) {
            if (studio != null) {
                studio.showStudio();
            }
        }
    }

    private static Studio pilihStudio(Studio[] daftarStudio) {
        System.out.println("\n--- PILIH STUDIO ---");
        for (int i = 0; i < daftarStudio.length; i++) {
            System.out.print((i + 1) + ". ");
            daftarStudio[i].showStudio();
        }
        int pilihan = bacaAngka("Pilih studio: ", 1, daftarStudio.length) - 1;
        return daftarStudio[pilihan];
    }

    private static Film pilihFilm(Film[] daftarFilm) {
        System.out.println("\n--- PILIH FILM ---");
        for (int i = 0; i < daftarFilm.length; i++) {
            System.out.print((i + 1) + ". ");
            daftarFilm[i].cetakInfo();
        }
        int pilihan = bacaAngka("Pilih film: ", 1, daftarFilm.length) - 1;
        return daftarFilm[pilihan];
    }

    private static JadwalFilm pilihJadwal(JadwalFilm[] daftarJadwal) {
        System.out.println("\n--- PILIH JADWAL ---");
        System.out.println("Tanggal tayang: " + daftarJadwal[0].getTanggal());
        for (int i = 0; i < daftarJadwal.length; i++) {
            System.out.printf("%d. %s WIB - Rp %,.0f%n",
                    (i + 1), daftarJadwal[i].getJamMulai(), daftarJadwal[i].getHarga());
        }
        int pilihan = bacaAngka("Pilih jam: ", 1, daftarJadwal.length) - 1;
        return daftarJadwal[pilihan];
    }

    private static Kursi[][] buatDenahKursi() {
        String[] namaBaris = {"A", "B"};
        Kursi[][] denah = new Kursi[namaBaris.length][5];
        for (int b = 0; b < denah.length; b++) {
            for (int k = 0; k < denah[b].length; k++) {
                String nomor = namaBaris[b] + (k + 1);
                denah[b][k] = new Kursi(nomor, namaBaris[b], true);
            }
        }
        return denah;
    }

    private static void tampilkanDenahKursi(Kursi[][] denahKursi, Tiket[] riwayatTiket,
                                            int jumlahTiket, Studio studio, JadwalFilm jadwal) {
        System.out.println("\n--- PILIH KURSI ---");
        System.out.println("O = tersedia, X = terisi");
        for (int b = 0; b < denahKursi.length; b++) {
            for (int k = 0; k < denahKursi[b].length; k++) {
                String nomor = denahKursi[b][k].getNomorKursi();
                boolean terisi = isKursiTerisi(riwayatTiket, jumlahTiket, studio, jadwal, nomor);
                System.out.printf("%s [%s]   ", nomor, terisi ? "X" : "O");
            }
            System.out.println();
        }
    }

    private static boolean isKursiTerisi(Tiket[] riwayatTiket, int jumlahTiket,
                                         Studio studio, JadwalFilm jadwal, String nomorKursi) {
        for (int i = 0; i < jumlahTiket; i++) {
            Tiket t = riwayatTiket[i];
            if (t != null && t.getStatus().equalsIgnoreCase("Dipesan")) {
                boolean samaStudio = t.getStudio().getIdStudio() == studio.getIdStudio();
                boolean samaJadwal = t.getJadwal().getIdJadwal().equalsIgnoreCase(jadwal.getIdJadwal());
                boolean samaKursi = t.getKursi().getNomorKursi().equalsIgnoreCase(nomorKursi);
                if (samaStudio && samaJadwal && samaKursi) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Pembayaran pilihMetodePembayaran(double totalHarga) {
        System.out.println("\n--- METODE PEMBAYARAN ---");
        System.out.println("1. Tunai");
        System.out.println("2. Transfer Bank");
        System.out.println("3. QRIS");
        int metode = bacaAngka("Pilih metode: ", 1, 3);

        String namaMetode;
        switch (metode) {
            case 1:
                namaMetode = "Tunai";
                break;
            case 2:
                namaMetode = "Transfer Bank";
                break;
            default:
                namaMetode = "QRIS";
                break;
        }
        return new Pembayaran(namaMetode, totalHarga);
    }

    private static int bacaAngka(String pesan, int minimum, int maksimum) {
        int angka = -1;
        boolean valid = false;
        do {
            System.out.print(pesan);
            if (input.hasNextInt()) {
                angka = input.nextInt();
                input.nextLine();
                if (angka >= minimum && angka <= maksimum) {
                    valid = true;
                } else {
                    System.out.println("Masukkan angka " + minimum + " sampai " + maksimum + ".");
                }
            } else {
                input.nextLine();
                System.out.println("Masukkan angka " + minimum + " sampai " + maksimum + ".");
            }
        } while (!valid);
        return angka;
    }
}
