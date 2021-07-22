# Práctica de laboratorio 04: Servicios Web

**1.  Objetivo Alcanzado**

      o	Diseñar funcionalidades para el uso de servicios Restful.
      o	Realizar las peticiones al servidor desde el Postman.
      o	Implementar correctamente las cuatro operaciones POST, GET, PUT y DELETE, en método de las clases del proyecto.

**2.	Gestión de cuentas de Usuario de los clientes de la distribuidora**

      o Iniciar Sesión con base a un Usuario y contraseña.
      
![image](https://user-images.githubusercontent.com/49213231/124609783-8fac5180-de35-11eb-94e3-332c2123d48b.png)
      
   
![image](https://user-images.githubusercontent.com/49213231/124609362-2c222400-de35-11eb-927b-c935b005644b.png)

      
      
      o Registrar cuenta del cliente con base al número de cedula.
      
![image](https://user-images.githubusercontent.com/49213231/124610266-f92c6000-de35-11eb-899c-34438ead4ac2.png)
      
![image](https://user-images.githubusercontent.com/49213231/124609936-b23e6a80-de35-11eb-9510-6f1795c4aa6d.png)

      
      o Modificar datos de la cuenta y personales del cliente.
      
 ![image](https://user-images.githubusercontent.com/49213231/124610340-08aba900-de36-11eb-8543-61715efb38b3.png)
      
 ![image](https://user-images.githubusercontent.com/49213231/124609980-bbc7d280-de35-11eb-8788-0d3eeade8105.png)
      
      
      o Anular cuenta del cliente (Eliminado lógico)
      
 ![image](https://user-images.githubusercontent.com/49213231/124610386-12351100-de36-11eb-89b9-24c73c7f49e4.png)
      
 ![image](https://user-images.githubusercontent.com/49213231/124610055-c7b39480-de35-11eb-9161-48f5c24b3c6a.png)
 
 ![image](https://user-images.githubusercontent.com/49213231/124611201-d3ec2180-de36-11eb-9408-b6a61d9dbd95.png)
 
 
 
 **3. Gestión de Pedidos**
 
   o  Listar productos del catálogo organizados por categorías con base a la selección de una bodega.
   
 
![image](https://user-images.githubusercontent.com/49213231/124611232-dc445c80-de36-11eb-8252-a42c053102e1.png)

![image](https://user-images.githubusercontent.com/49213231/124611278-e6665b00-de36-11eb-8ad8-16cde864b16e.png)
   
   
   o	Revisar el estado de los pedidos
   
![image](https://user-images.githubusercontent.com/49213231/124611323-f1b98680-de36-11eb-8f98-ead45b20fa1f.png)

![image](https://user-images.githubusercontent.com/49213231/124611353-fa11c180-de36-11eb-85dc-c46c0d69c359.png)


 **Resultados obtenidos**
 
Postman no realizo las peticiones a nuestro servidor, considerar los parámetros necesarios para cada servicio de acuerdo a la operación solicitada GET, PUT, POST, DELETE.


**Conclusiones**

RESTFUL es uno de los servicios web más importantes, más usados, está basado en la arquitectura REST y se basan en HTTP para el intercambio de información, realiza peticiones a recursos (servidor) y no necesita ningún tipo de encapsulado extra. Este servicio es más ligero, pero a la vez tiene más limitaciones a comparación del servicio web SOAP. De acuerdo con la documentación SOAP tiene la capacidad para integrar en sistemas heterogéneos mientras que Restful es usado en la web y está dirigida para clientes desconocidos.

Con esta practica desarrollada se pudo analizar de mejor manera los cuatro servicios RESTFUL.

![image](https://user-images.githubusercontent.com/49213231/124611592-33e2c800-de37-11eb-8a0b-01df431895e6.png)


**Recomendaciones**

o Para el buen funcionamiento del proyecto JEE se debe agregar las librerías correspondientes.

![image](https://user-images.githubusercontent.com/49213231/124611745-58d73b00-de37-11eb-94ab-ea17ef3685d3.png)


o Considerar la librería para el conector a la base de datos MYSQL, esta debe estar agregada en el servidor Glassfish.


![image](https://user-images.githubusercontent.com/49213231/124611807-6a204780-de37-11eb-924b-cf09eb890b38.png)








