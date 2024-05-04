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
Agar dapat menjalankan proyek pengujian API otomatis ini pada perangkat Anda, :
1. Perankat sudah terinstall Java JDK versi 17 atau yang lebih tinggi.
2. Perangkat sudah terinstall Maven versi 3.8.3 atau yang lebih tinggi.
3. Memiliki koneksi internet yang stabil saat mengunduh dependencies Maven.

### Installation
1. Untuh mengunduh repository proyek ini, jalankan perintah berikut di dalam terminal/CLI Anda:

   ```bash
   git clone https://github.com/MuhammadRafiFarhan/API-Testing.git
   ```
2. Buka folder root proyek ini bernama `api` di dalam text editor Visual Studio Code.
3. Buka terminal di dalam text editor tersebut, lalu jalankan perintah berikut untuk menginstall dependencies Maven dari file `api/pom.xml`:

   ```bash
   mvn clean install
   ```
4. Tunggu hingga proses installasi dependencies selesai. Setelah itu, proyek siap untuk dijalankan.