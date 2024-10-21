# projetoCadastroUsuarios


Este é um projeto de aplicação web desenvolvido com Spring Boot que permite o cadastro, atualização, listagem e exclusão de usuários. A aplicação se conecta a um banco de dados PostgreSQL e fornece uma API RESTful para interagir com os dados.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para interação com o banco de dados.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados.
- **Hibernate**: Framework de mapeamento objeto-relacional.
- **Java**: Linguagem de programação utilizada.

## Funcionalidades

- **Registrar Usuário**: Permite o cadastro de novos usuários com validação de senha.
- **Atualizar Usuário**: Permite a edição das informações de um usuário existente.
- **Listar Usuários**: Lista todos os usuários cadastrados.
- **Buscar Usuário por ID**: Permite a busca de um usuário específico pelo ID.
- **Excluir Usuário**: Remove um usuário do sistema.

## Endpoints da API

| Método   | Endpoint                       | Descrição                        |
|----------|--------------------------------|----------------------------------|
| `POST`   | `/api/usuarios/registrar`     | Cadastra um novo usuário        |
| `PUT`    | `/api/usuarios/alterar/{id}`  | Atualiza as informações de um usuário |
| `GET`    | `/api/usuarios`                | Lista todos os usuários         |
| `GET`    | `/api/usuarios/{id}`           | Busca um usuário pelo ID        |
| `DELETE` | `/api/usuarios/{id}`           | Exclui um usuário pelo ID       |

## Como Configurar

### Pré-requisitos

- **Java JDK 11 ou superior**: Necessário para executar a aplicação Java.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional.
- **Maven**: Ferramenta de automação de compilação utilizada para gerenciar dependências e construir o projeto.
- **IDE**: Um Ambiente de Desenvolvimento Integrado, como IntelliJ IDEA ou Eclipse, para facilitar o desenvolvimento e execução do código.


### Passos para Configuração

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/usuario-cadastro.git
   cd usuario-cadastro
