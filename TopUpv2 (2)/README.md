# Aplikasi Top Up Game Online

## Gambaran Umum
Aplikasi Top Up Game Online adalah program berbasis antarmuka grafis (GUI) menggunakan Java yang memungkinkan pengguna untuk mengelola dan memproses top-up game populer seperti Mobile Legends, Free Fire, dan Robux. Aplikasi ini juga dilengkapi panel admin untuk mengelola transaksi serta fitur pendaftaran akun pengguna.

---

## Fitur
### 1. **Menu Utama**
Menu utama menyediakan opsi berikut:
- **Top Up**: Mengakses menu top-up untuk memilih game dan memproses top-up.
- **Cek TopUp**: Melihat status semua transaksi.
- **Admin Only**: Mengakses panel admin untuk memperbarui status transaksi.
- **Buat Akun**: Membuat akun pengguna baru.

### 2. **Menu Top Up**
- Pengguna dapat memilih game (Mobile Legends, Free Fire, Robux).
- Menyediakan kolom untuk memasukkan ID pengguna, nominal top-up, metode pembayaran, serta mengunggah gambar bukti pembayaran.
- Memperbarui saldo pengguna setelah pembayaran berhasil.

### 3. **Menu Cek TopUp**
- Menampilkan semua transaksi dalam format tabel.
- Menunjukkan detail seperti ID, game, nominal, metode pembayaran, status, dan gambar yang diunggah.

### 4. **Panel Admin**
- Menampilkan semua transaksi.
- Admin dapat memperbarui status transaksi menjadi "Sedang Diproses" atau "Selesai".

### 5. **Pembuatan Akun**
- Memungkinkan pengguna membuat akun baru dengan memasukkan ID pengguna 13 digit, nama, dan alamat email.

---

## Struktur Kode
### 1. **Kelas Utama: TopUpGameOnline**
- Titik masuk utama aplikasi.
- Mengelola menu utama dan logika navigasi antar menu.

### 2. **Kelas User**
Merepresentasikan akun pengguna dengan atribut:
- `id`: ID unik pengguna dengan panjang 13 digit.
- `name`: Nama pengguna.
- `email`: Alamat email pengguna.
- `mlBalance`, `ffBalance`, `robuxBalance`: Saldo untuk Mobile Legends, Free Fire, dan Robux.

### 3. **Kelas TableCellRenderer**
Renderer khusus untuk menampilkan gambar yang diunggah dalam tabel transaksi.

---

## Dependensi
- **Java Swing**: Untuk komponen GUI dan penanganan event.
- **javax.swing.table**: Untuk pengelolaan dan rendering tabel.

---

## Cara Menjalankan
1. Pastikan Java JDK telah terinstal di perangkat Anda.
2. Kompilasi program dengan perintah berikut:
   ```
   javac -d . TopUpGameOnline.java
   ```
3. Jalankan aplikasi dengan perintah:
   ```
   java org.example.topupgameonline.TopUpGameOnline
   ```

---

## Petunjuk Penggunaan
1. Jalankan aplikasi.
2. Pilih opsi dari menu utama.
3. Ikuti instruksi untuk:
   - Top-up: Masukkan ID pengguna, nominal top-up, pilih metode pembayaran, dan unggah gambar bukti pembayaran.
   - Cek transaksi: Lihat detail semua transaksi.
   - Aksi admin: Perbarui status transaksi.
   - Pembuatan akun: Masukkan detail untuk membuat akun baru.

---

## Pengembangan di Masa Depan
- Menambahkan integrasi database untuk penyimpanan data secara permanen.
- Mengimplementasikan autentikasi untuk akses admin.
- Menambahkan validasi format email dan pengecekan ID pengguna yang duplikat.
- Meningkatkan antarmuka pengguna dengan pustaka gaya modern.

---

