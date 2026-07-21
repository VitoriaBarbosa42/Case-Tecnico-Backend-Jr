Desafio de Programação – Conexão com API e CRUD Básico
Você foi designado em um desafio de criar uma aplicação web simples, que se conecte em uma API pública para buscar informações à um gerente do
Consignado, e devolvê-la à este usuário por necessidade do negócio, em um estado de sucesso de requisição da sua aplicação.
Priorize a implementação, dentre as operações CRUD (Create, Read, Update e Delete) à leitura/consulta das informações (Read), ou seja, leia as informações
de uma API pública terceira, e disponibilize a leitura destas informações ao gerente consultivo, através de uma API/BFF, removendo as informações de IBGE,
GIA,
DDD e Siafe, desnecessárias ao nosso caso de uso aqui. O gerente precisa destas informações para registrar, durante à venda do produto, se o Endereço
do cliente informado condiz com seu histórico no banco.
Requisitos:
• Conexão com a API pública dos Correios (https://viacep.com.br/
)  para buscar a informação do logradouro, utilize o endpoint viacep.com.br/ws/{cep_da_sua_residencia}/json/.
• Implemente a operação de READ para gerenciar estas informações. Isso deve incluir as seguintes funcionalidades:
o Leitura (exibição) das informações do logradouro ao usuário em uma requisição
o Na leitura, faça a remoção dos seguintes campos IBGE, GIA, DDD e
Siafe
o Crie um método para colocar o campo logradouro
em lowercase  antes de enviar à informação ao usuário
• Utilize o padrão arquitetural de sua escolha, como MVC (Model-View-Controller)
• Siga boas práticas de código, evite comentários, use-os em casos específicos. Tente organizar a leitura deste através dos nomes de classes, métodos e variáveis.
• Versione seu código em um repositório público, como GitHub.
Dicas:
• Use uma IDE e uma linguagem com qual você se sinta confortável, Java é bem-vindo, mas lembre-se, aqui gostaríamos de entender seu raciocício (IntelliJ
pode ser uma boa opção)
• Use um sistema de gerenciamento de dependências da sua escolha, se aplicável, com bibliotecas que possam facilitar o seu trabalho, caso deseje.
• Testes unitários são bem vindos, uma adição valiosa para garantir a qualidade do seu código.
• Fornecer um README com instruções sobre como configurar e executar a sua aplicação é algo bem vindo