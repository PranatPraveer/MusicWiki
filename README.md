# MusicWiki
MusicWiki is an unofficial Last.fm app that contains information about different music genres, the albums, artists and tracks listed under a particular genre.

### Download and Test the app [here](https://drive.google.com/file/d/10FsUak_zg_hYYCqbQ0WbPNCjQsHahwQX/view?usp=sharing)

## Built using - 
- Kotlin Programming Language
- Glide Image Library
- Retrofit Library
- [lastfm api](https://www.last.fm/api)
- Android Studio

## Features of the app - 
- The App uses MVVM architecure & coroutines to asynchronously call the API. 
- This App is based on single activity multiple fragment concept.
- Clicking on the genre it takes the user to the 'GenreInfo Fragment' which contains information regarding it like - the title, description.
- In this very screen or activity there is a tabLayout which displays the list of top albums, top tracks and top artists as different sections.
> - Each item listed under the album shows the title, artist name and the cover image if available or the default launcher image.
> - Each item listed under the artists shows the artist name and the cover image if available or the default launcher image.
> - Each item listed under tracks shows the title, artist name and the cover image if available or the default launcher image.
- On click AlbumItem -> it takes you to the 'ArtistAlbum Fragment' 
> The 'ArtistAlbum Fragment' displays the cover image, title and the artist information which includes the the description. 
- On click ArtistItem -> it takes you to the 'ArtistInfo Fragment' 
> The 'ArtistAlbum Fragment' displays the image, title and description.

## Working of the app - 
<img src="https://user-images.githubusercontent.com/68765059/223964553-4e86f5f2-49a0-48f8-9b04-cadc0b780e9c.mp4" height = "400" width="200">

## Concepts used - 
- **MVVM Architecture** : Followed clean architecture and MVVM design pattern. Followed the respository pattern where API calls happen through repostiory and it becomes the single source of truth for the app. The ViewModels can access the repostiory and then provide the Livedata to the fragments to observe.
- **Dependency Injection** : Used Hilt library for dependency injection, made a Network Module class which provides instance of MusicAPI to Hilt. 
- **Coroutines** : Used coroutines to asynchronously call the API in background. 
- **Glide Image Library** : Used the famous Glide Library to parse the url of the images that are getting fetched from the API and then display it.
- **Retrofit Library** : Used the Retrofit library which is type-safe HTTP client for Android for interacting with API.
- **Tab layout and Adapter** : Used the material Tab Layout to show the split of 'Albums', 'Artists' & 'Tracks', of a particular genre.

## Decisions & Assumptions -
- Used the TabLayout to display all the topTags/topGenres on the homescreen. 
- Used the Retrofit library instead of the Volley library.
- Did not work properly on the UI as time was pretty less so tried to make it as minimalistic as possible. 
- Had to create many dataclasses because the API is not well maintained and changes its parameters. Making dataclasses un-reusable. 
- For the Artisits and Tracks section the images are not visible because the API itself doesn't provide the right image URL. 
