package pbl.model.persistencia;

import pbl.model.obras.Filme;
import pbl.model.obras.Livro;
import pbl.model.obras.Serie;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Classe responsável pela desserialização (leitura) de arquivos binários contendo listas
 * de objetos do tipo {@link Livro}, {@link Filme} e {@link Serie}.
 * <p>
 * Os dados são lidos a partir da pasta <code>dados/</code>, onde foram previamente salvos.
 * </p>
 */
public class Desserializacao {

    /**
     * Lê o arquivo <code>dados/livros.dat</code> e retorna a lista de livros desserializada.
     *
     * @return Lista de objetos {@link Livro} lidos do arquivo, ou {@code null} em caso de erro.
     */
    public static ArrayList<Livro> lerLivros() {
        ArrayList<Livro> livros = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        VerificarArquivo.verificacao("livros.dat");

        try {
            fis = new FileInputStream("dados/livros.dat");
            ois = new ObjectInputStream(fis);
            livros = (ArrayList<Livro>) ois.readObject();
            return livros;

        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada!");

        } catch (EOFException e) {
            return new ArrayList<>();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo!");
                }
            }
        }
        return null;
    }

    /**
     * Lê o arquivo <code>dados/filmes.dat</code> e retorna a lista de filmes desserializada.
     *
     * @return Lista de objetos {@link Filme} lidos do arquivo, ou {@code null} em caso de erro.
     */
    public static ArrayList<Filme> lerFilmes() {
        ArrayList<Filme> filmes = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        VerificarArquivo.verificacao("filmes.dat");

        try {
            fis = new FileInputStream("dados/filmes.dat");
            ois = new ObjectInputStream(fis);
            filmes = (ArrayList<Filme>) ois.readObject();
            return filmes;

        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada!");

        } catch (EOFException e) {
            return new ArrayList<>();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo!");
                }
            }
        }
        return null;
    }

    /**
     * Lê o arquivo <code>dados/series.dat</code> e retorna a lista de séries desserializada.
     *
     * @return Lista de objetos {@link Serie} lidos do arquivo, ou {@code null} em caso de erro.
     */
    public static ArrayList<Serie> lerSeries() {
        ArrayList<Serie> series = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        VerificarArquivo.verificacao("series.dat");

        try {
            fis = new FileInputStream("dados/series.dat");
            ois = new ObjectInputStream(fis);
            series = (ArrayList<Serie>) ois.readObject();
            return series;

        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada!");

        } catch (EOFException e) {
            return new ArrayList<>();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo!");
                }
            }
        }
        return null;
    }
}
