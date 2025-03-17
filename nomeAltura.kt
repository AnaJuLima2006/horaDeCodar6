//Nome e Altura
//Elaborar um programa que armazene o nome e a altura de 15 pessoas, por meio do uso
// de registros. O programa deverá ser manipulado por um menu que execute as
// seguintes etapas:
//a) Cadastrar os 15 registros.
//b) Apresentar os registros (nome e altura) das pessoas menores ou iguais a 1,5m.
//c) Apresentar os registros (nome e altura) das pessoas que sejam maiores que 1,5m.
//d) Apresentar os registros (nome e altura) das pessoas que sejam maiores
// que 1,5me menores que 2,0 m.
//e) Apresentar uma mídia extraída de todas as alturas salvas.
//f) Sair do programa.

data class Pessoas(
    val nome : String,
    val altura : Double
)
fun main(){

    val dados = mutableListOf<Pessoas>()
    do{
        println("Escolha uma opção:\n")
        println("1. Cadastrar os 15 registros")
        println("2. Apresentar os registros (nome e altura) das pessoas " +
                "menores ou iguais a 1,5m.")
        println("3. Apresentar os registros (nome e altura) das pessoas" +
                " que sejam maiores que 1,5m.")
        println("4. Apresentar os registros (nome e altura) das pessoas que sejam maiores\n" +
                "que 1,5me menores que 2,0 m.")
        println("5. Apresentar uma mídia extraída de todas as alturas salvas")
        println("6. Sair do programa\n")

        var opcao = readln().toInt()

        println("A opção selecionada foi: $opcao \n")
        when(opcao){
            1 -> cadastrar(dados)
            2 -> apresentarMenorOuIgual150(dados)
            3 -> apresentarMaioresQue150(dados)
            4 -> apresentarEntre150e200(dados)
            5 -> calcularMediaDeAlturas(dados)
            6 -> println("Saindo do programa...")
            else -> println("Opção inválida. Tente novamente.")
        }
    }while (opcao != 4)

}
//Função para verificar se a pessoa digitou algo
fun verificarSeDigitouAlgo(mensagem: String): String {
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
//Função para verificar se a pessoa digitou um número
fun lerAltura(mensagem: String): Double {
    while (true) {
        println(mensagem)
        try {
            return readln().trim().toDouble()
        } catch (e: NumberFormatException) {
            println("Valor inválido. Por favor, insira um número.")
        }
    }
}
//Função menu 1
fun cadastrar(dados: MutableList<Pessoas>){

    for (i in 1..15){
        val nome = verificarSeDigitouAlgo("Digite o nome $i º:")
        val altura = lerAltura("Digite a $i º altura:")

        val pessoa = Pessoas(nome, altura)
        dados.add(pessoa)
    }
    println("Cadastro concluído!\n")
}
//Função Menu 2
fun apresentarMenorOuIgual150(dados: List<Pessoas>){
    val filtrados = dados.filter { it.altura <= 1.5 }
    if (filtrados.isEmpty()) {
        println("Nenhuma pessoa com altura menor ou igual a 1,5m encontrada.\n")
    } else {
        println("Pessoas com altura menor ou igual a 1,5m:\n")
        filtrados.forEach { println("Nome: ${it.nome}, Altura: ${it.altura}m") }
    }
}
//Função Menu 3
fun apresentarMaioresQue150(dados: List<Pessoas>) {
    val filtrados = dados.filter { it.altura > 1.5 }
    if (filtrados.isEmpty()) {
        println("Nenhuma pessoa com altura maior que 1,5m encontrada.\n")
    } else {
        println("Pessoas com altura maior que 1,5m:\n")
        filtrados.forEach { println("Nome: ${it.nome}, Altura: ${it.altura}m") }
    }
}
//Função Menu 4
fun apresentarEntre150e200(dados: List<Pessoas>) {
    val filtrados = dados.filter { it.altura > 1.5 && it.altura <2.0}
    if (filtrados.isEmpty()) {
        println("Nenhuma pessoa com altura maior que 1,5 m  e menor que 2,0 m encontrada.\n")
    } else {
        println("Pessoas com altura entre 1,5 m e 2,0 m:\n")
        filtrados.forEach { println("Nome: ${it.nome}, Altura: ${it.altura}m") }
    }
}
//Função Menu 5
fun calcularMediaDeAlturas(dados: List<Pessoas>){
    if (dados.isEmpty()){
        println("Não há nenhum dado cadastrado para calcular média.")
    }else{
        val media = dados.map { it.altura }.average()
        println("A média das alturas é: ${"%.2f".format(media)}m\n")
    }
}