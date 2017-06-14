# Spotify REST example

An example project for fetching artist data from Spotify via REST API.

## Configuration

You have to get your Spotify Web API Client ID and Secret from [that webpage](https://developer.spotify.com/my-applications). If you don't have Application yet then you should create it.

Use below data as program arguments when you're starting SpotifyConnectorApplication. 

Syntax of program arguments:
```
--spotifyWebApiClientId=<CLIENT_ID>
--spotifyWebApiSecretKey=<SECRET>
```

Without that data program will not start.

Example usage from command line:
```
java -jar spotify-connector-1.0-SNAPSHOT.jar "--spotifyWebApiClientId=<CLIENT_ID>" "--spotifyWebApiSecretKey=<SECRET>"
```

Example usage from Eclipse:
Run configurations... -> Select your configuration -> Arguments -> Program arguments:
```
--spotifyWebApiClientId=<CLIENT_ID>
--spotifyWebApiSecretKey=<SECRET>
```

## Data model

Artist:
- Full name
- Music genres
- Hyperlink to photo
- Names of 5 similar artists
- List of the best tracks:
	* Name of the track
	* Album name
	* Album release date
	* Duration time
	* Hyperlink to fragment of the track
	
Data will be fetched by localization. You could change its value by using "locale" parameter in query string of request address (see below examples). **Please use full code of locale which specifies country also. Country is used to fetch top tracks of an artist. If you'll not provide full locale code then first found country for a given language will be taken.**

### Example usage

```
localhost:8080/artists/Lemon
```

```
localhost:8080/artists/Lemon?topTracksLimit=10
```

To change your locale use "locale" parameter:

```
localhost:8080/artists/Lemon?locale=PL_pl
```

```
localhost:8080/artists/Lemon?topTracksLimit=10&locale=PL_pl
```
```
localhost:8080/artists/Lemon?topTracksLimit=10&locale=DE_de
```

For more examples from API's side please look at JUnit tests.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Author

* **Dawid Samolyk** - (https://bitbucket.org/dawidsamolyk)

## License

This project is licensed under the MIT License.