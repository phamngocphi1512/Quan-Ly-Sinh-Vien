import java.io.Serializable;

public abstract class SinhVien implements IDanhGia, IKhenThuong, Serializable {
    private static final long serialVersionUID = 2L; 
    
    // Thuộc tính chung
    protected String maSo;
    protected String ten;
    protected String ngaySinh; 
    protected String maSV;
    protected double diemTB;
    protected String khoa;

    public SinhVien(String maSo, String ten, String ngaySinh, String maSV, double diemTB, String khoa) {
        this.maSo = maSo;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.maSV = maSV;
        this.diemTB = diemTB;
        this.khoa = khoa;
    }
    
    public String hienThiThongTinCoBan() {
        return "Ma: " + maSo + ", Ten: " + ten + ", Ngay sinh: " + ngaySinh;
    }
    
    public abstract String hienThiChiTietThem();
    
    @Override
    public String xepLoai() {
        if (diemTB >= 3.6) return "Xuat sac";
        if (diemTB >= 3.2) return "Gioi";
        if (diemTB >= 2.5) return "Kha";
        return "Trung binh";
    }

    @Override
    public double tinhTienThuong() {
        return (xepLoai().equals("Xuat sac")) ? 3000000.0 : 0.0;
    }

    @Override
    public void capNhatThongTin(String thongTinMoi) {
        System.out.println("-> Da cap nhat thong tin cho SV [" + this.maSV + "] - Ghi chu: " + thongTinMoi);
    }
    
    public String hienThiThongTinSVCoBan() {
        return ", Ma SV: " + maSV + ", Khoa: " + khoa + ", Diem TB: " + String.format("%.2f", diemTB);
    }

    @Override
    public String toString() {
        return hienThiThongTinCoBan() + hienThiThongTinSVCoBan() + hienThiChiTietThem() + ", Xep loai: " + xepLoai() + ", Thuong: " + String.format("%.0f", tinhTienThuong()) + " VND";
    }

    // Getters & Setters
    public void setDiemTB(double diemTB) { this.diemTB = diemTB; }
    public String getMaSV() { return maSV; }
    public double getDiemTB() { return diemTB; }
    public String getTen() { return ten; }
}