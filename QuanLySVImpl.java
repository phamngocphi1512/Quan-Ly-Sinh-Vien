import java.util.ArrayList;
import java.util.List;
import java.io.*; 

public class QuanLySVImpl implements IQuanLySV {
    private List<SinhVien> danhSachSV = new ArrayList<>();
    
    @Override
    public void themSV(SinhVien sv) {
        if (timKiemSV(sv.getMaSV()) == null) {
            danhSachSV.add(sv);
            System.out.println("-> Da them sinh vien: " + sv.getTen());
        } else {
            System.out.println("!!! Loi: Sinh vien co ma " + sv.getMaSV() + " da ton tai.");
        }
    }

    @Override
    public SinhVien timKiemSV(String maSV) {
        for (SinhVien sv : danhSachSV) {
            if (sv.getMaSV().equalsIgnoreCase(maSV)) {
                return sv;
            }
        }
        return null;
    }
    
    @Override
    public void xoaSV(String maSV) {
        boolean removed = danhSachSV.removeIf(sv -> sv.getMaSV().equalsIgnoreCase(maSV));
        if (removed) {
            System.out.println("-> Da xoa sinh vien co ma: " + maSV);
        } else {
            System.out.println("!!! Loi: Khong tim thay sinh vien co ma: " + maSV + " de xoa.");
        }
    }

    @Override
    public void suaDiem(String maSV, double diemMoi) {
        SinhVien sv = timKiemSV(maSV);
        if (sv != null) {
            sv.setDiemTB(diemMoi); 
            System.out.println("-> Da cap nhat diem cho SV " + maSV + " thanh " + String.format("%.2f", diemMoi));
        } else {
            System.out.println("!!! Loi: Khong tim thay sinh vien co ma " + maSV);
        }
    }
    
    @Override
    public void hienThiDanhSach() {
        if (danhSachSV.isEmpty()) {
            System.out.println("Danh sach sinh vien trong!");
        } else {
            System.out.println("\n----- DANH SACH SINH VIEN Hien Co -----");
            for (SinhVien sv : danhSachSV) {
                System.out.println(sv.toString());
            }
            System.out.println("----------------------------------------");
        }
    }

    @Override
    public double tinhDiemTrungBinhChung() {
        if (danhSachSV.isEmpty()) return 0;
        double tongDiem = 0;
        for (SinhVien sv : danhSachSV) {
            tongDiem += sv.getDiemTB();
        }
        return tongDiem / danhSachSV.size();
    }

    @Override
    public int demSVDuocKhenThuong(double diemGioi) {
        int count = 0;
        for (SinhVien sv : danhSachSV) {
            if (sv.getDiemTB() >= diemGioi) {
                count++;
            }
        }
        return count;
    }
    
    @Override
    public void luuDuLieu(String tenFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tenFile))) {
            oos.writeObject(danhSachSV);
            System.out.println("-> Du lieu da duoc luu vao file: " + tenFile);
        } catch (IOException e) {
            System.out.println("!!! Loi khi luu du lieu: " + e.getMessage());
        }
    }

    @Override
    public void taiDuLieu(String tenFile) {
        File f = new File(tenFile);
        if (!f.exists()) {
            System.out.println("-> File du lieu chua ton tai. Bat dau voi danh sach rong.");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tenFile))) {
            danhSachSV = (List<SinhVien>) ois.readObject();
            System.out.println("-> Da tai du lieu tu file: " + tenFile + " (" + danhSachSV.size() + " sinh vien)");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("!!! Loi khi tai du lieu: " + e.getMessage());
            danhSachSV = new ArrayList<>(); 
        }
    }
    public boolean isDanhSachEmpty() {
    return danhSachSV.isEmpty();
    }
}