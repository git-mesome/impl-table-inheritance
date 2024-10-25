package domain;

public class Artist extends Person {
  private String genre;

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getGenre() {
    return genre;
  }
}
