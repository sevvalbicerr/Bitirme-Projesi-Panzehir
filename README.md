## KARADENİZ TEKNİK ÜNİVERSİTESİ BİLGİSAYAR MÜHENDİSLİĞİ BİTİRME PROJESİ
Projenin amacı Alzheimer başlangıç evrelerinde hasta ve hasta yakınının günlük yaşamında karşılaştığı kısıtlamalara yardımcı olarak yaşam kalitesini arttırmaktır.
Proje Mobil platform için kotlin ile hazırlandı. 
- Kullanıcı verileri Firebase ile güvenli bir şekilde depolandı. 
- Kullanıcılar üye olduklarında her bir hesap için hasta/hasta yakını olarak sınıflandırdığımız iki UI bulunmakta. 
- Oyunlar bölümünde Sudoku,Labirent,Anagram Kelime Oyunu ve Eşleştirme oyunları bulunmakta. Sudoku ve Labirent UI Canvas ile tasarlandı. 
- Sudoku için Backtracking algoritması, Labirent için Depth first search algoritması kullanıldı.
- MVVM mimarisiyle gerçekleştirilen projede Livedata gibi yapılar da kullanıldı. 

- Projenin amacı Alzheimer başlangıç evrelerinde hasta ve hasta yakınının günlük yaşamında karşılaştığı kısıtlamalara yardımcı olarak yaşam kalitesini arttırmaktır.
Proje Mobil platform için kotlin ile hazırlandı. SOLID Prensipleri dikkate alınarak MVVM mimarisiyle gerçekleştirilmiştir. Alzheimer uygulaması için kullanıcıların verilerini depolanabilmesi için Firebase Google platformu tercih edildi.

### Kullanıcı Bilgilerin Kayıt Edilmesi:
- Kullanıcının Panzehir uygulamasına kayıt olabilmek için istenilen her bilgiyi boş bırakılmadan veya istenilen formatta girilip girilmediği kontrol edilmektedir. Kontrollerde herhangi bir problem yok ise kullanıcının bilgileri Cloud Firestore’a kayıt edilmektedir.

### Kullanıcı Oturum Açılması:
- Kayıtlı kullanıcılar oturum açma işlemlerinde girmiş oldukları e-mail adresi ve parolanın boş bırakılmadan ve geçerli bilgiler
girildiği kontrol edilir. Daha sonrasında girilen bilgilerin doğruluğu kontrol edilir.

### Kullanıcının Şifre Unutması:
- Kayıtlı kullanıcıların parolalarını unutması durumunda ‘Şifremi Unuttum’ yazısına tıklayarak farklı bir sayfaya yönlendirme yapılmaktadır. Yönlendirilen sayfada kullanıcının kayıtlı mail adresi girilmesi istenmektedir. Kullanıcının istenilen bilgileri yazmaması veya mail formatında yazılıp yazılmadığı kontrol edilmektedir. Bu kontrollerin sonunda herhangi bir sorun yok ise yazılan mail adresinin Panzehir uygulamasına kayıtlı olup olmadığı kontrol edilir.
#### Yönlendirme Sayfası:
- Panzehir uygulamasına başarılı bir şekilde oturum açan kullanıcıların ekranda Alert Dialog yardımıyla iki farklı seçenecek sunulmaktadır. Bu seçenekler ‘Hasta Girişi’ veya ‘Hasta Yakını Girişi’ şeklindedir. ‘Hasta Girişi’ butonuna tıklanıldığı zaman herhangi bir işlem gerekmeden hasta anasayfasına yönlendirme yapılmaktadır. ‘Hasta Yakını Girişi’ butonuna tıklanıldığı zaman ekstra parola istenmektedir.

<img src="https://user-images.githubusercontent.com/41507884/196002072-1bc19734-eb0a-406d-957a-ae41729634bf.png" width="300" height="400"><img src="https://user-images.githubusercontent.com/41507884/196002066-8352a7e1-2ea5-4579-bc02-28ad51695136.png" width="300" height="400">

### Görüntülü/Sesli Görüşme İmkanı:
- Firebase Cloud Messaging (FCM) ile anlık bildirim gönderimi sayesinde veritabanında gerçekleşen her değişikliğin anında diğer kullanıcıya iletilmesi mümkün hale gelmektedir. Firebase’in bu özelliği sayesinde Alzheimer uygulamasında görüntülü görüşme sağlanmaktadır. Görüşmenin sağlanabilmesi için API Server ve API Client tanımlamaları yapıldı.Hasta girişi sağlayan her kullanıcının ‘Ailemi ara’ butonuna tıklanmasıyla gerçekleştirilen Panzehir uygulamasına kayıt olan tüm hasta kullanıcıların listesini gösterildiği ekrana yönlendirilir. Gösterilen listede her hasta kullanıcının yanında sesli ve görüntülü arama gerçekleştirebilmesi için iki ayrı icon bulunmaktadır. Kullanıcının tercih ettiği seçimler kontrol edilmesinin ardından projenin ilgili kısımlarına yönlendirme sağlanmaktadır. Görüşme daveti gönderildikten sonra üç farklı durum bulunmaktadır. Bunlar;  görüşme daveti gönderen kullanıcının daveti iptal etmesi, görüşme daveti alan kullanıcının görüşmeyi kabul etmesi veya görüşmeyi reddetmesidir.

<img src="https://user-images.githubusercontent.com/41507884/196002105-792377b3-47f1-4299-a7c0-9d7bc040135d.png" width="340" height="300"><img src="https://user-images.githubusercontent.com/41507884/196002110-24341661-a2e8-4ec0-b5d6-67c730c36120.png" width="305" height="300"><img src="https://user-images.githubusercontent.com/41507884/196002114-188495b2-5ef2-4fe3-8163-c2b51bd59f8d.png" width="350" height="300">





### Labirent Oyunu:
- Alzheimer hastalığının öğrenme yetisinin ve bellekte bozulmalara neden olduğu saptanmıştır. Labirent oyunu görsel algı, hayal gücü, algılama ve kavrama yeteneği gibi açılardan fayda sağlar bu sayede hastalığın seyrini değiştirme konusunda uygulanabilecek etkinliklerden birisidir. Depth First Search (DFS) algoritması, recursive backtracker (özyinelemeli geri izleme) özelliğine sahip olduğu için çok büyük derinliğe sahip graflarda zaman karmaşıklığı arttığından daha fazla zaman almaktadır. DFS algoritmasının bu dezavantajından dolayı Breadth First Search (BFS) algoritmasını kullanmak daha mantıklı olmaktır. BFS algoritması tüm düğümlerin yollarını tarayıp belleğinde tuttuğundan dolayı daha hızlı sonuç vermektedir lakin DFS algoritmasına göre daha fazla bellek harcayacaktır. Bu durum Alzheimer uygulması için istenilen bir durum değildir. Uygulamada kullanılacak olan labirent oyunu alzheimer hastaların zihinsel becerilerini geliştirmeye yönelik bir oyun olacağından grafın derinliği büyük boyutlar olmamaktadır. Bu yüzden labirent oyununda DFS algoritması kullanılmaktadır.

![Mazegame](https://user-images.githubusercontent.com/41507884/196002101-81c84c99-b444-4e5c-87ff-ad84468923f4.png)![labirentçöz](https://user-images.githubusercontent.com/41507884/196002098-ee188c2d-b89b-4ddf-b7db-ea41ac5c3388.png)

### Sudoku Oyunu:
- Sudoku geri izleme algoritması kullanılarak çözülebilen ünlü bulmacalardan biridir. Sudoku oyunun kurallarını göz önüne alındığında bizim için en uygun algoritma backtracking algoritması olmaktadır. Backtracking algoritması, istenen sonucu bulmak için kaba kuvvet yaklaşımını (brute force approach) kullanan problem çözme algoritmasıdır. Brute force approach yaklaşımı, oluşabilecek tüm çözümleri deneyerek istenen veya en iyi çözümleri seçer. Backtracking algoritması mevcut çözüm uygun değilse geri dönerek diğer çözümleri deneyerek uygun çözümü bulmaya çalışır. Bu algoritma, birden fazla çözümü olan problemleri çözmek için kullanılır. 

![sudoku](https://user-images.githubusercontent.com/41507884/196002330-cdd3cc3f-7f83-4a21-bea9-0a5302925a23.png)![sudoku çözüm](https://user-images.githubusercontent.com/41507884/196002331-6d477674-ab1d-49e7-a88d-de885292f21a.png)


### Anagram Oyunu:
- Anagram, bir tür kelime oyunudur. Beyin anlama ve kavrama yetisini geliştiren , beyin jimnastiğine uygun egzersizlerden birisidir. Anlamlı bir kelimenin harflerinin yer değiştirilerek yeni anlamlı kelimenin üretilmesi amaçlanır. Anagram kelime oyunu için farklı sıralama algoritmaları incelendi. Sıralama algoritmaları yıllardır geliştirilen algoritmalar olmakla birlikte birçok çeşidi bulunmaktadır. İhtiyaç durumuna göre seçim farklılık gösterirken zaman ve bellek kullanımı açısından performans karşılaştırılması yapılmaktadır.  Merge, Selection, Quick sort olmak üzere 3 algoritmayı karşılaştırdık. 1000 verilik set için yapılan karşılaştırmaya göre merge sort : 0.05 ,quick sort: 0.03 selection sort : 0.4 saniyede çalıştı. Yapılan araştırmalar sonucu Hızlı sıralama (Quick Sort) algoritmasını tercih edildi.nagram kelime oyununa quick sort algoritmasını uygulayabilmemiz için sıralanabilecek bir veri dizisine ihtiyacımız vardı. Kelimedeki harflerin ASCII(aski) karşılıklarını kullanarak sıralama algoritmasını başarılı bir şekilde uygulayabildik.

![anagram](https://user-images.githubusercontent.com/41507884/196002104-5788c94a-8069-41e9-8923-273e548c01df.png)





