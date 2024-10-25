package domain;

public class Listener extends Person {
  private String favoriteGenre;

  public String getFavoriteGenre() {
    return favoriteGenre;
  }

  public void setFavoriteGenre(String favoriteGenre) {
    this.favoriteGenre = favoriteGenre;
  }
}
