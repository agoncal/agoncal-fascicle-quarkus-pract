package org.agoncal.fascicle.quarkus.book;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Random;

@Schema(description = "Book representation")
// tag::adocEntity[]
@Entity
public class Book extends PanacheEntity {

  @NotNull
  public String title;
  @NotNull
  public String isbn;
  public String author;
  @Column(name = "year_of_publication")
  public Integer yearOfPublication;
  public String genre;
  public BigDecimal price;

  // tag::adocFindRandom[]
  public static Book findRandom() {
    long countBookes = Book.count();
    Random random = new Random();
    int randomBook = random.nextInt((int) countBookes);
    return Book.findAll().page(randomBook, 1).firstResult();
  }
  // end::adocFindRandom[]

  // ======================================
  // =   Methods hash, equals, toString   =
  // ======================================

  @Override
  public String toString() {
    return "Book{" +
      "id=" + id +
      ", author='" + author + '\'' +
      ", title='" + title + '\'' +
      ", year=" + yearOfPublication +
      ", genre='" + genre + '\'' +
      ", isbn='" + isbn + '\'' +
      ", price='" + price + '\'' +
      '}';
  }
}
