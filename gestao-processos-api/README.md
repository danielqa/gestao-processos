# Guia de instalação: (Ubuntu 16.10)

Projeto Api de avaliação da Softplan.


## Install

### Instale o GIT (caso não tenha instalado)

```sh
$ sudo apt-get install git
```

### Instale o PostgreSQL

```sh
$ sudo apt-get update
$ sudo apt-get install postgresql
```

### Instale o Java 8

```sh
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```

### Instale o Gradle

```sh
$ sudo add-apt-repository ppa:cwchien/gradle
$ sudo apt-get update
$ sudo apt-get install gradle
```

### Crie a base de dados do projeto

```sh
$ sudo -i -u postgres
$ createdb sajadv
```

### Altere a senha do usuário "postgres"

```sh
$ psql
$ \password postgres
```
* Repita a senha "postgres" duas vezes
```sh
$ \q
$ exit
```

### Clone o projeto sajadv-processos-api na pasta desejada

```sh
$ git clone https://danielqa@bitbucket.org/danielqa/sajadv-processos-api.git
```

### Suba a aplicação (Spring Boot) via Gradle

```sh
$ cd sajadv-processos-api
$ gradle bootRun
```

