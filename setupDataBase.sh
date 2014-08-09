#!/bin/bash
set -e

red=$(tput setaf 1)
blue=$(tput setaf 4)
green=$(tput setaf 2)
yellow=$(tput setaf 3)
reset=$(tput sgr0)
current_directory=$(pwd)

echo "Criando banco de dados com a seguinte configuracao:"

if ["$(uname)" == "Darwin"]; then
  command=$(cat ./db.sql | psql)
else  
  command=$(sudo -i -u postgres psql --command="\\i ${current_directory}/db.sql")
fi

echo "Banco de dados: ${blue}e_practice${reset}"
echo "Usuario: ${blue}aceleradora${reset}"
echo "Senha: ${blue}aceleradora${reset}"

if $(command)
then
  echo "${green}[OK] O banco de dados foi configurado com sucesso!${reset}"
else
  echo "${red}[ERRO] Ocorreu um erro durante a configuração do banco de dados.${reset}"
fi
