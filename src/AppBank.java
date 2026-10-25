package src;
import java.nio.file.Path;
import java.nio.file.Files; 
import java.nio.file.DirectoryStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;



public class AppBank {

    static Path dir = Path.of("Entrada");
    static Path file = dir.resolve("acc_statement_2026.csv");

    static Path dir2 = Path.of("Entrada","Processats");

    static Path transferencies = dir.resolve("transferencies.csv");
    static Path pagaments = dir.resolve("pagaments.csv");
    static Path recarregues = dir.resolve("recarregues.csv");
    

    public static void main(String[] args) throws IOException {

        if (Files.exists(dir)) {
            System.out.println("La carpeta Entrada existeix");
        } else {
            System.out.println("ERROR: No existeix la carpeta Entrada");
            return;
        }

        if (Files.exists(file)) {
            System.out.println("El fitxer original existeix");
        } else {
            System.out.println("ERROR: No existeix el fitxer original");
            return;
        }

        if (!Files.exists(dir2)) {
            Files.createDirectories(dir2);
            System.out.println("Carpeta Processats creada");
        }



        try (DirectoryStream<Path> elements =
        Files.newDirectoryStream(dir)) {

        for (Path element : elements) {
            System.out.println(element.getFileName());
            
            String nom = element.getFileName().toString();
            
            if (nom.matches("acc_statement_\\d{4}\\.csv")) {
                System.out.println("Fitxer original: " + nom);
            }
        }

        }

        int count = 0;
        try (BufferedReader lector =
        Files.newBufferedReader(file, StandardCharsets.UTF_8)) {

            // Aquí leeremos las líneas
            String linea; 
            while ((linea = lector.readLine()) != null) {
                
                if (count == 0) {
                    count++;
                    continue;                    
                } else {
                    System.out.println(linea);
                    count++;
                    String[] camps = linea.split(";");
                    System.out.println(camps[0]);
                    if (camps[0].equalsIgnoreCase("transferir")) {
                        System.out.println("És una transferència");
                    } else if (camps[0].equalsIgnoreCase("Pago con tarjeta") || camps[0].equalsIgnoreCase("Reembolso de tarjeta")) {
                        System.out.println("És un pagament");
                    } else if (camps[0].equalsIgnoreCase("recargas")) {
                        System.out.println("És una recarrega");
                    } else {
                        System.out.println("Altres moviments");
                    }
                }
            }


        }  

        }
    
    
}


/*      Path dir = Path.of("dades");               // representa la ruta "dades" (encara no crea res)
        Path f   = dir.resolve("notes.txt");       // representa la ruta "dades/notes.txt"

        Files.createDirectories(dir);              // crea el directori i els pares que faltin
        Files.createFile(f);                       // crea el fitxer buit; si ja existeix llança una excepció

        System.out.println(Files.exists(f));       // true: comprova si existeix al disc
        System.out.println(f.getFileName());       // notes.txt: el nom, sense la ruta
        System.out.println(Files.size(f));         // 0: la mida en bytes

        Files.delete(f);                           // esborra el fitxer; si falla llança una excepció que diu per què
    }
}*/