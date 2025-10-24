Nama         : Muhammad Zaim Pahlevi<br>
NIM          : H1D023073<br>
Shift Baru   : E<br>
Shift Asal   : E<br>

<img src= "rec/recording.gif" height=600>

# Alur Data Aplikasi Ipswich Town FC

Dokumen ini menjelaskan alur arsitektur data dalam aplikasi, mulai dari pengambilan data dari API hingga penyajiannya di antarmuka pengguna (UI). Aplikasi ini menggunakan arsitektur modern yang umum diadopsi dalam pengembangan Android, yang memisahkan antara lapisan data, logika bisnis, dan UI.

## Komponen Arsitektur Utama

1.  **Retrofit & OkHttp (Lapisan Jaringan)**: Bertanggung jawab untuk melakukan pemanggilan HTTP ke API eksternal.
2.  **Repository (Lapisan Data)**: Bertindak sebagai sumber data tunggal (Single Source of Truth) bagi aplikasi. Ini mengabstraksi asal data (jaringan atau database lokal) dari seluruh aplikasi.
3.  **ViewModel (Lapisan UI/Logic)**: Menjembatani lapisan data dan UI. ViewModel menyimpan dan mengelola data terkait UI serta menjaga data tetap ada saat terjadi perubahan konfigurasi (seperti rotasi layar).
4.  **LiveData (Lapisan UI/Logic)**: Kelas penyimpan data yang dapat diamati dan sadar akan siklus hidup (lifecycle-aware). Ini memastikan UI selalu menampilkan data terbaru tanpa menyebabkan kebocoran memori (memory leaks).
5.  **Activity (Lapisan UI)**: Bertanggung jawab untuk menampilkan data ke pengguna dan menangani interaksi pengguna.

---

## Alur Proses Data

Berikut adalah alur data langkah demi langkah saat aplikasi mengambil dan menampilkan informasi tim:

### 1. Pemanggilan API (Network Layer)

-   **Inisialisasi**: `RetrofitClient` mengonfigurasi instance Retrofit dengan URL dasar API (`https://api.football-data.org/v4/`) dan sebuah `HttpLoggingInterceptor` untuk memudahkan proses debug dengan mencatat pemanggilan jaringan ke Logcat.
-   **Definisi Endpoint**: Interface `FootballAPIService` mendefinisikan endpoint yang akan diakses. Dalam kasus ini, fungsi `getTeamData()` memetakan ke permintaan `GET /teams/{id}`.
-   **Otentikasi**: Token API (`X-Auth-Token`) secara otomatis ditambahkan ke setiap header permintaan oleh `TeamRepository` saat memanggil service.

### 2. Pengambilan Data (Data Layer)

-   **Permintaan Data**: `TeamRepository` adalah satu-satunya kelas yang berkomunikasi langsung dengan `FootballAPIService`.
-   **Eksekusi Asinkron**: Fungsi `getIpswichTownData()` di dalam repositori adalah sebuah `suspend function`. Ini berarti pemanggilan jaringan dieksekusi di dalam Coroutine, sehingga tidak memblokir thread utama (UI thread) dan menjaga aplikasi tetap responsif.
-   **Penanganan Hasil**: Repositori memeriksa apakah respons dari API berhasil. Jika ya, ia mengembalikan objek `TeamResponse`. Jika terjadi kegagalan jaringan atau kesalahan lainnya (`IOException`), ia akan mengembalikan `null`.

### 3. Pemrosesan & Penyimpanan Data (ViewModel)

-   **Inisialisasi ViewModel**: Saat sebuah Activity (misalnya `CoachActivity` atau `SquadActivity`) dibuat, ia menginisialisasi `TeamViewModel` menggunakan `TeamViewModelFactory`. Factory ini diperlukan karena `TeamViewModel` memiliki dependensi pada `TeamRepository`.
-   **Memicu Pengambilan Data**: Di dalam blok `init` dari `TeamViewModel`, sebuah Coroutine diluncurkan melalui `viewModelScope`. Coroutine ini memanggil `repository.getIpswichTownData()` untuk memulai proses pengambilan data.
-   **Menyimpan Data di LiveData**: Setelah data berhasil diterima dari repositori, hasilnya diposting ke objek `_teamData` (sebuah `MutableLiveData`).
-   **Mengekspos LiveData**: ViewModel hanya mengekspos versi `LiveData` (read-only) dari `_teamData` ke UI. Ini adalah praktik terbaik untuk memastikan hanya ViewModel yang dapat memodifikasi data.

### 4. Penyajian Data di UI (UI Layer)

-   **Mengamati (Observing) Data**: Activity (misalnya `SquadActivity`) menggunakan `viewModel.teamData.observe(this) { ... }` untuk mengamati perubahan pada data.
-   **Pembaruan UI Otomatis**: Setiap kali nilai di dalam `teamData` LiveData berubah (yaitu, setelah data API berhasil diambil), blok `observe` akan dieksekusi secara otomatis.
-   **Menampilkan Data**: Di dalam blok `observe`, UI diperbarui:
    -   Di `SquadActivity`, data skuad (`teamData.squad`) dikirim ke `PlayerAdapter`.
    -   `RecyclerView` kemudian menggunakan adapter ini untuk menampilkan daftar pemain, di mana setiap kartu pemain diberi warna sesuai dengan kategorisasi posisi.
    -   Di `CoachActivity`, detail pelatih (`teamData.coach`) diambil dan ditampilkan langsung di `TextView` dan `ImageView` yang sesuai.

Dengan alur ini, setiap komponen memiliki tanggung jawab yang jelas, membuat kode lebih mudah diuji, dipelihara, dan diskalakan.

