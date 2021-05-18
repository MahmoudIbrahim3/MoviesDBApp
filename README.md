MoviesDB App
============

* The Project designed based on the `clean architecture` concepts and `MVVM` design architecture:

* The project consists of two modules:

    1- `app` module: consists of two layers:-

    * `Data Layer`: provides the data either from the local database or from the network using the helper class that called `NetworkBoundResource`. (Note, currently in this app the data comes from network only)

    * `Presentation Layer`: any thing related to the UI exists in this layer, and this layer designed using the MVVM architecture.

    2- `core` module: consists of three layers:-

    * `Gateways Layer`: represents an abstract definition of all the data sources.

    * `Usecases Layer`: defines actions the user can trigger and all the business logic exists here in this layer.

    * `Entity Layer`: contains the data classes of the whole App.


* I applied the dependency injection using `Dagger2`, you will see it in a separate package called `di` under the `app` module .

* For movies list, I used the `Paging` component of Jetpack to handle the paging and infinite scrolling.

* I used the `navigation` component to navigate between fragments.

* App display a list of movies. Clicking on a movie displays some details of that movie.
On `Tablet landscape`, the app show two panes on the same screen, one for the list of movies and the second for the movie details.
On the other cases the app show the list of popular movies in a separate screen, and when clicking on movie, the details show in another separate screen.
Like the following screenshoots:

    ### Phone: one pane
    ![movies_list](https://user-images.githubusercontent.com/17904163/118704754-c099de00-b817-11eb-9042-d06204ea6ede.jpeg) ![movie_details](https://user-images.githubusercontent.com/17904163/118705583-b4625080-b818-11eb-9c6b-b7b3bbf0b821.jpeg)

    ### Tablet (landscape): two pane
    ![tablet_landscape](https://user-images.githubusercontent.com/17904163/118707246-972e8180-b81a-11eb-9ef5-5c48fd77697c.png)


