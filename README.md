# Game simulator of world with superheroes and villains
Java, Java Swing, NetBeans

My task was to create a world simulator where three types of characters appear: civilians, villains and superheroes. Civilians are characters who live in cities and randomly travel around the world. Villains kill civilians and destroy cities. This way they become stronger. Superheroes fight with villains. Civilians and superheroes are created manually, while villains appear randomly. Objects on the map are interactive, i.e. you can view their status and in some cases modify it by clicking on them. Game ends when villains destroy all the cities.


# Gra symulator świata superbohaterów i złoczyńców 
Java, Java Swing, NetBeans

Projekt zakładał stworzenie gry w stylu retro - symulatora świata cywilów, superbohaterów i złoczyńców. W świecie znajdują się miasta, w tym największe - stolica. W miastach znajdują się źródła mocy.
W miastach mieszkają cywile. Co jakiś czas każdy cywil podróżuje do losowo wybranego miasta. Po jego odwiedzeniu wraca do miasta rodzinnego. Jeśli miasto rodzinne zostanie zniszczone to wybiera on nowe miasto rodzinne.
Co pewien czas na miasta napadają złoczyńcy. Po pojawieniu się złoczyńca wybiera najbliższe miasto i na nie napada. Złoczyńca zabija każdego napotkanego cywila. Gdy złoczyńca dotrze do miasta, pochłania potencjał zebrany w miejscowych źródłach mocy (niszczy miasto) i zabija znajdujących się w nim cywilów. Pochłaniając potencjał, złoczyńca zwiększa swoje zdolności, odpowiednio do pochłanianych źródeł mocy. Gdy liczba mieszkańców i potencjały wszystkich źródeł spadną do zera, złoczyńca napada na następne losowo wybrane miasto.
Stolica może wysłać na pomoc superbohaterów. Gdy superbohater spotka złoczyńcę, dochodzi do walki. Walka polega na zadawaniu ciosów opartych na zdolnościach. Gdy poziom życia spadnie do zera, złoczyńca/superbohater umiera.
Co pewien czas potencjał każdego źródła mocy zwiększa się proporcjonalnie do liczby mieszkańców miasta. Źródła mocy z miast pomagają w walce superbohaterom. Gra się kończy gdy złoczyńcy zniszczą wszystkie miasta.
Cywile i superbohaterowie są tworzeni ręcznie poprzez panel w oknie gry. Plansza jest interaktywna tzn. można na nią klikać i sprawdzać właściwości postaci lub miast. W niektórych przypadkach można je również zmieniać.
