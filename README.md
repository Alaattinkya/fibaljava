Sinema Sistemi Uygulaması
Bu proje, JSON tabanlı bir sinema sistemi yönetimi sağlar. Filmler, salonlar ve müşteriler JSON dosyalarında saklanır ve program kapanıp açıldığında kaldığı yerden devam eder. Kullanıcılar film, salon ve müşteri bilgilerini yönetebilir.

Özellikler ve Kullanım
Yeni Müşteri Ekleme: Kullanıcıdan müşteri adı alınıp bir salona atanır.
Filmleri ve Salonları Listeleme: Sistem, filmleri ve bu filmlerin oynatıldığı salonları listeler.
Salon Müşterilerini Listeleme: Bir salonun müşterileri görüntülenebilir.
Veri Saklama: Filmler, salonlar ve müşteriler Film.json, Salon.json ve Musteri.json dosyalarında saklanır.
Örnek Konsol Çıktısı:

plaintext
Kodu kopyala
Menü:
1. Yeni Müşteri Ekle
2. Filmleri ve Salonları Listele
3. Salon Müşterilerini Listele
4. Çıkış
Seçiminizi yapın: 1
Müşteri Adı: Ali
Salon Seçiniz (ID): 1
Yeni müşteri eklendi: Ali, Salon: Salon 1, Film: Inception
JSON Örnek Verileri
Musteri.json:

json
Kodu kopyala
[
  {
    "id": 1,
    "name": "Ali"
  }
]
Film.json:

json
Kodu kopyala
[
  {
    "ad": "Inception",
    "sure": 148,
    "tur": "Bilim Kurgu"
  }
]
Salon.json:

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
Teknolojik Detaylar
Programlama Dili: Java
Veri Saklama: JSON
Kütüphane: Gson
Bu sinema yönetim sistemiyle filmler, salonlar ve müşteriler üzerinde kolayca işlem yapabilirsiniz! 😊
