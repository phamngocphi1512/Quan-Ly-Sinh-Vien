public interface IQuanLySV {
    String FILE_NAME = "QuanLySinhVien.txt"; 

    void themSV(SinhVien sv);
    void xoaSV(String maSV);
    SinhVien timKiemSV(String maSV);
    void suaDiem(String maSV, double diemMoi);
    void hienThiDanhSach();
    double tinhDiemTrungBinhChung();
    int demSVDuocKhenThuong(double diemGioi);
    void luuDuLieu(String tenFile);
    void taiDuLieu(String tenFile);
}