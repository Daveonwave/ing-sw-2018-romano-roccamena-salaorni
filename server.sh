if [[ $LOCAL -eq 0 ]]; then
  echo "Using local codebase..."
  java \
    -Djava.rmi.server.useCodebaseOnly=false \
    -Djava.rmi.server.logCalls=true \
    -Djava.rmi.server.codebase="file:///`pwd`/" \
    ingsw2018.ServerApp
else
  echo "Using remote codebase..."
  java \
    -Djava.rmi.server.useCodebaseOnly=false \
    -Djava.rmi.server.logCalls=true \
    -Djava.rmi.server.codebase="http://localhost:8080/" \
    ingsw2018.ServerApp
fi