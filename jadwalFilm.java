public class jadwalFilm {
    
    private String idJadwal;
    private String tanggal;
    private String jamMulai;
    private double harga;

    public jadwalFilm(){
    }
    public jadwalFilm(String idJadwal, String tanggal, String jamMulai, double harga){
        this.idJadwal = idJadwal;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.harga = harga;
    }
    
    public String getIdJadwal(){
        return idJadwal;
    }
    public void setIdJadwal(String idJadwal){
        this.idJadwal = idJadwal;
    }
    public String getTanggal(){
        return tanggal;
    }
    public void setTanggal(String tanggal){
        this.tanggal = tanggal; 
    }
    public String getJamMulai(){
        return jamMulai;
    }
    public void setJamMulai(String jamMulai){
        this.jamMulai = jamMulai;
    }
    public double getHarga(){
        return harga;
    }
    public void setHarga(double harga){
        if(harga < 0){
            System.out.println("Harga tidak boleh negatif");
            return;
        }
        this.harga = harga;
    }
    public void tampilkanJadwal(){
        System.out.println("ID Jadwal    : " + this.idJadwal);
        System.out.println("Tanggal      : " + this.tanggal);
        System.out.println("Jam Mulai    : " + this.jamMulai + " WIB");
        System.out.printf("Harga Tiket  : Rp %,.0f%n", this.harga);
        System.out.println("----------------------------------------");
    }
    public boolean cekJadwal(String tanggalCari, String jamCari){
        boolean cocok = tanggalCari.equalsIgnoreCase(this.tanggal)
            && jamCari.equalsIgnoreCase(this.jamMulai);
            if (cocok) {
            System.out.println("[BERHASIL] Jadwal DITEMUKAN untuk " + tanggalCari + " pukul " + jamCari + " WIB!");
            System.out.println("ID Jadwal: " + this.idJadwal);
        }
        return cocok;
    }
}
