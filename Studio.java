
public class Studio {
    private int IdStudio;
    private String nameStudio;
    private int kapasitasStudio;

    public Studio(int IdStudio, String nameStudio, int kapasitasStudio) {
        this.IdStudio = IdStudio;
        this.nameStudio = nameStudio;
        this.kapasitasStudio = kapasitasStudio;
    }

    public void showStudio() {
        System.out.println("ID Studio: " + IdStudio);
        System.out.println("Nama Studio: " + nameStudio);
        System.out.println("Kapasitas: " + kapasitasStudio + " Kursi");
        System.out.println();
    }

    public void cekKapasitas(int jumlahPenonton) {
        if (jumlahPenonton <= kapasitasStudio && jumlahPenonton > 0) {
            System.out.println("Kapasitas studio masih mencukupi");
        } else {
            System.out.println("Kapasitas studio tidak mencukupi");
        }
    }

    public String getNameStudio() {
        return nameStudio;
    }

    public int getKapasitasStudio() {
        return kapasitasStudio;
    }
}
