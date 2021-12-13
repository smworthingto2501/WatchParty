def helloworld():
    return 'Hello World!'

def get_top_movies(genres, movies):
    from os.path import dirname, join
    from pandas import read_csv
    filename = join(dirname(__file__), 'movie_info.csv')
    movie_info = read_csv(filename)
    # Edit names to get rid of apostrophes
    movie_info['Title'] = movie_info['Title'].str.replace(r"[\"\',]", '')
    # Filter based on genres.
    genres = genres.split('  ')
    genres = [genre.strip() for genre in genres]
    good_inds = movie_info['Genre'].str.contains('|'.join(genres), case=False) #contains('|'.join(genres), case=False)
    movie_info = movie_info[good_inds.values]
    if len(movies)>0:
        movies = movies.split('~')
        movie_info = movie_info[~movie_info.Title.isin(movies)]
    movie_info = movie_info.sort_values('imdbRating', ascending=False)
    movies = Movies(movie_info.head(10))
    return movies #[['Title', 'Runtime', 'Genre', 'Plot', 'Poster']].values

class Movies:
    def __init__(self, movie_info):
        self.titles = movie_info.Title.values.tolist()
        self.years = movie_info.Year.values.tolist()
        self.ratings = movie_info.Rated.values.tolist()
        self.runtimes = movie_info.Runtime.values.tolist()
        self.genres = movie_info.Genre.values.tolist()
        self.plots = movie_info.Plot.values.tolist()
        self.posters = movie_info.Poster.values.tolist()


