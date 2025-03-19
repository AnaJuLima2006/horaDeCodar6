//Hora de Codar Seis, Exercício Um!!
//Agenda
//Considerando a necessidade de desenvolver uma agenda que contenha nomes, endereços
// e  telefones de 10 pessoas, defina a estrutura de registro, o diagrama de blocos
// e a  transferência de um programa que por meio do uso de um menu de opções,
// execute as seguintes  etapas:
//a) Cadastrar os 10 registros.
//b) Pesquisar um dos 10 registros de cada vez pelo campo nome
//(usar o método sequencial).
//c) Classificar por ordem de nome os registros cadastrados.
//d) Apresentar todos os registros.
//e) Sair do programa de cadastro.

//endereço é um objeto que guarda todos esses campos
data class Endereco(
    val logradouro : String,
    val bairro : String,
    val rua : String,
    val numero : Int,
    val cep : String,
    val estado : String,
    val cidade : String
){
    //Sobrescrevendo o método toString() para exibir apenas os campos
    override fun toString(): String {
        return "\nLogradouro - $logradouro \n   Bairro - $bairro \n   Rua - $rua \n   Número - $numero \n   CEP - $cep \n   Estado - $estado \n   Cidade - $cidade"
    }
}

//pessoa é um objeto que guarda esses campos, incluindo 'endereco' que tem como
// tipo o objeto Endereco
data class Pessoa(
    val nome : String,
    val telefone : String,
    val endereco : Endereco
)

//função principal
fun main(){
    //lista mutavel que usa como referencia o objeto Pessoa
    val agenda = mutableListOf<Pessoa>()

    //laço para escolher as opções
    do{
        println("Escolha uma opção:\n")
        println("1. Cadastrar 10 registros")
        println("2. Pesquisar registros")
        println("3. Classificar por nome")
        println("4.Ver todos os registros")
        println("5. Sair do programa\n")

        var opcao = readln().toInt()

        println("A opção selecionada foi: " + opcao + "\n")
        when(opcao){
            1 -> cadastrar(agenda)
            2 -> pesquisarPeloNome(agenda)
            3 -> classificarPorNome(agenda)
            4 -> apresentarRegistros(agenda)
            5 -> println("Fechando programa...")
            else -> println("Opção inválida. Tente novamente.")
        }
    }while (opcao != 5)

}
//Função para verificar se a pessoa digitou algo
fun verificarCampoObrigatorio(mensagem: String): String { //usa como parametro a
    // string menssagem
    while (true) {
        println(mensagem)
        val valor = readln().trim() //ao ler a string remove os espaços em branco
        if (valor.isNotEmpty()) { //verifica se oq foi digitado se em braco
            return valor
        } else {
            println("Este campo é obrigatório. Por favor, insira um valor.")
        }
    }
}
//Função para verificar se a pessoa digitou o telfone no formato certo
fun verificarTelefone(mensagem: String): String {
    while (true) {
        println(mensagem)
        val telefone = readln().trim()
        //verifica se foi digitado algo e se o formato é correspondente
        if (telefone.isNotEmpty() && telefone.matches(Regex("\\d{2} \\d{4}-\\d{4}"))) {
            return telefone
        } else {
            println("Telefone inválido. O formato correto é 11 1234-5678.")
        }
    }
}
// Função para verificar se o estado está no formato correto
fun verificarCEP(mensagem: String): String {
    while (true) {
        println(mensagem)
        val cep = readln().trim()
        if (cep.isNotEmpty() && cep.matches(Regex("\\d{5}-\\d{3}"))) {
            return cep
        } else {
            println("CEP inválido. O formato correto é 12345-678.")
        }
    }
}
// Função para verificar se o estado está no formato correto
fun verificarEstado(mensagem: String): String {
    //cria uma lista imutavel com os estados
    val estados = listOf("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
        "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO")
    while (true) {
        println(mensagem)
        //alem de retira os espaços em branco, transforma oq a pessoa em maiusculo
        val estado = readln().trim().uppercase()
        if (estado in estados) {//verifica se oq foi digitado está na lista
            return estado
        } else {
            println("Estado inválido. Insira a sigla do estado (ex: SP, RJ, MG).")
        }
    }
}

//Função para verificar se o número é um inteiro positivo
fun verificarNumPositivo(mensagem: String): Int {
    while (true) {
        println(mensagem)
        val valor = readln().trim()
        //verifica se a variavel não está vazia, se todos os caracteres
        // são digitos e se els são maiores que 0
        if (valor.isNotEmpty() && valor.all { it.isDigit() } && valor.toInt() > 0) {
            return valor.toInt()
        } else {
            println("Valor inválido. Por favor, insira um número inteiro positivo.")
        }
    }
}
//Função menu 1 que tem como parametro uma lista mutavel que usa como
// tipo o objeto Pessoa
fun cadastrar(agenda: MutableList<Pessoa>){
    if (agenda.size >= 10) {
        println("A agenda já está cheia (10 registros).")
        return
    }

    for (i in 1..10){
        //aqui são lidos os campos que estão nas data class
        println("Dados pessoais $i \n")
        val nome = verificarCampoObrigatorio("Digite o nome $i º:")
        val telefone = verificarTelefone("Digite o $i º telefone (formato: 11 1234-5678):")
        println("Dados de localização $i \n")
        val logradouro = verificarCampoObrigatorio("Digite o $i º logradouro:")
        val bairro = verificarCampoObrigatorio("Digite o $i º bairro:")
        val rua = verificarCampoObrigatorio("Digite a $i º rua:")
        val numero = verificarNumPositivo("Digite o $i º número :")
        val cep = verificarCEP("Digite o $i º CEP (formato: 12345-678):")
        val estado = verificarEstado("Digite a sigla do $i º estado (ex: SP, RJ, MG):")
        val cidade = verificarCampoObrigatorio("Digite a $i º cidade: 1")
        //aqui o objeto Endereco foi colocado em uma variavel chamada endereco
        val endereco = Endereco(logradouro, bairro, rua, numero, cep, estado, cidade)
        ////aqui o objeto pessoa foi colocado em uma variavel chamada pessoa, e a variavel
        // endereco é um de seus campos
        val pessoa = Pessoa(nome, telefone, endereco)
        agenda.add(pessoa) // aqui estão sendo adicionados a variavel pessoa e
        // tudo que tem nela na lita agenda
    }
    println("Cadastro concluído!\n")
}

//Função menu 2 que tem como parametro uma lista imutavel que usa como
//// tipo o objeto Pessoa
fun pesquisarPeloNome(agenda: List<Pessoa>){
    println("Digite o do registro: \n")
    val nomePesquisado = readln()
    val retorno = agenda.find{ it.nome.equals(nomePesquisado, ignoreCase = true) }
    if(retorno != null ){
        println("Registro encontrado\n")
        println("Nome: ${retorno.nome}")
        println("Telefone: ${retorno.telefone}")
        println("Endereço: ${retorno.endereco}")
    }else{
        println("Não foi encontrado nenhum registro com nome '$nomePesquisado'")
    }
}

//Função menu 3 que tem como parametro uma lista mutavel que usa como
//// tipo o objeto Pessoa
fun classificarPorNome(agenda: MutableList<Pessoa>){
    agenda.sortBy{it.nome} //ordena pelo nome
    println("Classificação dos registros por nome realizado com sucesso.\n")
}
//Função menu 4
fun apresentarRegistros(agenda: List<Pessoa>){
    if(agenda.isEmpty()){
        println("Não há nenhum registro até o momento.")
    }else{
        println("Regitors cadastrados")
        agenda.forEachIndexed { index, pessoa -> //mostra os registros pela ordem do
            // index, ele começa em 1
            println("${index + 1} - Nome: ${pessoa.nome}")
            println("   Telefone: ${pessoa.telefone}")
            println("   Endereço: ${pessoa.endereco}\n")
        }
    }
}
