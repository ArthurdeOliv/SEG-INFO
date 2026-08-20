Nomes: Arthur de Oliveira e Lucas Pacheco Kempfer

Exercicio 1:

	Aluno 1: Download do arquivo no sigaa

	Aluno 1: Move o arquivo baixado para o diretorio do projeto

	Aluno 1: openssl rand -base64 256 > chave.txt
	Para gerar a chave simetrica de 256 bits
	
	Aluno 1: openssl enc -aes-256-cbc -in seg-criptografia.pdf -out arquivo.cifrado -iter 1000 -pass file:chave.txt
	Para cifrar o arquivo baixado com a chave criada
	
	Aluno 1: Enviar para o arquivo cifrado e a chave para o aluno 2 (Usamos email)

	Aluno 2: Baixar o arquivo cifrado e a chave

	Aluno 2: Move os arquivos baixados para o diretorio do projeto

	Aluno 2: openssl enc -d -aes-256-cbc -in arquivo.cifrado -out arquivo.decifrado -iter 1000 -pass file:chave.txt
	
	
Exercicio 2:

	Aluno 1: Download do arquivo no sigaa

	Aluno 1: Move o arquivo baixado para o diretorio do projeto 
	
	Aluno 1 e 2: openssl rand -base64 32 > chave.txt
		Para gerar uma chave simetrica
		
	Aluno 1 e 2: openssl genrsa -out chave.priv 2048
		Para gerar uma chave privada

	Aluno 1 e 2: openssl rsa -in chave.priv -pubout -out chave.pub
		Para gerar uma chave publica a partir da chave privada
	
	Aluno 1: Envia a chave publica gerada para o aluno 2 (Usamos email)
		
	Aluno 2: Envia a chave publica gerada para o aluno 1 (Usamos email)
	
	Aluno 1: Baixa a chave publica do aluno 2 e move para o diretorio do projeto
	
	Aluno 2: Baixa a chave publica do aluno 1 e move para o diretorio do projeto
	
	Aluno 1: openssl dgst -sha256 -sign chave.priv -out seg-criptografia.sig seg-criptografia.pdf 
		Para assinar o arquivo com a chave privada
		
	Aluno 1: openssl enc -aes-256-cbc -in seg-criptografia.pdf -out arquivo.cifrado -iter 1000 -pass gfile:chave.txt
		Para cifrar o arquivo com a chave simetrica
			
	Aluno 1: openssl pkeyutl -encrypt -pubin -inkey chave.pub -in chave.txt -out chave.enc
		Para cifrar a chave simetrica com a chave pública do aluno 2
		
	Aluno 1: Envia o arquivo cifrado e o arquivo gerado na assinatura para o aluno 2 (Usamos email)
	
	Aluno 2: Baixa os arquivos do aluno 1 e move para o diretorio do projeto

	Aluno 2: openssl pkeyutl -decrypt -inkey chave.priv -in chave.enc -out chave.txt
		Para decifrar a chave simétrica com a chave privada
	
	Aluno 2: openssl enc -d -aes-256-cbc -in arquivo.cifrado -out arquivo.decifrado -iter 1000 -pass file:chave.txt
		Para decifrar o arquivo com a chave simétrica
	
	Aluno 2: openssl dgst -sha256 -verify chave.pub -signature seg-criptografia.sig arquivo.decifrado
		Para verificar a assinatura com a chave pública
