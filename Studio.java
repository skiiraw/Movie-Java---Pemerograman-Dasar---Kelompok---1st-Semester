package BioskopJava;

public class Studio {
    private int idStudio;
    private String namaStudio;
    private int kapasitasStudio;

    public Studio(int idStudio, String namaStudio, int kapasitasStudio) {
        this.idStudio = (idStudio > 0) ? idStudio : 1;
        this.namaStudio = (namaStudio != null && !namaStudio.isBlank()) ? namaStudio : "Studio";
        this.kapasitasStudio = (kapasitasStudio > 0) ? kapasitasStudio : 10;
    }

    public int getIdStudio() {
        return idStudio;
    }

    public String getNamaStudio() {
        return namaStudio;
    }

    public int getKapasitasStudio() {
        return kapasitasStudio;
    }

    public boolean isKapasitasCukup(int jumlahPenonton) {
        return jumlahPenonton > 0 && jumlahPenonton <= kapasitasStudio;
    }

    public void showStudio() {
        System.out.println("ID Studio   : " + idStudio);
        System.out.println("Nama Studio : " + namaStudio);
        System.out.println("Kapasitas   : " + kapasitasStudio + " Kursi");
        System.out.println();
    }
}
