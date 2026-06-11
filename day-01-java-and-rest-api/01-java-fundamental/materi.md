# Materi - Java Fundamental

## 1. Kenapa Backend Engineer Perlu Paham Java?

Spring Boot dibangun di atas Java. Saat membuat REST API dengan Spring Boot, kita tetap menulis class, variable, method, constructor, object, `List`, dan `Map`.

Sebelum memakai annotation seperti `@RestController`, `@Service`, atau `@RequestBody`, peserta perlu paham dulu dasar Java. Annotation membantu Spring membaca kode kita, tetapi logic program tetap ditulis menggunakan Java.

## 2. Basic Java Program Structure

Contoh program Java paling sederhana:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Backend");
    }
}
```

Penjelasan:

- `public class Main` adalah deklarasi class bernama `Main`.
- `public static void main(String[] args)` adalah method utama yang pertama kali dijalankan.
- `String[] args` adalah parameter untuk menerima argument dari command line.
- `System.out.println` digunakan untuk mencetak text ke console.

## 3. Variable

Variable adalah tempat untuk menyimpan value.

Contoh:

```java
String fullName = "Budi Santoso";
int age = 20;
boolean active = true;
```

Pada contoh di atas:

- `String fullName` adalah declaration.
- `"Budi Santoso"` adalah value.
- Tanda `=` digunakan untuk assignment.

Variable bisa diubah nilainya jika diperlukan.

```java
int age = 20;
age = 21;
```

Di Java, nama variable menggunakan camelCase.

Contoh:

- `fullName`
- `phoneNumber`
- `monthlyIncome`

## 4. Data Types

Data type menentukan jenis value yang dapat disimpan.

| Type | Use For | Example |
| --- | --- | --- |
| `String` | Text | `"Budi Santoso"` |
| `int` | Number sederhana | `20` |
| `long` / `Long` | Number lebih besar, sering untuk id | `1L` |
| `double` | Number desimal | `10.5` |
| `boolean` / `Boolean` | Benar atau salah | `true` |

Primitive type seperti `int`, `long`, dan `boolean` menyimpan value sederhana. Wrapper type seperti `Integer`, `Long`, dan `Boolean` adalah versi object dari primitive.

Untuk tahap awal, cukup ingat:

- Gunakan `String` untuk text.
- Gunakan `Long` untuk id.
- Gunakan `boolean` atau `Boolean` untuk nilai true/false.

## 5. String

`String` digunakan untuk menyimpan text.

Contoh:

```java
String firstName = "Budi";
String lastName = "Santoso";
String fullName = firstName + " " + lastName;
```

Operator `+` dapat digunakan untuk menggabungkan text.

## 6. Method

Method adalah behavior atau aksi di dalam class.

Method tanpa parameter:

```java
public void printHello() {
    System.out.println("Hello");
}
```

Method dengan parameter:

```java
public void printName(String name) {
    System.out.println(name);
}
```

Method dengan return value:

```java
public String getDisplayName(String fullName) {
    return "Customer: " + fullName;
}
```

Penjelasan:

- `void` berarti method tidak mengembalikan value.
- Parameter adalah input untuk method.
- `return` digunakan untuk mengembalikan value dari method.

## 7. Class

Class adalah blueprint atau cetakan untuk membuat object.

Contoh:

```java
public class Customer {
    private Long id;
    private String fullName;
    private String email;
}
```

Class `Customer` menjelaskan bahwa customer memiliki `id`, `fullName`, dan `email`.

## 8. Object

Object adalah instance atau hasil nyata dari class.

Contoh:

```java
Customer customer = new Customer();
```

`Customer` adalah class. `customer` adalah object yang dibuat dari class tersebut.

## 9. Field / Attribute

Field adalah data yang disimpan di dalam class.

Contoh:

```java
private Long id;
private String fullName;
private String email;
```

Field biasanya dibuat `private` agar tidak bisa diubah sembarangan dari luar class.

## 10. Constructor

Constructor adalah method khusus yang dipanggil saat object dibuat.

Contoh:

```java
public class Customer {
    private Long id;
    private String fullName;

    public Customer() {
    }

    public Customer(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
```

`Customer()` adalah default constructor. `Customer(Long id, String fullName)` adalah constructor dengan parameter.

Keyword `this` menunjuk ke object saat ini. Contoh `this.fullName = fullName` berarti field `fullName` milik object diisi dari parameter `fullName`.

## 11. Access Modifier

Access modifier mengatur siapa yang boleh mengakses class, field, atau method.

| Modifier | Arti Sederhana |
| --- | --- |
| `public` | Bisa diakses dari mana saja |
| `private` | Hanya bisa diakses dari class itu sendiri |
| `protected` | Bisa diakses dari package yang sama dan class turunan |
| default/package-private | Bisa diakses dari package yang sama |

Untuk pemula, pola yang sering dipakai adalah field dibuat `private`, lalu dibaca atau diubah lewat getter dan setter.

## 12. Getter and Setter

Getter digunakan untuk membaca value field.

Setter digunakan untuk mengubah value field.

Contoh:

```java
public String getFullName() {
    return fullName;
}

public void setFullName(String fullName) {
    this.fullName = fullName;
}
```

## 13. Encapsulation

Encapsulation adalah cara melindungi data di dalam object. Field dibuat `private`, lalu akses dilakukan lewat method.

Contoh kurang baik:

```java
public class Customer {
    public String fullName;
}
```

Field `fullName` bisa diubah langsung dari luar.

Contoh lebih baik:

```java
public class Customer {
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
```

Dengan cara ini, perubahan data bisa dikontrol melalui method.

## 14. List

`List` menyimpan banyak data secara berurutan.

Contoh:

```java
List<String> names = new ArrayList<>();
names.add("Budi");
names.add("Siti");
System.out.println(names.get(0));
```

Operasi dasar:

- `add` untuk menambah data.
- `get` untuk mengambil data berdasarkan index.
- `size` untuk menghitung jumlah data.

Loop data:

```java
for (String name : names) {
    System.out.println(name);
}
```

## 15. Map

`Map` menyimpan data dalam bentuk key-value. `Map` cocok untuk mencari data berdasarkan key, misalnya id.

Contoh:

```java
Map<Long, String> customers = new HashMap<>();
customers.put(1L, "Budi");
customers.put(2L, "Siti");

System.out.println(customers.get(1L));
```

Operasi dasar:

- `put` untuk menyimpan data.
- `get` untuk mengambil data berdasarkan key.
- `values` untuk mengambil semua value.
- `containsKey` untuk mengecek apakah key tersedia.

## 16. List vs Map

| List | Map |
| --- | --- |
| Menyimpan data berurutan | Menyimpan data dengan key-value |
| Cocok untuk daftar data | Cocok untuk pencarian berdasarkan key |
| Diakses dengan index | Diakses dengan key |
| Contoh: daftar nama | Contoh: customer berdasarkan id |

Gunakan `List` jika fokusnya daftar data. Gunakan `Map` jika perlu mencari data cepat berdasarkan key seperti `id`.

## 17. Interface

Interface adalah kontrak. Class yang menggunakan interface harus menyediakan implementasi method yang ada di interface tersebut.

Contoh:

```java
public interface CustomerService {
    Customer createCustomer(String fullName, String email, String phoneNumber);
    Customer getCustomerById(Long id);
}
```

Interface menjelaskan method apa yang harus ada, tetapi detail isi method ditulis di class implementasi.

## 18. Abstract Class

Abstract class adalah base class yang tidak bisa langsung dibuat object-nya. Abstract class bisa berisi method biasa dan method abstract.

Untuk Day 1, cukup pahami bahwa abstract class biasanya dipakai sebagai class dasar ketika beberapa class memiliki behavior yang mirip.

## 19. Interface vs Abstract Class

| Interface | Abstract Class |
| --- | --- |
| Fokus sebagai kontrak | Fokus sebagai base class |
| Method biasanya berisi definisi yang harus diimplementasikan | Bisa memiliki field dan method biasa |
| Satu class bisa implement banyak interface | Satu class hanya bisa extend satu class |

## 20. Java Naming Convention

Naming convention membantu kode lebih mudah dibaca.

| Jenis | Format | Contoh |
| --- | --- | --- |
| Class | PascalCase | `CustomerService` |
| Variable | camelCase | `fullName` |
| Method | camelCase | `createCustomer` |
| Constant | UPPER_SNAKE_CASE | `MAX_RETRY` |
| Package | lowercase | `com.example.training` |

## 21. Summary

- Java adalah dasar utama sebelum belajar Spring Boot.
- Variable menyimpan value.
- Class adalah blueprint, object adalah hasil dari class.
- Field menyimpan data di dalam class.
- Method menyimpan behavior.
- Constructor dipanggil saat object dibuat.
- Field biasanya `private` dan diakses lewat getter/setter.
- `List` cocok untuk daftar data.
- `Map` cocok untuk pencarian berdasarkan key.
- Interface adalah kontrak.
- Abstract class adalah base class.
