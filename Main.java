import java.util.Scanner;

public class Main {
    
    public static SinhVien taoSinhVien(Scanner sc) {
        String maSo, ten, maSV, khoa, ngaySinhStr, loai;
        double diemTB;

        System.out.println("\n--- TAO SINH VIEN MOI ---");
        System.out.print("Nhap Ma So (Nguoi): "); maSo = sc.nextLine();
        System.out.print("Nhap Ten: "); ten = sc.nextLine();
        System.out.print("Nhap Ngay Sinh (dd/MM/yyyy): "); ngaySinhStr = sc.nextLine(); 
        System.out.print("Nhap Ma SV: "); maSV = sc.nextLine();
        
        try {
            System.out.print("Nhap Diem TB: "); diemTB = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Diem khong hop le. Dat mac dinh 0.0");
            diemTB = 0.0;
        }

        System.out.print("Nhap Khoa: "); khoa = sc.nextLine();
        
        System.out.print("Chon loai sinh vien (1: Chinh Quy, 2: Lien Ket, 3: Cao Hoc, 4: Tai Chuc): ");
        loai = sc.nextLine();

        if (loai.equals("1")) {
            System.out.print("Nhap Nam Nhap Hoc: ");
            try {
                int namNhapHoc = Integer.parseInt(sc.nextLine());
                return new SinhVienChinhQuy(maSo, ten, ngaySinhStr, maSV, diemTB, khoa, namNhapHoc); 
            } catch (NumberFormatException e) {
                System.out.println("Nam nhap hoc khong hop le.");
                return null;
            }
        } else if (loai.equals("2")) {
            System.out.print("Nhap Don Vi Lien Ket: ");
            String donViLienKet = sc.nextLine();
            return new SinhVienLienKet(maSo, ten, ngaySinhStr, maSV, diemTB, khoa, donViLienKet); 
        } else if (loai.equals("3")) {
            System.out.print("Nhap Ten De Tai: ");
            String tenDeTai = sc.nextLine();
            return new SinhVienCaoHoc(maSo, ten, ngaySinhStr, maSV, diemTB, khoa, tenDeTai); 
        } else if (loai.equals("4")) {
            System.out.print("Nhap Noi Cong Tac: ");
            String noiCongTac = sc.nextLine();
            return new SinhVienTaiChuc(maSo, ten, ngaySinhStr, maSV, diemTB, khoa, noiCongTac); 
        } else {
            System.out.println("Loai sinh vien khong hop le.");
            return null;
        }
    }

    public static void main(String[] args) {
        QuanLySVImpl quanLyImpl = new QuanLySVImpl(); 
        IQuanLySV quanLy = quanLyImpl;
        Scanner sc = new Scanner(System.in);
        int luaChon;
        
        final String DATA_FILE = IQuanLySV.FILE_NAME;
        
        quanLy.taiDuLieu(DATA_FILE);

        if (quanLyImpl.isDanhSachEmpty()) {
            System.out.println("--- KHOI TAO DU LIEU MAU ---");
            quanLy.themSV(new SinhVienChinhQuy("MS001", "Nguyen Van A", "15/05/2003", "SV001", 3.75, "CNTT", 2021)); 
            quanLy.themSV(new SinhVienLienKet("MS002", "Le Thi B", "01/01/2002", "SV002", 3.30, "Kinh Te", "ABC Group")); 
            quanLy.themSV(new SinhVienCaoHoc("MS005", "Tran Dinh E", "05/05/1990", "SV005", 3.85, "Nghien Cuu", "AI trong Y te"));
            quanLy.themSV(new SinhVienTaiChuc("MS006", "Hoang Thi F", "06/06/1995", "SV006", 3.10, "Kinh Doanh", "FPT Software"));
            System.out.println("-----------------------------\n");
        }
        
        do {
            System.out.println("============= MENU QUAN LY SINH VIÊN =============");
            System.out.println("1. Them sinh vien moi");
            System.out.println("2. Hien thi toan bo danh sach (Kèm Xep Loai & Thuong)");
            System.out.println("3. Tim kiem & Thao tac chuyen biet (Demo Interface)");
            System.out.println("4. Sua diem trung binh");
            System.out.println("5. Tinh diem trung binh chung toan truong");
            System.out.println("6. Luu du lieu vao file");
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
                case 4:
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
                case 5:
                    double diemChung = quanLy.tinhDiemTrungBinhChung();
                    System.out.println("DIEM TRUNG BINH CHUNG TOAN TRUONG: " + String.format("%.2f", diemChung));
                    break;
                case 6: 
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