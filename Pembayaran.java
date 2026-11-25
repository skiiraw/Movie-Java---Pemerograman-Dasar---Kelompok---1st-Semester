package BioskopJava;

public class Pembayaran {
    private String metodePembayaran;
    private double totalBayar;

    public Pembayaran(String metodePembayaran, double totalBayar) {
        this.metodePembayaran = (metodePembayaran != null && !metodePembayaran.isBlank())
                ? metodePembayaran : "Tunai";
        this.totalBayar = (totalBayar >= 0) ? totalBayar : 0;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public void tampilkanPembayaran() {
        System.out.println("Metode Pembayaran : " + metodePembayaran);
        System.out.printf("Total Pembayaran  : Rp %,.0f%n", totalBayar);
    }
}
