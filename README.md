### Proyecto Shoot'em up (matamarcianos) del tipo multi-directional shooter (nave rotatoria)

#### Descripcion

Estamos realizando un juego de navecitas clasico en el que el jugador controla una nave mientras elimina meteoritos y avanza en oleadas

La ventana sirve para mostrar nuestro programa de forma grafica (java.awt)

Los hilos se usan para poder manipular el ciclo principal de nuestro juego y crear un sistema de fotogramas por segundo. (java.util.random)

Los buffers sirven para guardar espacio en la memoria para el procesamiento de imagenes y eliminar el titileo de las imagenes (java.awt.image)

Introducimos Assets (imagenes) a traves de la clase Loader (javax.imageio.ImageIO) y texto que mostramos a traves de la clase Message

Hacemos que la ventana y los assets detecten el teclado y el raton a traves de la clase KeyAdapter y MouseAdapter.

El sistema de vectores sirve para poder generar imagenes con movimiento.

Creamos una clase para cada objeto que utilizaremos en el juego que heredan de la clase MovingObject que permite mover los objetos dentro de la ventana y que a la vez hereda de GameObject que nos permite definir la imagen y la posicion del objeto.

Y por ultimo se crea una clase State que sera el padre de las clases que definan el estado del juego y del menu en cada momento.

#### Creditos

Este trabajo ha sido realizado por Brais Martinez Paredes y Joel Jorge Nunes Vázquez