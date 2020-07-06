# Contribuição e Execução do Projeto

### Configurando seu ambiente e executando projeto

1. Se não tiver instalado, realize as seguintes instalações:
   1. __Java__ na versão 1.8 e posterior.
   2. __Spring Tool Suite 4__. Se prefereir pode usar apenas o Eclipse padrão.
   3. Instalar e configurar um banco __MySQL__
2. Instalar e configurar o __Lombok__ na sua IDE. 
   1. [Download](https://projectlombok.org/download)
   2. [Veja como instalar Lombok no Eclipse e STS](https://projectlombok.org/setup/eclipse)
3. Abra o projeto no Eclipse e aguarde o maven realizar o Download das depêndencias.
4. Configure suas credênciais de banco
   1. Abra os arquivos `application.properties` e `application-test.properties` 
   2. No parâmetro `spring.datasource.url` informe o host onde seu banco MySQL está, Alterando `locahost:3600`* com o IP e Porta do seu banco.
   3. No parâmetro `spring.datasource.username` informe seu usuário do banco, no `spring.datasource.password` sua senha.
5. Para executar, se estiver usando o STS - Spring Tool Suite, clique com o botão direito do mouse no projeto em `Run As > Spring Boot App`, caso esteja usando o Eclipse padrão vá até a classe `GerenciadorDePessoasApplication`, no pacote principal `com.trinity.peoplemanagement`, clique com o botão direito do mouse no projeto em `Run As > Java Application`
6. Na subida do projeto ele realizará as migrações do banco, se realizou as configurações corretas informadas no __tópico 4__.
7. A applicação estará servindo em http://localhost:8080;

> No parâmetro `spring.datasource.url = jdbc:mysql://localhost:3306/people_manager?createDatabaseIfNotExist=true&serverTimezone=UTC` apenas mude o localhost para o IP do seu banco, nesse trecho já estará criando a base de dados e configurando o TimeZone.

### Contributing

- Faça um fork desse repositório;
- Cria uma branch com sua feature: `git checkout -b minha-feature`;
- Commit suas alterações: `git commit -m 'feat: Minha nova feature'`;
- Faça o push da branch: `git push origin minha-feature`.
- Solicite um pull request

> Quando seu pull request for aceito, sua branch pode ser deletada.

> A API REST está disponível neste [link](https://github.com/wwwgomes/people-manager)
