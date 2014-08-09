import os
from sys import platform as _platform

print "Banco de dados: e_practice"
print "Usuario: aceleradora"
print "Senha: aceleradora"

if _platform == "linux" or _platform == "linux2":
    command = os.system('sudo -i -u postgres psql --command="\i ${current_directory}/db.sql"')
elif _platform == "darwin":
    command = os.system("cat ./db.sql | psql")
else:
    print 'Qual sistema tu ta usando?'
    os.system('exit 1')

if command == 0:
    print "[OK] O banco de dados foi configurado com sucesso!"
else:
    print "[ERRO] Ocorreu um erro durante a configuracao do banco de dados."