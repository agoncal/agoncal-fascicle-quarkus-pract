/**
 * Book representation
 */
export interface Book {
    id?: number;
    author?: string;
    description?: string;
    isbn10?: string;
    isbn13?: string;
    mediumImageUrl?: string;
    nbOfPages?: number;
    price?: number;
    rank?: number;
    smallImageUrl?: string;
    title: string;
    yearOfPublication?: number;
}
