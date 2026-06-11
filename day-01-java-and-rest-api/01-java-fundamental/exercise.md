# Exercise - Java Fundamental

## Objective

Peserta dapat membuat program customer management sederhana menggunakan class, object, variable, method, constructor, getter/setter, `List`/`Map`, dan encapsulation.

## Important

- Jangan gunakan Spring Boot.
- Jangan gunakan database.
- Jalankan program dari class `Main`.

## Case

Buat program sederhana untuk mengelola data customer.

## Required Classes

1. `Customer`
2. `CustomerService`
3. `Main`

## A. Customer Class

Package:

```text
com.example.training.model
```

Fields:

- `private Long id`
- `private String fullName`
- `private String email`
- `private String phoneNumber`

Requirements:

- Buat constructor dengan semua fields.
- Buat getter dan setter untuk semua fields.
- Buat method `getDisplayName()`.
- `getDisplayName()` mengembalikan `"Customer: " + fullName`.

## B. CustomerService Class

Package:

```text
com.example.training.service
```

Fields:

```java
private Map<Long, Customer> customerStorage = new HashMap<>();
private Long sequence = 1L;
```

Methods:

### 1. createCustomer(String fullName, String email, String phoneNumber)

Ketentuan:

- Buat object `Customer` baru.
- Generate id dari `sequence`.
- Naikkan nilai `sequence`.
- Simpan customer ke `customerStorage`.
- Return customer yang berhasil dibuat.

### 2. getCustomerById(Long id)

Ketentuan:

- Return customer dari `customerStorage` berdasarkan id.

### 3. getAllCustomers()

Ketentuan:

- Return `List<Customer>`.
- Gunakan `new ArrayList<>(customerStorage.values())`.

## C. Main Class

Package:

```text
com.example.training
```

Di dalam `public static void main(String[] args)`:

- Buat object `CustomerService`.
- Buat minimal 2 customer.
- Print semua customer.
- Ambil customer berdasarkan ID.
- Print `getDisplayName()`.

## Expected Output

```text
All Customers:
1 - Budi Santoso - budi@mail.com - 08123456789
2 - Siti Aminah - siti@mail.com - 08222222222

Customer Detail:
Customer: Budi Santoso
```

## Suggested Structure

```text
src/main/java/com/example/training/
├── Main.java
├── model/
│   └── Customer.java
└── service/
    └── CustomerService.java
```

## Acceptance Criteria

- [ ] Program bisa berjalan dari `Main`.
- [ ] Field pada `Customer` menggunakan `private`.
- [ ] `Customer` memiliki constructor.
- [ ] `Customer` memiliki getter dan setter.
- [ ] `Customer` memiliki method `getDisplayName()`.
- [ ] `CustomerService` menggunakan `Map<Long, Customer>`.
- [ ] Method `createCustomer` berjalan dengan benar.
- [ ] Method `getCustomerById` berjalan dengan benar.
- [ ] Method `getAllCustomers` berjalan dengan benar.
- [ ] ID auto-increment dimulai dari 1.
- [ ] Tidak menggunakan Spring Boot.
- [ ] Tidak menggunakan database.

## Optional Challenge

Jika tugas utama sudah selesai, coba tambahkan:

- Method `updateCustomerEmail(Long id, String email)`.
- Method `deleteCustomer(Long id)`.
- Validasi sederhana agar `fullName` tidak boleh kosong.
