[![Build Status](https://snap-ci.com/aceleradora/e-practice/branch/master/build_image)](https://snap-ci.com/aceleradora/e-practice/branch/master)

# e-pr@ctice

## Projeto da aceleradora

* Utiliza o framework Play!
* Rodar o script db.sh para instalar e configurar o banco de dados localmente
* Usar _pass_ como senha de usuário no banco de dados ao rodar o script

## Instalando o Play! Framework
1. [Faça do download do Play! Framework](http://downloads.typesafe.com/play/2.2.3/play-2.2.3.zip)
2. Descompacte framework
3. Crie um alias no .bashrc ou .bash_profile

    ```
    alias play=/path/to/play/play
    ```
    
## Setup da aplicação:

1. Clone o repositório através do comando

    ```
    git clone https://github.com/aceleradora/e-practice.git
    ```
    
2. Rode o script _setupDB.py_
3. Compile e rode a aplicação com o comando

    ```
    play clean compile run
    ```

4. Acesse a aplicação na URL _http://localhost:9000_
5. No primeiro acesso, é necessário rodar uma Evolution. Clique em _Apply this script now!_

## Instalando o Postgresql
* Para Mac: [Postgres.app](http://postgresapp.com/)
* Para distribuições Linux baseadas no Debian (Ubuntu, Linux Mint, etc):

    ```
    sudo apt-get update
    sudo apt-get install postgresql
    ```
