## Android Permissions
/ ActivitiyLauncher kullanarak kullanıcından izin alma, vermez ise Snackbar ile tekrardan izin isteği gönderme işlemlerini içeren projedir. Galeriden alınan görseli aktivite üzerinde bulunan ImageView'da gösterir.

/ Projede sadece `READ_EXTERNAL_STORAGE` için izin alınmıştır, farklı izinler içinde kullanılabilir hale getirilebilir.

/ Diğer projelerde de rahat bir şekilde kullanmak için `registerLauncher` adlı fonksiyonda `activitiyLauncher` ve `permissionLauncher` adlı iki tanımlama yapılmıştır. Bu değişkenler diğer projelerde de kullanılabilir, diğer izinler için de düzenlenebilir.

/ activityLauncher, `StartActivityForResult()` kullanılarak tanımlanmıştır. Yani, içerisinde alınan izin ile ulaştığı birimden bir data dönderir ve startActivity işlemi için kullanılır.

/ permissionLauncher, `RequestPermission()` kullanılarak tanımlanmıştır. Kullanıcıdan izin almak için kullanılan değişkendir. result, false dönerse Toast ile bir uyarı gösterilir. Eğer true dönerse activityLauncher ile startIntent işlemi yapılır.
