# Posttest - Backend Quality: Testing, Peer Code Review & Observability

## Objective

Posttest ini digunakan untuk mengukur pemahaman peserta setelah mempelajari testing mindset, unit testing, peer code review, structured logging, correlation ID, dan PII safety.

## Instructions

- Jawab dengan singkat dan jelas.
- Total pertanyaan: 10.

1. Kenapa testing disebut risk reduction?

Jawaban:

```text
Karena dengan testing, kita bisa identifikasi celah, bug, atau kegagalan lebih awal. Dengan menemukan dan memperbaiki masalah tersebut sebelum produk atau sistem dirilis.
```

2. Apa perbedaan working code dan trusted code?

Jawaban:

```text
Working code yang penting kode bekerja sesuai dengan apa yang diharapkan. Sedangkan Trusted Code menjamin keamanan, keandalan, dan bebas dari kerentanan. Jadi trusted code tidak hanya bekerja sesuai dengan harapan, tapi juga aman, dan andal.
```

3. Jelaskan pola Given-When-Then.

Jawaban:

```text
Given when then adalah mendeskripsikan sebuah kondisi sistem.
Given -> Kondisi awal
When -> Ketika ada peristiwa
Then -> Menjelaskan hasil perubahan yang dilihat akibat dari when.
```

4. Kenapa service layer cocok untuk unit test?

Jawaban:

```text
Karena semua logika bisnis biasanya terisolasi di service layer.
```

5. Apa peran JUnit 5 dan Mockito dalam unit test?

Jawaban:

```text
JUnit untuk unit testing automated secara terstruktur di java. Dan Mockito Untuk membuat objek dummy untuk mengisolasi komponen yang sedang di test dari dependencies eksternal.
```

6. Sebutkan 3 test case penting untuk `LoanApplicationService`.

Jawaban:

```text
Tulis jawaban di sini.
```

7. Apa tujuan peer code review?

Jawaban:

```text
Proses dimana code yang ditulis oleh seorang developer, di cek oleh developer lainnya sebelum code tersebut di merge atau di publish.
```

8. Apa itu structured logging dan kenapa penting?

Jawaban:

```text
Log yang dilakukan secara terstruktur agar lebih mudah di analisis, dicari, dan dimanfaatkan dalam sistem monitoring modern.
```

9. Apa fungsi `correlation_id` pada log dan error response?

Jawaban:

```text
Untuk melacak atau trace satu request atau transaksi n to n dalam sistem.
```

10. Sebutkan minimal 5 data yang tidak boleh ditulis mentah di log.

Jawaban:

```text
1. Data pribadi customer PDP
2. Data Sensitif
3. Data keuangan
4. Token dan kredentials
5. Data internal perusahaan
```

## Reflection

Apa 3 hal utama yang kamu pahami hari ini?

```text
1. Logging
2. Unit Testing
3. Mock data
```

Apa 2 hal yang masih membingungkan?

```text
1. Perbedaan urgensi setiap log
2. Cara menentukan tingkat urgensi log
```

Apa 1 hal yang akan kamu cek saat melakukan code review?

```text
Apakah ada data penting yang dikirimkan saat logging
```
