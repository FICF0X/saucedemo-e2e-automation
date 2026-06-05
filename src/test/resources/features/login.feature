# language: es
Característica: Inicio de sesión
  Como comprador
  Quiero iniciar sesión en la tienda
  Para poder navegar y comprar productos

  Escenario: Un comprador registrado inicia sesión correctamente
    Dado el comprador está en la página de inicio de sesión
    Cuando el comprador inicia sesión con credenciales válidas
    Entonces se muestra la página de productos

  Escenario: Un comprador bloqueado no puede iniciar sesión
    Dado el comprador está en la página de inicio de sesión
    Cuando el comprador bloqueado intenta iniciar sesión
    Entonces se muestra un mensaje de cuenta bloqueada

  Escenario: Se rechaza el inicio de sesión con credenciales desconocidas
    Dado el comprador está en la página de inicio de sesión
    Cuando el comprador inicia sesión con un usuario y contraseña desconocidos
    Entonces se muestra un mensaje de credenciales inválidas
