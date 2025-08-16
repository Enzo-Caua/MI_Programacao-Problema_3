package pbl.model.persistencia;

import java.io.File;
import java.io.IOException;

/**
 * Classe responsável por verificar a existência de arquivos e diretórios utilizados
 * na persistência de dados do sistema. Caso o diretório ou arquivo não existam,
 * eles são criados automaticamente.
 */
public class VerificarArquivo {

    /**
     * Verifica se o diretório "dados" e o arquivo com o nome especificado existem.
     * Se não existirem, o método cria ambos.
     *
     * @param nomeArquivo Nome do arquivo a ser verificado (dentro do diretório "dados").
     */
    public static void verificacao(String nomeArquivo) {
        File pasta = new File("dados");
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        File arquivo = new File(pasta, nomeArquivo);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo " + nomeArquivo);
            }
        }
    }
}
