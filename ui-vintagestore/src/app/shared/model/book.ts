/**
 * Book representation
 */
export interface IBook {
  id?: number;
  title?: string;
  author?: string;
  nbOfPages?: number;
  price?: number;
  rank?: number;
  mediumImageUrl?: string;
  smallImageUrl?: string;
  yearOfPublication?: number;
  description?: string;
  isbn10?: string;
  isbn13?: string;
}

export class Book implements IBook {
  constructor(
    public id?: number,
    public title?: string,
    public author?: string,
    public nbOfPages?: number,
    public price?: number,
    public rank?: number,
    public mediumImageUrl?: string,
    public smallImageUrl?: string,
    public yearOfPublication?: number,
    public description?: string,
    public isbn10?: string,
    public isbn13?: string
  ) {
  }
}
