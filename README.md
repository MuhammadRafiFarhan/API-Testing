# API-Testing

Ini adalah proyek yang berisi tentang pengujian API yang 
disediakan oleh https://dummyapi.io/. Hanya API untuk 
data User yang diuji di dalam proyek sederhana ini. 
Proyek ini dikembangkan menggunakan bahasa pemrograman 
Java untuk keperluan pengembangan script testing-nya 
serta Maven untuk keperluan build tools.

## Tools yang Digunakan

Proyek pengujian API secara otomatis ini menggunakan 
beberapa tools yang esensial, diantaranya adalah:
* Java (versi JDK 17 atau yang lebih tinggi)
* Maven (versi 3.8.3)
* Rest Assured (versi 5.4.0)
* JUnit Jupiter (versi 5.7.2)
* Visual Studio Code (text editor)
* Command Line Interface (CLI) di lingkungan Windows 11

## Getting Started

### Prerequisite
Agar dapat menjalankan proyek pengujian API otomatis ini pada perangkat Anda,
mestilah memenuhi beberapa persyaratan berikut:
- [x] Perankat sudah terinstall Java JDK versi 17 atau yang lebih tinggi.
- [x] Perangkat sudah terinstall Maven versi 3.8.3 atau yang lebih tinggi.
- [x] Memiliki koneksi internet yang stabil saat mengunduh dependencies Maven.
- [x] Memiliki akun di https://dummyapi.io/ untuk mendapatkan API key app-id.

### Installation
1. Untuh mengunduh repository proyek ini, jalankan perintah berikut di dalam terminal/CLI Anda:

   ```bash
   git clone https://github.com/MuhammadRafiFarhan/API-Testing.git
   ```

2. Buka folder root proyek ini bernama `api` di dalam text editor Visual Studio Code.

   ```bash
   cd ./api
   ```

3. Buka terminal di dalam text editor tersebut, lalu jalankan perintah berikut untuk menginstall dependencies Maven dari file `api/pom.xml`:

   ```bash
   mvn clean install
   ```

4. Tunggu hingga proses installasi dependencies selesai. Setelah itu, proyek siap untuk dijalankan.

### Folder Structure
Berikut adalah struktur folder dari proyek ini:
```bash
API-Testing
├── .vscode
│   └── settings.json (file konfigurasi Visual Studio Code)
├── api
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── com
│   │   │           └── apitest
│   │   │               └── Main.java (default class)
│   │   └── test
│   │       ├── java
│   │       │   └── com
│   │       │        └── apitest
│   │       │            ├── GetTesting.java (test class)
│   │       │            ├── PostTesting.java (test class)
│   │       │            ├── PutTesting.java (test class)
│   │       │            └── DeleteTesting.java (test class)
│   │       └── resources
│   │           └── user-collection.json
│   ├── target
│   │   └── // folder-folder dan file-file hasil build Maven ... //
│   ├── .env
│   └── pom.xml
└── README.md
```

* Folder `api` adalah folder utama yang berisi source code proyek.
* Folder `src` berisi source code Java yang terbagi menjadi dua bagian, yaitu `main` dan `test`.
* Folder `main` berisi source code Java yang berfungsi sebagai default class.
* Folder `test` berisi source code Java yang berfungsi sebagai test class.
* Folder `resources` berisi file JSON yang berfungsi sebagai JSON schema data user.
* Folder `target` berisi hasil build Maven
* File `.env` berisi API key app-id yang diperoleh dari akun https://dummyapi.io/account.
* File `pom.xml` berisi konfigurasi Maven untuk mengatur dependencies dan plugin yang digunakan.

### Pengaturan Environment Pengujian

* Saat akan menjalankan perintah apapun, pastikan path terkini di command prompt berada di dalam folder `api`.
* Silakan update file `.env` dengan API key app-id yang diperoleh dari akun https://dummyapi.io/account milik Anda.

## Alur Kerja Pembuatan Test Script

1. Membuat class test baru di dalam folder `test/java/com/apitest/` dengan format nama file class `{MethodAPI}{NamaData}Testing.java`.
   ```
   Contoh: 
   - GetUserTesting.java
   - PostUserTesting.java
   - PutUserTesting.java
   - DeleteUserTesting.java
   ```

2. Import library yang diperlukan untuk menjalankan test script, yang berasal dari dependencies Maven pada file `pom.xml`. Misalkan:
   ```java
   package com.apitest;
   import io.github.cdimascio.dotenv.Dotenv;
   import io.restassured.RestAssured;
   import static io.restassured.RestAssured.given;
   import org.junit.jupiter.api.DisplayName;
   import org.junit.jupiter.api.Test;
   import org.junit.jupiter.api.BeforeEach;
   import org.hamcrest.Matchers;
   import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath; 
   ```

2. Melakukan load dotenv dari file `.env` untuk mendapatkan API key app-id.
   ```java
   Dotenv dotenv = Dotenv.load();
   ```

3. Membuat method `beforeEach()` untuk melakukan setup sebelum menjalankan test script.
   ```java
   @BeforeEach
    public void beforeEach() {
        // reset all request specifications
        RestAssured.reset();

        // set url
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
   }
   ```

4. Membuat method test sesuai nama test case pada dokumen Test Case Design. Pisahkan antar kata pada nama method dengan tanda underscore `_` dengan semua kata ditulis dalam huruf kecil. Berikan juga anotasi `@Test` dan `@DisplayName` pada method tersebut.
   ```java
   @Test  // <-- anotasi
   @DisplayName("Operasi GET menggunakan id user yang valid")
   public void get_valid_user_data() {
        // test script
    }
   ```

5. Isilah body method test tersebut dengan script testing yang sesuai dengan test case yang diinginkan. Struktur pipeline method test adalah sebagai berikut:
   ```java
         // test script
         given()
            .header("app-id", dotenv.get("APP_ID"))
         .when()
            .get("/user/{id}")
         .then()
            .assertThat()
            .statusCode({kode Status Kode yang ingin divalidasi})
            .body(matchesJsonSchemaInClasspath("user-collection.json"));
   ```

   Pada kasus error handling, ganti bagian `.then()` menjadi seperti berikut:
   ```java
         // ... kode sebelumnya
         .then()
            .assertThat()
            .statusCode({kode Status Kode error yang ingin divalidasi})
            .body("error", Matchers.equalTo({string pesan error yang diharapkan}));
   ```

6. Berikan komentar yang memadai di setiap kode. Utamanya, dengan memberikan kode terkait nomor Test Case di setiap header method test untuk memudahkan identifikasi test case yang dijalankan. Misal:
   ```java
   // TC_01  <<--- inilah komentar yang dimaksud
   @Test
   @DisplayName("Operasi GET menggunakan id user yang valid")
   public void get_valid_user_data() {
        // test script
    }
   ```

## Running Testing Script
> **Catatan:**<br/>
  Untuk proses selanjutnya, demi menghindari instalasi dependency Maven setiap kali dilakukan pemanggilan command mvn, selalu tambahkan flag `-o` pada command tersebut, menandakan bahwa command mvn akan dijalankan secara offline.

Terdapat dua pilihan dalam menjalankan test script pada proyek ini:
1. Menjalankan semua test script yang ada di dalam proyek ini.
   Untuk proses ini, jalankan sintaks berikut:

   ```bash
   mvn test -o
   ```

2. Menjalankan test script tertentu dari nama kelas test yang diinginkan. (Misalkan nama file kelas testnya adalah `GetTesting.java`)

   ```bash
   mvn test -Dtest=GetTesting -o
   ```

## Software Under Test (SUT)
Pengujian API dilakukan terhadap web service yang disediakan oleh https://dummyapi.io/. Hit API yang diuji adalah yang dituliskan di dalam dokumentasi User Data pada alamat https://dummyapi.io/docs/user :
1. GET User by id
   > /user/:id
2. POST User to create new user
   > /user/create
3. PUT User to update user data
   > /user/:id
4. DELETE User by id
   > /user/:id

## Test Case Design
Pengujian menggunakan pendekatan Black-box testing pada level fungsionalitas unit API. Test Case dihasilkan mengggunakan metode ECP dan BVA. Berikut adalah daftar test case yang diuji pada proyek ini:

### Test Case Valid
```
GET User dengan ID yang terdaftar
POST User baru dengan mengisi firstName, lastName, dan email
POST User baru dengan jumlah karakter firstName >= 2
POST User baru dengan jumlah karakter firstName <= 50
POST User baru dengan jumlah karakter lastName >= 2
POST User baru dengan jumlah karakter lastName <= 50
POST User baru dengan "title" sesuai dengan enum title yang diizinkan
POST User baru dengan "gender" sesuai dengan enum yang diizinkan
POST User baru dengan dateOfBirth >= 1 Januari 1900
PUT User dengan "firstName dan "lastName" baru
PUT User dengan title baru "mr"
PUT User dengan title baru "mrs"
PUT User dengan title baru "ms"
PUT User dengan title baru "miss"
PUT User dengan title baru "dr"
PUT User dengan title baru kosong " "
PUT User dengan "firstName" baru saja
PUT User dengan "lastName" baru saja
PUT User dengan gender baru "male"
PUT User dengan gender baru "female"
PUT User dengan gender baru "other"
PUT User dengan gender baru kosong " "
PUT User dengan dateOfBirth valid >= 1 Januari 1900
PUT User dengan "phone" baru
PUT User dengan picture url baru dengan format awalan URL https://
PUT User dengan object Location baru berisi "street" saja dengan format valid
PUT User dengan object Location baru berisi "city" saja dengan format valid
PUT User dengan object Location baru berisi "state" saja dengan format valid
PUT User dengan object Location baru berisi "country" saja dengan format valid
PUT User dengan object Location baru berisi "timezone" saja dengan format valid
PUT User dengan object Location berisi street, city, state, country, timezone valid
DELETE User dengan ID yang terdaftar
```

### Test Case Invalid
```
GET User dengan ID yang tidak terdaftar
POST User baru dengan mengisi "firstName" valid saja
POST User baru dengan mengisi "lastName" valid saja
POST User baru dengan mengisi "email" valid saja
POST User baru dengan email tanpa simbol at '@'
POST User baru dengan email tanpa simbol dot '.' setelah nama provider email
POST User baru dengan jumlah karakter "firstName" < 2
POST User baru dengan jumlah karakter "firstName" > 50
POST User baru dengan jumlah karakter "lastName" < 2
POST User baru dengan jumlah karakter "lastName" > 50
POST User baru dengan data "firstName" memiliki karakter numerik
POST User baru dengan data "lastName" memiliki karakter numerik
POST User baru dengan hanya mengisi "firstName" valid dan "lastName" valid saja
POST User baru dengan hanya mengisi "firstName" valid dan "email" valid saja
POST User baru dengan hanya mengisi "lastName" valid dan "email" valid saja
POST User baru dengan "title" di luar daftar enum title yang diizinkan
POST User baru dengan "gender" di luar daftar enum gender yang diizinkan
POST User baru dengan dateOfBirth < 1 Januari 1900
PUT User dengan ID yang tidak terdaftar
PUT User tanpa menyertakan User ID pada bagian URL request endpoint
PUT User dengan jumlah karakter "firstName" < 2
PUT User dengan jumlah karakter "firstName" > 50
PUT User dengan jumlah karakter "lastName" < 2
PUT User dengan jumlah karakter "lastName" > 50
PUT User dengan "title" di luar enum title yang diizinkan
PUT User dengan "gender" di luar enum title yang diizinkan
PUT User dengan mengisi "email" baru menggantikan email yang sudah ada
PUT User dengan dateOfBirth < 1 Januari 1900
PUT User dengan picture URL baru tanpa format awalan URL https://
PUT User dengan jumlah karakter "street" < 5 pada objek location
PUT User dengan jumlah karakter "street" > 100 pada objek location
PUT User dengan jumlah karakter "city" < 2 pada objek location
PUT User dengan jumlah karakter "city" > 30 pada objek location
PUT User dengan jumlah karakter "state" < 2 pada objek location
PUT User dengan jumlah karakter "state" > 30 pada objek location
PUT User dengan jumlah karakter "country" < 2 pada objek location
PUT User dengan jumlah karakter "country" > 30 pada objek location
DELETE User dengan ID yang tidak terdaftar
Tidak menyertakan parameter APP_ID pada header request
Menyertakan parameter APP_ID yang tidak tersedia (mengganti karakter terakhir dengan huruf baru"
Melakukan get User dengan struktur endpoint {{baseURL}}/user/"60d0fe4f5311236168a109fc"
Mengakses URL request endpoint yang tidak tersedia pada dokumentasi (misal: {{baseURL}}/user/creates)
```

## Author
* Muhammad Rafi Farhan - NIM. 211524054
* Rachmat Purwa Saputra - NIM. 211524054
* Reihan Hadi Fauzan - NIM. 211524058

Mahasiswa Kelas 3B Program Studi DIV-Teknik Informatika<br>
Jurusan Teknik Komputer dan Informatika<br>
Politeknik Negeri Bandung<br>
(C) Mei 2024

## References
Berikut merupakan daftar resource yang dapat dipelajari lebih lanjut terkait pengujian API