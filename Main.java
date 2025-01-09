import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;

// Temel Sınıf
abstract class BaseEntity {
    private int id;
    private String name;

    public BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void bilgiGoster();
}

// Müşteri Sınıfı
class Musteri extends BaseEntity {
    public Musteri(int id, String name) {
        super(id, name);
    }

    @Override
    public void bilgiGoster() {
        System.out.println("Müşteri ID: " + getId() + ", Adı: " + getName());
    }
}

// Film Sınıfı
class Film {
    private String ad;
    private int sure;
    private String tur;

    public Film(String ad, int sure, String tur) {
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
    }

    public String getAd() {
        return ad;
    }

    public int getSure() {
        return sure;
    }

    public String getTur() {
        return tur;
    }

    public void bilgiGoster() {
        System.out.println("Film Adi: " + ad + ", Süresi: " + sure + " dakika, Türü: " + tur);
    }
}

// Salon Sınıfı
class Salon extends BaseEntity {
    private Film film;
    private List<Musteri> musteriler;

    public Salon(int id, String name, Film film) {
        super(id, name);
        this.film = film;
        this.musteriler = new ArrayList<>();
    }

    public Film getFilm() {
        return film;
    }

    public List<Musteri> getMusteriler() {
        return musteriler;
    }

    public void musteriEkle(Musteri musteri) {
        musteriler.add(musteri);
    }

    @Override
    public void bilgiGoster() {
        System.out.println("Salon ID: " + getId() + ", Adı: " + getName());
        System.out.println("Gösterimdeki Film:");
        film.bilgiGoster();
        System.out.println("Kayıtlı Müşteriler:");
        for (Musteri musteri : musteriler) {
            musteri.bilgiGoster();
        }
    }
}

// JSON Yardımcı Sınıfı
class JsonUtils {
    private static final Gson gson = new Gson();

    public static <T> void writeToJsonFile(String fileName, List<T> data) {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> readFromJsonFile(String fileName, Class<T> clazz) {
        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, TypeToken.getParameterized(List.class, clazz).getType());
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

// Sinema Sistemi
class SinemaSistemi {
    private static final String MUSTERI_FILE = "Musteri.json";
    private static final String FILM_FILE = "Film.json";
    private static final String SALON_FILE = "Salon.json";

    private List<Musteri> musteriler;
    private List<Film> filmler;
    private List<Salon> salonlar;

    public SinemaSistemi() {
        this.musteriler = JsonUtils.readFromJsonFile(MUSTERI_FILE, Musteri.class);
        this.filmler = JsonUtils.readFromJsonFile(FILM_FILE, Film.class);
        this.salonlar = JsonUtils.readFromJsonFile(SALON_FILE, Salon.class);
    }

    public void yeniMusteriEkle(Musteri musteri, int salonIndex) {
        musteriler.add(musteri);
        JsonUtils.writeToJsonFile(MUSTERI_FILE, musteriler);
        salonlar.get(salonIndex).musteriEkle(musteri);
        JsonUtils.writeToJsonFile(SALON_FILE, salonlar);
        System.out.println("Yeni müşteri eklendi: " + musteri.getName() + ", Salon: " + salonlar.get(salonIndex).getName() + ", Film: " + salonlar.get(salonIndex).getFilm().getAd());
    }

    public void yeniFilmEkle(Film film) {
        filmler.add(film);
        JsonUtils.writeToJsonFile(FILM_FILE, filmler);
        System.out.println("Yeni film eklendi: " + film.getAd());
    }

    public void yeniSalonEkle(Salon salon) {
        salonlar.add(salon);
        JsonUtils.writeToJsonFile(SALON_FILE, salonlar);
        System.out.println("Yeni salon eklendi: " + salon.getName());
    }

    public void filmVeSalonlariListele() {
        System.out.println("=== Filmler ve Salonlar ===");
        for (Film film : filmler) {
            film.bilgiGoster();
            System.out.println("Gösterildiği Salonlar:");
            for (Salon salon : salonlar) {
                if (salon.getFilm().equals(film)) {
                    System.out.println("   - Salon: " + salon.getName() + " (Salon ID: " + salon.getId() + ")");
                }
            }
            System.out.println();
        }
    }

    public void salonMusterileriniListele(int salonId) {
        for (Salon salon : salonlar) {
            if (salon.getId() == salonId) {
                salon.bilgiGoster();
                return;
            }
        }
        System.out.println("Salon bulunamadı.");
    }

    public List<Film> getFilmler() {
        return filmler;
    }

    public List<Salon> getSalonlar() {
        return salonlar;
    }
}

// Main Sınıfı
public class Main {
    public static void main(String[] args) {
        SinemaSistemi sistem = new SinemaSistemi();
        Scanner scanner = new Scanner(System.in);

        // Varsayılan Filmler
        sistem.yeniFilmEkle(new Film("Inception", 148, "Bilim Kurgu"));
        sistem.yeniFilmEkle(new Film("Titanic", 195, "Romantik"));
        sistem.yeniFilmEkle(new Film("The Dark Knight", 152, "Aksiyon"));
        sistem.yeniFilmEkle(new Film("Interstellar", 169, "Bilim Kurgu"));
        sistem.yeniFilmEkle(new Film("Forrest Gump", 142, "Dram"));
        sistem.yeniFilmEkle(new Film("Avengers: Endgame", 181, "Aksiyon"));

        // Varsayılan Salonlar
        sistem.yeniSalonEkle(new Salon(1, "Salon 1", sistem.getFilmler().get(0)));
        sistem.yeniSalonEkle(new Salon(2, "Salon 2", sistem.getFilmler().get(1)));
        sistem.yeniSalonEkle(new Salon(3, "Salon 3", sistem.getFilmler().get(2)));
        sistem.yeniSalonEkle(new Salon(4, "Salon 4", sistem.getFilmler().get(3)));
        sistem.yeniSalonEkle(new Salon(5, "Salon 5", sistem.getFilmler().get(4)));
        sistem.yeniSalonEkle(new Salon(6, "Salon 6", sistem.getFilmler().get(5)));

        while (true) {
            System.out.println("\nMenü:");
            System.out.println("1. Yeni Müşteri Ekle");
            System.out.println("2. Filmleri ve Salonları Listele");
            System.out.println("3. Salon Müşterilerini Listele");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Enter'ı tüketmek için

            switch (secim) {
                case 1: // Yeni Müşteri Ekle
                    System.out.print("Müşteri Adı: ");
                    String musteriAdi = scanner.nextLine();
                    sistem.filmVeSalonlariListele();
                    System.out.print("Salon Seçiniz (ID): ");
                    int salonId = scanner.nextInt() - 1;

                    if (salonId >= 0 && salonId < sistem.getSalonlar().size()) {
                        Musteri yeniMusteri = new Musteri(sistem.getSalonlar().get(salonId).getMusteriler().size() + 1, musteriAdi);
                        sistem.yeniMusteriEkle(yeniMusteri, salonId);
                    } else {
                        System.out.println("Geçersiz seçim.");
                    }
                    break;

                case 2: // Filmleri ve Salonları Listele
                    sistem.filmVeSalonlariListele();
                    break;

                case 3: // Salon Müşterilerini Listele
                    System.out.print("Salon ID: ");
                    int id = scanner.nextInt();
                    sistem.salonMusterileriniListele(id);
                    break;

                case 4: // Çıkış
                    System.out.println("Programdan çıkılıyor...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }
    }
}
