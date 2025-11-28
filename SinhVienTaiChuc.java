public class SinhVienTaiChuc extends SinhVien {
    private String noiCongTac;
    private static final long serialVersionUID = 3L;

    public SinhVienTaiChuc(String maSo, String ten, String ngaySinh, String maSV, double diemTB, String khoa, String noiCongTac) {
        super(maSo, ten, ngaySinh, maSV, diemTB, khoa);
        this.noiCongTac = noiCongTac;
    }

    @Override
    public String xepLoai() {
        if (diemTB >= 3.4) return "Xuất sắc (TC)";
        if (diemTB >= 3.0) return "Giỏi (TC)";
        if (diemTB >= 2.0) return "Kha (TC)";
        return "Trung binh (TC)";
    }
    
    @Override
    public double tinhTienThuong() {
        return (diemTB >= 3.0) ? 1000000.0 : 0.0;
    }

    @Override
    public String hienThiChiTietThem() {
        return ", Loai: TAI CHUC, Noi cong tac: " + noiCongTac;
    }
}