//Hora de Codar Seis, Exercício Um!!
//Agenda
//Considerando a necessidade de desenvolver uma agenda que contenha nomes, endereços
// e telefones de 10 pessoas, defina a estrutura de registro, o diagrama de blocos
// e a transferência de um programa que por meio do uso de um menu de opções,
// execute as seguintes  etapas:
//a) Cadastrar os 10 registros.
//b) Pesquisar um dos 10 registros de cada vez pelo campo nome
//(usar o método sequencial).
//c) Classificar por ordem de nome os registros cadastrados.
//d) Apresentar todos os registros.
//e) Sair do programa de cadastro.

//Sublasse endereço
data class Endereco(
    val logradouro : String,
    val bairro : String,
    val rua : String,
    val numero : Int,
    val cep : String,
    val estado : String,
    val cidade : String
)

//Classe pessoa (contém subclasse endereço como campo
data class Pessoa(
    val nome : String,
    val telefone : String,
    val endereco : Endereco
)

fun main(){
    val agenda = mutableListOf<Pessoa>()
    do{
        println("Escolha uma opção:\n")
        println("1. Cadastrar 10 registros")
        println("2. Pesquisar registors")
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
        }
    }while (opcao != 5)

}
//Função para verificar se a pessoa digitou algo
fun verificarCampoObrigatorio(mensagem: String): String {
    while (true) {
        println(mensagem)
        val valor = readln().trim()
        if (valor.isNotEmpty()) {
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
        if (telefone.isNotEmpty() && telefone.matches(Regex("\\d{2}\\d{4}-\\d{4}"))) {
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
    val estados = listOf("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
        "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO")
    while (true) {
        println(mensagem)
        val estado = readln().trim().uppercase()
        if (estado in estados) {
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
        if (valor.isNotEmpty() && valor.all { it.isDigit() } && valor.toInt() > 0) {
            return valor.toInt()
        } else {
            println("Valor inválido. Por favor, insira um número inteiro positivo.")
        }
    }
}
//Função menu 1
fun cadastrar(agenda: MutableList<Pessoa>){

    for (i in 1..10){
        println("Dados pessoais $i º\n")
        val nome = verificarCampoObrigatorio("Digite o nome $i:")
        val telefone = verificarTelefone("Digite o $i telefone (formato: 11 1234-5678):")
        println("Dados de localização $i º\n")
        val logradouro = verificarCampoObrigatorio("Digite o $i logradouro:")
        val bairro = verificarCampoObrigatorio("Digite o $i bairro:")
        val rua = verificarCampoObrigatorio("Digite a $i rua:")
        val numero = verificarNumPositivo("Digite o $i número :")
        val cep = verificarCEP("Digite o $i CEP (formato: 12345-678):")
        val estado = verificarEstado("Digite a sigla do $i estado (ex: SP, RJ, MG):")
        val cidade = verificarCampoObrigatorio("Digite a $i cidade:")
        val endereco = Endereco(logradouro, bairro, rua, numero, cep, estado, cidade)
        val pessoa = Pessoa(nome, telefone, endereco)
        agenda.add(pessoa)
    }
    println("Cadastro concluído!\n")
}

//Função menu 2
fun pesquisarPeloNome(agenda: List<Pessoa>){
    println("Digite o do registro: \n")
    val nomePesquisado = readln()
    val retorno = agenda.find{ it.nome == nomePesquisado}
    if(retorno != null ){
        println("Registro encontrado\n")
        println("Nome: ${retorno.nome}")
        println("Telefone: ${retorno.telefone}")
        println("Endereço: ${retorno.endereco}")
    }else{
        println("Não foi encontrado nenhum registro com nome '$nomePesquisado'")
    }
}

//Função menu 3
fun classificarPorNome(agenda: MutableList<Pessoa>){
    agenda.sortBy{it.nome}
    println("Registros classificados por nome realizado com sucesso.\n")
}
//Função menu 4
fun apresentarRegistros(agenda: List<Pessoa>){
    if(agenda.isEmpty()){
        println("Não há nenhum registro até o momento.")
    }else{
        println("Regitors cadastrados")
        agenda.forEachIndexed { index, pessoa ->
            println("${index+1} - Nome: ${pessoa.nome} \n ${pessoa.telefone} \n ${pessoa.endereco}")
        }
    }
}
