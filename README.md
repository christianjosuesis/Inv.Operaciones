# Inv.Operaciones
## Resolucion de Motodo Simmplex. ##
Este codigo realiza la resolucion del metodo simplex tomando como base la lectura de un  

  archivo excel donde se descargan las ecuacuiones tal como restriccioines y funcion objetivo  

  dicha ingresion de datos se relizan depues de la colocacion de las variables de holgura.  

  Posteriormente al leer,validar y extraer de los componentes se colocan en un arreglo bidimencional

  
![No se necontro imagen verifica Ruta.](https://raw.githubusercontent.com/christianjosuesis/Inv.Operaciones/84c16696b80781db88ac93c4ce286e5a71e129df/tab1.png "Tabla de inicio.")

  al tener la informacion en el arreglo comenzamos buscando el valos mas bajo por columna para detectar la columna pivote, 

  al detectar ese valor se comienza con la divicion de la ultima columna (R) entre la columna pivote, se busca el resultado mas bajo  


  para detectar la fila pivote posterior a realizar este paso se toma el valor de la interseccion como coeficiente, al identificar el coeficiente  


  tendriamos que igualar a 1 dividiendolo entre si mismo para que de 1 esta divicion se reliza en toda la fila, se divide cada elementoi de la fila  

  entre el coeficiente 

![No se necontro imagen verifica Ruta.](https://raw.githubusercontent.com/christianjosuesis/Inv.Operaciones/84c16696b80781db88ac93c4ce286e5a71e129df/tab2.png "Tabla cociente = 1.")


  continuamos con la implementacion de la formula FA-(PF*FP) FA= Fila actual(valor de la fila actual), PF=Pivote de Fila(valor de la fila que  
  
  se encuentra en la columna pivote), FP= Fila Pivote(valor de la fila pivote paralelo a el valor de la Fila Actual) esta operacion se realiza en  
  
  todas las filas a excepcion de la fila pivote.  
  ![No se necontro imagen verifica Ruta.](https://raw.githubusercontent.com/christianjosuesis/Inv.Operaciones/84c16696b80781db88ac93c4ce286e5a71e129df/tab3.png "Tabla Final.")
