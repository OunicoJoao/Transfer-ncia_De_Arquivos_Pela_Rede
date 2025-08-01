# Transfer-ncia_De_Arquivos_Pela_Rede

Este projeto visa resolver um problema real que eu enfrentava.
Salvo arquivos da faculdade no meu notebook ou no meu PC, porém posso precisar 
de um mesmo arquivo que só esta disponível em um dos computadores. A minha ideia
era "sincronizar" os computadores, possibilitando passar arquivos (inicialmente) 
de um para o outro e futuramente pastas completas. 
Sem a necessidade de um pendrive, ou depender de Google Drive ou algo assim.

Pesquisei possibilidades para tornar isso possível, e então Sockets e Server 
Sockets do Java se mostraram o caminho. Logo quando terminei a matéria de 
Orientação a Objetos foquei em por a ideia em prática.

O sistema foi rapidamente implementado, pois se trata de um ideia tão simples e eficaz. 
Como a comunicação irá ser entre dois sistemas Java, basta eu usar um objeto Serializable, 
representando o arquivo, com nome e conteúdo (bytes)  e transferir o objeto pela rede 
entre os computadores. Eu escolho o computador de destino, lendo um arquivo (enderecos.txt) 
onde anoto com um padrão específico o nome do dispositivo e o ip, na hora do envio 
apenas digito o id. Do outro lado, há a leitura do arquivo recebido e então a escrita dele.

Super simples e completamente funcional para o meu caso hahah...
Foi bem legal fazer tudo se conectar, pois saiu de um pensamento "E se eu fizesse assim?", 
"Mas é tão simples!", para um "Caramba! Funcionou!". 
Percebi que podemos criar complexidade em nossas mentes, como se algo simples não 
pudesse funcionar, mas de fato pode sim.
