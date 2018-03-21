# lkDB

LK Q1 Reichelt, Thema: Datenbanken

**Wichtig:**
Für jedes Unterthema bzw. jede eigenständige Implementierung ist ein eigener Unterordner vorhanden.  
Dieser enthält:

- `README.md`: Darin sind die wichtigen Punkte des Themas festgehalten.
- `Lernblog.md`: Darin steht der Artikel für den Lernblog.

## Einstieg

- RDBMS: "**R**elational **D**ata**B**ase **M**anagement **S**ystem"
- Verwaltet Daten in *Relationen* (&rarr; Tabellen)

- Tabelle wird durch Schema definiert
  - Attribute/Spalten
  - Datentypen
  - Restriktionen (muss vorhanden sein, ist primärschlüssel, länge des Texts)
- Ein Datensatz &rarr; Eine Zeile in der Tabelle

## Fachbegriffe

- *Primärschlüssel:* Menge an Attributen, durch die sich jede mögliche Entität eindeutig identfizieren lässt
  - Oft: spezielle ID
  - Bsp: ISBN, Benutzername
- *Fremdschlüssel:* Attribut, das auf andere Entität verweist
  - Bsp: ID des Autors in Buch-Eintrag
- *Redundanz:* Daten werden mehrmals gespeichert. Man kann sie löschen, ohne dass Information verloren geht
- *Anomalie:* Probleme und Fehler, die bei Schreib-Operationen entstehen
- *Inkonsistenz:* Widersprüche innerhalb der Datenbank. Wird durch Anomalie hervorgerufen.

## Relations-Kardinalitäten

- *One-to-One:* Eine Entität ist genau *einer* anderen Entität zugeordnet
  - Bsp: Buch &rarr; ISBN
  - *Einem* Buch ist *eine* ISBN zugeordnet, *einer* ISBN ist *ein* Buch zugeordnet
  - Ist meistens Unnötig, da Daten in gleicher Tabelle stehen können
- *One-to-Many:* Eine Entität ist *mehreren* anderen Entität zugeordnet
  - Bsp: Buch &rarr; Verlag
  - Ein Buch kann bei *einem* Verlag veröffentlicht werden, ein Verlag kann *mehrere* Bücher veröffentlichen
- *Many-to-Many:* Auf beiden Seiten können beliebig viele Entitäten stehen
  - Bsp: Buch &harr; Autor
  - Ein Buch kann von mehreren Autoren stammen, ein Autor kann mehrere Bücher schreiben
  - Verbindungstabelle muss verwendet werden (enthält nur zwei Fremdschlüssel)
