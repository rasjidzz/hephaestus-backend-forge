# Posttest - Database, SQL, JPA, Hibernate, Flyway & Query Relationship

## Objective

Posttest ini digunakan untuk mengukur pemahaman peserta setelah mempelajari Database, SQL, JPA, Hibernate, Flyway, dan Query Relationship.

1. Apa perbedaan in-memory Map dan database?

Jawaban:

```text
Data yang disimpan di in memory map tidak akan tersimpan secara permanen, data tersebut akan ke reset saat aplikasi di re run. Sedangkan data yang disimpan di database akan tersimpan secara permanen kecuali ada kesalahan pada pengelolaan database.
```

2. Kenapa database diperlukan pada aplikasi backend production?

Jawaban:

```text
Karena data yang digunakan di level production membutuhkan penyimpanan data yang persistent, konsisten, dan aman. Database memungkinkan data tetap tersimpan meskipun aplikasi di-restart atau server mengalami gangguan.
```

3. Apa fungsi SELECT, INSERT, UPDATE, dan DELETE pada SQL?

Jawaban:

```text
Select -> Digunakan untuk mengambil data dari database.
Insert -> Digunakan untuk menambah data di database.
Update -> Digunakan untuk mengubah data di database.
Delete -> Digunakan untuk menghapus data dari database.
```

4. Apa fungsi WHERE dan ILIKE di PostgreSQL?

Jawaban:

```text
Untuk melakukan filtering data di databse.
```

5. Apa itu primary key dan foreign key?

Jawaban:

```text
Primary Key berfungsi untuk identitas unik di tabel tersebut. Sedangkan foreign key adalah atribut di suatu tabel yang berfungsi untuk menghubungkan tabel a dengan tabel b.
```

6. Apa itu JPA dan Hibernate, serta apa perbedaannya?

Jawaban:

```text
JPA adalah standar yang dijelaskan di java untuk mengelola data ke database menggunakan konsep ORM. Hibernate adalah tools untuk mengimplementasi dari JPA itu sendiri.
```

7. Apa itu Entity dan apa fungsi anotasi `@Entity`, `@Id`, serta `@GeneratedValue`?

Jawaban:

```text
@Entity adalah anotasi pada java JPA untuk mendeklarasikan suatu class menjadi sebuah tabel di database.
@Id adalah anotasi pada java JPA untuk mendeklarasikan suatu attribut menjadi ID di sebuah tabel di database.
@GeneratedValue adalah sebuah anotasi pada java JPA untuk menentukan sebuah attribut di sebuah class itu akan tergenerate automatis valuenya.
```

8. Apa fungsi `@Table` dan `@Column`?

Jawaban:

```text
@Table adalah anotasi pada Java JPA untuk menentukan nama tabel di database yang akan dipetakan dengan class entity.

@Column adalah anotasi untuk memetakan atribut class ke kolom di tabel database.
```

9. Apa itu Repository dan apa manfaat `JpaRepository`?

Jawaban:

```text
Repository adalah komponen dalam spring yang berfungsi sebagai jembatan antara controller dan database, antara backend dengan database. Khususnya untuk CRUD (create, read, update, delete)

JPA adalah interface yang menyediakan method CRUD automatis tanpa perlu menulis query SQL.
```

10. Apa itu derived query method? Berikan contoh method untuk mencari customer berdasarkan email.

Jawaban:

```text
Derived query method adalah metode pada Spring Data JPA yang secara otomatis dibuat berdasarkan nama method tersebut, tanpa perlu menulis query SQL atau JPQL secara manual. Spring akan menerjemahkan nama method menjadi query yang sesuai.
```

11. Apa fungsi `@Query`? Jelaskan perbedaan JPQL dan native query.

Jawaban:

```text
@Query digunakan untuk menuliskan query secara manual di dalam repository ketika query yang dibutuhkan tidak bisa dibuat dengan derived query method. Dengan @Query, kita bisa menggunakan JPQL maupun native SQL
```

12. Apa itu Flyway dan kenapa database migration penting?

Jawaban:

```text
Flyway adalah tool untuk mengelola dan melakukan versioning pada database schema melalui proses migration.
```

13. Apa maksud penamaan file migration seperti `V1__create_customers_table.sql`? Kenapa migration lama sebaiknya tidak diubah setelah dijalankan?

Jawaban:

```text
V1 -> Versioningnya.
Create_customer_table -> deskrpisi dari perubahan yang dilakukan.

Migration lama sebaiknya tidak diubah setelah dijalankan karena Flyway akan mencatat checksum (jejak/validasi isi file).
```

14. Jelaskan relationship one-to-many dan many-to-one dengan contoh Customer dan Order.

Jawaban:

```text
One to many adalah satu class x bisa memiliki banyak class y. Contohnya, Satu customer bisa memiliki banyak order. Jadi relasinya adalah one to many.

Many to one adalah banyak class y bisa merujuk ke satu class x. Contohnya, Banyak order bisa hanya dimiliki oleh satu class Customer.
```

15. Apa fungsi `@ManyToOne`, `@OneToMany`, dan `@JoinColumn`?

Jawaban:

```text
@ManyToOne digunakan untuk mendefinisikan hubungan banyak ke satu (many-to-one) antara entity. Artinya, banyak data pada satu entity dapat berelasi dengan satu data pada entity lain.

@OneToMany digunakan untuk mendefinisikan hubungan satu ke banyak (one-to-many). Artinya, satu data pada entity dapat memiliki banyak data terkait pada entity lain.

@JoinColumn digunakan untuk menentukan kolom foreign key pada tabel yang digunakan untuk menghubungkan relasi antar entity. Biasanya digunakan bersamaan dengan @ManyToOne atau @OneToOne.
```

16. Apa perbedaan lazy loading dan eager loading? Kenapa `FetchType.LAZY` sering lebih aman sebagai default?

Jawaban:

```text
@ManyToOne digunakan untuk mendefinisikan hubungan banyak ke satu (many-to-one) antara entity. Artinya, banyak data pada satu entity dapat berelasi dengan satu data pada entity lain.

@OneToMany digunakan untuk mendefinisikan hubungan satu ke banyak (one-to-many). Artinya, satu data pada entity dapat memiliki banyak data terkait pada entity lain.

@JoinColumn digunakan untuk menentukan kolom foreign key pada tabel yang digunakan untuk menghubungkan relasi antar entity. Biasanya digunakan bersamaan dengan @ManyToOne atau @OneToOne.
```

17. Apa itu SQL join? Jelaskan perbedaan `INNER JOIN` dan `LEFT JOIN`.

Jawaban:

```text
SQL join adalah operasi pada database yang digunakan untuk menggabungkan data dari dua atau lebih tabel berdasarkan hubungan tertentu (biasanya melalui foreign key).

INNER JOIN hanya mengambil data yang memiliki pasangan di kedua tabel, Sedangkan LEFT JOIN mengambil semua data dari tabel kiri, dan data yang cocok dari tabel kanan. Jika tidak ada pasangan, maka akan bernilai NULL.s
```

18. Apa itu N+1 query problem dan bagaimana cara sederhana menguranginya?

Jawaban:

```text
N+1 query problem adalah kondisi ketika aplikasi menjalankan 1 query untuk mengambil data utama, lalu menjalankan banyak query tambahan (N query) untuk mengambil data relasi satu per satu, sehingga menyebabkan performa yang buruk.
```

19. Kenapa Entity sebaiknya tidak langsung dikembalikan sebagai API response? Apa manfaat DTO?

Jawaban:

```text
Entity sebaiknya tidak langsung dikembalikan sebagai API response karena dapat mengekspos struktur database secara langsung yang berisiko membocorkan data sensitif.

DTO (Data Transfer Object) digunakan untuk membatasi dan mengontrol data yang dikirim ke client. Manfaat DTO:
- Menyembunyikan field yang tidak diperlukan atau sensitif
- Menghindari masalah serialisasi
- Membuat response API lebih rapi dan sesuai kebutuhan
```

20. Apa fungsi `@Transactional` dan kapan menggunakan `@Transactional(readOnly = true)`?

Jawaban:

```text
@Transactional berfungsi untuk memastikan bahwa serangkaian operasi database dijalankan dalam satu transaksi (atomic). Jika terjadi error, maka semua perubahan akan di-rollback, sehingga menjaga konsistensi data.

@Transactional(readOnly = true) digunakan untuk operasi yang hanya membaca data tanpa melakukan perubahan. Ini dapat meningkatkan performa karena database tahu bahwa transaksi tersebut tidak akan melakukan update, insert, atau delete.
```

## Reflection

Apa 3 hal utama yang kamu pahami hari ini?

```text
1. Menyambungkan database ke backend.
2. Membuat transaksi database dari backend java.
3. Menentukan primary key dan foreign key dari setiap tabel
```

Apa 2 hal yang masih membingungkan?

```text
1. RBAC akses kalau di resource level
2. Cara cepat menjelaskan relasi antar di springboot
```

Apa 1 pertanyaan untuk mentor?

```text
Cara agar bisa berfikir dengan cepat untuk mendesain sistem yang scalable.
```
