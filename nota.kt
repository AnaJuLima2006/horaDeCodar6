//Qual a nota?
//Considerando a necessidade de um programa que armazene o nome e as notas
// bimestrais de 20  aulas do curso de Técnicas de Programação, defina a
// estrutura de registro completa, o diagrama  de blocos e a composição
// de um programa que, por meio do uso de um menu de programação, execute
// as  seguintes etapas:
//
//a) Cadastrar os 20 registros (após o cadastro efetuar a classificação por nome).
//b) Pesquisar os 20 registros, de cada vez, por campo nome. Nesta pesquisa o
// programa deverá também apresente a média do aluno e as mensagens: “Aprovado”
// caso sua média seja maior ou igual a 5, ou “Reprovado” para média abaixo de 5.
//c) Apresentar todos os registros, médias e mensagens de aprovação ou reprovação.
//d) Sair do programa de cadastro.

data class Bimestre(
    val notaUm : Double,
    val notaDois : Double,
    val notaTres : Double,
    val notaQuatro: Double,
)
data class Estudante(
    val nome : String,
    val bimestre : Bimestre
)
fun main(){

    val notas = mutableListOf<Estudante>()
    do{
        println("Escolha uma opção:\n")
        println("1. Cadastrar 20 registros e classificar por nome")
        println("2. Pesquisar registros por nome e mostrar média")
        println("3. Ver todos os registros: médias e mensagens de " +
                "aprovação/reprovação")
        println("4. Sair do programa\n")

        var opcao = readln().toInt()

        println("A opção selecionada foi: " + opcao + "\n")
        when(opcao){
            1 -> cadastrar(notas)
            2 -> pesquisarPorNome(notas)
            3 -> exibirRegistros(notas)
            4 -> println("Saindo do programa...")
            else -> println("Opção inválida. Tente novamente.")
        }
    }while (opcao != 4)

}
//Função para verificar se o número é um inteiro positivo
fun verificarNumeroPositivo(mensagem: String): Int {
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
//Função para verificar se a pessoa digitou algo
fun verificarSeDigitou(mensagem: String): String {
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
//Função apoio para menu 1
fun classificarPeloNome(notas: MutableList<Estudante>){
    notas.sortBy{it.nome}
    println("Registros classificados por nome realizado com sucesso.\n")
}
//Função menu 1
fun cadastrar(notas: MutableList<Estudante>){

    for (i in 1..20){
        println("Dados pessoais $i º\n")
        val nome = verificarSeDigitou("Digite o nome $i:")
        println("Notas do Bimestre $i º\n")
        val notaUm = verificarNumeroPositivo("Digite a nota um do $i º estudante:").toDouble()
        val notaDois = verificarNumeroPositivo("Digite a nota dois do $i º estudante:").toDouble()
        val notaTres = verificarNumeroPositivo("Digite a nota três do $i º estudante:").toDouble()
        val notaQuatro = verificarNumeroPositivo("Digite a nota quatro do $i º estudante :").toDouble()

        val bimestre = Bimestre(notaUm, notaDois, notaTres, notaQuatro)
        val estudante = Estudante(nome, bimestre)
        notas.add(estudante)
    }
    classificarPeloNome(notas)
    println("Cadastro concluído!\n")
}
fun calcularMedia(bimestre: Bimestre) : Double{
    return(bimestre.notaUm + bimestre.notaDois +
            bimestre.notaTres + bimestre.notaQuatro) / 4
}
//Função menu 2
fun pesquisarPorNome(notas: List<Estudante>){
    println("Digite o do registro: \n")
    val nomePesquisado = readln()
    val retorno = notas.find{ it.nome == nomePesquisado}
    if(retorno != null ){
        println("Registro encontrado\n")
        println("Nome: ${retorno.nome}")
        println("Notas bimestrais: ${retorno.bimestre}")

        val media = calcularMedia(retorno.bimestre)
        println("Média: $media")

        if (media >= 5){
            println("Situação: Aprovado.")
        }else{
            println("Situação: Reprovado.")
        }
    }else{
        println("Não foi encontrado nenhum registro com nome '$nomePesquisado'")
    }
}
//Função menu 3
fun exibirRegistros(notas: List<Estudante>){
    notas.forEach { estudante ->
        val media = calcularMedia(estudante.bimestre)
        println("Nome: ${estudante.nome}")
        println("Notas bimestrais: ${estudante.bimestre}")
        println("Média: $media")
        if (media >= 5) {
            println("Situação: Aprovado")
        } else {
            println("Situação: Reprovado")
        }
        println("_______________________________\n")
    }
}
