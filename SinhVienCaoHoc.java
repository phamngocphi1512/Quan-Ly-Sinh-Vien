public class SinhVienCaoHoc extends SinhVien {
    private String tenDeTai;
    private static final long serialVersionUID = 3L;

    public SinhVienCaoHoc(String maSo, String ten, String ngaySinh, String maSV, double diemTB, String khoa, String tenDeTai) {
        super(maSo, ten, ngaySinh, maSV, diemTB, khoa);
        this.tenDeTai = tenDeTai;
    }

    @Override
    public String xepLoai() {
        if (diemTB >= 3.8) return "Xuất sắc (CH)";
        if (diemTB >= 3.5) return "Giỏi (CH)";
        return "Dat"; 
    }
    
    @Override
    public double tinhTienThuong() {
        return (diemTB >= 3.8) ? 5000000.0 : 0.0;
    }

    @Override
    public String hienThiChiTietThem() {
        return ", Loai: CAO HOC, De tai: " + tenDeTai;
    }
}