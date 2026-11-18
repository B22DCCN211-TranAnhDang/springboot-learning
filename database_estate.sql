-- ==============================================================================
-- DỰ ÁN QUẢN LÝ BẤT ĐỘNG SẢN - 12 BẢNG (POSTGRESQL / PGADMIN 4)
-- ==============================================================================

-- 1. XÓA CÁC BẢNG CŨ NẾU ĐÃ TỒN TẠI (CASCADE ĐỂ XÓA TOÀN BỘ KHÓA NGOẠI LIÊN QUAN)
DROP TABLE IF EXISTS "transaction" CASCADE;
DROP TABLE IF EXISTS "transactiontype" CASCADE;
DROP TABLE IF EXISTS "assignmentcustomer" CASCADE;
DROP TABLE IF EXISTS "customer" CASCADE;
DROP TABLE IF EXISTS "assignmentbuilding" CASCADE;
DROP TABLE IF EXISTS "user_role" CASCADE;
DROP TABLE IF EXISTS "role" CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS "building_renttype" CASCADE;
DROP TABLE IF EXISTS "renttype" CASCADE;
DROP TABLE IF EXISTS "rentarea" CASCADE;
DROP TABLE IF EXISTS "building" CASCADE;
DROP TABLE IF EXISTS "district" CASCADE;

-- ==============================================================================
-- NHÓM 1: QUẢN LÝ TÒA NHÀ & VỊ TRÍ, DIỆN TÍCH THUÊ
-- ==============================================================================

-- Bảng 1: Quận / Huyện
CREATE TABLE district (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

-- Bảng 2: Thông tin Tòa nhà
CREATE TABLE building (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    street VARCHAR(255),
    ward VARCHAR(255),
    district_id BIGINT REFERENCES district(id) ON DELETE SET NULL,
    structure VARCHAR(100),
    numberofbasement INT,
    floorarea INT,
    direction VARCHAR(50),
    level VARCHAR(50),
    rentprice INT,
    rentpricedescription TEXT,
    servicefee VARCHAR(255),
    carfee VARCHAR(255),
    motorbikefee VARCHAR(255),
    overtimefee VARCHAR(255),
    waterfee VARCHAR(255),
    electricityfee VARCHAR(255),
    deposit VARCHAR(255),
    payment VARCHAR(255),
    renttime VARCHAR(255),
    decorationtime VARCHAR(255),
    brokeragefee NUMERIC(10, 2),
    note TEXT,
    linkofbuilding VARCHAR(255),
    map VARCHAR(255),
    image VARCHAR(255),
    createddate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifieddate TIMESTAMP,
    createdby VARCHAR(100),
    modifiedby VARCHAR(100)
);

-- Bảng 3: Diện tích thuê của từng tòa nhà (1 Tòa có nhiều diện tích thuê)
CREATE TABLE rentarea (
    id BIGSERIAL PRIMARY KEY,
    value INT NOT NULL,
    building_id BIGINT NOT NULL REFERENCES building(id) ON DELETE CASCADE,
    createddate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifieddate TIMESTAMP,
    createdby VARCHAR(100),
    modifiedby VARCHAR(100)
);

-- Bảng 4: Danh mục loại tòa nhà (Nguyên căn, Tầng trệt, Có nội thất...)
CREATE TABLE renttype (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

-- Bảng 5: Bảng trung gian Tòa nhà - Loại tòa nhà (N - N)
CREATE TABLE building_renttype (
    id BIGSERIAL PRIMARY KEY,
    building_id BIGINT NOT NULL REFERENCES building(id) ON DELETE CASCADE,
    renttype_id BIGINT NOT NULL REFERENCES renttype(id) ON DELETE CASCADE
);

-- ==============================================================================
-- NHÓM 2: NHÂN SỰ & PHÂN QUYỀN HỆ THỐNG
-- ==============================================================================

-- Bảng 6: Bảng người dùng / nhân viên
CREATE TABLE "user" (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    status INT DEFAULT 1,
    createddate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifieddate TIMESTAMP,
    createdby VARCHAR(100),
    modifiedby VARCHAR(100)
);

-- Bảng 7: Bảng vai trò (Role)
CREATE TABLE "role" (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

-- Bảng 8: Bảng trung gian Phân quyền User - Role (N - N)
CREATE TABLE user_role (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
    role_id BIGINT NOT NULL REFERENCES "role"(id) ON DELETE CASCADE
);

-- Bảng 9: Bảng trung gian Phân công nhân viên quản lý tòa nhà (N - N)
CREATE TABLE assignmentbuilding (
    id BIGSERIAL PRIMARY KEY,
    staff_id BIGINT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
    building_id BIGINT NOT NULL REFERENCES building(id) ON DELETE CASCADE
);

-- ==============================================================================
-- NHÓM 3: KHÁCH HÀNG & GIAO DỊCH CHĂM SÓC
-- ==============================================================================

-- Bảng 10: Khách hàng cần thuê
CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    fullname VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    demand TEXT,
    status VARCHAR(50),
    is_active INT DEFAULT 1,
    createddate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifieddate TIMESTAMP,
    createdby VARCHAR(100),
    modifiedby VARCHAR(100)
);

-- Bảng 11: Bảng trung gian Phân công nhân viên chăm sóc khách hàng (N - N)
CREATE TABLE assignmentcustomer (
    id BIGSERIAL PRIMARY KEY,
    staff_id BIGINT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
    customer_id BIGINT NOT NULL REFERENCES customer(id) ON DELETE CASCADE
);

-- Bảng 12: Danh mục loại giao dịch (CSKH, Dẫn đi xem...)
CREATE TABLE transactiontype (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

-- Bảng 13 (Phụ trợ lịch sử): Chi tiết nhật ký từng lần giao dịch với khách
CREATE TABLE "transaction" (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50),
    note TEXT,
    customer_id BIGINT NOT NULL REFERENCES customer(id) ON DELETE CASCADE,
    staff_id BIGINT REFERENCES "user"(id) ON DELETE SET NULL,
    createddate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifieddate TIMESTAMP,
    createdby VARCHAR(100),
    modifiedby VARCHAR(100)
);
