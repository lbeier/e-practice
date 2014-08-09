[![Build Status](https://snap-ci.com/aceleradora/e-practice/branch/master/build_image)](https://snap-ci.com/aceleradora/e-practice/branch/master)

### e-pr@ctice

## Projeto da aceleradora

* Utiliza o framework Play!
* Rodar o script db.sh para instalar e configurar o banco de dados localmente
* Usar _pass_ como senha de usuário no banco de dados ao rodar o script

## Setup da aplicação:

1. Clone o repositório através do comando
`git clone https://github.com/aceleradora/e-practice.git`
2. Rode o script _setupDataBase.sh_ (é necessário ter o Postgresql instalado e rodando na porta 5432)
3. Compile e rode a aplicação com o comando
`play clean compile run`
4. Acesse a aplicação na URL _http://localhost:9000_
