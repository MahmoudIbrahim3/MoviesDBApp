MoviesDB App
============

* The Project designed based on the `clean architecture` concepts and `MVVM` design architecture:

* The project consists of two modules:
    1- `app` module: consists of two layers:-

    * `Data Layer`: provides the data either from the local database or from the network using the helper class that called `NetworkBoundResource`. (Note, currently in this app the data comes from network only)

    * `Presentation Layer`: any thing related to the UI exists in this layer, and I designed this layer using the MVVM architecture.

    2- `core` module: consists of three layers:-

    * `Gateways Layer`: represents an abstract definition of all the data sources.

    * `Usecases Layer`: defines actions the user can trigger and all the business logic exists in this here.

    * `Entity Layer`: contains the data classes of the whole App.


* I applied the dependency injection using `Dagger2`, you will see it in a separate package called `di` under the `app` module .

* The app show the list of popular movies in a screen, and when items on the list are tapped, the details shown in another separate screen. But, on Tablet, the app use two pane on the same screen, one for the list of movies and the second for the movie details. Like the followings:

![WhatsApp Image 2021-05-18 at 5 09 30 PM](https://user-images.githubusercontent.com/17904163/118677129-4b6cdf80-b7fc-11eb-8ce8-4f9d7498f049.jpeg)

* For movies list, I used the `paging3` component of jetback to handle the paging and infinite scrolling.

* I used the `navigation` component to navigate between fragments.
