[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)

Every week has its challenges, so it's time to keep track of them with the Baller Bullet Journal. With this
application, add and delete events and keep track of tasks you need to complete in the Task queue. Your week
will be laid out for you so you can take on each day one by one. Set Commitment limits so you don't
accidentally over commit your time on too many tasks and freely edit or remove or weekly entries.
Feeling unmotivated? Pin your favorite motivational quotes on the side to keep you going and make notes so
you don't forget small nuances. The Baller Bullet Journal also features fully customizable themes and fonts
so it will suit any aesthetic you want.

The first principle of solid, single responsibility, requires methods and classes to focus on a singular goal.
This was used in BujoFile classes. BujoFileReader's purpose is to solely focus on reading and parsing the
information on a .bujo file to usable information for the Diary. On the Contrary, the job of writing to the
.bujo file is solely for the BujoFileSaver. These two tasks are split between two different classes to
follow the Single Responsibility principle.

The next principle is the Open/Closed principle where classes are open for extensions but closed for
modification. In our case, we used an AbstractTheme class that was extended to the subclass, CustomTheme.
This way, CustomTheme inherits all the methods in AbstractTheme and can add functions without modifying
the original super class.

An example in our program of the Liskov Substitution principle is also our CustomTheme class. The CustomTheme
class retains all the functionality of the AbstractTheme parent class so if a reference type of AbstractTheme
was changed to a Type of CustomTheme, it wouldn't break the program.

The fourth principle is the interface segregation principle with dictates that interfaces shouldn't
contain methods that aren't implemented in their implemented classes. We followed this principle in
many instances, such as our Controller interface, which only contained a run method and was used by
all its implementations such as the EventController and the DiaryController. As a result, there
was no method that was forced on a class that didn't need it.

The last principle is the Dependency inversion principle which asks for high-level modules to be
unaffected by changes in lower level modules. An example of this in our code would be our DiaryController
class. It is always able to display the diary and interact with the user regardless if clicking a certain
button displays a Task or a event instead. Changing smaller utility methods used in DiaryController
wouldn't prevent it from working in the long run

One feature we didn't implement was the privacy lock. To implement this, we could simply extend our
GuiViewImpl to a new subclass to display a new FXML. To implement the controls for this, we would
also implement our Controller interface again to a new class and implement all the features and then
add to the main DiaryController.

![Screenshot 2023-06-22 052659.png](..%2F..%2FDesktop%2FScreenshot%202023-06-22%20052659.png)
