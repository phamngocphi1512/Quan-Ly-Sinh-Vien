import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    
    public static SinhVien taoSinhVien(Scanner sc) {
        String maSo, ten, maSV, khoa;
        double diemTB = 0.0;
        String ngaySinhStr = "";
        String loai = "";

        System.out.println("\n--- TAO SINH VIEN MOI ---");
        
        do {
            System.out.print("Nhap Ma So (Nguoi): "); maSo = sc.nextLine().trim();
            if (maSo.isEmpty()) System.out.println("Loi: Ma so khong duoc de trong.");
        } while (maSo.isEmpty());

        do {
            System.out.print("Nhap Ten: "); ten = sc.nextLine().trim();
            if (ten.isEmpty()) System.out.println("Loi: Ten khong duoc de trong.");
        } while (ten.isEmpty());

        boolean ngaySinhHopLe = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (!ngaySinhHopLe) {
            System.out.print("Nhap Ngay Sinh (dd/MM/yyyy): "); 
            ngaySinhStr = sc.nextLine().trim();
            try {
                LocalDate.parse(ngaySinhStr, formatter);
                ngaySinhHopLe = true;
            } catch (DateTimeParseException e) {
                System.out.println("Loi: Ngay sinh khong dung dinh dang dd/MM/yyyy. Vui long nhap lai.");
            }
        }
        
        do {
            System.out.print("Nhap Ma SV: "); maSV = sc.nextLine().trim();
            if (maSV.isEmpty()) System.out.println("Loi: Ma SV khong duoc de trong.");
        } while (maSV.isEmpty());

        do {
            System.out.print("Nhap Khoa: "); khoa = sc.nextLine().trim();
            if (khoa.isEmpty()) System.out.println("Loi: Khoa khong duoc de trong.");
        } while (khoa.isEmpty());

        boolean diemHopLe = false;
        while (!diemHopLe) {
            try {
                System.out.print("Nhap Diem TB (0.0 - 4.0): ");
                diemTB = Double.parseDouble(sc.nextLine().trim());
                if (diemTB >= 0.0 && diemTB <= 4.0) {
                    diemHopLe = true;
                } else {
                    System.out.println("Loi: Diem TB phai nam trong khoang 0.0 den 4.0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Diem TB phai la mot so. Vui long nhap lai.");
            }
        }

        boolean loaiHopLe = false;
        while (!loaiHopLe) {
            System.out.print("Chon loai sinh vien (1: Chinh Quy, 2: Lien Ket, 3: Cao Hoc, 4: Tai Chuc): ");
            loai = sc.nextLine().trim();
            if (loai.equals("1") || loai.equals("2") || loai.equals("3") || loai.equals("4")) {
                loaiHopLe = true;
            } else {
                System.out.println("Loi: Loai sinh vien khong hop le. Vui long nhap 1, 2, 3 hoac 4.");
            }
        }


        if (loai.equals("1")) {
            int namNhapHoc = 0;
            boolean namHopLe = false;
            int namHienTai = LocalDate.now().getYear();
            while (!namHopLe) {
                System.out.print("Nhap Nam Nhap Hoc: ");
                try {
                    namNhapHoc = Integer.parseInt(sc.nextLine().trim());
                    if (namNhapHoc >= 1990 && namNhapHoc <= namHienTai) {
                         namHopLe = true;
                    } else {
                         System.out.println("Loi: Nam nhap hoc khong hop le (phai >= 1990 va <= " + namHienTai + ").");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Loi: Nam nhap hoc phai la so nguyen. Vui long nhap lai.");
                }
            }
            return new SinhVienChinhQuy(maSo, ten, ngaySinhStr, maSV, diemTB, khoa, namNhapHoc); 

        } else if (loai.equals("2")) {
            String donViLienKet;
            do {
                System.out.print("Nhap Don Vi Lien Ket: ");
                donViLienKet = sc.nextLine().trim();
                if (donViLienKet.isEmpty()) System.out.println("Loi: Don vi lien ket khong duoc de trong.");
            } while (donViLienKet.isEmpty());
            return new SinhVienLienKet(maSo, ten, ngaySinhStr, maSV, diemTB, khoa, donViLienKet); 

        } else if (loai.equals("3")) {
            String tenDeTai;
            do {
                System.out.print("Nhap Ten De Tai: ");
                tenDeTai = sc.nextLine().trim();
                if (tenDeTai.isEmpty()) System.out.println("Loi: Ten de tai khong duoc de trong.");
            } while (tenDeTai.isEmpty());
            return new SinhVienCaoHoc(maSo, ten, ngaySinhStr, maSV, diemTB, khoa, tenDeTai); 

        } else if (loai.equals("4")) {
            String noiCongTac;
            do {
                System.out.print("Nhap Noi Cong Tac: ");
                noiCongTac = sc.nextLine().trim();
                if (noiCongTac.isEmpty()) System.out.println("Loi: Noi cong tac khong duoc de trong.");
            } while (noiCongTac.isEmpty());
            return new SinhVienTaiChuc(maSo, ten, ngaySinhStr, maSV, diemTB, khoa, noiCongTac); 
        }
        
        return null; 
    }

    public static void main(String[] args) {
        QuanLySVImpl quanLyImpl = new QuanLySVImpl(); 
        IQuanLySV quanLy = quanLyImpl;
        Scanner sc = new Scanner(System.in);
        int luaChon;
        
        final String DATA_FILE = IQuanLySV.FILE_NAME;
        
        quanLy.taiDuLieu(DATA_FILE);
        // Thêm vào hàm main()
System.out.println("------------------------------------");
System.out.println("Thu muc lam viec: " + System.getProperty("user.dir"));
System.out.println("------------------------------------");

        if (quanLyImpl.isDanhSachEmpty()) { 
            System.out.println("--- KHOI TAO DU LIEU MAU ---");
            quanLy.themSV(new SinhVienChinhQuy("MS001", "Nguyen Van A", "15/05/2003", "SV001", 3.75, "CNTT", 2021)); 
            quanLy.themSV(new SinhVienLienKet("MS002", "Le Thi B", "01/01/2002", "SV002", 3.30, "Kinh Te", "ABC Group")); 
            quanLy.themSV(new SinhVienCaoHoc("MS005", "Tran Dinh E", "05/05/1990", "SV005", 3.85, "Nghien Cuu", "AI trong Y te"));
            quanLy.themSV(new SinhVienTaiChuc("MS006", "Hoang Thi F", "06/06/1995", "SV006", 3.10, "Kinh Doanh", "FPT Software"));
            System.out.println("-----------------------------\n");
        }
        
        do {
            System.out.println("============= MENU QUAN LY SINH VIEN =============");
            System.out.println("1. Them sinh vien moi");
            System.out.println("2. Hien thi toan bo danh sach (Kèm Xep Loai & Thuong)");
            System.out.println("3. Xoa sinh vien theo Ma SV"); 
            System.out.println("4. Tim kiem & Thao tac chuyen biet");
            System.out.println("5. Sua diem trung binh");
            System.out.println("6. Tinh diem trung binh chung toan truong");
            System.out.println("7. Luu du lieu vao file");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("==================================================");
            System.out.print("Nhap lua chon cua ban: ");
            
            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1; 
            }

            switch (luaChon) {
                case 1:
                    SinhVien svMoi = taoSinhVien(sc);
                    if (svMoi != null) {
                        quanLy.themSV(svMoi);
                    }
                    break;
                case 2:
                    quanLy.hienThiDanhSach();
                    break;
                case 3:
                    System.out.print("Nhap Ma SV can XOA: ");
                    String maXoa = sc.nextLine();
                    quanLy.xoaSV(maXoa);
                    break;
                case 4:
                    System.out.print("Nhap Ma SV can tim de demo: ");
                    String maTim = sc.nextLine();
                    SinhVien tim = quanLy.timKiemSV(maTim);
                    if (tim != null) {
                        System.out.println("\n--- THAO TAC DA HINH ---");
                        System.out.println("Xep loai tu IDanhGia: " + tim.xepLoai());
                        System.out.println("Tien thuong tu IKhenThuong: " + String.format("%.0f", tim.tinhTienThuong()) + " VND");
                        tim.capNhatThongTin("Da duoc khen thuong");
                    } else {
                        System.out.println("Khong tim thay sinh vien co ma " + maTim);
                    }
                    break;
                case 5:
                    System.out.print("Nhap Ma SV can sua diem: ");
                    String maSua = sc.nextLine();
                    System.out.print("Nhap Diem TB moi: ");
                    try {
                        double diemMoi = Double.parseDouble(sc.nextLine());
                        quanLy.suaDiem(maSua, diemMoi);
                    } catch (NumberFormatException e) {
                        System.out.println("Diem khong hop le.");
                    }
                    break;
                case 6:
                    double diemChung = quanLy.tinhDiemTrungBinhChung();
                    System.out.println("DIEM TRUNG BINH CHUNG TOAN TRUONG: " + String.format("%.2f", diemChung));
                    break;
                case 7: 
                    quanLy.luuDuLieu(DATA_FILE); 
                    break;
                case 0:
                    System.out.println("Ban co muon luu du lieu truoc khi thoat khong? (Y/N)");
                    if (sc.nextLine().equalsIgnoreCase("Y")) {
                        quanLy.luuDuLieu(DATA_FILE);
                    }
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai.");
            }
        } while (luaChon != 0);
        
        sc.close();
    }
}