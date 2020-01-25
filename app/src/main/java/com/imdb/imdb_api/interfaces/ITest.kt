package com.imdb.imdb_api.interfaces

interface ITest {

    fun funkcja(){

    }
}


//1. Zamień obrazek na tekst w layoucie dla DetailFilmFragment //DetailFilmFragment i fragment_detail_film.xml
//2. Zamień "obrazek" aktorów danego filmu w DetailFilmFragment na obrazek danego filmu //DetailFilmFragment.kt, AdapterDetail, item_movies_rv.xml (opisuje templatkę aktorów)
//3. Zamień ikonki dodawania filmów do ulubionych na dowolne inne i zmień kolory na różowy i niebieski //drawable ppm>new>vector asset, item_movies_rv.xml
//4. Zamień wygląd image i text w rozsuwanym menu na własne, oraz zamień je miejscami //activity_main_drawer.xml zamienić miejscami, nav_header_main.xml zmiana tekstów i obrazka, ewentualnie strings.xml
//5. Posortuj we wszystkich fragmentach tj. SearchFragment, FilmFrament, DetailFilmFragment, ActorFramgment oraz Director Fragment itemy w recyclerView //wszystkie adaptery
//6. Dodaj jedną opcję do Menu wysuwanego z lewej strony, która otworzy pusty fragment //MainActivity.kt, fragment_blank.xml, BlankFragment.kt, activity_main_drawer.xml
//
//Zadania rozszerzające aplikacje:
//v1. Dodaj przycisk w Fragmencie "DetailFilmFragment" Pozwalający na otwarcie w przeglądarce strony imdb z danym tytułem filmu //detailfilmfragment && layout detailfilfrag
//v2. Zwiększ rozmiar imageview w templatce itemu, dodaj jedno textview i dla filmu w FilmFragment dodaj idimdb pobraną z api //layout item movies && adapterfilm
//3. Dodaj radioGroup do DetailFilmFragment który pozwoli na wybranie jak bardzo ci się film podobał (bardzo/bardzo bardzo/ bardzo bardzo bardzo) (+ połączenie z bazą danych)
//v4. Dodaj czas trwania filmu do DetailFilmFragment //do 4 5 i 6 = fragment detail film <jako layout> oraz DetailFilmFragment
//v5. Dodaj rok wydania filmu do DetailFilmFragment
//v6. Dodaj fabułę filmu do DetailFilmFragment
//v7. Dodaj typ produkcji do itemu w wyszukiwarce (serial, film, gra) // AdapterSearch, SearchFilmApi, item_movies_rv.xml
//8. Dodaj switch do ActorFragment który pozwoli na sortowanie itemów w rv. (odznaczony- po ID, zaznaczony- po imieniu)
//9. Spraw by klawiarura znikała gdy "Szukaj" klikniemy poza polem wyszukiwania
//
//Dodaj splash screena