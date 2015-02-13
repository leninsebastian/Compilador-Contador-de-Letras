package analexicosintacticoletras;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sebastian
 */
public class Main {

    public final static int CREAR_ARCHIVOS = 1;
    public final static int EJECUATR_ARCHIVOS = 2;
    private static java.util.Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int valor = 0;
        do {
            
            System.out.println("********COMPILADOR CONTADOR DE LETRAS*********");
            System.out.println("----------Ejem: (leninsebastianocampovelez); ------");
            System.out.println("\n" + "Digite 1 para crear los archivos o 2\n"
                    + "si desea ejecutar (para la opcion 2 los\n"
                    + "archivos deben haber sido generados\npreviamente");
            valor = scanner.nextInt();
            switch (valor) {

                case CREAR_ARCHIVOS: {
                    System.out.println("\n*** Generando ***\n");
                    String archLexico = "";
                    String archSintactico = "";
                    if (args.length > 0) {
                        archLexico = args[0];
                        archSintactico = args[1];
                    } else {
                        archLexico = "lexer.txt";
                        archSintactico = "parser.txt";
                    }
                    String[] alexico
                            = {archLexico};
                    String[] asintactico
                            = {"-parser", "AnalizadorSintactico", archSintactico};
                    jflex.Main.main(alexico);
                    try {
                        java_cup.Main.main(asintactico);
                    } catch (Exception ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE,
                                null, ex);
                    }
                    boolean mvAL = moverArch("AnalizadorLexico.java");
                    boolean mvAS = moverArch("AnalizadorSintactico.java");
                    boolean mvSym = moverArch("sym.java");
                    if (mvAL && mvAS && mvSym) {
                        System.out.println("Generado!");
                        System.exit(0);
                    }
                }
                case EJECUATR_ARCHIVOS: {
                    String[] archivoPrueba
                            = {"entrada.txt"};
                    AnalizadorSintactico.main(archivoPrueba);
                    break;
                }
                default: {
                    System.out.println("Opcion no valida ADIOS =(!!!!");
                }
            }
        } while (valor == 2);

    }

    public static boolean moverArch(String archNombre) {
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "lexico/sintactico" + File.separator + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                efectuado = true;
            }
        }
        return efectuado;
    }

   

}
