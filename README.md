![alt-text](http://i.imgur.com/XoTikUf.png)

bergwerkLABS Framework
======================

Module
======

| Name             | Package                                       | Artifact Id                        |
|------------------|-----------------------------------------------|------------------------------------| 
| SpigotCommons    | de.bergwerklabs.framework.commons.spigot      | labs-framework-spigotcommons       |
| FrameworkCommons | de.bergwerklabs.framework.commons             | labs-framework-commons             |
| SchematicService | de.bergwerklabs.framework.schematicservice    | schematic-service                  |
| GameService      | de.bergwerklabs.framework.gameservice         | game-service                       |
| ChatCommons      | de.bergwerklabs.framework.commons.spigot.chat | labs-framework-spigot-chat-commons |


Ein neues Modul hinzufügen
============================
Ein neues Modul sollte den Parent-Tag in der `pom.xml` enhalten

```XML
<parent>
    <groupId>de.bergwerklabs.framework</groupId>
    <artifactId>labsframework-parent</artifactId>
    <version>1.0-SNAPSHOT</version>
</parent>
```
Hinweis: Die Version kann natürlich variieren.


Code Konventionen
=================

Aufbau einer Klasse
-------------------
Diese Vorgabe muss nicht zwingen umgesetzt werden, dennoch sollte zumindest ein Wenig darauf geachtet werden.
```
public class MyClass {
    
    {getter}
    
    {setter}

    {öffentliche variablen}
    
    {private Variablen}
    
    {konstruktoren}
    
    {öffentliche Instanzmethoden}
    
    {private Instanzmethoden}
}
``` 
Die Methoden sollten zu dem nach Datentypen sortiert sein. Zwischen jedem Getter muss immer eine Zeile freibleiben, dies gilt auch für Setter.



Variablen
---------
Auf Instanzvariablen soll immer über das this Keyword zugegriffen werden. Dies gilt auch für Instanzmethoden.

Benennung der Variablen
-----------------------
Hier gelten die Java-Konventionen. Wobei bei eine Ausnahme erfolgt:
Bei Akronymen wie z. B. XML oder JSON werden die Klassen-Namen nicht in all-caps geschrieben, sondern nur der Anfangsbuchstabe.

**Richtig**
```JAVA
private XmlWriter writer = new XmlWriter();
private JsonObject jsonObject;
```

**Falsch**
```JAVA
private XMLWriter writer = new XMLWriter();
private JSONObject jsonObject;
```

Layout-Konventionen
-------------------
```JAVA
public class MyClass {
 
    public void myMethod(boolean bool) {
        if (bool) {
            System.out.println(bool);
        }
        else {
            System.out.println(!bool);
        }
    }
}
```
Der obige Code zeigt, wie die Klammern gesetzt werden müssen. Zudem muss die erste Zeile nach der Klassendeklaration ebenfalls frei bleiben.

Kommentare
----------

### Kommentare
Kommentare haben die selbe Einrückung, wie umgebende Code. Kommentar-Styles, die erlaubt sind:

```JAVA
/*                               // Das ist auch
 * Das ist ein Kommentar         // ein Kommentar    
 */
```
Jede andere Form von Kommentaren sollte nicht verwendet werden. Zudem sollte, wenn möglich, ausschließlich in Englisch kommentiert werden.

### JavaDoc
Javadoc-Kommentare sollte so aussehen:
```JAVA
/**
 * Das ist der Kommentar
 *
 * @param integer Das ist ein Parameter
 */
public void func(int integer) {}
```
Zwischen der Auflistung der `@param`-Notationen und der Beschreibung der Methode muss immer eine Zeile frei bleiben.

Klassen sollten wie folgt Kommentiert sein:
```JAVA
/**
 * Das ist eine Klasse
 * <p> 
 * Sie tut Dinge.
 *
 * @author {author}
 */
public class MyClass {}
```
Zwischen der Beschreibung und `@author` muss immer eine Zeile frei bleiben. Ein neuer Paragraph wird immer mit eine einzelnen `<p>` begonnen und steht immer alleine in einer Zeile, dies gilt ebenfalls für Methoden-Beschreibungen.
