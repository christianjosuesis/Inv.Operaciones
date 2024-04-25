package simplex.com.example;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class simplex {
    private static int obtenerContenidoCelda(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return Integer.parseInt(cell.getStringCellValue());
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1 : 0;
            default:
                return 0;
        }
    }

    private static void printDoubleArray(int numFilas, int numColumnas, double[][] datos) {
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
            System.out.println("Error al cerrar el archivo de Excel.");
        }
    }

    public static void guardarInformacion(Workbook datos, String rutaArchivo) {
        try {
            FileOutputStream escribir = new FileOutputStream(rutaArchivo);
            datos.write(escribir);
            escribir.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de Excel. Asegúrate de que el archivo no está abierto en otro programa.");
        }
    }
    public static void simplex(double[][] tableau) {
        int m = tableau.length; // Número de filas
        int n = tableau[0].length; // Número de columnas

        // Imprime el arreglo antes de las operaciones
        System.out.println("Arreglo original:");
        printDoubleArray(m, n, tableau);

        // Encuentra la columna pivote (la columna más negativa)
        int pivotCol = -1;
        double minValue = 0;
        for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (tableau[i][j] < minValue) {
                minValue = tableau[i][j];
                pivotCol = j;
            }
        }
    }
        // Si no hay columnas negativas, terminamos
        if (pivotCol == -1) {
            return;
        }

        // Encuentra la fila pivote (el resultado más pequeño de la división)
        int pivotRow = -1;
        minValue = Double.MAX_VALUE;
        for (int i = 0; i < m - 1; i++) {
            if (tableau[i][pivotCol] <= 0) continue;
            double value = tableau[i][n - 1] / tableau[i][pivotCol];
            if (value < minValue) {
                minValue = value;
                pivotRow = i;
            }
        }

        // Guarda el valor de intersección en la variable coeficiente
        double coeficiente = tableau[pivotRow][pivotCol];

        // Divide la fila pivote por el coeficiente
        for (int j = 0; j < n; j++) {
            tableau[pivotRow][j] /= coeficiente;
        }

        // Imprime el arreglo final después de que el algoritmo haya terminado
        System.out.println("Arreglo coeficiente =1:");
        printDoubleArray(m, n, tableau);
        subtractCoefficientFromRow(pivotRow, coeficiente, tableau, pivotCol);
       
    }
    private static void subtractCoefficientFromRow(int pivotRow, double coeficiente, double[][] tableau, int pivotCol) {
        int m = tableau.length; // Número de filas
        int n = tableau[0].length; // Número de columnas
        double[][] tabla = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tabla[i][j] = tableau[i][j];
            }
        }
        // Realiza la operación en cada fila, excepto en la fila pivote
        for (int i = 0; i < m; i++) {
            if (i == pivotRow)continue;
            for (int j = n-1 ; j >= 0; j--) {  
            tabla [i][j]=tableau[i][j] -(tableau[i][pivotCol]*tableau[pivotRow][j]);
               
        }
        }
    
        // Imprime el arreglo después de las operaciones
        System.out.println("Arreglo después de las operaciones:");
        printDoubleArray(m, n, tabla);
    }
    public static void main(String args[]) {
        Scanner info = new Scanner(System.in);
        FileInputStream lector = null;
        Workbook libro = null;
        Sheet hoja = null;
        try {
            lector = new FileInputStream("C:/Users/chris/OneDrive/Documents/simplex.xlsx");
            libro = new XSSFWorkbook(lector);
            hoja = libro.getSheetAt(0);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo de Excel.");
            return;
        }

        int numFilas = hoja.getLastRowNum() + 1; // Número de filas
        int numColumnas = hoja.getRow(0).getLastCellNum(); // Número de columnas

        int[][] datos = new int[numFilas][numColumnas];
        try {
            for (int i = 0; i < numFilas; i++) {
                Row row = hoja.getRow(i);

                for (int j = 0; j < numColumnas; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    int contenidoCelda = obtenerContenidoCelda(cell);
                    datos[i][j] = contenidoCelda;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convertir los datos a double para el método simplex
        double[][] tableau = new double[numFilas][numColumnas];
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                tableau[i][j] = datos[i][j];
            }
        }

        // Llamar al método simplex
        simplex(tableau);
        

        // Cerrar el archivo de Excel
        cerrarArchivo(libro);
    }
}