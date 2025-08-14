# Sistema Empresa – Upload e Gerenciamento de Dados

Aplicação web desenvolvida em **Java** com **Spring Boot**, **JPA/Hibernate** e **H2 Database** para gerenciar dados de **Produtos** e **Clientes**.

## 📌 Funcionalidades

- **Upload de arquivo XML** contendo dados de produtos e clientes.
- **Leitura e parse do XML** usando JAXB.
- **Persistência** no banco de dados H2 (arquivo local).
- **Consulta centralizada** listando produtos e clientes.
- **Exclusão direta** a partir da listagem.
- **CRUD completo** (criação e edição) para produtos e clientes.
- Interface web usando **Thymeleaf**, HTML e CSS.

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Thymeleaf**
- **JAXB** (Jakarta XML Binding)
- **Maven**

## 📂 Estrutura do XML Aceito

```xml
<DadosEmpresa>
    <Produtos>
        <Produto>
            <id>1</id>
            <nome>Notebook Gamer</nome>
            <descricao>Processador i7, 16GB RAM, SSD 512GB</descricao>
            <preco>5000.00</preco>
            <categoria>Eletrônicos</categoria>
            <estoque>10</estoque>
        </Produto>
    </Produtos>
    <Clientes>
        <Cliente>
            <id>101</id>
            <nome>Ana Silva</nome>
            <email>ana.silva@example.com</email>
            <endereco>Rua das Flores, 123, Centro</endereco>
            <telefone>+5511987654321</telefone>
        </Cliente>
    </Clientes>
</DadosEmpresa>

Como Executar

Clonar o repositório

git clone https://github.com/Jacqueline-Paiva/Sistema_Empresa_Upload_Dados.git


Executar com Maven Wrapper

./mvnw spring-boot:run   # Linux/Mac
.\mvnw.cmd spring-boot:run  # Windows


Acessar no navegador

Página de upload: http://localhost:8082/upload

Consulta de dados: http://localhost:8082/consultar-dados

Console do H2: http://localhost:8082/h2-console
JDBC URL: jdbc:h2:file:./data/empresa-db
Usuário: sa | Senha: (vazio)


Licença: Este projeto é de uso acadêmico e não possui licença comercial.

Desenvolvido por Jacqueline Paiva
