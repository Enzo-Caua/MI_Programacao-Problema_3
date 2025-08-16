package pbl.model.persistencia;

import pbl.model.obras.Filme;
import pbl.model.obras.Livro;
import pbl.model.obras.Serie;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Classe responsável por serializar e salvar listas de objetos do tipo {@link Livro}, {@link Filme} e {@link Serie}
 * em arquivos binários localizados na pasta <code>dados/</code>.
 * <p>
 * Caso a pasta <code>dados</code> não exista, ela será criada automaticamente.
 * </p>
 */
public class Serializacao {

    /**
     * Serializa e salva uma lista de objetos {@link Livro} no arquivo <code>dados/livros.dat</code>.
     * <p>
     * A pasta <code>dados</code> será criada automaticamente, se necessário.
     * </p>
     *
     * @param livros Lista de livros a serem serializados.
     */
    public static void salvarLivro(ArrayList<Livro> livros) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        VerificarArquivo.verificacao("livros.dat");

        try {
            fos = new FileOutputStream("dados/livros.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(livros);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");

        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo!");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo!");
                }
            }
        }
    }

    /**
     * Serializa e salva uma lista de objetos {@link Filme} no arquivo <code>dados/filmes.dat</code>.
     * <p>
     * A pasta <code>dados</code> será criada automaticamente, se necessário.
     * </p>
     *
     * @param filmes Lista de filmes a serem serializados.
     */
    public static void salvarFilme(ArrayList<Filme> filmes) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        VerificarArquivo.verificacao("filmes.dat");

        try {
            fos = new FileOutputStream("dados/filmes.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(filmes);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");

        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo!");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo!");
                }
            }
        }
    }

    /**
     * Serializa e salva uma lista de objetos {@link Serie} no arquivo <code>dados/series.dat</code>.
     * <p>
     * A pasta <code>dados</code> será criada automaticamente, se necessário.
     * </p>
     *
     * @param series Lista de séries a serem serializadas.
     */
    public static void salvarSerie(ArrayList<Serie> series) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        VerificarArquivo.verificacao("series.dat");

        try {
            fos = new FileOutputStream("dados/series.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(series);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");

        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo!");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo!");
                }
            }
        }
    }
}
