````markdown
# 📊 Meta Post Performance Analyzer (Backend)

Bu layihə, Meta Graph API sistemini simulyasiya edərək post performans analizi aparan, nəticələri relational verilənlər bazasında saxlayan və sıralı REST API hesabatı təqdim edən peşəkar Spring Boot tətbiqidir.

---

## 🛠️ Texnologiya Steki

- **Dil:** Java 21
- **Framework:** Spring Boot 3.5.7, Spring Data JPA, Spring Security
- **Verilənlər Bazası:** PostgreSQL 15
- **Konteynerləşdirmə:** Docker, Docker Compose
- **Köməkçi Kitabxanalar:** Lombok

---

## 📦 Layihəni Lokal Mühitdə İşə Salma

### 1. Repozitoriyanı klonlayın

```bash   
git clone https://github.com/Flowercrown06/meta-post-analyzer.git
cd meta-post-analyzer
```

### 2. Kök qovluqda `.env` faylı yaradın və lazımi dəyişənləri daxil edin

```env
META_ACCESS_TOKEN=mock_secret_token_12345
```

### 3. Docker Compose vasitəsilə tətbiqi və verilənlər bazasını tək komanda ilə başladın

```bash
docker-compose up --build
```

> Əgər köhnə bazadan qalan miqrasiya xətası alsanız:

```bash
docker-compose down -v
docker-compose up --build
```

### 4. Brauzerdə və ya Postman-da hesabat API sonluğunu yoxlayın

```http
GET http://localhost:8080/api/analysis/report
```

---

## 📊 Analizin Qısa İzahı və Metodologiya

Tətbiq daxilinə inteqrasiya edilmiş `PostService` biznes məntiqi, Meta platformasından simulyasiya edilən son 20 postu analiz edərək tapşırıq şərtlərinə uyğun olaraq aşağıdakı hesablamaları aparır.

### 1. Engagement Rate (Qarşılıqlı Əlaqə) Hesablanması

Hər bir postun istifadəçilər tərəfindən nə dərəcədə maraq gördüyünü ölçmək üçün aşağıdakı formula tətbiq edilmişdir:

```text
Engagement = Likes + Comments
```

Bu göstərici əsasında postlar azalan sıra ilə çeşidlənir və **Top 3 ən uğurlu post** müəyyən edilir.

### 2. Günlərə Görə Performans Analizi

Postların paylaşıldığı dinamik keçmiş tarixlər Stream API vasitəsilə həftənin günlərinə (`DayOfWeek`) qruplaşdırılır (`Collectors.groupingBy`) və hər günə düşən ümumi bəyənmə (like) sayı cəmlənir.

---

## 📝 Nümunə API Cavabı (JSON Output)

Tətbiq uğurla icra olunduqda geri qayıdan ardıcıl (`LinkedHashMap` əsaslı) hesabat nümunəsi:

```json
{
  "1. Top 3 Engaged Posts": [
    {
      "id": 12,
      "message": "Bu bizim test məqsədli paylaşılan 12 nömrəli postumuzdur. #meta",
      "likesCount": 159,
      "commentsCount": 13,
      "createdAt": "2026-06-12T13:19:47",
      "updatedAt": "2026-06-12T13:19:47",
      "engagement": 172
    },
    {
      "id": 9,
      "message": "Bu bizim test məqsədli paylaşılan 9 nömrəli postumuzdur. #meta",
      "likesCount": 123,
      "commentsCount": 41,
      "createdAt": "2026-06-15T13:19:47",
      "updatedAt": "2026-06-15T13:19:47",
      "engagement": 164
    },
    {
      "id": 11,
      "message": "Bu bizim test məqsədli paylaşılan 11 nömrəli postumuzdur. #meta",
      "likesCount": 147,
      "commentsCount": 9,
      "createdAt": "2026-06-13T13:19:47",
      "updatedAt": "2026-06-13T13:19:47",
      "engagement": 156
    }
  ],
  "2. Total Likes by Day of Week": {
    "MONDAY": 245,
    "TUESDAY": 180,
    "WEDNESDAY": 310,
    "THURSDAY": 150,
    "FRIDAY": 420,
    "SATURDAY": 290,
    "SUNDAY": 210
  },
  "3. Brief Conclusion": "Ən yüksək engagement alan postlar həftənin müəyyən günlərinə dinamik paylanmışdır. İstənilən analitikanı buradan izləmək mümkündür."
}
```

---

## ☁️ Railway Deployment

Tətbiqin canlı mühitə miqrasiyası və bulud üzərində problemsiz işləməsi təmin edilmişdir.

### Railway Canlı API Linki

https://meta-post-analyzer-production-7716.up.railway.app/api/analysis/report

### İdarəetmə

Canlı mühitdə verilənlər bazası bağlantısı və təhlükəsizlik parametrləri birbaşa Railway-in Environment Variables bölməsindən inject edilir.

`spring.jpa.hibernate.ddl-auto=update` rejimi vasitəsilə verilənlər bazasının davamlılığı və məlumat təhlükəsizliyi qorunur.

---

## 📌 Layihənin Əsas Məqsədi

Bu layihənin məqsədi aşağıdakı bacarıqları nümayiş etdirməkdir:

- Spring Boot ilə REST API hazırlanması
- Spring Data JPA və PostgreSQL inteqrasiyası
- Docker və Docker Compose istifadəsi
- Environment Variables ilə təhlükəsiz konfiqurasiya idarəetməsi
- Stream API vasitəsilə məlumat analizi
- Clean Architecture prinsiplərinin tətbiqi
- Railway üzərində cloud deployment
- Real iş mühitinə yaxın backend arxitekturasının qurulması
````
