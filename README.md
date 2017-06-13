# Spotify REST example

An example project for fetching artist data from Spotify via REST API.

## Data model

Artist:
1. Full name
2. Music genres
3. Hyperlink to photo
4. Names of 5 similar artists
5. List of the best tracks:
- Name of the track
- Album name
- Album release date
- Duration time
- Hyperlink to fragment of the track

### Example usage

```
localhost:8080/artists/Lemon
```

```
localhost:8080/artists/Lemon?topTracksLimit=10
```

For more examples for API's side please look at JUnit tests.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Author

* **Dawid Samolyk** - (https://bitbucket.org/dawidsamolyk)

## License

This project is licensed under the MIT License.