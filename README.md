# ShopSystemNG
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

### Descrição:

Desenvolver um sistema utilizando Spring framework contendo as funcionalidades impostas pelo stakeholder.

### Requisitos funcionais:

- [ ] RF01 - O sistema deve permitir o cadastro de produtos de supermercado,
com foto, nome e descrição.
- [ ] RF02 - O sistema deve permitir a desativação de produtos de
supermercado - não é a exclusão, apenas desativação.
- [ ] RF03 - O sistema deve permitir o alteração de produtos de
supermercado, com foto, nome e descrição.
- [ ] RF04 - O sistema deve permitir o consulta de produtos de supermercado
por nome e descrição.
- [ ] RF05 - O sistema deve permitir o cadastro de listas de compra de
mercado, adicionando produtos previamente cadastrados e suas
quantidades.
- [ ] RF06 - O sistema deve permitir a atualização de listas de compra de
mercado, removendo produtos previamente adicionados ou alterando
suas quantidades.
- [ ] RF07 - O sistema deve permitir excluir listas de mercado já cadastradas.

### Requisitos não funcionais:

- [ ] RNF01 - O sistema deve ter dois perfis: Administrator e Usuário. Os
administradores podem gerir produtos (RF01 ... 04). Os usuários podem
gerir listas (RF05 ... 07).
- [ ] RNF02 - Deve ser possível acessar estas funções por meio de interface ou
API. No caso de API, deve ser utilizado um JSON web token. No caso de
interface, deve ser feita autenticação por usuário e senha.
