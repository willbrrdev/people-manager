<h1 align="center">
    Gerenciador de Cadastro de Pessoas
</h1>

<h4 align="center">
  <strong>API RESTful</strong>
</h4>

<p align="center">
  <img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT&color=7159c1&labelColor=000000">
</p>

<p align="center">
  <a href="#floppy_disk-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#computer-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#family_man_man_boy_boy-como-contribuir-ou-executar">Como Contribuir</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-license">License</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#blue_heart-contributors">Colaboradores</a>
</p>

<br>

<p align="center" style="color: #333">
<strong>People Manager</strong> é um gerenciador de cadastro de pessoas. Podendo adicionar, remover, atualizar e ver o detalhe do cadastro de uma pessoa.</p>

## :floppy_disk: Tecnologias

Este projeto foi desenvolvido usando as seguintes tecnologias:

<ul>
  <li><a href="https://start.spring.io/">Spring Initializr</a></li>
  <li><a href="https://spring.io/projects/spring-boot">Spring Boot</a></li>
  <li><a href="https://spring.io/projects/spring-data-jpa">Spring Data JPA</a></li>
  <li><a href="https://swagger.io/">Swagger</a></li>
  <li><a href="https://www.mysql.com/">MySQL</a></li>
  <li><a href="https://flywaydb.org/">Flyway</a></li>
  <li><a href="https://projectlombok.org/">Lombok</a></li>
</ul>

## :computer: Projeto

API REST da aplicação Gerenciador de Pessoas

##### 🔢 EndPoints

__API V1__

`GET /api/v1/pessoas`

__Listar todos os cadastrados__
``` 
Response
[
  {
    "id": 1,
    "nome": "Viviane Maria",
    "email": "vivianemaria@gmail.com",
    "dataNascimento": "1994-05-12",
    "naturalidade": "Recife",
    "nacionalidade": "Brasilieiro",
    "cpf": "94659530086",
    "sexo": "F"
  },
  {
    "id": 2,
    "nome": "Salomão",
    "email": "salomao@gmail.com",
    "dataNascimento": "1994-05-11",
    "naturalidade": "Recife",
    "nacionalidade": "Brasilieiro",
    "cpf": "11164480413",
    "sexo": "M"
  }
]
```

`POST /api/v1/pessoas`

__Cadastrar Pessoa__

``` 
Request
{
  "nome": "Salomão",
  "email": "salomao@gmail.com",
  "dataNascimento": "1994-05-11",
  "naturalidade": "Recife",
  "nacionalidade": "Brasilieiro",
  "cpf": "11164480413",
  "sexo": "M"
}
```

``` 
Response
{
  "id": 2,
  "nome": "Salomão",
  "email": "salomao@gmail.com",
  "dataNascimento": "1994-05-11",
  "naturalidade": "Recife",
  "nacionalidade": "Brasilieiro",
  "cpf": "11164480413",
  "sexo": "M"
}
```

`GET /api/v1/pessoas/{id}`

__Lista um cadastro__

``` 
Response
{
  "id": 2,
  "nome": "Salomão",
  "email": "salomao@gmail.com",
  "dataNascimento": "1994-05-11",
  "naturalidade": "Recife",
  "nacionalidade": "Brasilieiro",
  "cpf": "11164480413",
  "sexo": "M"
}
```

`PUT /api/v1/pessoas/{id}`

__Atualiza Cadastro__

``` 
Request
{
  "nome": "Salomão",
  "email": "salomao@gmail.com",
  "dataNascimento": "1994-05-11",
  "naturalidade": "Recife",
  "nacionalidade": "Brasilieiro",
  "cpf": "11164480413",
  "sexo": "M"
}
```

``` 
Response
{
  "id": 2,
  "nome": "Salomão",
  "email": "salomao@gmail.com",
  "dataNascimento": "1994-05-11",
  "naturalidade": "Recife",
  "nacionalidade": "Brasilieiro",
  "cpf": "11164480413",
  "sexo": "M"
}
```

`DELETE /api/v1/pessoas/{id}`

__Remove Cadastro__
> Codigo de retorno (204)

---
__API V2__

`GET /api/v2/pessoas`

__Listar todos os cadastrados__
``` 
Response
[
  {
    "id": 1,
    "nome": "Ana Paula",
    "email": "anapaula@gmail.com",
    "dataNascimento": "1994-05-11",
    "naturalidade": "Recife",
    "nacionalidade": "Brasilieiro",
    "cpf": "81994099089",
    "sexo": "M",
    "endereco": {
      "rua": "Rua das Ninfas",
      "numero": "57",
      "bairro": "Graças",
      "cidade": "Recife",
      "uf": "PE",
      "cep": "54730000",
      "complemento": "Em frente ao Shopping Recife, Apartamento 15, Andar 7"
    }
  },
  {
    "id": 2,
    "nome": "Williams Gomes",
    "email": "williamsgomes@gmail.com",
    "dataNascimento": "2020-07-06",
    "naturalidade": "Recife",
    "nacionalidade": "Brasileiro",
    "cpf": "21473092060",
    "sexo": "M",
    "endereco": {
      "rua": "Da Lagoa",
      "numero": "10",
      "bairro": "Tiuma",
      "cidade": "São Lourenço da Mata",
      "uf": "PE",
      "cep": "54730000",
      "complemento": null
    }
  }
]
```

`POST /api/v2/pessoas`

__Cadastrar Pessoa__

``` 
Request
{
	"nome": "Williams",
	"email": "www3@gmail.com",
	"dataNascimento": "1994-05-11",
	"naturalidade": "Recife",
	"nacionalidade": "Brasilieiro",
	"cpf": "819.940.990-89",
	"sexo": "M",
	"endereco": {
    "bairro": "Graças",
    "cep": 54730000,
    "cidade": "Recife",
    "complemento": "Em frente ao Shopping Recife, Apartamento 15, Andar 7",
    "numero": 57,
    "rua": "Rua das Ninfas",
    "uf": "PE"
  }
}
```

``` 
Response
{
  "id": 6,
  "nome": "Williams",
  "email": "www3@gmail.com",
  "dataNascimento": "1994-05-11",
  "naturalidade": "Recife",
  "nacionalidade": "Brasilieiro",
  "cpf": "81994099089",
  "sexo": "M",
  "endereco": {
    "rua": "Rua das Ninfas",
    "numero": "57",
    "bairro": "Graças",
    "cidade": "Recife",
    "uf": "PE",
    "cep": "54730000",
    "complemento": "Em frente ao Shopping Recife, Apartamento 15, Andar 7"
  }
}
```

`GET /api/v2/pessoas/{id}`

__Lista um cadastro__

``` 
Response
{
  "id": 6,
  "nome": "Williams",
  "email": "www3@gmail.com",
  "dataNascimento": "1994-05-11",
  "naturalidade": "Recife",
  "nacionalidade": "Brasilieiro",
  "cpf": "81994099089",
  "sexo": "M",
  "endereco": {
    "rua": "Rua das Ninfas",
    "numero": "57",
    "bairro": "Graças",
    "cidade": "Recife",
    "uf": "PE",
    "cep": "54730000",
    "complemento": "Em frente ao Shopping Recife, Apartamento 15, Andar 7"
  }
}
```

`PUT /api/v2/pessoas/{id}`

__Atualiza Cadastro__

``` 
Request
{
	"nome": "Williams",
	"email": "www3@gmail.com",
	"dataNascimento": "1994-05-11",
	"naturalidade": "Recife",
	"nacionalidade": "Brasilieiro",
	"cpf": "819.940.990-89",
	"sexo": "M",
	"endereco": {
    "bairro": "Graças",
    "cep": 54730000,
    "cidade": "Recife",
    "complemento": "Em frente ao Shopping Recife, Apartamento 15, Andar 7",
    "numero": 57,
    "rua": "Rua das Ninfas",
    "uf": "PE"
  }
}
```

``` 
Response
{
  "id": 6,
  "nome": "Williams",
  "email": "www3@gmail.com",
  "dataNascimento": "1994-05-11",
  "naturalidade": "Recife",
  "nacionalidade": "Brasilieiro",
  "cpf": "81994099089",
  "sexo": "M",
  "endereco": {
    "rua": "Rua das Ninfas",
    "numero": "57",
    "bairro": "Graças",
    "cidade": "Recife",
    "uf": "PE",
    "cep": "54730000",
    "complemento": "Em frente ao Shopping Recife, Apartamento 15, Andar 7"
  }
}
```

`DELETE /api/v2/pessoas/{id}`

__Remove Cadastro__
> Codigo de retorno (204)

## :family_man_man_boy_boy: Como Contribuir ou Executar

<p>Para contribuir e/ou instruções de como rodar a aplicação, <a href="CONTRIBUTING.md">veja nessas instruções</a>.</p>

## :books: Documentation

Ao executar a API a documentação dela ficará disponível em `/swagger-ui.html`

> Ainda não sabe como executar a API? [Veja aqui](CONTRIBUTING.md)

## :blue_heart: Colaboradores

<table>
  <tr>
    <td align="center" style="border: none;">
      <a href="https://github.com/wwwgomes">
        <img style="border-radius: 50px;" src="https://avatars3.githubusercontent.com/u/57773072?s=400&u=3e7a2a8a432118afa4446cacfcaf9c118056db7b&v=4" width="70px;" alt="Williams Gomes"/>
        <br />
        <sub>
          <b>Williams Gomes</b>
        </sub>
      </a>
      <br />
      <p><scan title="Code">💻</scan>|<scan title="Documentation">📖</scan>|<scan title="Bugs">🐛</scan></p>
    </td>
  </tr>
</table>

---
