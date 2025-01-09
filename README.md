Sinema Sistemi Uygulaması
Bu proje, bir sinema sisteminin film, salon ve müşteri yönetimini sağlayan bir simülasyondur. Veriler JSON dosyalarında saklanır ve her çalışma sırasında bu dosyalardan okunarak işlenir. Programın amacı, sinema salonlarının yönetimini kolaylaştırmak ve müşteri bilgilerini takip etmektir.

Özellikler
Film Yönetimi
Yeni film ekleme.
Filmleri listeleme.
Salon Yönetimi
Yeni salon ekleme.
Salonlara film atama.
Salonlardaki filmleri listeleme.
Müşteri Yönetimi
Yeni müşteri ekleme.
Salonlardaki müşterileri listeleme.
JSON Tabanlı Veri Saklama
Filmler, salonlar ve müşteriler JSON dosyalarında saklanır.
Program kapanıp tekrar açıldığında JSON dosyalarındaki verilerle çalışmaya devam eder.
Kullanılan Teknolojiler
Programlama Dili: Java
JSON İşlemleri için Kütüphane: Gson
Veri Formatı: JSON
Kurulum
Gson Bağımlılığı

Projenize Gson kütüphanesini ekleyin.
Maven kullanıyorsanız pom.xml dosyanıza şu bağımlılığı ekleyin:
xml
Kodu kopyala
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.8</version>
</dependency>
Manuel kurulum için Gson jar dosyasını buradan indirebilirsiniz.
Proje Yapısı

Projeniz aşağıdaki dosya yapısına sahip olmalıdır:
bash
Kodu kopyala
SinemaSistemi/
├── Main.java             # Programın başlangıç noktası
├── Musteri.json          # Müşteri bilgileri
├── Film.json             # Film bilgileri
├── Salon.json            # Salon bilgileri
JSON Dosyaları
Musteri.json
Müşteri bilgilerini tutar. Örnek içerik:

json
Kodu kopyala
[
  {
    "id": 1,
    "name": "Ali"
  },
  {
    "id": 2,
    "name": "Veli"
  }
]
Film.json
Sistemde kayıtlı filmleri içerir. Örnek içerik:

json
Kodu kopyala
[
  {
    "ad": "Inception",
    "sure": 148,
    "tur": "Bilim Kurgu"
  },
  {
    "ad": "Titanic",
    "sure": 195,
    "tur": "Romantik"
  }
]
Salon.json
Salon bilgilerini ve salonlarda bulunan müşterileri içerir. Örnek içerik:

json
Kodu kopyala
[
  {
    "id": 1,
    "name": "Salon 1",
    "film": {
      "ad": "Inception",
      "sure": 148,
      "tur": "Bilim Kurgu"
    },
    "musteriler": [
      {
        "id": 1,
        "name": "Ali"
      }
    ]
  }
]
Kullanım
Başlatma
Program çalıştırıldığında mevcut JSON dosyalarındaki veriler okunur.
Varsayılan filmler ve salonlar sisteme eklenir.
Menü
Program, aşağıdaki menü ile etkileşim sağlar:

markdown
Kodu kopyala
Menü:
1. Yeni Müşteri Ekle
2. Filmleri ve Salonları Listele
3. Salon Müşterilerini Listele
4. Çıkış
1. Yeni Müşteri Ekle
Kullanıcıdan müşteri adı istenir.
Mevcut salonlar ve oynatılan filmler listelenir.
Kullanıcı bir salon seçerek müşteriyi o salona ekler.
2. Filmleri ve Salonları Listele
Sistemdeki filmleri ve bu filmlerin hangi salonlarda oynatıldığını listeler.
3. Salon Müşterilerini Listele
Kullanıcıdan bir salon ID'si istenir.
Belirtilen salondaki müşteriler listelenir.
4. Çıkış
Programdan çıkılır ve tüm veriler JSON dosyalarına kaydedilir.
