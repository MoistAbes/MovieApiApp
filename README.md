# MovieApiApp
REST Api application using OMDb API to find information about movies and add them to favourites

Endpointy

  GET

    http://localhost:8080/v1/movies/{title}
    Wyszukanie filmu po tytule. Zwróci informacje o filmie.

    http://localhost:8080/v1/movies/favourites
    Zwróci nam aktaulną liste filmów dodanych do ulubionych
    
    
  POST
  
     http://localhost:8080/v1/movies/{title}
     Wyszuka film po tytule. Doda go do filmów ulubionych
     
     http://localhost:8080/v1/movies
     Przyjmuje wartość JSON w postaci informacji o flimie po czym dodaje go do ulubionych

  
  
