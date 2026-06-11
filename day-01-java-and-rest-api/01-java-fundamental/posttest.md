# Posttest - Java Fundamental

Jawab pertanyaan berikut setelah membaca materi dan mengerjakan exercise Java Fundamental.

### 1. Apa itu variable?

Jawaban:

```text
Variabel adalah salah satu attribute yang ada di dunia programming, variabel digunakan untuk menyimpan sebuah value. Variabel bisa dinamis dan bisa statis.

Contoh -> String nama = "Risjad"
```

### 2. Apa perbedaan String, int, Long, dan boolean?

Jawaban:

```text
String adalah tipe data yang digunakan untuk Text/kata/kalimat.

int atau Integer adalah tipe data yang digunakan untuk angka bilangan bulat tanpa desimal.

Long adalah tipe data yang digunakan untuk angka dengan panjang digit lebih panjang dari Integer.

Boolean atau boolean adalah tipe data yang digunakan untuk menentukan true or false atau 1 or 0
```

### 3. Kenapa Java menggunakan camelCase untuk variable?

Jawaban:

```text
Karena itu termasuk dalam naming convention agar kode lebih konsisten dan mudah dibaca.

Contoh -> String phoneNumber
```

### 4. Apa perbedaan class dan object?

Jawaban:

```text
Class adalah blueprint / template untuk membuat objek.

Contoh -> Class Mobil

Object adalah hasil nyata dari sebuah class, hasil implementasi dari sebuah class.

Contoh -> Class Mobil -> Object mobil1 -> Mobil mobil1 = new Mobil()
```

### 5. Apa itu field?

Jawaban:

```text
Field dalam sebuah class adalah data / properti yang dimiliki oleh object / class.

Contoh -> int umur, String phoneNumber
```

### 6. Apa itu method?

Jawaban:

```text
Method adalah kumpulan instruksi yang disatukan dalam sebuah fungsi atau procedure.
Method yang mereturn sebuah value disebut fungsi, dan yang tidak mereturn value disebut procedure.
```

### 7. Apa itu parameter?

Jawaban:

```text
Parameter adalah data atau nilai yang digunakan atau dibutuhkan didalam sebuah method mau itu fungsi atau procedure agar method tersebut bisa bekerja dengan data tertentu.
```

### 8. Apa itu return value?

Jawaban:

```text
Value yang akan di return dari sebuah fungsi setelah melalui proses pengolahan data. Sebuah method yang mereturn sebuah value disebut juga fungsi/function.
```

### 9. Apa fungsi constructor?

Jawaban:

```text
 Constructor adalah method yang pertamakali dijalankan ketika pembuatan object dari sebuah class.
```

### 10. Apa fungsi `this`?

Jawaban:

```text
This pada java dan object oriented programming adalah untuk menunjuk dan merujuk ke object saat ini dan attribute yang ada didalam class atau object tersebut.
```

### 11. Kenapa field dibuat private?

Jawaban:

```text
Agar sebuah data field atau attribut lebih secure dan tidak bisa diakses diluar dari class tersebut. Dibuat private karena alasan keamanan, kontrol, dan enkapsulasi. Agar
```

### 12. Apa fungsi getter dan setter?

Jawaban:

```text
Getter dalah sebuah fungsi untuk mereturn dan mengakses sebuah atttribut / field.

Setter adalah sebuah method untuk mengassign sebuah attribut / field dari sebuah class, dengan menerima parameter input dari user.
```

### 13. Apa itu encapsulation?

Jawaban:

```text
Enkapsulasi adalah pembungkusan. Pembukusan data dan metode dalam sebua class, agar membatasi akses langsung ke data tersebut.
```

### 14. Apa perbedaan List dan Map?

Jawaban:

```text
List adalah sebuah kumpulan data yang disimpan secara berurutan dan diakses menggunakan posisi indeks.

Map adalah sebuah kumpulan data untuk menyimpan data yang terdiri dari key dan value.

- List disimpan secara berurutan, sedangkan Map menggunakan Key dan Value.
- struktur dari list sederhana, kalau map strukturnya pasangan data key dan value
```

### 15. Kenapa CustomerService menggunakan Map<Long, Customer>?

Jawaban:

```text
Karena customerStorage menyimpan data disimpan berdasarkan ID customer. Kunci Long berfungsi sebagai identifier unik, sedangkan nilai Customer adalah objek detailnya.
```

### 16. Kenapa getAllCustomers mengembalikan List<Customer>?

Jawaban:

```text
getAllCustomers adalah fungsi yang mengembalikan isi dari customerStorage yang tadi sudah kita isi dengan banya Customer.
```

### 17. Apa itu interface?

Jawaban:

```text
Interface adalah kontrak fungsi apa saja yang harus ada di kelas yang implement inteface.
```

### 18. Apa perbedaan interface dan abstract class?

Jawaban:

```text
Interface -> Kontrak fungsi apa saja yang harus ada di kelas yang implement interface.

Abstract -> Membuat blueprint/template dari sebuah class atau object.
```

### 19. Dari exercise, jelaskan flow createCustomer.

Jawaban:

```text
fungsi createCustomer ada didalam service CustomerService.
1. Inisiasi Object dari class CustomerService.
2. Di main akan memanggil fungsi createCustomer yang dimiliki class CustomerService.
3. Fungsi akan mengirimkan parameter fullName, email, phoneNumber.
4. Fungsi pertamakali akan mengecek apakah fullName kosong atau tidak, jika kosong maka error dan jika tidak maka lanjut.
5. Lalu akan membuat satu object Customer baru memanggil constructor dengan parameter fullName, email, phoneNumber.
6. lalu object Customer tersebut kaan dimasukkan kedalam Map customerStorage.
7. Fungsi mengembalikan object Customer yang baru dibuat.
```

### 20. Bagian mana yang paling sulit?

Jawaban:

```text
Tidak ada yang susah jika kita ingin belajar, tapi yang lumayan menantang adalah ketika struktur folder kita tidak benar dan mengakibatkan error di package javanya.
```
