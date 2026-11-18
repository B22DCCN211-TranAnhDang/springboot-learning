-- ==============================================================================
-- SCRIPT CHÈN DỮ LIỆU MẪU ĐẦY ĐỦ CHO TRỌN BỘ 13 BẢNG CSDL QUẢN LÝ BẤT ĐỘNG SẢN
-- ==============================================================================

-- 1. XÓA SẠCH DỮ LIỆU CŨ VÀ RESET LẠI BẮT ĐẦU TỪ ID = 1
TRUNCATE TABLE 
    "transaction",
    transactiontype,
    assignmentcustomer,
    customer,
    assignmentbuilding,
    user_role,
    "role",
    "user",
    building_renttype,
    renttype,
    rentarea,
    building,
    district 
RESTART IDENTITY CASCADE;

-- ==============================================================================
-- NHÓM 1: QUẬN, LOẠI HÌNH & TÒA NHÀ, DIỆN TÍCH THUÊ
-- ==============================================================================

-- 1. Thêm danh mục Quận / Huyện (4 quận tiêu biểu)
INSERT INTO district (code, name) VALUES 
('Q1', 'Quận 1'),
('Q2', 'Quận 2'),
('Q3', 'Quận 3'),
('Q_CG', 'Quận Cầu Giấy');

-- 2. Thêm danh mục Loại hình cho thuê (Rent Type)
INSERT INTO renttype (code, name) VALUES 
('NGUYEN_CAN', 'Thuê nguyên căn'),
('TANG_TRET', 'Thuê tầng trệt'),
('NOI_THAT', 'Có sẵn nội thất cao cấp');

-- 3. Thêm 4 Tòa nhà mẫu tiêu biểu
INSERT INTO building (
    name, street, ward, district_id, structure, numberofbasement, floorarea, direction, level, 
    rentprice, rentpricedescription, servicefee, carfee, motorbikefee, overtimefee, waterfee, 
    electricityfee, deposit, payment, renttime, decorationtime, brokeragefee, note
) VALUES 
(
    'Landmark 81', '720A Điện Biên Phủ', 'Phường 22', 1, '81 tầng nổi, 3 tầng hầm', 3, 1500, 'Đông Nam', 'Hạng A',
    50000000, '50 triệu/tháng đã bao gồm VAT', '100.000đ/m2', '2.500.000đ/tháng', '250.000đ/tháng', 'Thỏa thuận', 'Miễn phí',
    'Theo giá nhà nước', '3 tháng', 'Thanh toán theo quý', 'Tối thiểu 3 năm', '30 ngày', 1.0, 'Tòa nhà cao nhất Việt Nam, view triệu đô'
),
(
    'Keangnam Hanoi Landmark', 'Đường Phạm Hùng', 'Mễ Trì', 4, '72 tầng nổi, 2 tầng hầm', 2, 2000, 'Tây Bắc', 'Hạng A',
    35000000, '35 triệu/tháng', '80.000đ/m2', '2.000.000đ/tháng', '200.000đ/tháng', 'Miễn phí ngoài giờ', 'Theo công tơ',
    'Theo giá nhà nước', '3 tháng', 'Thanh toán 6 tháng/lần', 'Tối thiểu 2 năm', '45 ngày', 1.0, 'Vị trí đắc địa trung tâm Cầu Giấy'
),
(
    'Bitexco Financial Tower', 'Số 2 Hải Triều', 'Bến Nghé', 1, '68 tầng nổi, 3 tầng hầm', 3, 1000, 'Đông Bắc', 'Hạng A',
    42000000, '42 triệu/tháng (chưa VAT)', '90.000đ/m2', '3.000.000đ/tháng', '300.000đ/tháng', '200.000đ/giờ', 'Miễn phí',
    'Theo giá EVN', '3 tháng', 'Thanh toán theo tháng', 'Tối thiểu 1 năm', '15 ngày', 0.8, 'Biểu tượng kinh tế TP. Hồ Chí Minh'
),
(
    'Lotte Center Hanoi', '54 Liễu Giai', 'Cống Vị', 4, '65 tầng nổi, 5 tầng hầm', 5, 1800, 'Chính Nam', 'Hạng A',
    38000000, '38 triệu/tháng', '85.000đ/m2', '2.200.000đ/tháng', '220.000đ/tháng', 'Thỏa thuận', 'Miễn phí',
    'Giá kinh doanh', '3 tháng', 'Thanh toán theo quý', 'Tối thiểu 2 năm', '30 ngày', 1.0, 'Khu phức hợp trung tâm thương mại cao cấp'
);

-- 4. Thêm các mức diện tích thuê cho từng tòa nhà (Rent Area)
INSERT INTO rentarea (value, building_id) VALUES 
(100, 1), (200, 1), (500, 1), -- Landmark 81 có các diện tích: 100, 200, 500 m2
(150, 2), (300, 2), (800, 2), -- Keangnam có các diện tích: 150, 300, 800 m2
(200, 3), (400, 3),           -- Bitexco: 200, 400 m2
(250, 4), (500, 4), (1000, 4); -- Lotte: 250, 500, 1000 m2

-- 5. Bảng trung gian Tòa nhà - Loại hình cho thuê (building_renttype: N - N)
INSERT INTO building_renttype (building_id, renttype_id) VALUES 
(1, 1), (1, 3), -- Landmark 81: Thuê nguyên căn & Có sẵn nội thất
(2, 2), (2, 3), -- Keangnam: Thuê tầng trệt & Có sẵn nội thất
(3, 1), (3, 2), -- Bitexco: Nguyên căn & Tầng trệt
(4, 1), (4, 2), (4, 3); -- Lotte Center: Có đủ cả 3 loại hình

-- ==============================================================================
-- NHÓM 2: NHÂN SỰ & PHÂN QUYỀN HỆ THỐNG
-- ==============================================================================

-- 6. Danh mục Chức vụ / Vai trò (Role)
INSERT INTO "role" (code, name) VALUES 
('MANAGER', 'Quản lý phòng ban'),
('STAFF', 'Nhân viên môi giới');

-- 7. Danh sách Tài khoản nhân sự (User)
-- Mật khẩu mặc định là 123456
INSERT INTO "user" (username, password, fullname, phone, email, status) VALUES 
('admin', '123456', 'Quản Trị Viên Hệ Thống', '0901111111', 'admin@estate.vn', 1),
('dang_tran', '123456', 'Trần Anh Đăng', '0902222222', 'dang.tran@estate.vn', 1),
('dat_nguyen', '123456', 'Nguyễn Thành Đạt', '0903333333', 'dat.nguyen@estate.vn', 1),
('linh_le', '123456', 'Lê Thùy Linh', '0904444444', 'linh.le@estate.vn', 1);

-- 8. Phân quyền cho User (user_role: N - N)
INSERT INTO user_role (user_id, role_id) VALUES 
(1, 1), -- Admin là MANAGER
(2, 1), -- Trần Anh Đăng là MANAGER (Quản lý)
(3, 2), -- Nguyễn Thành Đạt là STAFF (Nhân viên)
(4, 2); -- Lê Thùy Linh là STAFF (Nhân viên)

-- 9. Phân công Nhân viên quản lý Tòa nhà (assignmentbuilding: N - N)
INSERT INTO assignmentbuilding (staff_id, building_id) VALUES 
(3, 1), (3, 2), -- Nguyễn Thành Đạt phụ trách Landmark 81 & Keangnam
(4, 3), (4, 4), -- Lê Thùy Linh phụ trách Bitexco & Lotte
(2, 1), (2, 2), (2, 3), (2, 4); -- Quản lý Trần Anh Đăng giám sát cả 4 tòa

-- ==============================================================================
-- NHÓM 3: KHÁCH HÀNG & GIAO DỊCH CHĂM SÓC
-- ==============================================================================

-- 10. Danh sách Khách hàng có nhu cầu thuê (Customer)
INSERT INTO customer (fullname, phone, email, demand, status, is_active) VALUES 
('Công ty Cổ phần Công nghệ FPT', '02473007300', 'contact@fpt.com.vn', 'Cần thuê 500m2 văn phòng tầng cao tại Cầu Giấy', 'ĐANG_XU_LY', 1),
('Tập đoàn Vingroup', '02439749999', 'info@vingroup.net', 'Tìm mặt bằng tầng trệt kinh doanh siêu thị 200m2', 'DA_XEM_MAT_BANG', 1),
('Nguyễn Văn Tuấn (Startup AI)', '0912999888', 'tuan.nguyen@aitech.io', 'Cần văn phòng 100m2 có sẵn nội thất cho 20 nhân viên', 'CHUA_XU_LY', 1),
('Trần Mai Phương', '0988776655', 'phuong.tran@gmail.com', 'Mở showroom trưng bày thời trang cao cấp tại Quận 1', 'DA_KY_HOP_DONG', 1);

-- 11. Phân công Nhân viên chăm sóc Khách hàng (assignmentcustomer: N - N)
INSERT INTO assignmentcustomer (staff_id, customer_id) VALUES 
(3, 1), -- Nguyễn Thành Đạt chăm sóc FPT
(3, 3), -- Nguyễn Thành Đạt chăm sóc Nguyễn Văn Tuấn
(4, 2), -- Lê Thùy Linh chăm sóc Vingroup
(4, 4); -- Lê Thùy Linh chăm sóc Trần Mai Phương

-- 12. Danh mục Loại giao dịch CSKH (Transaction Type)
INSERT INTO transactiontype (code, name) VALUES 
('GOI_DIEN', 'Gọi điện tư vấn báo giá'),
('DAN_DI_XEM', 'Dẫn khách đi khảo sát mặt bằng thực tế'),
('DAM_PHAN', 'Đàm phán hợp đồng & chiết khấu giá');

-- 13. Nhật ký chi tiết các lần giao dịch với Khách hàng (Transaction)
INSERT INTO "transaction" (code, note, customer_id, staff_id) VALUES 
('GOI_DIEN', 'Đã gọi điện tư vấn cho khách, khách rất ưng ý diện tích 500m2 tại Keangnam', 1, 3),
('DAN_DI_XEM', 'Đã dẫn đại diện FPT qua xem thực tế tầng 25 tòa Keangnam lúc 9h sáng', 1, 3),
('DAN_DI_XEM', 'Dẫn khách Vingroup xem tầng trệt Landmark 81, khách cần đàm phán thêm giá thuê', 2, 4),
('DAM_PHAN', 'Khách hàng chốt thuê mặt bằng Bitexco, hẹn ngày 15 ký hợp đồng chính thức', 4, 4);
