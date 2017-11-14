# Guia de instalação: (Ubuntu 16.10)

Projeto Web de avaliação da Softplan.


## Install

### Instale o NGINX

```sh
$ sudo apt-get install nginx
```

### Instale o GIT (caso não tenha instalado)

```sh
$ sudo apt-get install git
```

### Instale o Node.js

```sh
$ sudo apt-get update
$ sudo apt-get install nodejs nodejs-legacy npm
```

### Instale o Webpack

```sh
$ sudo npm install -g webpack
```

### Clone o projeto sajadv-processos-web na pasta desejada

```sh
$ git clone https://danielqa@bitbucket.org/danielqa/sajadv-processos-web.git
```

### Instale as libs do projeto e rode a build

```sh
$ cd sajadv-processos-web
$ npm install
$ npm run build
```

### Edite o arquivo sajadv-processos.conf (nginx.conf)

```sh
$ gedit sajadv-processos.conf
```
* No atributo 'root' do objeto 'location /', altere o valor com o caminho correto da pasta '/app/' do projeto web (com '/' no final)
* No atributo 'alias' do objeto 'location /node_modules/', altere o valor com o caminho correto da pasta '/node_modules/' do projeto web (com '/' no final)

### Copie o arquivo para o NGINX

```sh
$ sudo cp sajadv-processos.conf /etc/nginx/conf.d/
```

### Restarte o servidor web NGINX

```sh
$ sudo service nginx restart
```

### Edite o arquivo hosts do Linux para adicionar o host da aplicação

```sh
$ sudo gedit /etc/hosts
```
* "127.0.0.1	www.gestaodeprocessos.com.br" - Adicione o texto entre aspas logo abaixo dos hosts existentes


