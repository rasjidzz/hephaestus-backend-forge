# Materi - API Contract, API Testing & Swagger

## 1. Recap Day 1 and Day 2

Pada Day 1, kita membuat REST API dasar untuk Customer. API tersebut bisa menerima request, mengembalikan response, dan menyimpan data sementara di memory menggunakan `Map`.

Pada Day 2, kita menambahkan validation dan error handling. Request yang tidak valid ditolak dengan `400 Bad Request`, customer yang tidak ditemukan dikembalikan sebagai `404 Not Found`, dan error response dibuat lebih konsisten.

Day 3 fokus pada API contract, API testing, dan Swagger. Materi ini membantu peserta memahami cara mendokumentasikan dan menguji API agar behavior backend sesuai dengan kebutuhan client.

## 2. Big Picture Day 3

Flow belajar Day 3:

1. Buat project dari Spring Initializr.
2. Tambahkan dependency Spring Web dan Validation.
3. Buat Customer REST API.
4. Buat DTO request dan response.
5. Tulis API contract.
6. Test API dengan Postman.
7. Tambahkan Swagger dependency.
8. Buka Swagger UI.
9. Test API dari Swagger UI.
10. Cek OpenAPI JSON.

## 3. Membuat Project dari Spring Initializr

Buka:

```text
https://start.spring.io
```

Gunakan konfigurasi berikut:

```text
Project      : Maven
Language     : Java
Spring Boot  : 2.7.x
Group        : com.example
Artifact     : demo
Name         : demo
Description  : Demo project for API Contract Testing Swagger
Package name : com.example.demo
Packaging    : Jar
Java         : 8
```

Dependencies:

```text
Spring Web
Validation
```

Penjelasan:

- Spring Web digunakan untuk membuat REST API.
- Validation digunakan untuk request validation.
- Swagger dependency akan ditambahkan manual setelah project dibuat.

Langkah:

1. Klik Generate.
2. Extract file zip.
3. Buka project di IntelliJ IDEA atau VS Code.
4. Tunggu Maven download dependency.
5. Run `DemoApplication`.
6. Pastikan aplikasi berjalan di port `8080`.

## 4. Dependency Awal dari Spring Initializr

Dependency yang diharapkan:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

Penjelasan:

- `spring-boot-starter-web` menyediakan REST controller.
- `spring-boot-starter-validation` menyediakan annotation validation seperti `@Valid`, `@NotBlank`, `@Email`, dan `@Size`.

## 5. Apa Itu API Contract?

API contract adalah kesepakatan antara backend dan client tentang cara menggunakan API.

API contract biasanya berisi:

- HTTP method
- endpoint URL
- description
- request body
- response body
- success status code
- error status code
- validation rule
- example request
- example response

Contoh contract singkat:

```text
Method      : POST
URL         : /api/v1/customers
Description : Create new customer
Success     : 201 Created
Error       : 400 Bad Request
```

Request:

```json
{
  "full_name": "Budi Santoso",
  "email": "budi@mail.com",
  "phone_number": "08123456789"
}
```

Response:

```json
{
  "id": 1,
  "full_name": "Budi Santoso",
  "email": "budi@mail.com",
  "phone_number": "08123456789"
}
```

## 6. DTO dalam API Contract

DTO adalah Data Transfer Object. DTO digunakan untuk membawa data dari client ke backend dan dari backend ke client.

Jenis DTO:

- Request DTO: data masuk dari client.
- Response DTO: data keluar dari backend.

Request dan response DTO sebaiknya dipisah karena:

- Request dan response bisa punya field berbeda.
- Response biasanya punya `id`.
- Request create biasanya belum punya `id`.
- Lebih aman.
- Lebih fleksibel.
- Lebih mudah menjaga contract.

## 7. DTO Example

Contoh `CreateCustomerRequest`:

```java
public class CreateCustomerRequest {

    @JsonProperty("full_name")
    @NotBlank(message = "full_name is required")
    private String fullName;

    @JsonProperty("email")
    @NotBlank(message = "email is required")
    @Email(message = "email format is invalid")
    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "phone_number is required")
    private String phoneNumber;

    // getter and setter
}
```

Contoh `UpdateCustomerRequest`:

```java
public class UpdateCustomerRequest {

    @JsonProperty("full_name")
    @NotBlank(message = "full_name is required")
    private String fullName;

    @JsonProperty("email")
    @NotBlank(message = "email is required")
    @Email(message = "email format is invalid")
    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "phone_number is required")
    private String phoneNumber;

    // getter and setter
}
```

Contoh `PatchCustomerRequest`:

```java
public class PatchCustomerRequest {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    // getter and setter
}
```

Contoh `CustomerResponse`:

```java
public class CustomerResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    // getter and setter
}
```

## 8. Customer REST API Endpoint

| Method | URL | Description | Success |
| --- | --- | --- | --- |
| POST | `/api/v1/customers` | Create customer | 201 Created |
| GET | `/api/v1/customers` | Get customer list | 200 OK |
| GET | `/api/v1/customers/{id}` | Get customer by id | 200 OK |
| PUT | `/api/v1/customers/{id}` | Update full customer | 200 OK |
| PATCH | `/api/v1/customers/{id}` | Update partial customer | 200 OK |

Penjelasan:

- POST untuk membuat data baru.
- GET untuk mengambil data.
- PUT untuk update semua field utama.
- PATCH untuk update sebagian field.

## 9. CustomerController Example

Contoh controller sederhana:

```java
@Tag(name = "Customer API", description = "API for managing customers")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Create customer", description = "Create a new customer")
    @ApiResponse(responseCode = "201", description = "Customer created")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request
    ) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get customer list", description = "Get all customers")
    @ApiResponse(responseCode = "200", description = "Customer list returned")
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @Operation(summary = "Get customer by id", description = "Get one customer by id")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Operation(summary = "Update customer", description = "Update all customer fields")
    @ApiResponse(responseCode = "200", description = "Customer updated")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request
    ) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    @Operation(summary = "Patch customer", description = "Update provided customer fields only")
    @ApiResponse(responseCode = "200", description = "Customer patched")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> patchCustomer(
            @PathVariable Long id,
            @RequestBody PatchCustomerRequest request
    ) {
        return ResponseEntity.ok(customerService.patchCustomer(id, request));
    }
}
```

Controller menggunakan:

- `@RestController`
- `@RequestMapping`
- `@PostMapping`
- `@GetMapping`
- `@PutMapping`
- `@PatchMapping`
- `@RequestBody`
- `@PathVariable`
- `@Valid`
- `ResponseEntity`

## 10. CustomerService with In-Memory Map

Contoh service sederhana menggunakan `Map<Long, Customer>` dan `AtomicLong`:

```java
@Service
public class CustomerService {

    private final Map<Long, Customer> customers = new LinkedHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Long id = idGenerator.incrementAndGet();

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());

        customers.put(id, customer);
        return toResponse(customer);
    }

    public List<CustomerResponse> getCustomers() {
        List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : customers.values()) {
            responses.add(toResponse(customer));
        }
        return responses;
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = findCustomerOrThrow(id);
        return toResponse(customer);
    }

    public CustomerResponse updateCustomer(Long id, UpdateCustomerRequest request) {
        Customer customer = findCustomerOrThrow(id);
        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        return toResponse(customer);
    }

    public CustomerResponse patchCustomer(Long id, PatchCustomerRequest request) {
        Customer customer = findCustomerOrThrow(id);

        if (request.getFullName() != null) {
            customer.setFullName(request.getFullName());
        }
        if (request.getEmail() != null) {
            customer.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null) {
            customer.setPhoneNumber(request.getPhoneNumber());
        }

        return toResponse(customer);
    }

    private Customer findCustomerOrThrow(Long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        return customer;
    }

    private CustomerResponse toResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setFullName(customer.getFullName());
        response.setEmail(customer.getEmail());
        response.setPhoneNumber(customer.getPhoneNumber());
        return response;
    }
}
```

Jika customer tidak ditemukan, service melempar `CustomerNotFoundException`.

## 11. API Contract Documentation

### Create Customer

Method:

```text
POST
```

URL:

```text
/api/v1/customers
```

Description:

```text
Create new customer
```

Request:

```json
{
  "full_name": "Budi Santoso",
  "email": "budi@mail.com",
  "phone_number": "08123456789"
}
```

Success response:

```json
{
  "id": 1,
  "full_name": "Budi Santoso",
  "email": "budi@mail.com",
  "phone_number": "08123456789"
}
```

Status:

```text
201 Created
```

### Get Customer List

Method:

```text
GET
```

URL:

```text
/api/v1/customers
```

Description:

```text
Get all customers
```

Request:

```text
No request body
```

Success response:

```json
[
  {
    "id": 1,
    "full_name": "Budi Santoso",
    "email": "budi@mail.com",
    "phone_number": "08123456789"
  }
]
```

Status:

```text
200 OK
```

### Get Customer By Id

Method:

```text
GET
```

URL:

```text
/api/v1/customers/{id}
```

Description:

```text
Get one customer by id
```

Request:

```text
No request body
```

Success response:

```json
{
  "id": 1,
  "full_name": "Budi Santoso",
  "email": "budi@mail.com",
  "phone_number": "08123456789"
}
```

Status:

```text
200 OK
```

### Update Customer with PUT

Method:

```text
PUT
```

URL:

```text
/api/v1/customers/{id}
```

Description:

```text
Update all main customer fields
```

Request:

```json
{
  "full_name": "Budi Santoso Updated",
  "email": "budi.updated@mail.com",
  "phone_number": "089999999999"
}
```

Success response:

```json
{
  "id": 1,
  "full_name": "Budi Santoso Updated",
  "email": "budi.updated@mail.com",
  "phone_number": "089999999999"
}
```

Status:

```text
200 OK
```

### Patch Customer with PATCH

Method:

```text
PATCH
```

URL:

```text
/api/v1/customers/{id}
```

Description:

```text
Update only provided customer fields
```

Request:

```json
{
  "phone_number": "087777777777"
}
```

Success response:

```json
{
  "id": 1,
  "full_name": "Budi Santoso Updated",
  "email": "budi.updated@mail.com",
  "phone_number": "087777777777"
}
```

Status:

```text
200 OK
```

### Validation Error

Condition:

```text
Request body tidak sesuai validation rule
```

Error response:

```json
{
  "code": "VALIDATION_ERROR",
  "message": "Invalid request",
  "errors": [
    {
      "field": "email",
      "message": "email format is invalid"
    }
  ]
}
```

Status:

```text
400 Bad Request
```

### Customer Not Found Error

Condition:

```text
Customer id tidak ditemukan
```

Error response:

```json
{
  "code": "CUSTOMER_NOT_FOUND",
  "message": "Customer not found with id: 999",
  "errors": []
}
```

Status:

```text
404 Not Found
```

## 12. API Testing dengan Postman

API testing adalah proses mengecek apakah API berjalan sesuai contract.

Yang perlu dicek:

- URL benar.
- Method benar.
- Request body benar.
- Status code sesuai.
- Response body sesuai.
- Error response sesuai.

### Test 1 - Create customer valid request

- Method: POST
- URL: `http://localhost:8080/api/v1/customers`
- Expected: `201 Created`

Request:

```json
{
  "full_name": "Budi Santoso",
  "email": "budi@mail.com",
  "phone_number": "08123456789"
}
```

### Test 2 - Create customer invalid email

- Method: POST
- URL: `http://localhost:8080/api/v1/customers`
- Expected: `400 Bad Request`

Request:

```json
{
  "full_name": "Budi Santoso",
  "email": "wrong-email",
  "phone_number": "08123456789"
}
```

### Test 3 - Get customer list

- Method: GET
- URL: `http://localhost:8080/api/v1/customers`
- Expected: `200 OK`

### Test 4 - Get customer by id

- Method: GET
- URL: `http://localhost:8080/api/v1/customers/1`
- Expected: `200 OK`

### Test 5 - Get customer not found

- Method: GET
- URL: `http://localhost:8080/api/v1/customers/999`
- Expected: `404 Not Found`

### Test 6 - Update customer with PUT

- Method: PUT
- URL: `http://localhost:8080/api/v1/customers/1`
- Expected: `200 OK`

Request:

```json
{
  "full_name": "Budi Santoso Updated",
  "email": "budi.updated@mail.com",
  "phone_number": "089999999999"
}
```

### Test 7 - Patch customer

- Method: PATCH
- URL: `http://localhost:8080/api/v1/customers/1`
- Expected: `200 OK`

Request:

```json
{
  "phone_number": "087777777777"
}
```

## 13. Menambahkan Swagger / OpenAPI

Swagger UI membantu melihat dan mencoba endpoint API dari browser. OpenAPI adalah format dokumentasi API yang bisa dibaca oleh tools.

Tambahkan dependency berikut ke `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.15</version>
</dependency>
```

Penjelasan:

- Dependency ini cocok untuk Spring Boot 2.x.
- Setelah dependency ditambahkan, restart aplikasi.
- Swagger UI akan membaca endpoint Spring Controller secara otomatis.

## 14. Membuka Swagger UI

Langkah:

1. Jalankan aplikasi.
2. Buka browser.
3. Akses:

```text
http://localhost:8080/swagger-ui.html
```

4. Pastikan daftar endpoint muncul.
5. Buka endpoint Customer.
6. Klik Try it out.
7. Isi request body.
8. Klik Execute.
9. Cek response code.
10. Cek response body.

## 15. Membuka OpenAPI JSON

OpenAPI JSON adalah representasi contract API dalam format JSON.

Buka:

```text
http://localhost:8080/v3/api-docs
```

Penjelasan:

- File ini bisa dibaca oleh Swagger UI.
- Bisa digunakan untuk generate client SDK.
- Bisa digunakan untuk integrasi dengan tools lain.

## 16. Swagger Annotation Dasar

Springdoc bisa membaca endpoint otomatis. Annotation tambahan dipakai agar dokumentasi lebih jelas.

Annotation dasar:

- `@Tag`
- `@Operation`
- `@ApiResponse`

Contoh `@Tag`:

```java
@Tag(name = "Customer API", description = "API for managing customers")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
}
```

Contoh `@Operation` dan `@ApiResponse`:

```java
@Operation(summary = "Create customer", description = "Create a new customer")
@ApiResponse(responseCode = "201", description = "Customer created")
@ApiResponse(responseCode = "400", description = "Invalid request")
@PostMapping
public ResponseEntity<CustomerResponse> createCustomer(
        @Valid @RequestBody CreateCustomerRequest request
) {
    CustomerResponse response = customerService.createCustomer(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
```

Penjelasan:

- `@Tag` memberi nama grup API.
- `@Operation` menjelaskan endpoint.
- `@ApiResponse` menjelaskan kemungkinan response.

## 17. Swagger UI vs Postman

| Item | Swagger UI | Postman |
| --- | --- | --- |
| Source | Dari aplikasi/backend | Dibuat manual oleh developer |
| Fungsi | Dokumentasi dan testing cepat | Testing lebih fleksibel |
| Cocok untuk | Membaca contract API | Membuat collection test |
| Kelebihan | Auto-generated | Bisa simpan banyak scenario |
| Kekurangan | Bergantung pada aplikasi running | Dokumentasi bisa tidak sinkron |

## 18. Common Beginner Errors

Kesalahan yang sering terjadi:

- Salah pilih Spring Boot 3 padahal project memakai Java 8.
- Lupa menambahkan dependency Validation.
- Lupa menambahkan dependency Swagger.
- Aplikasi belum restart setelah update `pom.xml`.
- Membuka URL Swagger yang salah.
- Menggunakan `/swagger-ui` padahal yang dipakai `/swagger-ui.html`.
- Port 8080 sudah dipakai aplikasi lain.
- Controller tidak terdeteksi karena package salah.
- Tidak menggunakan `@RestController`.
- Tidak menggunakan `@RequestBody` untuk POST/PUT/PATCH.
- Tidak menggunakan `@PathVariable` untuk id.
- Response status selalu 200.
- API contract tidak sesuai implementasi.
- Swagger tampil, tetapi request body tidak sesuai DTO.
- JSON memakai camelCase padahal contract minta snake_case.

## 19. Troubleshooting

Problem:

```text
Swagger UI 404.
```

Possible causes:

- Dependency belum ditambahkan.
- Maven belum reload.
- Aplikasi belum restart.
- URL salah.
- Spring Boot version tidak cocok dengan springdoc version.

Problem:

```text
Endpoint tidak muncul di Swagger.
```

Possible causes:

- Controller tidak pakai `@RestController`.
- Package controller berada di luar base package.
- Aplikasi belum berhasil start.

Problem:

```text
Validation tidak jalan.
```

Possible causes:

- Dependency validation belum ada.
- `@Valid` belum ditambahkan.
- Annotation validation belum dipasang di DTO.

Problem:

```text
Request body tidak terbaca.
```

Possible causes:

- Lupa `@RequestBody`.
- JSON invalid.
- Field JSON tidak sesuai contract.

## 20. Summary

- API contract adalah kesepakatan antara backend dan client.
- DTO membantu menjaga struktur request dan response.
- API testing memastikan API berjalan sesuai contract.
- Postman cocok untuk manual testing dan collection.
- Swagger UI cocok untuk dokumentasi dan testing cepat dari browser.
- `springdoc-openapi-ui` bisa membuat Swagger UI otomatis untuk Spring Boot.
- Swagger UI dapat dibuka di `/swagger-ui.html`.
- OpenAPI JSON dapat dibuka di `/v3/api-docs`.
