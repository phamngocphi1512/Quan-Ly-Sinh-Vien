public class SinhVienChinhQuy extends SinhVien {
    private int namNhapHoc;
    private static final long serialVersionUID = 2L;

    public SinhVienChinhQuy(String maSo, String ten, String ngaySinh, String maSV, double diemTB, String khoa, int namNhapHoc) {
        super(maSo, ten, ngaySinh, maSV, diemTB, khoa);
        this.namNhapHoc = namNhapHoc;
    }

    @Override
    public String xepLoai() {
        if (diemTB >= 3.7) return "Xuất sắc (CQ)";
        if (diemTB >= 3.3) return "Giỏi (CQ)";
        return super.xepLoai();
    }
    
    @Override
    public double tinhTienThuong() {
        double thuongCoBan = super.tinhTienThuong(); 
        double thuongThem = (diemTB >= 3.7) ? 1000000.0 : 0.0;
        return thuongCoBan + thuongThem;
    }
    
    @Override
    public String hienThiChiTietThem() {
        return ", Loai: CHINH QUY, Nam NH: " + namNhapHoc;
    }
}