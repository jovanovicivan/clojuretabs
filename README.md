# clojuretabs

An application written in clojure. It uses Noir, Enlive and Monger libraries.

The application calls a REST service from http://www.guitarparty.com and displays artists and guitar tabs. 
First the database is filled with some dummy data about artists and their biographies. After that user can see these artists on home page.

The application consists of 3 pages:

1.	Main page - serves as a starting point, and displays top three artists from database and their information.
2.  Tabs page - displays one default tab (Eric Clapton - Layla) and a search bar where user can search tabs via REST service
3.  Artists page - displays one default artist biography (Eric Clapton) and a search bar where user can search artists' biographies via REST service

Also a user can login and register to the system in which case he is able to add a new artist to a database. Adding artists is enabled on the artists page.
User can add name, biography and upload a picture about an artist that can be displayed on the front page.

## Usage

It's necessary to start MongoDB before running the application. Database used in this project is MongoDB 2.2.3 (to download, visit http://www.mongodb.org/downloads). To start database open command line, navigate to mongodb/bin folder, and then execute mongod.exe (on windows). For more detailed instructions on how to start MongoDB, see http://docs.mongodb.org/manual/installation/.

The application automaticaly inserts dummy data in database. If you would like to prevent this comment out the first line in the main method.

To start the application navigate to the root folder and enter: lein run

## License

Distributed under the Eclipse Public License, the same as Clojure
