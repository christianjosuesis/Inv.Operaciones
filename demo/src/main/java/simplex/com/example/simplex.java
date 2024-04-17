package simplex.com.example;
 import org.apache.poi.ddf.EscherColorRef.SysIndexProcedure;
 import org.apache.poi.ss.usermodel.*;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.util.InputMismatchException;
 import java.util.Iterator;
 import java.util.Scanner;
 import javax.print.DocFlavor.STRING;
 import java.time.LocalTime;
 import java.time.format.DateTimeParseException;
public class simplex{
    private static int obtenerContenidoCelda(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                // Modificación: Convertí la cadena a un entero
                return Integer.parseInt(cell.getStringCellValue());
            case NUMERIC:
                // Modificación: Convertí el valor numérico a un entero
                return (int) cell.getNumericCellValue();
            case BOOLEAN:
                // Modificación: Convertí el valor booleano a un entero (1 para true, 0 para false)
                return cell.getBooleanCellValue() ? 1 : 0;
            default:
                return 0;
        }
    }
    
    private static void printDoubleArray(int numFilas, int numColumnas, int[][] datos)
    {
         // Imprimir el arreglo bidimensional
         for (int i = 0; i < numFilas; i++) {
             for (int j = 0; j < numColumnas; j++) {
                 System.out.print(datos[i][j] + "\t");
             }
             System.out.println();
         }
    }
    public static void cerrarArchivo(Workbook datos) {
        try {
            datos.close();
        } catch (IOException e) {
            System.out.println( "Error al cerrar el archivo de Excel.");
        }
    }    
    public static void guardarInformacion(Workbook datos, String rutaArchivo) {
        try {
            FileOutputStream escribir = new FileOutputStream(rutaArchivo);
            datos.write(escribir);
            escribir.close(); // Es importante cerrar el FileOutputStream después de usarlo
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de Excel. Asegúrate de que el archivo no está abierto en otro programa.");
        }
    }
    public static void main(String args[]){
        Scanner info= new Scanner(System.in);
        FileInputStream lector = null;
        Workbook libro = null;
        Sheet hoja = null;
        try {
            lector = new FileInputStream("C:/Users/chris/OneDrive/Documents/simplex.xlsx");
            libro = new XSSFWorkbook(lector);
            hoja = libro.getSheetAt(0);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo de Excel. Asegúrate de que el archivo existe y es accesible.");
            return;
        }

        int numFilas = hoja.getLastRowNum() + 1; // Número de filas
        int numColumnas = hoja.getRow(0).getLastCellNum(); // Número de columnas más la columna de conteo

        int[][] datos = new int[numFilas][numColumnas];

        for (int i = 0; i < numFilas; i++) {
            Row row = hoja.getRow(i);

            for (int j = 0; j < numColumnas; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                int contenidoCelda = obtenerContenidoCelda(cell);
                datos[i][j] = contenidoCelda;

            }
        }

        printDoubleArray(numFilas, numColumnas, datos);
        guardarInformacion(libro, "C:/Users/chris/OneDrive/Documents/simplex.xlsx");
        cerrarArchivo(libro);
    


    }
   
}