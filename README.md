# fuzzy
La formula que se eligió para esta prueba fue el de “coeficiente de los dados” 

s = (2|X UNION Y|) / (|X| + |Y|)

esta formula nos pemite saber la similitud de las cadenas mediante una simple division en donde 0 es ninguna coincidencia y 1 es 100% de coincidencia

Pero para poder empezar con el algoritmo primero debemos atender un primer paso el cual consta en separar la cadena de entrada (“cogo”) y la cadena a buscar (“Congo”) en biagramas:

Biagrama 1 :
{“co”, “og”, “go”}
Biagrama 2:
{“co”,”on”,”ng”,”go”}
Una vez separadas procedemos a la búsqueda de las uniones que hay entre ellas.
La unión de los conjuntos de biagramas es {“co”, “go”} 
hay 2 elementos que hacen match en este conjunto por lo tanto 2( X UNION Y) = 2( 2 ) = 4  
Ahora pasamos con la sumatoria de elementos de los 2 biagramas:
 |X| (elementos del biagrama 1) + |Y | (elementos del biagrama 2) = 3 + 4 = 7
La medida de similitud de estas dos cadenas seria 4/7 


Elegí esta formula  ya que me pareció de los mas sencillo de entender para desarrolladores que están empezando a codificar este tipo de búsquedas como fue mi caso al estar haciendo esta practica
También quiero agregar que ya existen varias librerías las cuales  implementan este tipo de búsqueda pero me parece que este es un buen ejemplo para entender un poco como es que se usan 


El articulo y codigo (Open Source) utilizado para esta solucion fue de George Stragand el cual lo publico el 17 de enero del año 2017 
dejo la liga para tener un poco de mayor contexto:


https://www.codeproject.com/Articles/147230/Simple-Fuzzy-String-Similarity-in-Java


