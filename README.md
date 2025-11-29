# CariTak

Bu proje, Java, JavaFX ve SQLite kullanılarak geliştirilmiş basit bir masaüstü cari hesap takip uygulamasıdır. Müşteri veya tedarikçi hesaplarını (carileri) yönetmenize, borç ve alacak işlemlerini kaydetmenize olanak tanır.

<img width="605" height="434" alt="image" src="https://github.com/user-attachments/assets/d40911af-693f-40d3-8902-85b8ff9a8d9c" />
<img width="609" height="435" alt="image" src="https://github.com/user-attachments/assets/13b788e0-fc84-46a4-ae1f-4ed41575db77" />
<img width="614" height="440" alt="image" src="https://github.com/user-attachments/assets/575c6839-ff40-4ae3-9490-a253d81f35f7" />

## ✨ Özellikler

- **Cari Yönetimi:**
  - Yeni müşteri/tedarikçi hesabı ekleme
  - Mevcut hesap bilgilerini düzenleme
  - Hesapları silme (ilgili tüm işlem kayıtları ile birlikte)
- **İşlem Takibi:**
  - Her bir cari için borç ve alacak işlemleri (hareketler) ekleme
  - Yapılan işlemleri tarihe göre sıralı bir şekilde görüntüleme
- **Dinamik Bakiye Hesaplama:**
  - Her bir hesabın bakiyesi, manuel bir giriş yerine, kaydedilen borç ve alacak işlemlerinin toplamına göre anlık ve doğru bir şekilde hesaplanır.
- **Veri Kalıcılığı:**
  - Tüm veriler, sunucu gerektirmeyen, dosya tabanlı ve güvenilir bir SQLite veritabanında saklanır.

## 🛠️ Kullanılan Teknolojiler

- **Platform:** Java 11
- **Arayüz (UI):** JavaFX 21
- **Proje Yönetimi ve Bağımlılıklar:** Apache Maven
- **Veritabanı:** SQLite (sqlite-jdbc sürücüsü ile)

## 🚀 Nasıl Çalıştırılır?

Projeyi yerel makinenizde çalıştırmak için aşağıdaki adımları izleyebilirsiniz.

### Gereksinimler

- Java Development Kit (JDK) 11 veya üstü
- Apache Maven

### Kurulum ve Çalıştırma

1.  **Projeyi klonlayın:**
    ```sh
    git clone https://github.com/lastlos/CariTak.git
    ```

2.  **Proje dizinine gidin:**
    ```sh
    cd CariTak
    ```

3.  **Maven ile projeyi derleyin ve çalıştırın:**
    Aşağıdaki komut, gerekli bağımlılıkları indirecek, projeyi derleyecek ve uygulamayı başlatacaktır.
    ```sh
    mvn clean javafx:run
    ```
    Uygulama ilk kez çalıştığında, proje ana dizininde `cari_takip.db` adında bir veritabanı dosyası otomatik olarak oluşturulacaktır.

## 📁 Proje Yapısı

Proje, sorumlulukların ayrılması ilkesine uygun olarak temel bir Model-View-Controller (MVC) mimarisi üzerine kurulmuştur:

- `src/main/java/com/example/caritakip`
  - **model:** `Cari.java` ve `CariHareket.java` gibi veri yapılarını içeren POJO sınıfları.
  - **service:** `CariService`, `CariHareketService` ve `DatabaseService` gibi veritabanı işlemlerini ve iş mantığını yöneten sınıflar.
  - **controller:** `MainController.java` gibi FXML dosyalarını yöneten ve arayüz olaylarını işleyen kontrolör sınıfları.
- `src/main/resources/com/example/caritakip`
  - `.fxml` uzantılı dosyalar ile uygulamanın kullanıcı arayüzü tasarımları.

## 📄 Lisans

Bu proje GPL v3 Lisansı altında lisanslanmıştır. Daha fazla bilgi için `LICENSE` dosyasına bakınız.
