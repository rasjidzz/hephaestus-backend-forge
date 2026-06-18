# Posttest - Authentication, Authorization & RBAC

## Objective

Posttest ini digunakan untuk mengukur pemahaman peserta setelah mempelajari Authentication, Authorization, JWT, RBAC, dan resource-level authorization.

### 1. Apa itu authentication?

Jawaban:

```text
Authentification adalah proses meverifikasi siapa kita.
```

### 2. Apa itu authorization?

Jawaban:

```text
Authorization adalah proses menentukan apa yang boleh kamu lakukan.
```

### 3. Apa perbedaan authentication dan authorization?

Jawaban:

```text
Authentification adalah untuk verifikasi identitas user (kamu siapa ?), dan authorization adalah proses menentukan hak akses user (apa yang boleh kamu lakukan ?)
```

### 4. Kenapa user yang sudah login belum tentu boleh melakukan semua action?

Jawaban:

```text
Untuk membatasi hak akses setiap user yang akan memiliki role nya yang berbeda beda.
```

### 5. Apa itu token-based authentication?

Jawaban:

```text
Metode autentikasi dimana setelah user login, sistem memberikan sebuah token sebagai bukti identitas, dan token tersebut digunakan untuk mengakses sistem tanpa perlu login ulang.
```

### 6. Apa fungsi Authorization header?

Jawaban:

```text
Authorization header digunakan untuk mengirimkan informasi autentikasi (token) dari client ke server agar server bisa memverifikasi identitas user dan menentukan hak aksesnya.
```

### 7. Apa arti Bearer token?

Jawaban:

```text
Bearer token adalah token autentikasi yang digunakan untuk mengakses API atau layanan tertentu, di mana siapa pun yang membawa (bearer) token tersebut dianggap sudah terautentikasi.
```

### 8. Apa itu JWT?

Jawaban:

```text
Format token berbasis json yang digunakan untuk mengirim informasi secara aman antara client dan server. Digunakan untuk authentication dan authorization.
```

### 9. Apa itu claim pada JWT?

Jawaban:

```text
Informasi atau data yang disimpan dalam jwt biasanya tentang user dan token.
```

### 10. Sebutkan 4 claim yang umum ada pada JWT.

Jawaban:

```text
- identitas user
- expired date
- role
- username
```

### 11. Kenapa JWT payload tidak boleh dipercaya sebelum signature divalidasi?

Jawaban:

```text
Karena payloard jwt bisa dibaca dan di modif siapa saja, sehingga harus diverifikasi dulu menggunakan signature untuk memastikan data tersebut valid.
```

### 12. Data apa saja yang tidak boleh disimpan di JWT?

Jawaban:

```text
Data sensitif seperti password, pin, nomor kartu kredit, data rahasia.
```

### 13. Kenapa token perlu expiry?

Jawaban:

```text
Token memiliki expiry date untuk mengurangi risiko penyalahgunaan jika token bocor.
```

### 14. Apa perbedaan access token dan refresh token?

Jawaban:

```text
Access token digunakan mengakses api dan biasanya memiliki active date yang pendek. Refresh token digunakan untuk mendapatkan access token baru tanpa login ulang.
```

### 15. Apa itu RBAC?

Jawaban:

```text
RBAC atau role based access control adalah pembatasan akses sesuai dengan role suatu user. Bukan per orang satu per satu.
```

### 16. Apa perbedaan role dan permission?

Jawaban:

```text
Role adalah sekumpulan hak akses yang diberikan kepada user untuk menentukan apa saja yang boleh dilakukan didalam sistem.

Sedangkan Permisison adalah Izin atau hak akses spesifik yang menentukan aksi apa saja yang diizinkan.
```

### 17. Berikan contoh role dalam loan system.

Jawaban:

```text
1. SuperAdmin
2. Customer
3. Credit Analyst
4. Disbursement Officer
```

### 18. Berikan contoh permission dalam loan system.

Jawaban:

```text
1. View customer data
2. create loan
3. approve loan
4. reject loan
5. update loan status
6. disburse fund
7. upload_document
8. verify document
```

### 19. Kenapa role check saja tidak cukup?

Jawaban:

```text
User dengan role yang sama bisa mengakses data milik user lain yang seharusnya tidak boleh diakses.
```

### 20. Apa itu resource-level authorization?

Jawaban:

```text
Kontrol akses yang menentukan user boleh mengakses resource teretentu bedasarkan kepimilikan atau konteks data tersebut.
```

### 21. Apa itu ownership check?

Jawaban:

```text
Proses memastikan bahwa user hanya dapat mengakses data yang memang dimilikinya.
```

### 22. Apa itu IDOR?

Jawaban:

```text
IDOR (Insecure Direct Object Reference) adalah salah satu jenis kerentanan keamanan (security vulnerability) pada aplikasi web atau API, di mana pengguna bisa mengakses data atau resource milik orang lain hanya dengan memanipulasi parameter (misalnya ID) tanpa otorisasi yang benar.
```

### 23. Bagaimana cara mencegah customer melihat data customer lain?

Jawaban:

```text
Backend melakukan validasi authorization, seperti cek role dan ownership data, tidak hanya dari id request, memastikan setiap resource diverifikasi apakah user berhak mengakses data tersebut.
```

### 24. Kapan menggunakan 401 Unauthorized?

Jawaban:

```text
Ketika user belum terautentikasi atau tidak mengirimkan token yang valid, sehingga server tidak mengenali identitas user.
```

### 25. Kapan menggunakan 403 Forbidden?

Jawaban:

```text
User sudah login, tapi tidak memiliki izin untuk akses resource atau aksi tertentu.
```

### 26. Apa perbedaan 401 dan 403?

Jawaban:

```text
401 unauthorized itu berarti belum login atau token tidak valid, sedangkan 403 forbidden berarti user sudah login tapi tidak miliki hak akses yang benar.
```

### 27. Kenapa error message security tidak boleh terlalu detail?

Jawaban:

```text
Error message dalam sistem keamanan tidak boleh terlalu detail karena bisa memberikan informasi sensitif kepada attacker (penyerang). 
```

### 28. Apa itu principle of least privilege?

Jawaban:

```text
Principle of Least Privilege (Prinsip Hak Akses Minimum) adalah konsep keamanan yang menyatakan bahwa setiap pengguna, sistem, atau aplikasi hanya diberikan hak akses yang benar-benar diperlukan untuk menjalankan tugasnya—tidak lebih.
```

### 29. Kenapa access log penting dalam finance backend?

Jawaban:

```text
Memantau aktivitas user, membantu audit keamanan, mendeteksi penyalahgunaan role, dan memudahkan ketika debugging.
```

### 30. Sebutkan field penting dalam access log.

Jawaban:

```text
- Timestamp
- userid
- endpoint yang diakses
- http method
- status code
- request id
```

### 31. Bagaimana auth requirement ditulis di API contract?

Jawaban:

```text
Auth requirement dalam API contract adalah bagian yang menjelaskan bagaimana cara autentikasi dan otorisasi dilakukan saat mengakses endpoint API.


1. Jenis autentikasi yang digunakan (misalnya: Bearer Token, API Key, Basic Auth)
2. Lokasi pengiriman kredensial (misalnya di header, query, atau body)
3. Format penggunaan autentikasi
4. Apakah endpoint membutuhkan autentikasi atau tidak

```

### 32. Bagaimana Swagger/OpenAPI membantu dokumentasi endpoint yang protected?

Jawaban:

```text
Swagger/OpenAPI membantu dokumentasi endpoint yang protected dengan menyediakan cara standar untuk mendefinisikan dan menampilkan kebutuhan autentikasi secara jelas dan terstruktur.
```

### 33. Apa risiko jika role dikirim dari client lalu langsung dipercaya?

Jawaban:

```text
Risiko jika role (misalnya: admin, user, dll) dikirim dari client lalu langsung dipercaya adalah sangat berbahaya karena client bisa dengan mudah memanipulasi data tersebut.
```

### 34. Apa risiko token tanpa expiry?

Jawaban:

```text
Risiko token tanpa expiry (masa berlaku) adalah token tersebut dapat digunakan selamanya selama tidak dicabut, sehingga meningkatkan risiko keamanan secara signifikan.
```

### 35. Bagian mana yang paling sulit dari Day 4?

Jawaban:

```text
Lumayan challenging semua, RBAC, autentifikasi, dan authorization.
```

## Reflection

Apa 3 hal utama yang kamu pahami hari ini?

```text
1. Authentication
2. Authorization
3. RBAC
```

Apa 2 hal yang masih membingungkan?

```text
1. RBAC
2. Authorization
```

Apa 1 pertanyaan untuk mentor?

```text
Cara belajr implementasi rbac yang benar dan pemetaan rolenya
```
