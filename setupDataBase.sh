#!/bin/bash
set -e

red=$(tput setaf 1)
blue=$(tput setaf 4)
green=$(tput setaf 2)
yellow=$(tput setaf 3)
reset=$(tput sgr0)
current_directory=$(pwd)

if [[ "$OSTYPE" == "linux-gnu" ]]; then
  command = "sudo -i -u postgres psql --command='\i ${current_directory}/db.sql'"
else
  command = "cat ./db.sql | psql"
fi

echo "Criando banco de dados com a seguinte configuracao:"
echo "Banco de dados: ${blue}e_practice${reset}"
echo "Usuario: ${blue}aceleradora${reset}"
echo "Senha: ${blue}aceleradora${reset}"

echo "${yellow}Insira a senha do Postgres:${reset}"

if ${command}
then
  echo "${green}[OK] O banco de dados foi configurado com sucesso!${reset}"
else
  echo "${red}[ERRO] Ocorreu um erro durante a configuração do banco de dados.${reset}"
fi
