# E-Tailor

An Android app for ordering tailored clothes, written in Java and storing everything on the device with SQLite. Customers register, browse a catalogue of garments, place and edit orders, and save their body measurements for the tailor. An admin login shows every customer's orders. It was built in May 2022 for **CSE-2218 Advanced Programming Sessional** at BAUET (Bangladesh Army University of Engineering & Technology). The project report is included as [E-Tailor.pdf](E-Tailor.pdf), with the editable version in [E-Tailor.docx](E-Tailor.docx).

## Features

- **Start-up:** a splash screen, then sign in or sign up (name, email, password and password confirmation).
- **Main menu:** Home, My Orders, My Account, My Tailor, About and Sign Out.
- **Catalogue:** the Home screen lists eight garments (short kurta, suit, bandhgala suit, bandi, sherwani, jacket, waistcoat and trouser), each with a picture, a price and a description.
- **Ordering:** a garment's detail page lets you set the quantity with + and − buttons and enter a delivery address and phone number before placing the order.
- **My Orders:** lists your orders. Tap an order to change and update it, or long-press it to delete it after a confirmation.
- **Measurements:** My Account saves your collar, chest, sleeve, waist, inside leg, outside leg and centre back length in inches, and lets you update them later.
- **Admin view:** signing in with the built-in admin account opens a list of all orders from all customers.
- **Info pages:** My Tailor shows the tailor's contact details, and About shows the developer's.

## Tech stack

- Java, Android SDK (compile and target SDK 32, minimum SDK 17)
- AndroidX AppCompat, Material Components, ConstraintLayout and RecyclerView, with view binding
- SQLite through `SQLiteOpenHelper`, with three databases: `Login.db` (users), `orders.db` (orders) and `account.db` (measurements)
- Gradle 7.2 with Android Gradle Plugin 7.1.3

## Getting started

1. Open the `etailor/` folder in Android Studio and let Gradle sync.
2. Run the `app` configuration on an emulator or a device.

   Or build a debug APK from the command line:

   ```bash
   cd etailor
   ./gradlew assembleDebug
   ```

3. Create an account on the sign-up screen and sign in. The admin account is defined in `Global.java`.

## Project structure

```
etailor/app/src/main/java/com/example/etailor/
  MainActivity.java          splash screen
  Sign_in_Activity.java      sign in, including the admin login
  Sign_up_Activity.java      registration
  Selection_Activity.java    main menu
  HomeActivity.java          garment catalogue
  DetailActivity.java        place or update an order
  OrderActivity.java         order list
  AccountActivity.java       body measurements
  TailorActivity.java        tailor contact page
  AboutActivity.java         developer page
  Adapters/                  RecyclerView adapters for garments and orders
  Databases/                 SQLite helpers (LoginDB, OrdersDB, AccountDB)
  Models/                    garment and order models
etailor/app/src/main/res/    layouts, garment images, icons, fonts
E-Tailor.pdf, E-Tailor.docx  project report
```

## Screenshots

The repository has no separate screenshot files. Chapter 4 of [E-Tailor.pdf](E-Tailor.pdf) has screenshots of the splash, login, register, home, product, order, admin and measurement screens.

## Author

Md Arik Rayhan — [mdarikrayhan.com](https://mdarikrayhan.com)
