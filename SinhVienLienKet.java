public class SinhVienLienKet extends SinhVien {
    private String donViLienKet;
    private static final long serialVersionUID = 2L;

    public SinhVienLienKet(String maSo, String ten, String ngaySinh, String maSV, double diemTB, String khoa, String donViLienKet) {
        super(maSo, ten, ngaySinh, maSV, diemTB, khoa);
        this.donViLienKet = donViLienKet;
    }
    
    @Override
    public double tinhTienThuong() {
        double thuongCoBan = super.tinhTienThuong(); 
        double thuongThem = (diemTB >= 3.2) ? 500000.0 : 0.0;
        return thuongCoBan + thuongThem;
    }

    @Override
    public String hienThiChiTietThem() {
        return ", Loai: LIEN KET, Don vi: " + donViLienKet;
    }
}