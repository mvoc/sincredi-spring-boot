# Instruções
A porta escolhida foi a 8888. Portanto, a URL base utilizada deve ser: `http://localhost:8888`. A ferramenta utilizada para testes foi o Postman.

Foi feita uma documentação com Swagger, a url para acessá-la é `http://localhost:8888/swagger-ui.html`

O arquivo contendo a simulação de uma implementação interna do "servico" do banco central está localizada em `src/main/java/br/com/sincredi/domain/service/helper`.
O arquivo main padrão enviado foi desconsiderado.
O teste pode ser executado através de uma requisição `PUT` para o caminho `http://localhost:8888/receita` enviando um parâmetro indicando o caminho de um arquivo CSV ou enviando uma lista em JSON das receitas.

