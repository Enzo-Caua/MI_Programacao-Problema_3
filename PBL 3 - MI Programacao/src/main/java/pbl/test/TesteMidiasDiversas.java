package pbl.test;

import pbl.model.obras.*;
import pbl.model.persistencia.PersistirAcervo;

import java.util.ArrayList;
import java.util.Arrays;

public class TesteMidiasDiversas {

    public static void main(String[] args) {
        PersistirAcervo.deserializarAcervo(); // Carrega o acervo existente

        Acervo acervo = Acervo.getAcervo();

        // -------- LIVROS (mantidos) --------
        acervo.addLivro(new Livro("Dom Casmurro", "Don Casmurro", "Romance", "1899",
                "Machado de Assis", "Nova Fronteira", "111111111", true));
        acervo.addLivro(new Livro("1984", "Nineteen Eighty-Four", "Distopia", "1949",
                "George Orwell", "Companhia das Letras", "222222222", true));
        acervo.addLivro(new Livro("A Revolução dos Bichos", "Animal Farm", "Sátira", "1945",
                "George Orwell", "Companhia das Letras", "333333333", true));
        acervo.addLivro(new Livro("O Hobbit", "The Hobbit", "Fantasia", "1937",
                "J.R.R. Tolkien", "HarperCollins", "444444444", false));
        acervo.addLivro(new Livro("Duna", "Dune", "Ficção Científica", "1965",
                "Frank Herbert", "Aleph", "555555555", true));
        acervo.addLivro(new Livro("O Código Da Vinci", "The Da Vinci Code", "Suspense", "2003",
                "Dan Brown", "Arqueiro", "666666666", true));
        acervo.addLivro(new Livro("It: A Coisa", "It", "Terror", "1986",
                "Stephen King", "Suma", "777777777", false));
        acervo.addLivro(new Livro("O Alquimista", "The Alchemist", "Autoajuda", "1988",
                "Paulo Coelho", "Rocco", "888888888", true));
        acervo.addLivro(new Livro("A Menina que Roubava Livros", "The Book Thief", "Drama", "2005",
                "Markus Zusak", "Intrínseca", "999999999", true));
        acervo.addLivro(new Livro("O Nome do Vento", "The Name of the Wind", "Fantasia", "2007",
                "Patrick Rothfuss", "Arqueiro", "1010101010", true));

        // -------- FILMES --------
        acervo.addFilme(new Filme("A Origem", "Inception", "Ficção Científica", "2010", "148",
                new ArrayList<>(Arrays.asList("Christopher Nolan")),
                new ArrayList<>(Arrays.asList("Christopher Nolan")),
                new ArrayList<>(Arrays.asList("Leonardo DiCaprio", "Elliot Page")),
                "Netflix"));

        acervo.addFilme(new Filme("O Senhor dos Anéis: A Sociedade do Anel", "The Fellowship of the Ring", "Fantasia", "2001", "178",
                new ArrayList<>(Arrays.asList("Peter Jackson")),
                new ArrayList<>(Arrays.asList("Fran Walsh", "Philippa Boyens", "Peter Jackson")),
                new ArrayList<>(Arrays.asList("Elijah Wood", "Ian McKellen")),
                "HBO Max"));

        acervo.addFilme(new Filme("Matrix", "The Matrix", "Ação", "1999", "136",
                new ArrayList<>(Arrays.asList("Lana Wachowski", "Lilly Wachowski")),
                new ArrayList<>(Arrays.asList("Lana Wachowski", "Lilly Wachowski")),
                new ArrayList<>(Arrays.asList("Keanu Reeves", "Carrie-Anne Moss")),
                "Netflix"));

        acervo.addFilme(new Filme("Clube da Luta", "Fight Club", "Drama", "1999", "139",
                new ArrayList<>(Arrays.asList("David Fincher")),
                new ArrayList<>(Arrays.asList("Jim Uhls")),
                new ArrayList<>(Arrays.asList("Brad Pitt", "Edward Norton")),
                "Star+"));

        acervo.addFilme(new Filme("Interestelar", "Interstellar", "Ficção Científica", "2014", "169",
                new ArrayList<>(Arrays.asList("Christopher Nolan")),
                new ArrayList<>(Arrays.asList("Jonathan Nolan", "Christopher Nolan")),
                new ArrayList<>(Arrays.asList("Matthew McConaughey", "Anne Hathaway")),
                "HBO Max"));

        acervo.addFilme(new Filme("O Poderoso Chefão", "The Godfather", "Crime", "1972", "175",
                new ArrayList<>(Arrays.asList("Francis Ford Coppola")),
                new ArrayList<>(Arrays.asList("Mario Puzo", "Francis Ford Coppola")),
                new ArrayList<>(Arrays.asList("Marlon Brando", "Al Pacino")),
                "Paramount+"));

        acervo.addFilme(new Filme("Titanic", "Titanic", "Romance", "1997", "195",
                new ArrayList<>(Arrays.asList("James Cameron")),
                new ArrayList<>(Arrays.asList("James Cameron")),
                new ArrayList<>(Arrays.asList("Leonardo DiCaprio", "Kate Winslet")),
                "Star+"));

        acervo.addFilme(new Filme("Coringa", "Joker", "Drama", "2019", "122",
                new ArrayList<>(Arrays.asList("Todd Phillips")),
                new ArrayList<>(Arrays.asList("Todd Phillips", "Scott Silver")),
                new ArrayList<>(Arrays.asList("Joaquin Phoenix", "Robert De Niro")),
                "HBO Max"));

        acervo.addFilme(new Filme("Toy Story", "Toy Story", "Animação", "1995", "81",
                new ArrayList<>(Arrays.asList("John Lasseter")),
                new ArrayList<>(Arrays.asList("Joss Whedon", "Andrew Stanton")),
                new ArrayList<>(Arrays.asList("Tom Hanks", "Tim Allen")),
                "Disney+"));

        acervo.addFilme(new Filme("Pantera Negra", "Black Panther", "Ação", "2018", "134",
                new ArrayList<>(Arrays.asList("Ryan Coogler")),
                new ArrayList<>(Arrays.asList("Ryan Coogler", "Joe Robert Cole")),
                new ArrayList<>(Arrays.asList("Chadwick Boseman", "Michael B. Jordan")),
                "Disney+"));

        // -------- SÉRIES --------
        Serie s1 = new Serie("Breaking Bad", "Breaking Bad", "Crime", "2008",
                new ArrayList<>(Arrays.asList("Vince Gilligan")),
                new ArrayList<>(Arrays.asList("Vince Gilligan")),
                new ArrayList<>(Arrays.asList("Bryan Cranston", "Aaron Paul")),
                "Netflix");
        s1.addTemporadas(new Temporada("2008", 7, 1));
        s1.addTemporadas(new Temporada("2009", 13, 2));
        acervo.addSerie(s1);

        Serie s2 = new Serie("Stranger Things", "Stranger Things", "Ficção Científica", "2016",
                new ArrayList<>(Arrays.asList("The Duffer Brothers")),
                new ArrayList<>(Arrays.asList("The Duffer Brothers")),
                new ArrayList<>(Arrays.asList("Millie Bobby Brown", "David Harbour")),
                "Netflix");
        s2.addTemporadas(new Temporada("2016", 8, 1));
        s2.addTemporadas(new Temporada("2017", 9, 2));
        acervo.addSerie(s2);

        Serie s3 = new Serie("Game of Thrones", "Game of Thrones", "Fantasia", "2011",
                new ArrayList<>(Arrays.asList("David Benioff", "D. B. Weiss")),
                new ArrayList<>(Arrays.asList("George R. R. Martin")),
                new ArrayList<>(Arrays.asList("Emilia Clarke", "Kit Harington")),
                "HBO Max");
        s3.addTemporadas(new Temporada("2011", 10, 1));
        s3.addTemporadas(new Temporada("2012", 10, 2));
        acervo.addSerie(s3);

        Serie s4 = new Serie("Dark", "Dark", "Ficção Científica", "2017",
                new ArrayList<>(Arrays.asList("Baran bo Odar")),
                new ArrayList<>(Arrays.asList("Jantje Friese")),
                new ArrayList<>(Arrays.asList("Louis Hofmann", "Lisa Vicari")),
                "Netflix");
        s4.addTemporadas(new Temporada("2017", 10, 1));
        s4.addTemporadas(new Temporada("2019", 8, 2));
        acervo.addSerie(s4);

        Serie s5 = new Serie("The Office", "The Office", "Comédia", "2005",
                new ArrayList<>(Arrays.asList("Greg Daniels")),
                new ArrayList<>(Arrays.asList("Ricky Gervais", "Stephen Merchant")),
                new ArrayList<>(Arrays.asList("Steve Carell", "John Krasinski")),
                "Prime Video");
        s5.addTemporadas(new Temporada("2005", 6, 1));
        s5.addTemporadas(new Temporada("2006", 22, 2));
        acervo.addSerie(s5);

        Serie s6 = new Serie("Brooklyn Nine-Nine", "Brooklyn Nine-Nine", "Comédia", "2013",
                new ArrayList<>(Arrays.asList("Dan Goor", "Michael Schur")),
                new ArrayList<>(Arrays.asList("Dan Goor", "Michael Schur")),
                new ArrayList<>(Arrays.asList("Andy Samberg", "Terry Crews", "Andre Braugher")),
                "Netflix");
        s6.addTemporadas(new Temporada("2013", 22, 1));
        s6.addTemporadas(new Temporada("2014", 23, 2));
        acervo.addSerie(s6);


        PersistirAcervo.serializarAcervo(); // Salva os dados no arquivo

        System.out.println("Livros, filmes e séries reais adicionados ao acervo com sucesso.");
    }
}
